package com.lulu.dp.strategy;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-05 22:13
 */
public class CatComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        if (o1.getHeight() < o2.getHeight()) return 1;
        if (o1.getHeight() > o2.getHeight()) return -1;
        return 0;
    }
}
