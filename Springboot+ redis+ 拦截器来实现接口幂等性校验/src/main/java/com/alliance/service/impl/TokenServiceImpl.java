package com.alliance.service.impl;

import com.alliance.JedisUtil;
import com.alliance.common.Constant;
import com.alliance.common.ResponseCode;
import com.alliance.common.ServerResponse;
import com.alliance.exception.ServiceException;
import com.alliance.service.TokenService;
import com.alliance.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:    TokenServiceImpl
 * Package:    com.alliance.redistest.idempotent
 * Description:
 * Datetime:    2020/1/14   11:03
 * Author:   XXXXX@XX.com
 */
@Service
public class TokenServiceImpl implements TokenService {
    private Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    private static final String TOKEN_NAME = "token";

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public ServerResponse createToken() {
        String str = RandomUtil.UUID32();
        StrBuilder token = new StrBuilder();
        StrBuilder append = token.append(Constant.Redis.TOKEN_PREFIX);//生成token
        token=append.append(str);

        jedisUtil.set(token.toString(), token.toString(), Constant.Redis.EXPIRE_TIME_HOUR);//把token存入redis

        return ServerResponse.success(token.toString());
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)) {// header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {// parameter中也不存在token
                logger.info("token验证失败");
                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }

        if (!jedisUtil.exists(token)) {
            logger.info("token验证失败");
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }

        Long del = jedisUtil.del(token);
        logger.info("token验证成功");

        if (del <= 0) {
            logger.info("token验证失败");
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }

}