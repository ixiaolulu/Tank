package com.lulu.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-04 21:45
 */
public class PropertyMgr {


    public static class ProperyMgrHolder {

        public static final Properties props = new Properties();

        static {
            try {
                props.load(PropertyMgr.class.getResourceAsStream("/config"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String getString(String key) {
        if (ProperyMgrHolder.props == null) return null;
        return (String) ProperyMgrHolder.props.get(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt((String) ProperyMgrHolder.props.get(key));
    }


    private PropertyMgr() {
    }

}
