package com.yl.learn.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class CharSetUtil {

    public static CharBuffer charset(ByteBuffer byteBuffer, String charset) {

        if(byteBuffer == null || charset == null) {
            return null;
        }

        return Charset.forName(charset).decode(byteBuffer);
    }
}
