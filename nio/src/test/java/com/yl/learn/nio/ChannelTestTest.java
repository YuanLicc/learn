package com.yl.learn.nio;

import com.yl.learn.nio.buffer.BufferPrinter;
import com.yl.learn.nio.file.FileReader;
import org.junit.Test;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channel;

public class ChannelTestTest extends TestSuper {

    String relativePath = "test.txt";

    @Test
    public void testReadFileChar() {

        BufferPrinter.println(FileReader.byteBuffer(relativePath), "", "");
    }


    @Test
    public void testReadFileByte() {
        BufferPrinter.println(FileReader.charBuffer(relativePath, "utf-8")
                , "utf-8", "", "");



    }
}
