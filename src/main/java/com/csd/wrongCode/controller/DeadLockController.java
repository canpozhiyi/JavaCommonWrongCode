package com.csd.wrongCode.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author csd
 */
@RestController
@Slf4j
public class DeadLockController {

    private static Map<String, Item> items;

    static {
        items = new HashMap<>();
        items.put("item1", new Item("item1"));
        items.put("item2", new Item("item2"));
        items.put("item3", new Item("item3"));
        items.put("item4", new Item("item4"));
        items.put("item5", new Item("item5"));
        items.put("item6", new Item("item6"));
        items.put("item7", new Item("item7"));
        items.put("item8", new Item("item8"));
        items.put("item9", new Item("item9"));
        items.put("item10", new Item("item10"));
    }

    @GetMapping("/deadLock/right")
    public long right() {

        long begin = System.currentTimeMillis();
        //并发进行100次下单操作，统计成功次数
        long success = IntStream.rangeClosed(1, 100).parallel()
                .mapToObj(i -> {
                    List<Item> cart = createCart().stream().sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());
                    return createOrder(cart);
                })
                .filter(result -> result)
                .count();
        log.info("success:{} totalRemaining:{} took:{}ms items:{}",
                success,
                items.entrySet().stream().map(item -> item.getValue().remaining).reduce(0, Integer::sum),
                System.currentTimeMillis() - begin, items);

        return success;
    }



    @GetMapping("/deadLock/wrong")
    public long wrong() {

        long begin = System.currentTimeMillis();
        //并发进行100次下单操作，统计成功次数
        long success = IntStream.rangeClosed(1, 100).parallel()
                .mapToObj(i -> {
                    List<Item> cart = createCart();
                    return createOrder(cart);
                })
                .filter(result -> result)
                .count();
        log.info("success:{} totalRemaining:{} took:{}ms items:{}",
                success,
                items.entrySet().stream().map(item -> item.getValue().remaining).reduce(0, Integer::sum),
                System.currentTimeMillis() - begin, items);

        return success;
    }


    private List<Item> createCart() {

        List<Item> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            String name = "item" + ThreadLocalRandom.current().nextInt(1, items.size());
            Item item = items.get(name);
            list.add(item);
        }
        return list;

//        return IntStream.rangeClosed(1, 3)
//                .mapToObj(i -> "item" + ThreadLocalRandom.current().nextInt(items.size()))
//                .map(name -> items.get(name)).collect(Collectors.toList());
    }

    private boolean createOrder(List<Item> order) {
        //存放所有获得的锁
        List<ReentrantLock> locks = new ArrayList<>();

        for (Item item : order) {
            try {
                //获得锁10秒超时
                if (item.lock.tryLock(10, TimeUnit.SECONDS)) {
                    locks.add(item.lock);
                } else {
                    locks.forEach(ReentrantLock::unlock);
                    return false;
                }
            } catch (InterruptedException e) {
            }
        }
        //锁全部拿到之后执行扣减库存业务逻辑
        try {
            order.forEach(item -> item.remaining--);
        } finally {
            locks.forEach(ReentrantLock::unlock);
        }
        return true;
    }

    @Data
    @RequiredArgsConstructor
    static class Item {
        final String name; //商品名
        int remaining = 1000; //库存剩余
        @ToString.Exclude //ToString不包含这个字段
        ReentrantLock lock = new ReentrantLock();
    }
}
