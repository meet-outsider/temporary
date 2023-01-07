package com.outsider;

import com.outsider.client.MyClient;
import com.outsider.netty.common.RpcRequest;
import com.outsider.netty.common.RpcResponse;
import com.outsider.server.MyServer;
import com.outsider.service.UserService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppTest {

    public static CountDownLatch latch = new CountDownLatch(2);

    @Resource
    private UserService service;

    @Test
    void test(){
        System.out.println("amend before");
    }


    @Test
    void lambda() {
        service.lambda();
    }

    @Test
    void netty() throws InterruptedException {
        nettyStart();
    }

    public static void nettyStart() throws InterruptedException {
        // server start
        Thread.ofVirtual().unstarted(() -> {
            try {
                new MyServer().bind(8122);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
        // client start
        Thread.ofVirtual().start(() -> {
            String id = "C-" + 0;
            try {
                initClient(id, id);
                latch.countDown();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        latch.await();
        manualSend();
    }

    /**
     * 手动发送socket消息
     *
     * @throws InterruptedException
     */
    static void manualSend() throws InterruptedException {
        RpcResponse response = new RpcResponse();
        System.out.println(LocalDateTime.now() + ", " + MyServer.clientMap.get("C-" + 0));
        ChannelHandlerContext ctx = MyServer.clientMap.get("C-" + 0);
        response.setId("S-" + MyServer.clientMap);
        response.setData("server响应结果");
        response.setStatus(1);
        ctx.writeAndFlush(response);
    }

    public static void initClient(String id, String name) throws Exception {
        MyClient client = new MyClient("127.0.0.1", 8122, name);
        // 启动client服务
        client.start();
        Channel channel = client.getChannel();
        // 消息体
        RpcRequest request = new RpcRequest();
        request.setId(id);
        request.setData("00000000001111");
        // channel对象可保存在map中，供其它地方发送消息
        channel.writeAndFlush(request);
    }
}
