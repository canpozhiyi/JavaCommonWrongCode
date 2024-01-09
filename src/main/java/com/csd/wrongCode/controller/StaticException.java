package com.csd.wrongCode.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author csd
 */
@Slf4j
public class StaticException {

    public static void main(String[] args) {
        try {
            createOrderWrong();
        } catch (Exception ex) {
            log.error("createOrder got error", ex);
        }
        try {
            cancelOrderWrong();
        } catch (Exception ex) {
            log.error("cancelOrder got error", ex);
        }
    }

    private static void createOrderWrong() {
        //这里有问题
//        throw Exceptions.ORDEREXISTS;
        throw Exceptions.orderExists();
    }

    private static void cancelOrderWrong() {
        //这里有问题
//        throw Exceptions.ORDEREXISTS;

        throw Exceptions.orderExists();
    }

    @Data
    public static class Exceptions {
        public static RuntimeException ORDEREXISTS = new RuntimeException("订单已经存在");

        public static RuntimeException orderExists(){ return new RuntimeException("订单已经存在"); }
    }
}
