// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/file/FileDisclosure.java
// hash: a7694d0

package injection;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

// java_inject_rule-FileDisclosureRequestDispatcher
public class FileDisclosureRequestDispatcher extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            String jspFile = request.getParameter("jspFile");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(jspFile);

            // ruleid: java_inject_rule-FileDisclosureRequestDispatcher
            requestDispatcher.include(request, response);

            requestDispatcher = request.getSession().getServletContext().getRequestDispatcher(jspFile);

            // ruleid: java_inject_rule-FileDisclosureRequestDispatcher
            requestDispatcher.forward(request, response);

            // Create a look up table or pull from a data source
            HashMap<String, String> lookupTable = new HashMap<>();
            lookupTable.put("key1", "/ServletSample/simpleForm.jsp");
            // Get user input
            String userInput = request.getParameter("key");
            // Look up resource to redirect to from the user input
            String redirectValue = lookupTable.getOrDefault(userInput, "/ServletSample/index.html");
            // Redirect the user
            // ok: java_inject_rule-FileDisclosureRequestDispatcher
            response.sendRedirect(redirectValue);

            RequestDispatcher requestDispatcherTwo = request.getSession().getServletContext().getRequestDispatcher(redirectValue);
            // ok: java_inject_rule-FileDisclosureRequestDispatcher
            requestDispatcherTwo.forward(request, response);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
