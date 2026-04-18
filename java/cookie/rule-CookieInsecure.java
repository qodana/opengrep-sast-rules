// License: LGPL-3.0 License (c) find-sec-bugs
package cookie;

public class CookieInsecure {

    public void dangerJavax(javax.servlet.http.HttpServletResponse res) {
        // ruleid: java_cookie_rule-CookieInsecure
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("key", "value");
        cookie.setSecure(false);
        cookie.setMaxAge(60);
        cookie.setHttpOnly(true);
        res.addCookie(cookie);
    }

    public void danger2Javax(javax.servlet.http.HttpServletResponse res) {
        // ruleid: java_cookie_rule-CookieInsecure
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("key", "value");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60);
        res.addCookie(cookie);
    }

    protected void safeJavax(javax.servlet.http.HttpServletResponse response) {
        // rule ok: java_cookie_rule-CookieInsecure
        javax.servlet.http.Cookie myCookie = new javax.servlet.http.Cookie("key", "value");
        myCookie.setSecure(true);
        myCookie.setMaxAge(60);
        response.addCookie(myCookie);
    }

    protected void dangerJakarta(jakarta.servlet.http.HttpServletResponse response) {
        // ruleid: java_cookie_rule-CookieInsecure
        jakarta.servlet.http.Cookie myCookie = new jakarta.servlet.http.Cookie("key", "value");
        myCookie.setSecure(false);
        myCookie.setMaxAge(60);
        response.addCookie(myCookie);
    }

    protected void danger2Jakarta(jakarta.servlet.http.HttpServletResponse response) {
        // ruleid: java_cookie_rule-CookieInsecure
        jakarta.servlet.http.Cookie myCookie = new jakarta.servlet.http.Cookie("key", "value");
        myCookie.setMaxAge(60);
        response.addCookie(myCookie);
    }

    protected void safeJakarta(jakarta.servlet.http.HttpServletResponse response) {
        // rule ok: java_cookie_rule-CookieInsecure
        jakarta.servlet.http.Cookie myCookie = new jakarta.servlet.http.Cookie("key", "value");
        myCookie.setSecure(true);
        myCookie.setMaxAge(60);
        response.addCookie(myCookie);
    }
}
