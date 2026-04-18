// License: LGPL-3.0 License (c) find-sec-bugs
package inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpotbugsPathTraversalAbsolute extends HttpServlet {

    public String get_file(Model model, String input) {
        String input_file_name = "somedir/"+input;

        // ok: java_inject_rule-SpotbugsPathTraversalAbsolute
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(input_file_name); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
        return "something";
    }

    @GetMapping("/somepath")
    public String get_image(Model model, @RequestParam String input) {
        String input_file_name = "somedir/"+input;

        // ok: java_inject_rule-SpotbugsPathTraversalAbsolute
        InputStream staticFile = getClass().getClassLoader().getResourceAsStream("static_file.xml"); // should not match

        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(input_file_name); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        URL loaderInput = getClass().getClassLoader().getResource(input_file_name); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
        
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        InputStream classStream = getClass().getResourceAsStream(input_file_name); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        URL resourceInput = getClass().getResource(input_file_name); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        // ... read file ...
        return "something";
    }

    // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("input");
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new File(input + "/abs/path"); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
    }

    // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    protected void danger2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input1 = req.getParameter("input1");
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new File(input1); // BAD
    }

    protected void danger3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, URISyntaxException {
        String input = req.getParameter("test");
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new File(input);
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new File("test/" + input, "misc.jpg"); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new RandomAccessFile(input, "r"); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new File(new URI(input)); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL

        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new FileReader(input); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new FileInputStream(input); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL

        // false positive test
        // ok: java_inject_rule-SpotbugsPathTraversalAbsolute
        new RandomAccessFile("safe", input);
        // ok: java_inject_rule-SpotbugsPathTraversalAbsolute
        new FileWriter("safe".toUpperCase());
        // ok: java_inject_rule-SpotbugsPathTraversalAbsolute
        new File(new URI("safe"));

        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        File.createTempFile(input, "safe"); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        File.createTempFile("safe", input); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        File.createTempFile("safe", input, new File("safeDir")); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    }

    // nio path traversal
    // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    public void loadFile(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getParameter("test");
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Paths.get(path);
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Paths.get(path,"foo");
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Paths.get(path,"foo", "bar");
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Paths.get("foo", path);
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Paths.get("foo", "bar", path);

        // ok: java_inject_rule-SpotbugsPathTraversalAbsolute
        Paths.get("foo");
        // ok: java_inject_rule-SpotbugsPathTraversalAbsolute
        Paths.get("foo","bar");
        // ok: java_inject_rule-SpotbugsPathTraversalAbsolute
        Paths.get("foo","bar", "allsafe");

    }

    // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    public void tempDir(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String input = req.getParameter("test");
        Path p = Paths.get("/");

        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Files.createTempFile(p,input,"");
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Files.createTempFile(p,"",input);
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Files.createTempFile(input,"");
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Files.createTempFile("", input);

        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Files.createTempDirectory(p,input);
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        Files.createTempDirectory(input);
    }

    // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    public void writer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String input = req.getParameter("test");
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new FileWriter(input);
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new FileWriter(input, true);
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new FileOutputStream(input);
        // ruleid: java_inject_rule-SpotbugsPathTraversalAbsolute
        new FileOutputStream(input, true);
    }


}
