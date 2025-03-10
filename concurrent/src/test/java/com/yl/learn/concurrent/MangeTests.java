package com.yl.learn.concurrent;

import junit.framework.TestCase;
import java.security.MessageDigest;

public class MangeTests extends TestCase {
    
    
    public void testMD5() throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update("aaaa".getBytes());
        System.out.println(new String(messageDigest.digest()));
        messageDigest.update(messageDigest.digest());
        System.out.println(new String(messageDigest.digest()));
    }
}
