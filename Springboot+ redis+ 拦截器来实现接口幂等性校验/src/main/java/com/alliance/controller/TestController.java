package com.alliance.controller;

import com.alliance.annotation.ApiIdempotent;
import com.alliance.common.ServerResponse;
import com.alliance.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:    TestController
 * Package:    com.alliance.controller
 * Description:
 * Datetime:    2020/1/14   11:34
 * Author:   XXXXX@XX.com
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @ApiIdempotent
    @PostMapping("testIdempotence")
    public ServerResponse testIdempotence() {
        return testService.testIdempotence();
    }

}