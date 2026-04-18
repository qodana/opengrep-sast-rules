// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=com.amazonaws.aws-java-sdk-simpledb@1.12.187
package inject;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandInjection {

    public void danger(String cmd) throws IOException {
        Runtime r = Runtime.getRuntime();
        String[] cmds = new String[] {
            "/bin/sh",
            "-c",
            cmd
        };

        // ruleid: java_inject_rule-CommandInjection
        r.exec(cmd);
        r.exec(new String[]{"test"});
        // ruleid: java_inject_rule-CommandInjection
        r.exec(new String[]{"bash", cmd},new String[]{});
        // ruleid: java_inject_rule-CommandInjection
        r.exec(new String[]{"bash"+ cmd},new String[]{});

        String tainted = "bash"+ cmd + "test";
        // ruleid: java_inject_rule-CommandInjection
        r.exec(tainted);
        // ruleid: java_inject_rule-CommandInjection
        r.exec(tainted + "custom");
        // ruleid: java_inject_rule-CommandInjection
        r.exec(new String[]{"bash", tainted},new String[]{});
        // ruleid: java_inject_rule-CommandInjection
        r.exec(new String[]{"/bin/sh", "-c" + tainted},new String[]{});

        // ruleid: java_inject_rule-CommandInjection
        r.exec(cmds);
        // ruleid: java_inject_rule-CommandInjection
        r.exec(cmds,new String[]{});
        // ruleid: java_inject_rule-CommandInjection
        r.exec(cmds,new String[]{"test"});

        // ok: java_inject_rule-CommandInjection
        r.exec(new String[]{"ls", "-alh"});
    }

    public void danger2(String cmd) {
        ProcessBuilder b = new ProcessBuilder();
        // ruleid: java_inject_rule-CommandInjection
        b.command(cmd);
        b.command("test");
        // ruleid: java_inject_rule-CommandInjection
        b.command(Arrays.asList("/bin/sh", "-c", cmd));

        String tainted = "test2"+ cmd + "test";
        // ruleid: java_inject_rule-CommandInjection
        b.command("test2"+ cmd + "test");
        // ruleid: java_inject_rule-CommandInjection
        b.command(tainted);
        // ruleid: java_inject_rule-CommandInjection
        b.command(Arrays.asList("/bin/sh", "-c", tainted));

        // ok: java_inject_rule-CommandInjection
        b.command(Arrays.asList("echo", "Hello, World!"));
    }

    public void danger3(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = "";
        if (request.getHeader("Command") != null) {
            param = request.getHeader("Command");
        }

        param = java.net.URLDecoder.decode(param, "UTF-8");

        java.util.List<String> argList = new java.util.ArrayList<String>();

        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            argList.add("cmd.exe");
            argList.add("/c");
        } else {
            argList.add("sh");
            argList.add("-c");
        }
        argList.add("echo " + param);

        ProcessBuilder pb = new ProcessBuilder();

        // ruleid:java_inject_rule-CommandInjection
        pb.command(argList);

        try {
            Process p = pb.start();
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }
}
