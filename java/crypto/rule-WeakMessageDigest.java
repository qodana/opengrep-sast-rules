// License: LGPL-3.0 License (c) find-sec-bugs
package crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Signature;

public class WeakMessageDigest {

    public static void weakDigestMoreSig() throws NoSuchProviderException, NoSuchAlgorithmException {
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("MD5", "SUN");
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("MD4", "SUN");
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("MD2", "SUN");
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("MD5");
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("MD4");
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("MD2");
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("MD5", new ExampleProvider());
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("MD4", new ExampleProvider());
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("MD2", new ExampleProvider());
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("SHA", "SUN");
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("SHA", new ExampleProvider());
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("SHA1", "SUN");
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("SHA1", new ExampleProvider());
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("SHA-1", "SUN");
        // ruleid: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("SHA-1", new ExampleProvider());
        // ok: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("sha-384","SUN"); //OK!
        // ok: java_crypto_rule-WeakMessageDigest
        MessageDigest.getInstance("SHA-512", "SUN"); //OK!

        // ruleid: java_crypto_rule-WeakMessageDigest
        Signature.getInstance("MD5withRSA");
        // ruleid: java_crypto_rule-WeakMessageDigest
        Signature.getInstance("SHA1withRSA", new ExampleProvider());
        // ok: java_crypto_rule-WeakMessageDigest
        Signature.getInstance("SHA256withRSA"); //OK
        Signature.getInstance("uncommon name", ""); //OK
    }

    static class ExampleProvider extends Provider {
        protected ExampleProvider() {
            super("example", 1.0, "");
        }
    }
}
