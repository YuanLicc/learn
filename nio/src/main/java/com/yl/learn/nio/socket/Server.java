package com.yl.learn.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    public void server() throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.socket().bind(new InetSocketAddress(8080));

        while (true) {
            SocketChannel socket = ssc.accept();

            if(socket != null) {
                CharBuffer charBuffer = CharBuffer.allocate(100);
                charBuffer.append('a');
            }
        }

    }

}
