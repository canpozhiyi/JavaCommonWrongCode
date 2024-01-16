package com.csd.wrongCode.controller.scopeTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author csd
 */
@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
@Slf4j
public class SayByeInterfaceInterface implements com.csd.wrongCode.controller.scopeTest.SayServiceInterface {

    List<String> data = new ArrayList<>();

    @Override
    public void say() {
        data.add(IntStream.rangeClosed(1, 1000000)
                .mapToObj(__ -> "a")
                .collect(Collectors.joining("")) + UUID.randomUUID().toString());
        log.info("I'm {} size:{}", this, data.size());
        log.info("bye");
    }
}
