package com.lulu.tank.cor;

import com.lulu.tank.GameObject;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-07 12:53
 */
public interface Collider extends Serializable {

    boolean collide(GameObject o1, GameObject o2);
}
