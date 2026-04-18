// License: LGPL-3.0 License (c) find-sec-bugs
package file;

import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.apache.commons.io.FilenameUtils.*;

@WebServlet(name = "FilenameUtils", value = "/FilenameUtils")
public class FilenameUtils extends HttpServlet {

    final static String basePath = "/usr/local/lib";

    //Method where input is not sanitized with normalize:

    //Normal Input (opens article as intended):
    //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: article.txt'

    //Malicious Input (returns restricted file):
    //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: ../../passwords.txt'

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Input from From Servlet Param
        String input = req.getHeader("input");

        String method = req.getHeader("method");

        String fullPath = null;

        if(method != null && !method.isEmpty()) {
            switch (method) {
                //Malicious Input (returns restricted file):
                //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: /etc/passwd/' --header 'method: getFullPath'
                case "getFullPath": {
                    //ruleid: java_file_rule-FilenameUtils
                    fullPath = getFullPath(input);
                    break;
                }
                //Malicious Input (returns restricted file):
                //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: /etc/passwd/' --header 'method: getFullPathNoEndSeparator'
                case "getFullPathNoEndSeparator": {
                    //ruleid: java_file_rule-FilenameUtils
                    fullPath = getFullPathNoEndSeparator(input);
                    break;
                }
                //Malicious Input (returns restricted file):
                //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: / /etc/passwd/' --header 'method: getPath'
                case "getPath": {
                    //ruleid: java_file_rule-FilenameUtils
                    fullPath = getPath(input);
                    break;
                }
                //Malicious Input (returns restricted file):
                //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: / /etc/passwd/' --header 'method: getPathNoEndSeparator'
                case "getPathNoEndSeparator": {
                    //ruleid: java_file_rule-FilenameUtils
                    fullPath = getPathNoEndSeparator(input);
                    break;
                }
                //Malicious Input (returns restricted file):
                //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: /etc/passwd' --header 'method: normalize'
                case "normalize": {
                    //ruleid: java_file_rule-FilenameUtils
                    fullPath = normalize(input);
                    break;
                }
                //Malicious Input (returns restricted file):
                //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: /etc/passwd' --header 'method: normalizeNoEndSeparator'
                case "normalizeNoEndSeparator": {
                    //ruleid: java_file_rule-FilenameUtils
                    fullPath = normalizeNoEndSeparator(input);
                    break;
                }
                //Malicious Input (returns restricted file):
                //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: /etc/passwd' --header 'method: removeExtension'
                case "removeExtension": {
                    //ruleid: java_file_rule-FilenameUtils
                    fullPath = removeExtension(input);
                    break;
                }
                //Malicious Input (returns restricted file):
                //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: /etc/passwd' --header 'method: separatorsToSystem'
                case "separatorsToSystem": {
                    //ruleid: java_file_rule-FilenameUtils
                    fullPath = separatorsToSystem(input);
                    break;
                }
                //Malicious Input (returns restricted file):
                //curl --location 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: /etc/passwd' --header 'method: separatorsToUnix'
                case "separatorsToUnix": {
                    //ruleid: java_file_rule-FilenameUtils
                    fullPath = separatorsToUnix(input);
                    break;
                }
                case "concat": {
                    // ruleid: java_file_rule-FilenameUtils
                    fullPath = concat(basePath, input);
                    break;
                }
                default: {
                    input = "article.txt";
                    // ok: java_file_rule-FilenameUtils
                    fullPath = concat(basePath, input);
                    break;
                }

            }
        }

        // Remove whitespaces
        fullPath = fullPath.replace(" ","");

        System.out.println(fullPath);

        // Read the contents of the file
        File file = new File(fullPath);
        String fileContents = FileUtils.readFileToString(file, "UTF-8");

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        // Write the contents to the response output
        writer.write(fileContents);

        writer.close();

    }

    //Method not using user input
    //curl --location --request POST 'http://localhost:8080/ServletSample/FilenameUtils'
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filepath = "article.txt";

        //ok: java_file_rule-FilenameUtils
        String fullPath = concat(basePath, filepath);

        System.out.println(fullPath);

        // Read the contents of the file
        File file = new File(fullPath);
        String fileContents = FileUtils.readFileToString(file, "UTF-8");

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        // Write the contents to the response output
        writer.write(fileContents);

        writer.close();

    }

    //Method where input is sanitized with getName:

    //Normal Input (opens article as intended):
    //curl --location --request PUT 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: article.txt'

    //Malicious Input (causes exception):
    //curl --location --request PUT 'http://localhost:8080/ServletSample/FilenameUtils' --header 'input: ../../passwords.txt'
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String input = req.getHeader("input");

        // Securely combine the user input with the base directory
        input = getName(input); // Extracts just the filename, no paths
        // Construct the safe, absolute path
        //ok: java_file_rule-FilenameUtils
        String safePath = concat(basePath, input);

        System.out.println(safePath);

        // Read the contents of the file
        File file = new File(safePath);
        String fileContents = FileUtils.readFileToString(file, "UTF-8");

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        // Write the contents to the response output
        writer.write(fileContents);

        writer.close();

    }
 
    protected void safe(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        
        String input = req.getHeader("input");

        String base = "/var/app/restricted";
        Path basePath = Paths.get(base);
        // ok: java_file_rule-FilenameUtils
        String inputPath = getPath(input);
        // Resolve the full path, but only use our random generated filename
        Path fullPath = basePath.resolve(inputPath);
        // verify the path is contained within our basePath
        if (!fullPath.startsWith(base)) {
            throw new Exception("Invalid path specified!");
        }
        
        // Read the contents of the file
        File file = new File(fullPath.toString());
        String fileContents = FileUtils.readFileToString(file, "UTF-8");

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        // Write the contents to the response output
        writer.write(fileContents);

        writer.close();

    }
}