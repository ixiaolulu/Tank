package com.lulu.dp.strategy;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-05 22:08
 */
public class DogComparator implements Comparator<Dog> {
    @Override
    public int compare(Dog o1, Dog o2) {
        if (o1.getFood() < o2.getFood()) return -1;
        if (o1.getFood() > o2.getFood()) return 1;
        return 0;
    }
}
