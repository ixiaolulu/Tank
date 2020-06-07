package com.lulu.tank.cor;

import com.lulu.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-07 13:26
 */
public class ColliderChain implements Collider {

    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
        add(new BulletWallCollider());
        add(new TankWallCollider());
    }

    public void add(Collider collider) {
        colliders.add(collider);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for (Collider collider : colliders) {
            if (collider.collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
