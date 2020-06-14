package com.lulu.dp.state.thread;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-14 13:06
 */
public class TerminatedState extends ThreadState_ {
    private Thread_ t;

    public TerminatedState(Thread_ t) {
        this.t = t;
    }

    @Override
    void move(Action action) {

    }

    @Override
    void run() {

    }
}
