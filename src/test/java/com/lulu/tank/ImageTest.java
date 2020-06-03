package com.lulu.tank;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-02 21:53
 */
public class ImageTest {

    @Test
    public void test() throws IOException {
        BufferedImage bi = ImageIO.read(new File("E:/pic/1.gif"));
        Assert.assertNotNull(bi);
        InputStream inputStream = ImageTest.class.getClassLoader().getResourceAsStream("images/0.gif");
        Assert.assertNotNull(inputStream);
    }
}
