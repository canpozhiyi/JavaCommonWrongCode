package com.csd.wrongCode.controller;

import lombok.extern.slf4j.Slf4j;

/**
 * @author csd
 */
@Slf4j
public class TryCatchThrowException {


    public static void main(String[] args) {
        try {
            throw new RuntimeException("try");
        } finally {
            try {
                throw new RuntimeException("finally");
            } catch (Exception e) {
                log.error("finally", e);
            }
        }
    }
}
