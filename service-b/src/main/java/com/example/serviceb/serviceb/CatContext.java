package com.example.serviceb.serviceb;

import com.dianping.cat.Cat;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: 肖奈（贺金龙）
 * @create: 2019-11-21 14:08
 */
public class CatContext implements Cat.Context {


    private Map<String,String> properties = new HashMap<String, String>();

    @Override
    public void addProperty(String key, String value) {
        properties.put(key,value);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }
}
