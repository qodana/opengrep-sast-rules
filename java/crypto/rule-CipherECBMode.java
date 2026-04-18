// License: MIT (c) GitLab Inc.

package crypto;

class Cipher {

    javax.crypto.Cipher cipher;

    public void config1() throws Exception {
        // ruleid: java_crypto_rule-CipherECBMode
        cipher = javax.crypto.Cipher.getInstance("AES/ECB/NoPadding");
        // ruleid: java_crypto_rule-CipherECBMode
        cipher = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
        // ruleid: java_crypto_rule-CipherECBMode
        cipher = javax.crypto.Cipher.getInstance("DES/ECB/NoPadding");
        // ruleid: java_crypto_rule-CipherECBMode
        cipher = javax.crypto.Cipher.getInstance("DES/ECB/PKCS5Padding");
        // ruleid: java_crypto_rule-CipherECBMode
        cipher = javax.crypto.Cipher.getInstance("DESede/ECB/NoPadding");
        // ruleid: java_crypto_rule-CipherECBMode
        cipher = javax.crypto.Cipher.getInstance("DESede/ECB/PKCS5Padding");
        // ruleid: java_crypto_rule-CipherECBMode
        cipher = javax.crypto.Cipher.getInstance("AES/ECB/PKCS7Padding");

        // ok: java_crypto_rule-CipherECBMode
        cipher = javax.crypto.Cipher.getInstance("AES/GCM/PKCS1Padding");
    }

}
