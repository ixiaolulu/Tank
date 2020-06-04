package com.lulu.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-04 21:45
 */
public class PropertyMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getResourceAsStream("/config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key) {
        if (props == null) return null;
        return (String) props.get(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt((String) props.get(key));
    }


    public static void main(String[] args) {
        System.out.println(getString("initTankCount"));
    }
}
