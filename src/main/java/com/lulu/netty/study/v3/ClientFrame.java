package com.lulu.netty.study.v3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-17 22:05
 */
public class ClientFrame extends Frame {

    public static final ClientFrame INSTANCE = new ClientFrame();

    TextArea ta = new TextArea();
    TextField tf = new TextField();

    Client client = null;

    public static void main(String[] args) {
        ClientFrame frame = INSTANCE;
        frame.setVisible(true);
        frame.connectToServer();
    }

    public ClientFrame() {
        this.setSize(600, 400);
        this.setLocation(100, 20);
        this.add(ta, BorderLayout.CENTER);
        this.add(tf, BorderLayout.SOUTH);
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 把字符串发送到服务器
                client.send(tf.getText());
                // ta.setText(ta.getText() + tf.getText());
                tf.setText("");
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.closeConnect();
                System.exit(0);
            }
        });
    }

    private void connectToServer() {
        client = new Client();
        client.connect();
    }

    public void updateTaText(String msgAccepted) {
        ta.setText(ta.getText() + System.lineSeparator() + msgAccepted);
    }

}
