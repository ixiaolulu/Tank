package com.lulu.dp.builder;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-13 16:31
 */
public class Main {
    public static void main(String[] args) {
        Terrain terrain = new TerrainBuilder().buildFort().buildMine().buildWall().build();
        System.out.println(terrain.fort.x);

        Person person = new Person.PersonBuilder()
                .buildBaseInfo(1, 20, "xiaoming")
                .builderScore(90)
                .buildWeight(130.23)
                .builderLocation("外滩18号", "123")
                .build();
        System.out.println(person.toString());
    }
}
