package com.lulu.dp.prototype.v4;

/**
 * @Description: String 是否需要深克隆？
 * @Author: Milo
 * @Date: 2020-06-14 10:41
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person();
        Person p2 = (Person) p1.clone();
        System.out.println("p1.location == p2.location? " + (p1.location == p2.location));

        p1.location.street.reverse();
        System.out.println(p2.location.street);
    }

}

class Person implements Cloneable {
    String name = "小王";
    int score = 90;
    int age = 19;

    Location location = new Location(new StringBuilder("天安门"), "1234");

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone();
        person.location = (Location) location.clone();
        return person;
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

class Location implements Cloneable {
    StringBuilder street;
    String roomNo;

    public Location(StringBuilder street, String roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", roomNo='" + roomNo + '\'' +
                '}';
    }
}