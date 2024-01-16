package com.csd.wrongCode.controller.scopeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author csd
 */

public interface SayServiceInterface {
    List<String> data = new ArrayList<>();

    default void say() {
        data.add(IntStream.rangeClosed(1, 1000000)
                .mapToObj(__ -> "a")
                .collect(Collectors.joining("")) + UUID.randomUUID().toString());

    }

}
