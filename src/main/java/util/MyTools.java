package util;

import org.fisco.bcos.sdk.crypto.hash.Keccak256;

import java.security.MessageDigest;

/**
 * @author Xenqiao
 * @create 2023/5/14 12:40
 */
public class MyTools {


    /***
     * 用于加密信息的函数，MD5算法单向加密
     * @param password
     * @return
     */
    public static String encrypt(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hash = messageDigest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
