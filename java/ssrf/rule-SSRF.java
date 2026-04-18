// License: LGPL-3.0 License (c) find-sec-bugs
package ssrf;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

/**
 * @author Tomas Polesovsky
 */
public class SSRF {


    private static final int TIMEOUT_IN_SECONDS = 20;

    public static void testURL(String url) throws IOException {
        // ruleid: java_ssrf_rule-SSRF
        new URL(url).openConnection().connect();

        // ruleid: java_ssrf_rule-SSRF
        new URL("http://safe.com").openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(url, 8080))).connect();

        // ruleid: java_ssrf_rule-SSRF
        new URL(url).openConnection().getInputStream();

        // ruleid: java_ssrf_rule-SSRF
        new URL(url).openConnection().getLastModified();

        // ruleid: java_ssrf_rule-SSRF
        new URL(url).openStream();

        // ruleid: java_ssrf_rule-SSRF
        new URL(url).getContent();

        // ruleid: java_ssrf_rule-SSRF
        new URL(url).getContent(new Class[0]);
    }

    public static void testURI(String url) throws IOException, URISyntaxException {
        // ruleid: java_ssrf_rule-SSRF
        new URI(url).toURL().openConnection().connect();
    }

    public static void connect(URI url, SSLContext ctx) throws IOException {
        int port = url.getPort();
        port = port > 0 ? port : 443;
        try (Socket s = ctx.getSocketFactory().createSocket()) {
            // ruleid: java_ssrf_rule-SSRF
            InetSocketAddress socketAddress = new InetSocketAddress(url.getHost(), port);
            s.connect(socketAddress, TIMEOUT_IN_SECONDS * 1000);
            try (OutputStream os = s.getOutputStream()) {
                os.write("GET / HTTP/1.1\n\n".getBytes());
            }
        }
    }
}
