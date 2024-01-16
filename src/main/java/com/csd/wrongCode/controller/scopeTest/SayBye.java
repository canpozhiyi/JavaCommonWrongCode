package com.csd.wrongCode.controller.scopeTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 * @author csd
 */
@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class SayBye extends SayService {

    @Override
    public void say() {
        super.say();
        log.info("bye");
    }
}
