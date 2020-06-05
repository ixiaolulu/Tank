package com.lulu.dp.strategy;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-05 21:34
 */
public class Dog implements Comparable<Dog> {

    private int food;

    public Dog(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }

    @Override
    public int compareTo(Dog dog) {
        if (this.food < dog.food) return -1;
        else if (this.food > dog.food) return 1;
        else return 0;
    }
}
