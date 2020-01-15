package com.alliance.service.impl;

import com.alliance.common.ServerResponse;
import com.alliance.service.TestService;
import org.springframework.stereotype.Service;

/**
 * ClassName:    TestServiceImpl
 * Package:    com.alliance.service.impl
 * Description:
 * Datetime:    2020/1/14   11:39
 * Author:   XXXXX@XX.com
 */
@Service
public class TestServiceImpl implements TestService {

   /* @Autowired
    private MsgLogMapper msgLogMapper;*/

   /* @Autowired
    private RabbitTemplate rabbitTemplate;*/

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

    @Override
    public ServerResponse accessLimit() {
        return ServerResponse.success("accessLimit: success");
    }

   /* @Override
    public ServerResponse send(Mail mail) {
        String msgId = RandomUtil.UUID32();
        mail.setMsgId(msgId);

        MsgLog msgLog = new MsgLog(msgId, mail, RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME);
        msgLogMapper.insert(msgLog);// 消息入库

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);// 发送消息

        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }*/

}