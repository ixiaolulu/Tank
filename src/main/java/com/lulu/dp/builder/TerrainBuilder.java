package com.lulu.dp.builder;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-13 16:28
 */
public class TerrainBuilder {

    Terrain t = new Terrain();

    public TerrainBuilder buildWall() {
        t.wall = new Wall(100, 200, 100, 200);
        return this;
    }

    public TerrainBuilder buildFort() {
        t.fort = new Fort(50, 50, 50, 50);
        return this;
    }

    public TerrainBuilder buildMine() {
        t.mine = new Mine(10, 10, 10, 10);
        return this;
    }

    public Terrain build() {
        return t;
    }
}
