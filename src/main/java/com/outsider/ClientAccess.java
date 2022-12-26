package com.outsider;

import com.outsider.client.MyClient;
import com.outsider.netty_common.RpcRequest;
import io.netty.channel.Channel;

public class ClientAccess {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
                int finalI = i;
                new Thread(() -> {
                    try {
                        String id = "C-" + finalI;
                        todo(id, id);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void todo(String id, String name) throws Exception {
        MyClient client = new MyClient("127.0.0.1", 8122, name);
        // 启动client服务
        client.start();
        Channel channel = client.getChannel();
        // 消息体
        RpcRequest request = new RpcRequest();
        request.setId(id);
        request.setData(
            "1--------------------------------------------------------------------------------------------\n"
                + "2--------------------------------------------------------------------------------------------\n"
                + "3--------------------------------------------------------------------------------------------\n"
                + "4--------------------------------------------------------------------------------------------\n"
                + "5--------------------------------------------------------------------------------------------\n"
                + "6--------------------------------------------------------------------------------------------\n"
                + "7--------------------------------------------------------------------------------------------\n");
        // channel对象可保存在map中，供其它地方发送消息
        channel.writeAndFlush(request);
    }
}
