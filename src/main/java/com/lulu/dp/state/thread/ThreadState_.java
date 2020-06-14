package com.lulu.dp.state.thread;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-14 13:02
 */
public abstract class ThreadState_ {
    abstract void move(Action action);

    abstract void run();
}
