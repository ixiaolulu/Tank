package com.lulu.dp.strategy;

import java.util.Arrays;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-05 21:35
 */
public class Main {

    public static void main(String[] args) {
//        int[] a = new int[]{3, 6, 2, 4, 7, 89, 23, 1};
        Cat[] a = new Cat[]{new Cat(3, 4), new Cat(5, 3), new Cat(2, 2)};
//        Dog[] a = new Dog[]{new Dog(4), new Dog(5), new Dog(1)};

        Sorter<Cat> sorter = new Sorter<Cat>();
//        sorter.sort(a, new CatComparator());
        sorter.sort(a, (o1, o2) -> {
            if (o1.getHeight() < o2.getHeight()) return 1;
            if (o1.getHeight() > o2.getHeight()) return -1;
            return 0;
        });
        System.out.println(Arrays.toString(a));
    }

}
