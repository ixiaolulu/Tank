package com.lulu.io.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-16 21:19
 */
public class PoolServer {
    ExecutorService pool = Executors.newFixedThreadPool(50);

    private Selector selector;

    public static void main(String[] args) throws IOException {
        PoolServer poolServer = new PoolServer();
        poolServer.innitServer(8000);
        poolServer.listen();
    }


    public void innitServer(int port) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);

        this.selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动成功......");
    }

    public void listen() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
                    pool.execute(new ThreadHandlerChannel(key));
                }
            }


        }

    }

}

class ThreadHandlerChannel implements Runnable {
    SelectionKey key;

    public ThreadHandlerChannel(SelectionKey key) {
        this.key = key;
    }

    @Override
    public void run() {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            int size = 0;

            while ((size = channel.read(byteBuffer)) > 0) {
                byteBuffer.flip();
                baos.write(byteBuffer.array(), 0, size);
                byteBuffer.clear();
            }
            baos.close();


            byte[] content = baos.toByteArray();
            ByteBuffer writeBuffer = ByteBuffer.allocate(content.length);
            writeBuffer.put(content);
            writeBuffer.flip();
            channel.write(writeBuffer);

            System.out.println("server:" + new String(content));
            if (size == -1) {
                channel.close();
            } else {
                key.interestOps(key.interestOps() | SelectionKey.OP_READ);
                key.selector().wakeup();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
