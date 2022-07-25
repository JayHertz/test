package com.hx.mdesign.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: Hx
 * @date: 2022年04月23日 21:20
 */
public class Conversion {

    /**
     * 获取MD5值
     */
    public static String MD5(String str) {
        String res;
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(str.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    sb.append('0');
                }
                sb.append(temp);
            }
            res = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            res = String.valueOf(str.hashCode());
        }
        return res;
    }


    /**
     * 文件转Byte[]
     *
     * @param path
     * @return
     */
    public static byte[] fileToBytes(String path) {

        byte[] data = null;
        InputStream in = null;

        try {
            in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
