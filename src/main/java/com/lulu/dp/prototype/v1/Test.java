package com.lulu.dp.prototype.v1;

/**
 * @Description: 浅克隆
 * @Author: Milo
 * @Date: 2020-06-14 10:41
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person();
        Person p2 = (Person) p1.clone();
        System.out.println(p2.name + "" + p2.age + "" + p2.score);
        System.out.println(p2.location);

        System.out.println(p1.location == p2.location);
        p1.location.street = "西湖";
        System.out.println(p1.location);
        System.out.println(p2.location);
    }

}

class Person implements Cloneable {
    String name = "小王";
    int score = 90;
    int age = 19;

    Location location = new Location("天安门", "1234");

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", age=" + age +
                ", location=" + location +
                '}';
    }
}

class Location {
    String street;
    String roomNo;

    public Location(String street, String roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", roomNo='" + roomNo + '\'' +
                '}';
    }
}