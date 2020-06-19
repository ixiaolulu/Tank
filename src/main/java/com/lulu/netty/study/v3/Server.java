package com.lulu.netty.study.v3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-17 21:38
 */
public class Server {

    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void serverStart() {
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);

        try {
            ServerBootstrap b = new ServerBootstrap();
            ChannelFuture f = b.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

                    socketChannel.pipeline().addLast(new TankMsgDecoder()).addLast(new ServerChildHandler());
                }
            }).bind(8888).sync();

            ServerFrame.INSTANCE.updateServerMsg("server started!");

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            boosGroup.shutdownGracefully();
        }
    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = null;
        try {

            TankMsg tm = (TankMsg) msg;
            System.out.println(tm);
            // buf = (ByteBuf) msg;
            // byte[] bytes = new byte[buf.readableBytes()];
            // buf.getBytes(buf.readerIndex(), bytes);
            //
            // String acceptMsg = new String(bytes);
            // ServerFrame.INSTANCE.updateClientMsg(acceptMsg);
            //
            // if ("bye".equals(acceptMsg)) {
            // System.out.println("客户端:" + ctx.channel().remoteAddress() + "要求退出...");
            // Server.clients.remove(ctx.channel());
            // ctx.close();
            // } else {
            // // System.out.println(acceptMsg);
            // Server.clients.writeAndFlush(msg);
            // }
        } finally {
            if (buf != null)
                ReferenceCountUtil.release(buf);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Server.clients.remove(ctx.channel());
        ctx.close();
    }
}
