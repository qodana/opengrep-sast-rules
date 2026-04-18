// License: LGPL-3.0 License (c) find-sec-bugs
package password;

import com.hazelcast.config.SymmetricEncryptionConfig;
import io.vertx.ext.web.handler.CSRFHandler;
import java.net.PasswordAuthentication;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.sql.DriverManager;
import javax.crypto.spec.PBEKeySpec;
import javax.net.ssl.KeyManagerFactory;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.kerberos.KerberosKey;

public class HardcodePassword {
    private final String passwordString = "secret";
    private final SymmetricEncryptionConfig passwordS = new SymmetricEncryptionConfig();

    public void danger1(String password) throws Exception {
        // ruleid: java_password_rule-HardcodePassword
        new KeyStore.PasswordProtection("secret".toCharArray());
        
	// ruleid: java_password_rule-HardcodePassword
        new KeyStore.PasswordProtection(passwordString.toCharArray());
    }

    public void danger2(String password) throws Exception {
        // ruleid: java_password_rule-HardcodePassword
        KeyStore.getInstance(null).load(null, "secret".toCharArray());
    }

    public void danger3(KeyStore ks, String password) throws Exception {
        // ruleid: java_password_rule-HardcodePassword
        ks.load(null, "secret".toCharArray());
    }

    public void danger4(String password) throws Exception {
        // ruleid: java_password_rule-HardcodePassword
        KeyManagerFactory.getInstance(null).init(null, "secret".toCharArray());
    }

    public void danger5(KeyManagerFactory kmf, String password) throws Exception{
        // ruleid: java_password_rule-HardcodePassword
        kmf.init(null, "secret".toCharArray());
    }

    public void danger10(String password) throws Exception{
        // ruleid: java_password_rule-HardcodePassword
        DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "secret");

        // ok: java_password_rule-HardcodePassword
        DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", password);
    }

    public void danger11(String password) throws Exception{
        // ruleid: java_password_rule-HardcodePassword
        CSRFHandler.create(null, "secret");
    }

    public void danger12(SymmetricEncryptionConfig s, String password) throws Exception{
        // ruleid: java_password_rule-HardcodePassword
        s.setPassword("secret");

        // ok: java_password_rule-HardcodePassword
        s.setPassword(password);
    }
}
