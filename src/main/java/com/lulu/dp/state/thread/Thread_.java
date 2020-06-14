package com.lulu.dp.state.thread;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-14 13:03
 */
public class Thread_ {
    ThreadState_ state;

    void move(Action action) {
        state.move(action);
    }

    void run() {
        state.run();
    }

}
