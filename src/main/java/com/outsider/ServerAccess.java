package com.outsider;

import com.outsider.server.MyServer;

public class ServerAccess {

    public static void main(String[] args) throws Exception {
        new MyServer().bind(8122);
    }
}
