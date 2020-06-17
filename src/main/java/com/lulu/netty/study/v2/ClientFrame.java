package com.lulu.netty.study.v2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-17 22:05
 */
public class ClientFrame extends Frame {
    TextArea ta = new TextArea();
    TextField tf = new TextField();

    public static void main(String[] args) {
        new ClientFrame();
    }

    public ClientFrame() {
        this.setSize(600, 400);
        this.setLocation(100, 20);
        this.add(ta, BorderLayout.CENTER);
        this.add(tf, BorderLayout.SOUTH);
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //把字符串发送到服务器
                ta.setText(ta.getText() + tf.getText());
                tf.setText("");
            }
        });
        this.setVisible(true);

    }
}
