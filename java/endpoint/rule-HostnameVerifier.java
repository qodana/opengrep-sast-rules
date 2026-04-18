// License: LGPL-3.0 License (c) find-sec-bugs
package endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

class AllHosts implements HostnameVerifier {

    // ruleid: java_endpoint_rule-HostnameVerifier
    public boolean verify(final String hostname, final SSLSession session) {
        return true;
    }
}

class LoggedHosts implements HostnameVerifier {

    Logger logger = LogManager.getLogger(HostnameVerifier.class);

    // ruleid: java_endpoint_rule-HostnameVerifier
    public boolean verify(final String hostname, final SSLSession session) {
        logger.info("All hosts are permitted");
        return true;
    }
}

class SomeHosts implements HostnameVerifier {

    // ok: java_endpoint_rule-HostnameVerifier
    public boolean verify(final String hostname, final SSLSession session) {
        if("192.187.1.1".equals(hostname))
            return true;
        return hostname.startsWith("192.168");
    }
}

class SomeHosts2 implements HostnameVerifier {

    // ok: java_endpoint_rule-HostnameVerifier
    public boolean verify(final String hostname, final SSLSession session) {
        if("192.186.1.1".equals(hostname))
            return false;
        return true;
    }
}

class TrustedHosts implements HostnameVerifier {

    // ok: java_endpoint_rule-HostnameVerifier
    @Override
    public boolean verify(final String hostname, final SSLSession session) {
        if (!hostname.equals("javaxservlet-trusted-server-1"))
            return false;
        return true;
    }
}


class TrustedHosts2 implements HostnameVerifier {

    // ok: java_endpoint_rule-HostnameVerifier
    @Override
    public boolean verify(final String hostname, final SSLSession session) {
        boolean a = false;
        return a;
    }
}

class TrustedHosts3 implements HostnameVerifier {
    // ok: java_endpoint_rule-HostnameVerifier
    @Override
    public boolean verify(final String hostname, final SSLSession session) {
        if (!hostname.equals("javaxservlet-trusted-server-1"))
            return true;
        if (!hostname.equals("javaxservlet-trusted-server-2"))
            return false;
        if (!hostname.equals("javaxservlet-trusted-server-3"))
            return true;
        return true;
    }
}

class FaultyTrustedHosts implements HostnameVerifier {
    // ruleid: java_endpoint_rule-HostnameVerifier
    @Override
    public boolean verify(final String hostname, final SSLSession session) {
        if (!hostname.equals("javaxservlet-trusted-server-1"))
            return true;
        if (!hostname.equals("javaxservlet-trusted-server-2"))
            return true;
        if (!hostname.equals("javaxservlet-trusted-server-3"))
            return true;
        return true;
    }
}

class FaultyTrustedHosts2 implements HostnameVerifier {
    // ruleid: java_endpoint_rule-HostnameVerifier
    @Override
    public boolean verify(final String hostname, final SSLSession session) {
        if (!hostname.equals("javaxservlet-trusted-server-1"))
            return true;
        return true;
    }
}


class FaultyTrustedHosts3 implements HostnameVerifier {
    // ruleid: java_endpoint_rule-HostnameVerifier
    @Override
    public boolean verify(final String hostname, final SSLSession session) {
        boolean a = true;
        return a;
    }
}