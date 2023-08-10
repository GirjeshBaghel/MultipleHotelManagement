package com.Utils;
import java.util.Base64;

public class Base64Utils {

    public static String encodeToString(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decodeFromString(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
}