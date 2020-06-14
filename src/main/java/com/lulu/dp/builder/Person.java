package com.lulu.dp.builder;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-13 16:32
 */
public class Person {
    int id;
    int age;
    String name;
    double weight;
    int score;
    Location location;


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", score=" + score +
                ", location=" + location +
                '}';
    }

    private Person() {
    }

    public static class PersonBuilder {
        Person p = new Person();

        public PersonBuilder buildBaseInfo(int id, int age, String name) {
            p.id = id;
            p.age = age;
            p.name = name;
            return this;
        }

        public PersonBuilder buildWeight(double weight) {
            p.weight = weight;
            return this;
        }

        public PersonBuilder builderScore(int score) {
            p.score = score;
            return this;
        }

        public PersonBuilder builderLocation(String street, String roomNo) {
            p.location = new Location(street, roomNo);
            return this;
        }

        public Person build() {
            return p;
        }


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