package com.spirngboot.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.net.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author brandon
 * create on 2017-10-12 15:49
 * desc: 加密工具类
 */
public class EncryptUtil {

    private EncryptUtil() {
    }

    public static String MD5(String str) {
        return encrypt(str, "MD5").toUpperCase();
    }

    public static String SHA1(String str) {
        return encrypt(str, "SHA-1");
    }

    public static String SHA256(String str) {
        return encrypt(str, "SHA-256");
    }

    public static String encodeBase64(String str) {
        return encodeBase64(str.getBytes());
    }

    public static String decodeBase64(String str) {
        return new String(Base64.decodeBase64(str.getBytes()));
    }

    public static String encodeBase64(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    private static String encrypt(String src, String type) {
        if (src == null) {
            return null;
        }
        String result = null;
        try {
            byte[] b = src.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance(type);
            md.update(b);

            result = new String(Hex.encodeHex(md.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
