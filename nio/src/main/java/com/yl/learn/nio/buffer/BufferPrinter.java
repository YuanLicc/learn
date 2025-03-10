package com.yl.learn.nio.buffer;

import com.yl.learn.common.util.PrintUtil;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class BufferPrinter {

    public static void print(ByteBuffer byteBuffer, String prefix, String suffix) {

        if(byteBuffer == null) {
            return;
        }

        PrintUtil.print(prefix);

        while (byteBuffer.hasRemaining()) {
            PrintUtil.print(byteBuffer.get());
        }

        PrintUtil.print(suffix);
    }

    public static void println(ByteBuffer byteBuffer, String prefix, String suffix) {

        print(byteBuffer, prefix, suffix + "\n");
    }

    public static void print(CharBuffer charBuffer, String prefix, String delimiter, String suffix) {

        if(charBuffer == null) {
            return;
        }

        PrintUtil.print(prefix);

        while (charBuffer.hasRemaining()) {
            PrintUtil.print(charBuffer.get() + delimiter);
        }

        PrintUtil.print(suffix);
    }

    public static void println(CharBuffer charBuffer, String prefix, String delimiter, String suffix) {

        print(charBuffer , prefix, delimiter, suffix + "\n");
    }

}
