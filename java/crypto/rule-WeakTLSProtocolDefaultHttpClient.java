// License: LGPL-3.0 License (c) find-sec-bugs
package crypto;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.IOException;

// ref: java_crypto_rule-WeakTLSProtocol-DefaultHttpClient
public class WeakTLSProtocolDefaultHttpClient {
    public void danger() throws IOException {
        // ruleid: java_crypto_rule-WeakTLSProtocol-DefaultHttpClient
        HttpClient client = new DefaultHttpClient(); 
        HttpUriRequest request = new HttpGet("https://test.com");
        client.execute(request);
    }
}
