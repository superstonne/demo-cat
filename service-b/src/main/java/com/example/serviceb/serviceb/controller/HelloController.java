package com.example.serviceb.serviceb.controller;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;


/**
 * @description:
 * @author: 肖奈（贺金龙）
 * @create: 2019-11-18 14:45
 */

@RestController
@RequestMapping("/b")
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    public static final String URL_SERVICE_C = "http://localhost:7777";

    @GetMapping("/hello")
    public String hello() {
        Transaction t = Cat.newTransaction("URL", "hello");
        String msg = "";
        try {
            Cat.logEvent("URL.Server", InetAddress.getLocalHost().getHostAddress());
            Cat.logEvent("URL.Method", "HelloController.hello()");
            Cat.logMetricForCount("HELLO_B");
            Cat.logMetricForDuration("HELLO_B", 5);

            msg = msg = restTemplate.getForObject(URL_SERVICE_C + "/c/hello", String.class);

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
