package com.csd.wrongCode.controller.scopeTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author csd
 */
@RestController
@Slf4j
public class SayController {


    @Autowired
    List<SayService> sayServiceList;

    @GetMapping("/sayController/test")
    public void test() {
        log.info("====================");
        sayServiceList.forEach(SayService::say);
    }
}
