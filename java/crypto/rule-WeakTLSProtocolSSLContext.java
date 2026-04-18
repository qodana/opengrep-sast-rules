// License: LGPL-3.0 License (c) find-sec-bugs
package crypto;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.NoSuchProviderException;

// ref: java_crypto_rule-WeakTLSProtocol-SSLContext
public class WeakTLSProtocolSSLContext {

    public static void main(String[] args) {
        try {
            // ruleid: java_crypto_rule-WeakTLSProtocol-SSLContext
            SSLContext.getInstance("SSL"); // BAD
            // ok: java_crypto_rule-WeakTLSProtocol-SSLContext
            SSLContext.getInstance("TLS"); // BAD
            // ruleid: java_crypto_rule-WeakTLSProtocol-SSLContext
            SSLContext.getInstance("TLSv1.0"); // WARN - should be v1.2 or above
            // ruleid: java_crypto_rule-WeakTLSProtocol-SSLContext
            SSLContext.getInstance("TLSv1.1"); // WARN - should be v1.2 or above
            // ruleid: java_crypto_rule-WeakTLSProtocol-SSLContext
            SSLContext.getInstance("DTLSv1.0"); // WARN - should be v1.2 or above
            // ruleid: java_crypto_rule-WeakTLSProtocol-SSLContext
            SSLContext.getInstance("DTLSv1.1"); // WARN - should be v1.2 or above
            // ok: java_crypto_rule-WeakTLSProtocol-SSLContext
            SSLContext.getInstance("TLSv1.2"); // OK
            // ok: java_crypto_rule-WeakTLSProtocol-SSLContext
            SSLContext.getInstance("TLSv1.3"); // OK

            // Testing getInstance(String protocol, String provider)
            try{
                // ok: java_crypto_rule-WeakTLSProtocol-SSLContext
                SSLContext sslContext2 = SSLContext.getInstance("TLSv1.3", "SunJSSE");
            }catch (NoSuchProviderException e) {
                e.printStackTrace();
            }

            // Testing getInstance(String protocol, Provider provider)
            Provider provider = Security.getProvider("SunJSSE");
            // ok: java_crypto_rule-WeakTLSProtocol-SSLContext
            SSLContext sslContext3 = SSLContext.getInstance("TLSv1.3", provider);

            try{
                // ok: java_crypto_rule-WeakTLSProtocol-SSLContext
                SSLContext sslContext4 = SSLContext.getInstance("TLS", "SunJSSE");
            }catch (NoSuchProviderException e) {
                e.printStackTrace();
            }

            // ruleid: java_crypto_rule-WeakTLSProtocol-SSLContext
            SSLContext sslContext5 = SSLContext.getInstance("SSL", provider);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
