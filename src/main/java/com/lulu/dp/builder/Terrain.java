package com.lulu.dp.builder;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-13 16:23
 */
//地形、地势
public class Terrain {
    Wall wall;
    Fort fort;
    Mine mine;


}

//城墙
class Wall {
    int x, y, w, h;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}

//营地、城堡
class Fort {
    int x, y, w, h;

    public Fort(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}

//矿井、地雷
class Mine {
    int x, y, w, h;

    public Mine(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}