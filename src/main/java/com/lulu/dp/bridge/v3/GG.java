package com.lulu.dp.bridge.v3;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-13 16:49
 */
public class GG {
    public void chase(MM mm) {
        Gift gift = new WarmGift(new Flower("玫瑰"));
        give(mm, gift);
    }

    public void give(MM mm, Gift gift) {
        System.out.println(gift.impl.name + "is gived to " + mm.name);
    }


    public static void main(String[] args) {
        MM mm = new MM("翠花");
        GG gg = new GG();
        gg.chase(mm);
    }
}
