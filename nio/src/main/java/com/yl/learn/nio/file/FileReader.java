package com.yl.learn.nio.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileReader {

    private static String classpath = FileReader.class.getResource("/").getPath();

    public static ByteBuffer byteBuffer(String relativePath) {

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(classpath + relativePath));

            FileChannel channel = fileInputStream.getChannel();

            ByteBuffer byteBuffer  = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            channel.close();
            fileInputStream.close();

            return byteBuffer;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static CharBuffer charBuffer(String relativePath, String charSet) {

        ByteBuffer byteBuffer = byteBuffer(relativePath);

        return Charset.forName(charSet).decode(byteBuffer);

    }

}
