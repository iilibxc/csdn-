package com.alliance.service;

import com.alliance.common.ServerResponse;

/**
 * ClassName:    TestService
 * Package:    com.alliance.service
 * Description:
 * Datetime:    2020/1/14   11:36
 * Author:   XXXXX@XX.com
 */
public interface TestService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

   /* ServerResponse send(Mail mail);*/
}
