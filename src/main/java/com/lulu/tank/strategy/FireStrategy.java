package com.lulu.tank.strategy;

import com.lulu.tank.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Tank t);
}
