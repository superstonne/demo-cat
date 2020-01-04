package com.example.servicea.servicea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: 肖奈（贺金龙）
 * @create: 2019-12-16 19:09
 */
public class Solution {
    public static int sqrt(int x) {
        // write your code here

        long end = Long.valueOf(x);
        long start = 1;

        do {
            long tmpStart = start * 2l;
            long tmp = tmpStart * tmpStart;
            if (tmp < x) {
                start = tmpStart;
            }
            if (tmp > x) {
                end = tmpStart;
                break;
            }
            if (tmp == x) {
                return (int) tmpStart;
            }
        } while (true);

        long result = 0;

        for (long i =  start; i <= end; i++) {
            if (i * i > x) {
                return (int) result;
            }

            result = i;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(2147483647));
    }
}
