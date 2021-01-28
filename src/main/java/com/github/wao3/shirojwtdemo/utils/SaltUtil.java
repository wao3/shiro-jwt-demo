package com.github.wao3.shirojwtdemo.utils;

import java.util.Random;

public class SaltUtil {

    public static String getSalt(int num) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=".toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < num; ++i) {
            sb.append(chars[new Random().nextInt(chars.length)]);
        }

        return sb.toString();
    }
}
