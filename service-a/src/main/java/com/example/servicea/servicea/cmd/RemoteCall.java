package com.example.servicea.servicea.cmd;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: 肖奈（贺金龙）
 * @create: 2020-01-02 15:42
 */
public class RemoteCall extends HystrixCommand<String> {

    public RemoteCall() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    @Override
    protected String run() throws Exception {
        System.out.println("In Hystrix command running.");

        sleep(Integer.MAX_VALUE);

        System.out.println("Finished Hystrix command running.");

        return null;
    }

    @Override
    protected String getFallback() {
        return "Failed to get response";
    }

    private void sleep(long sleepMinutes) {
        try {
            TimeUnit.MINUTES.sleep(sleepMinutes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
