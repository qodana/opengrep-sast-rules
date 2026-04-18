// License: LGPL-3.0 License (c) find-sec-bugs
package strings;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// ref: java_strings_rule-BadHexConversion
public class BadHexConversion {

    public String danger(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] resultBytes = md.digest(text.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        // ruleid: java_strings_rule-BadHexConversion
        for (byte b : resultBytes) {
            stringBuilder.append(Integer.toHexString(b));
        }
        return stringBuilder.toString();
    }


    public String danger2(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] resultBytes = md.digest(text.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        // ruleid: java_strings_rule-BadHexConversion
        for (int i = 0, resultBytesLength = resultBytes.length; i < resultBytesLength; i++) {
            byte b = resultBytes[i];
            stringBuilder.append(Integer.toHexString(b));
        }
        return stringBuilder.toString();
    }

    public String danger3(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] resultBytes = md.digest(text.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        // ruleid: java_strings_rule-BadHexConversion
        for (int i = 0, resultBytesLength = resultBytes.length; i < resultBytesLength; i++) {
            stringBuilder.append(Integer.toHexString(resultBytes[i]));
        }
        return stringBuilder.toString();
    }

    public String danger4(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] resultBytes = md.digest(text.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        // ruleid: java_strings_rule-BadHexConversion
        while (i < resultBytes.length) {
            stringBuilder.append(Integer.toHexString(resultBytes[i]));
            i++;
        }
        return stringBuilder.toString();
    }

    public String danger5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] resultBytes = md.digest(text.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        // ruleid: java_strings_rule-BadHexConversion
        do {
            stringBuilder.append(Integer.toHexString(resultBytes[i]));
            i++;
        } while (i < resultBytes.length);
        return stringBuilder.toString();
    }

    public String danger6(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] resultBytes = md.digest(text.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        // ruleid: java_strings_rule-BadHexConversion
        while (i < resultBytes.length) {
            byte resultByte = resultBytes[i];
            stringBuilder.append(Integer.toHexString(resultByte));
            i++;
        }
        return stringBuilder.toString();
    }

    public String danger7(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] resultBytes = md.digest(text.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        // ruleid: java_strings_rule-BadHexConversion
        do {
            byte resultByte = resultBytes[i];
            stringBuilder.append(Integer.toHexString(resultByte));
            i++;
        } while (i < resultBytes.length);
        return stringBuilder.toString();
    }

    public static String safeOne(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] resultBytes = md.digest(password.getBytes("UTF-8"));

        StringBuilder stringBuilder = new StringBuilder();
        // ok: java_strings_rule-BadHexConversion
        for (byte b : resultBytes) {
            stringBuilder.append(String.format("%02X", b));
        }

        return stringBuilder.toString();
    }
}
