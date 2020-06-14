package com.lulu.dp.state.thread;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-14 13:05
 */
public class RunningState extends ThreadState_ {
    private Thread_ t;

    public RunningState(Thread_ t) {
        this.t = t;
    }

    @Override
    void move(Action action) {
        if (action.msg == "running")
            t.state = new RunningState(t);
    }

    @Override
    void run() {

    }
}
