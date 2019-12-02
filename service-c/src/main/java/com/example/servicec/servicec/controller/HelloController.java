package com.example.servicec.servicec.controller;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;


/**
 * @description:
 * @author: 肖奈（贺金龙）
 * @create: 2019-11-18 14:45
 */

@RestController
@RequestMapping("/c")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        Transaction t = Cat.newTransaction("URL", "hello");
        String msg = "";
        try {
            Cat.logEvent("URL.Server", InetAddress.getLocalHost().getHostAddress());
            Cat.logEvent("URL.Method", "HelloController.hello()");
            Cat.logMetricForCount("HELLO_C");
            Cat.logMetricForDuration("HELLO_C", 100);

            msg = "Hello You! This is from msg from C.";

            t.setStatus(Transaction.SUCCESS);


        } catch (Exception e) {
            t.setStatus(e);
            Cat.logError(e);
        } finally {
            t.complete();
        }

        return msg;
    }
}
