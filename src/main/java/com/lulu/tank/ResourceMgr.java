package com.lulu.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-03 20:43
 */
public class ResourceMgr {
    public static BufferedImage tankL, tankU, tankR, tankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getResourceAsStream("/images/tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getResourceAsStream("/images/tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getResourceAsStream("/images/tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getResourceAsStream("/images/tankD.gif"));

            bulletL = ImageIO.read(ResourceMgr.class.getResourceAsStream("/images/bulletL.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getResourceAsStream("/images/bulletU.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getResourceAsStream("/images/bulletR.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getResourceAsStream("/images/bulletD.gif"));

            for(int i=0; i<16; i++)
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
