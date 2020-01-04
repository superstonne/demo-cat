package com.example.servicea.servicea.controller;

import com.example.servicea.servicea.cmd.RemoteCall;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.Remote;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: 肖奈（贺金龙）
 * @create: 2019-12-14 14:29
 */
@RestController
@RequestMapping("/long")
public class LongTimeController {

    @GetMapping("/none")
    public void sleep() {
        sleep(Integer.MAX_VALUE);
    }

    @GetMapping("/hystrix")
    public void breaker() {
        String result = new RemoteCall().execute();

        System.out.println(result);
    }



    private void sleep(long sleepMinutes) {
        try {
            TimeUnit.MINUTES.sleep(sleepMinutes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
