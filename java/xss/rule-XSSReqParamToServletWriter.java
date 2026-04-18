// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=org.owasp.encoder.encoder@1.2.3
package xss;

import org.owasp.encoder.Encode;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

// Also contains vulnerabilities found under ids: XSS_SERVLET,SERVLET_PARAMETER
public class XSSReqParamToServletWriter extends HttpServlet {

    protected void danger(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input1 = req.getParameter("input1");
        // ruleid: java_xss_rule-XSSReqParamToServletWriter
        resp.getWriter().write(input1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input1 = req.getParameter("input1"); // BAD
        String sessionId = req.getRequestedSessionId(); // BAD
        String queryString = req.getQueryString(); // BAD

        String referrer = req.getHeader("Referer"); //Should have a higher priority
        if (referrer != null && referrer.startsWith("http://company.ca")) {
            // Header access
            String host = req.getHeader("Host"); // BAD
            String referer = req.getHeader("Referer"); // BAD
            String userAgent = req.getHeader("User-Agent"); // BAD
        }

        PrintWriter writer = resp.getWriter();
        // ruleid: java_xss_rule-XSSReqParamToServletWriter
        writer.write(input1);
    }


    protected void danger3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input1 = req.getParameter("input1");
        // ruleid: java_xss_rule-XSSReqParamToServletWriter
        resp.getWriter().write(input1);
    }

    protected void ok2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input1 = req.getParameter("input1");
        PrintWriter writer = resp.getWriter();
        // ok: java_xss_rule-XSSReqParamToServletWriter
        writer.write(Encode.forHtml(input1));
    }
}
