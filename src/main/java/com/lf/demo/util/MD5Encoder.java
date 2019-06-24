package com.lf.demo.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * PACKAGE      :  com.jiahui.eappt.util
 * CREATE DATE  :  2018/4/2
 * AUTHOR       :  linfei
 * 文件描述      :
 */
public class MD5Encoder {

    public MD5Encoder() {

    }

    public static String md5(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String str = base64Encoder.encode(messageDigest.digest(data.getBytes()));
        return str;
    }

}