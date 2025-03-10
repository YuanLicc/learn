package com.yl.learn.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class ChannelTest {

    private static String classpath = ChannelTest.class.getResource("/").getPath();

    private static String filePath = classpath + "test.txt";

    public static void fileReadChar() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        Channel channel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (((FileChannel) channel).read(byteBuffer) != -1) {
            byteBuffer.flip();

            Charset charset = Charset.forName("utf-8");
            CharBuffer charBuffer = charset.decode(byteBuffer);

            while (charBuffer.hasRemaining()) {
                System.out.print(charBuffer.get()  + " ");
            }
            charBuffer.clear();
            charBuffer = null;

            byteBuffer.compact();
        }
        channel.close();
        fileInputStream.close();
        System.out.println();
    }


    public static void fileReadByte() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        Channel channel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (((FileChannel) channel).read(byteBuffer) != -1) {
            byteBuffer.flip();

            while (byteBuffer.hasRemaining()) {
                System.out.print(byteBuffer.get() + " ");
            }

            byteBuffer.compact();
        }

        channel.close();
        fileInputStream.close();
        System.out.println();
    }

}
