// License: LGPL-3.0 License (c) find-sec-bugs
package smtp;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;

public class InsecureSmtp{

    public static void main(String[] args) throws Exception {
        // ruleid: java_smtp_rule-InsecureSmtp
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSSLOnConnect(false); //OK

        // ruleid: java_smtp_rule-InsecureSmtp
        Email email2 = new SimpleEmail();
        email2.setHostName("smtp2.googlemail.com");        
        email2.setSSLOnConnect(true); //BAD
        //email2.setSmtpPort(465);
        //email2.setAuthenticator(new DefaultAuthenticator("username", "password"));
        //email2.setFrom("user@gmail.com");
        //email2.setSubject("TestMail");
        //email2.setMsg("This is a test mail ... :-)");
        //email2.addTo("foo@bar.com");
        //email2.send();

        // ruleid: java_smtp_rule-InsecureSmtp
        MultiPartEmail emailMulti = new MultiPartEmail();
        emailMulti.setHostName("mail.myserver.com");
        emailMulti.setSSLOnConnect(true); //BAD

        // ruleid: java_smtp_rule-InsecureSmtp
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName("mail.myserver.com");
        htmlEmail.setSSLOnConnect(true); //BAD

        // ok: java_smtp_rule-InsecureSmtp
        ImageHtmlEmail imageEmail = new ImageHtmlEmail();
        imageEmail.setHostName("mail.myserver.com");
        imageEmail.setSSLOnConnect(true);
        imageEmail.setSSLCheckServerIdentity(true); //OK
        
        // ok: java_smtp_rule-InsecureSmtp
        ImageHtmlEmail imageEmail2 = new ImageHtmlEmail();
        imageEmail2.setHostName("mail2.myserver.com");
        imageEmail2.setSSLCheckServerIdentity(true); //OK - reversed order
        imageEmail2.setSSLOnConnect(true);

        // ruleid: java_smtp_rule-InsecureSmtp
        ImageHtmlEmail imageEmail3 = new ImageHtmlEmail();
        imageEmail3.setHostName("mail3.myserver.com");
        imageEmail3.setSSLOnConnect(true); //BAD
    }

    public void rfh4017() {
        try {
            // ok: java_smtp_rule-InsecureSmtp
            SimpleEmail simpleEmail = new SimpleEmail();
            simpleEmail.setSSLOnConnect(true);
            simpleEmail.setHostName("");
            simpleEmail.setSSLCheckServerIdentity(true);
            simpleEmail.send();
        } catch (Exception ex) {
            // nop
        }

        try {
            // ok: java_smtp_rule-InsecureSmtp
            SimpleEmail simpleEmail = new SimpleEmail();
            simpleEmail.setSSLCheckServerIdentity(true);
            simpleEmail.setHostName("");
            simpleEmail.setSSLOnConnect(true);
            simpleEmail.send();
        } catch (Exception ex) {
            // nop
        }
    }
}
