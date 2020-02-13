package com.linz.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    private static final String salt = "5d052f1e32af4e4ac2544a5fc2a9b992";

    public static String getResult(String inputStr) {
        System.out.println("=======加密前的数据:" + inputStr);
        BigInteger bigInteger = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] inputData = (inputStr + Md5Util.salt).getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert bigInteger != null;
        System.out.println("MD5加密后:" + bigInteger.toString(16));
        return bigInteger.toString(16);
    }
}