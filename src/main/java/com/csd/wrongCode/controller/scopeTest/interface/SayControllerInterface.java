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
public class SayControllerInterface {


    @Autowired
    List<com.csd.wrongCode.controller.scopeTest.SayServiceInterface> sayServiceInterfaceList;

    @GetMapping("/sayController/interface/test")
    public void test() {
        log.info("====================");
        sayServiceInterfaceList.forEach(com.csd.wrongCode.controller.scopeTest.SayServiceInterface::say);
    }
}
