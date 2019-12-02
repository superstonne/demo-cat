package com.example.servicea.servicea.controller;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.example.servicea.servicea.context.CatContextImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/a")
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    public static final String URL_SERVICE_B = "http://localhost:9999";

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String helloB() {
        Transaction t = Cat.newTransaction("URL", "hello");
        String msg = "";
        try {
            Cat.logEvent("URL.Server", InetAddress.getLocalHost().getHostAddress());
            Cat.logEvent("URL.Method", "HelloController.helloB");
            Cat.logMetricForCount("HELLO_A");
            Cat.logMetricForDuration("HELLO_A", 5);

            msg = restTemplate.getForObject(URL_SERVICE_B + "/b/hello", String.class);

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
