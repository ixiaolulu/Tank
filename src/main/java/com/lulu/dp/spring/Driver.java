package com.lulu.dp.spring;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-07 18:14
 */
public class Driver {
    private String name;
    private String msg;

    public Driver() {
        System.out.println("Driver");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
