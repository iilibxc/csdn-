package com.alliance.controller;

import com.alliance.common.ServerResponse;
import com.alliance.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:    TokenController
 * Package:    com.alliance.controller
 * Description:
 * Datetime:    2020/1/14   11:33
 * Author:   XXXXX@XX.com
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/token")
    public ServerResponse token() {
        return tokenService.createToken();
    }

}