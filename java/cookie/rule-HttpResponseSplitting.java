// License: LGPL-3.0 License (c) find-sec-bugs
package cookie;

import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class HttpResponseSplitting extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("input");
        Cookie c = new Cookie("name", null);
        //ruleid: java_cookie_rule-HttpResponseSplitting
        c.setValue(input);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(c);

        String paramNames = req.getParameterNames().nextElement();
        Cookie cParamNames = new Cookie("name", null);
        //ruleid: java_cookie_rule-HttpResponseSplitting
        cParamNames.setValue(paramNames);
        cParamNames.setHttpOnly(true);
        cParamNames.setSecure(true);
        resp.addCookie(cParamNames);

        String paramValues = req.getParameterValues("input")[0];
        Cookie cParamValues = new Cookie("name", null);
        //ruleid: java_cookie_rule-HttpResponseSplitting
        cParamValues.setValue(paramValues);
        cParamValues.setHttpOnly(true);
        cParamValues.setSecure(true);
        resp.addCookie(cParamValues);

        String paramMap = req.getParameterMap().get("input")[0];
        Cookie cParamMap = new Cookie("name", null);
        //ruleid: java_cookie_rule-HttpResponseSplitting
        cParamMap.setValue(paramMap);
        cParamMap.setHttpOnly(true);
        cParamMap.setSecure(true);
        resp.addCookie(cParamMap);

        String header = req.getHeader("input");
        Cookie cHeader = new Cookie("name", null);
        //ruleid: java_cookie_rule-HttpResponseSplitting
        c.setValue(header);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cHeader);

        String contextPath = req.getPathInfo();
        Cookie cPath = new Cookie("name", null);
        //ruleid: java_cookie_rule-HttpResponseSplitting
        c.setValue(contextPath);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cHeader);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("input");
        //ruleid: java_cookie_rule-HttpResponseSplitting
        Cookie c = new Cookie("name", input);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(c);

        String header = req.getHeader("input");
        //ruleid: java_cookie_rule-HttpResponseSplitting
        Cookie cHeader = new Cookie("name", header);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cHeader);

        String contextPath = req.getPathInfo();
        //ruleid: java_cookie_rule-HttpResponseSplitting
        Cookie cPath = new Cookie("name", contextPath);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cPath);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getParameter("input");
        String input = data.replaceAll("\n", "");
        //ruleid: java_cookie_rule-HttpResponseSplitting
        Cookie c = new Cookie("name", input);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(c);

        String header = req.getHeader("input");
        header = header.replaceAll("\n", "");
        //ruleid: java_cookie_rule-HttpResponseSplitting
        Cookie cHeader = new Cookie("name", header);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cHeader);

        String contextPath = req.getPathInfo();
        contextPath = contextPath.replaceAll("\n", "");
        //ruleid: java_cookie_rule-HttpResponseSplitting
        Cookie cPath = new Cookie("name", contextPath);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cPath);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getParameter("input");
        String input = data.replaceAll("\r", "");
        //ruleid: java_cookie_rule-HttpResponseSplitting
        Cookie c = new Cookie("name", input);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(c);

        String header = req.getHeader("input");
        header = header.replaceAll("\r", "");
        //ruleid: java_cookie_rule-HttpResponseSplitting
        Cookie cHeader = new Cookie("name", header);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cHeader);

        String contextPath = req.getPathInfo();
        contextPath = contextPath.replaceAll("\r", "");
        //ruleid: java_cookie_rule-HttpResponseSplitting
        Cookie cPath = new Cookie("name", contextPath);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cPath);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getParameter("input");
        String input = data.replaceAll("[\r\n]+", "");
        //ok: java_cookie_rule-HttpResponseSplitting
        Cookie c = new Cookie("name", input);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(c);

        String header = req.getHeader("input");
        header = header.replaceAll("[\r\n]+", "");
        //ok: java_cookie_rule-HttpResponseSplitting
        Cookie cHeader = new Cookie("name", header);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cHeader);

        String contextPath = req.getPathInfo();
        contextPath = contextPath.replaceAll("[\r\n]+", "");
        //ok: java_cookie_rule-HttpResponseSplitting
        Cookie cPath = new Cookie("name", contextPath);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cPath);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getParameter("input");
        String input = StringEscapeUtils.escapeJava(data);
        //ok: java_cookie_rule-HttpResponseSplitting
        Cookie c = new Cookie("name", input);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(c);

        String header = req.getHeader("input");
        header = StringEscapeUtils.escapeJava(header);
        //ok: java_cookie_rule-HttpResponseSplitting
        Cookie cHeader = new Cookie("name", header);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cHeader);

        String contextPath = req.getPathInfo();
        contextPath = StringEscapeUtils.escapeJava(contextPath);
        //ok: java_cookie_rule-HttpResponseSplitting
        Cookie cPath = new Cookie("name", contextPath);
        c.setHttpOnly(true);
        c.setSecure(true);
        resp.addCookie(cPath);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // BAD
        String tainted = req.getParameter("input");
        resp.setHeader("test", tainted);

        // OK: False negative but reported by spotbugs
        String data = req.getParameter("input");
        String normalized = data.replaceAll("\n", "\n");
        resp.setHeader("test", normalized);

        // OK: False negative but reported by spotbugs
        String normalized2 = data.replaceAll("\n", req.getParameter("test"));
        resp.setHeader("test2", normalized2);

        // OK
        String normalized3 = org.apache.commons.text.StringEscapeUtils.unescapeJava(tainted);
        resp.setHeader("test3", normalized3);

        // BAD
        String normalized4 = getString(tainted);
        resp.setHeader("test4", normalized4);

        // BAD
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(resp);
        wrapper.addHeader("test", tainted);
        wrapper.setHeader("test2", tainted);

    }

    private String getString(String s) {
        return s;
    }
}
