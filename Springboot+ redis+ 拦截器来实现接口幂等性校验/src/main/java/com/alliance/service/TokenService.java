package com.alliance.service;


import com.alliance.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:    TokenService
 * Package:    com.alliance.redistest.idempotent
 * Description:
 * Datetime:    2020/1/14   11:01
 * Author:   XXXXX@XX.com
 */

public interface TokenService {

    ServerResponse createToken();

    void checkToken(HttpServletRequest request);

}
