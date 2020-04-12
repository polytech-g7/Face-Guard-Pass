package org.g7.robbo.opd.fgs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * @author Orlov Diga
 */
public class Application {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:config/ioadapter.xml");
    }
}
