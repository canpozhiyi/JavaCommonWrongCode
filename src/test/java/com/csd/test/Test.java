package com.csd.test;

import com.csd.wrongCode.Application;
import com.csd.wrongCode.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

/**
 * @author csd
 */
@Slf4j
@SpringBootTest(
        classes = Application.class)
@RunWith(SpringRunner.class)
public class Test {


    @org.junit.Test
    public void test1() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);


    }
}
