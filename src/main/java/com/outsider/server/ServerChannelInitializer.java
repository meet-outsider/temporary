package com.outsider.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import java.util.concurrent.TimeUnit;


public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    static final EventExecutorGroup group = new DefaultEventExecutorGroup(2);

    public ServerChannelInitializer() {
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //IdleStateHandler心跳机制,如果超时触发Handle中userEventTrigger()方法
        pipeline.addLast("idleStateHandler",
            new IdleStateHandler(15, 0, 0, TimeUnit.MINUTES));
        //自定义Hadler
        pipeline.addLast("handler",new ServerHandler());
    }
}
