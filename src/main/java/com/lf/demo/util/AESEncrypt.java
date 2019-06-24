package com.lf.demo.util;

import com.sun.crypto.provider.SunJCE;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

/**
 * PACKAGE      :  com.jiahui.eappt.util
 * CREATE DATE  :  2018/4/2
 * AUTHOR       :  linfei
 * 文件描述      :
 */
public class AESEncrypt {

    private KeyGenerator keygen;
    private SecretKey deskey;
    private Cipher aesEncrpt;
    private Cipher aesDecrpt;
    private BASE64Encoder base64Encoder = new BASE64Encoder();
    private BASE64Decoder base64Decoder = new BASE64Decoder();

    public AESEncrypt(String password)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException {
        Security.addProvider(new SunJCE());
        this.keygen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes("utf-8"));
        this.keygen.init(128, secureRandom);
        this.deskey = this.keygen.generateKey();
        this.aesEncrpt = Cipher.getInstance("AES");
        this.aesEncrpt.init(1, this.deskey);
        this.aesDecrpt = Cipher.getInstance("AES");
        this.aesDecrpt.init(2, this.deskey);
    }

    public byte[] Encrytor(byte[] src)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] cipherByte = this.aesEncrpt.doFinal(src);
        return cipherByte;
    }

    public byte[] Decryptor(byte[] buff)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] cipherByte = this.aesDecrpt.doFinal(buff);
        return cipherByte;
    }

    public String encode(String src)
            throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException, UnsupportedEncodingException {
        byte[] bytes = src.getBytes("utf-8");
        byte[] out = this.Encrytor(bytes);
        String result = this.base64Encoder.encode(out);
        return result;
    }

    public String decode(String dst)
            throws IOException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        byte[] bytes = this.base64Decoder.decodeBuffer(dst);
        byte[] out = this.Decryptor(bytes);
        String result = new String(out, "utf-8");
        return result;
    }

}