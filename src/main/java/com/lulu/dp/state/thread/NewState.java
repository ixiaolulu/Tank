package com.lulu.dp.state.thread;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-14 13:02
 */
public class NewState extends ThreadState_ {

    private Thread_ t;

    public NewState(Thread_ t) {
        this.t = t;
    }

    @Override
    void move(Action action) {
        if (action.msg == "start")
            t.state = new RunningState(t);
    }

    @Override
    void run() {

    }
}
