// License: LGPL-3.0 License (c) find-sec-bugs
package endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

class X509TrustManagerTest {

    class TrustAllManager implements X509TrustManager {

        // ruleid: java_endpoint_rule-X509TrustManager
        public void checkClientTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
        }

        // ruleid: java_endpoint_rule-X509TrustManager
        public void checkServerTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
        }

        // ruleid: java_endpoint_rule-X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    class LoggedTrustAllManager implements X509TrustManager {

        Logger logger = LogManager.getLogger(LoggedTrustAllManager.class);

        // ruleid: java_endpoint_rule-X509TrustManager
        public void checkClientTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {

        }

        // ruleid: java_endpoint_rule-X509TrustManager
        public void checkServerTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
        }

        // ruleid: java_endpoint_rule-X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            logger.info("returning all getAcceptedIssuers");
            return null;
        }
    }

    class LimitedManager implements X509TrustManager {

        X509Certificate[] certificates;

        int issuerRequestsCount = 0;

        // ok: java_endpoint_rule-X509TrustManager
        public void checkClientTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            for (X509Certificate certificate : cert){
                certificate.checkValidity();
            }
        }

        // ok: java_endpoint_rule-X509TrustManager
        public void checkServerTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            for (X509Certificate certificate : cert) {
                if (certificate.getSubjectX500Principal().getName().contains("ALLOWED_HOST")) {
                    return;
                }
            }
            throw new CertificateException("Server certificate is not issued for provided Host");
        }

        // ok: java_endpoint_rule-X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            if(issuerRequestsCount < 5){
                issuerRequestsCount++;
                return null;
            }
            return certificates;
        }
    }

    class FaultyLimitedManager implements X509TrustManager {

        X509Certificate[] certificates;

        int issuerRequestsCount = 0;

        // ok: java_endpoint_rule-X509TrustManager
        public void checkClientTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            for (X509Certificate certificate : cert){
                certificate.checkValidity();
            }
        }

        // ok: java_endpoint_rule-X509TrustManager
        public void checkServerTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            for (X509Certificate certificate : cert) {
                if (certificate.getSubjectX500Principal().getName().contains("ALLOWED_HOST")) {
                    return;
                }
            }
            throw new CertificateException("Server certificate is not issued for provided Host");
        }

        // ruleid: java_endpoint_rule-X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            if(issuerRequestsCount < 5){
                issuerRequestsCount++;
                return null;
            }
            return null;
        }
    }

    class LimitedManager2 implements X509TrustManager {

        X509Certificate[] certificates;

        int issuerRequestsCount = 0;

        // ok: java_endpoint_rule-X509TrustManager
        public void checkClientTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            for (X509Certificate certificate : cert){
                certificate.checkValidity();
            }
        }

        // ok: java_endpoint_rule-X509TrustManager
        public void checkServerTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            for (X509Certificate certificate : cert) {
                if (certificate.getSubjectX500Principal().getName().contains("ALLOWED_HOST")) {
                    return;
                }
            }
            throw new CertificateException("Server certificate is not issued for provided Host");
        }

        // ok: java_endpoint_rule-X509TrustManager
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            X509Certificate[] a = certificates;
            return a;
        }
    }

    class LimitedManager3 implements X509TrustManager {

        X509Certificate[] certificates;

        int issuerRequestsCount = 0;
        
        String currentAuthType;

        // ok: java_endpoint_rule-X509TrustManager
        public void checkClientTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            currentAuthType = authType;
            for (X509Certificate certificate : cert){
                certificate.checkValidity();
            }
        }

        // ok: java_endpoint_rule-X509TrustManager
        public void checkServerTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            currentAuthType = authType;
            for (X509Certificate certificate : cert) {
                if (certificate.getSubjectX500Principal().getName().contains("ALLOWED_HOST")) {
                    return;
                }
            }
            throw new CertificateException("Server certificate is not issued for provided Host");
        }

        // ok: java_endpoint_rule-X509TrustManager
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            if (!currentAuthType.equals("javaxservlet-trusted-server-1"))
                return null;
            if (!currentAuthType.equals("javaxservlet-trusted-server-2"))
                return certificates;
            if (!currentAuthType.equals("javaxservlet-trusted-server-3"))
                return null;
            return certificates;
        }
    }

    class FaultyLimitedManager2 implements X509TrustManager {

        X509Certificate[] certificates;

        int issuerRequestsCount = 0;

        String currentAuthType;

        // ok: java_endpoint_rule-X509TrustManager
        public void checkClientTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            currentAuthType = authType;
            for (X509Certificate certificate : cert){
                certificate.checkValidity();
            }
        }

        // ok: java_endpoint_rule-X509TrustManager
        public void checkServerTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            currentAuthType = authType;
            for (X509Certificate certificate : cert) {
                if (certificate.getSubjectX500Principal().getName().contains("ALLOWED_HOST")) {
                    return;
                }
            }
            throw new CertificateException("Server certificate is not issued for provided Host");
        }

        // ruleid: java_endpoint_rule-X509TrustManager
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            if (!currentAuthType.equals("javaxservlet-trusted-server-1"))
                return null;
            if (!currentAuthType.equals("javaxservlet-trusted-server-2"))
                return null;
            if (!currentAuthType.equals("javaxservlet-trusted-server-3"))
                return null;
            return null;
        }
    }

    class FaultyLimitedManager3 implements X509TrustManager {

        X509Certificate[] certificates;

        int issuerRequestsCount = 0;

        String currentAuthType;

        // ok: java_endpoint_rule-X509TrustManager
        public void checkClientTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            currentAuthType = authType;
            for (X509Certificate certificate : cert){
                certificate.checkValidity();
            }
        }

        // ok: java_endpoint_rule-X509TrustManager
        public void checkServerTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            currentAuthType = authType;
            for (X509Certificate certificate : cert) {
                if (certificate.getSubjectX500Principal().getName().contains("ALLOWED_HOST")) {
                    return;
                }
            }
            throw new CertificateException("Server certificate is not issued for provided Host");
        }

        // ruleid: java_endpoint_rule-X509TrustManager
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            if (!currentAuthType.equals("javaxservlet-trusted-server-1"))
                return null;
            return null;
        }
    }


    class FaultyLimitedManager4 implements X509TrustManager {

        X509Certificate[] certificates;

        int issuerRequestsCount = 0;

        // ok: java_endpoint_rule-X509TrustManager
        public void checkClientTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            for (X509Certificate certificate : cert){
                certificate.checkValidity();
            }
        }

        // ok: java_endpoint_rule-X509TrustManager
        public void checkServerTrusted(final X509Certificate[] cert, final String authType)
                throws CertificateException {
            for (X509Certificate certificate : cert) {
                if (certificate.getSubjectX500Principal().getName().contains("ALLOWED_HOST")) {
                    return;
                }
            }
            throw new CertificateException("Server certificate is not issued for provided Host");
        }

        // ruleid: java_endpoint_rule-X509TrustManager
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            X509Certificate[] a = null;
            return a;
        }
    }

}
