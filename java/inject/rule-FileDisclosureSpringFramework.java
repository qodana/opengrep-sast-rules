// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/file/FileDisclosure.java
// hash: a7694d0

package injection;

import java.io.IOException;
import org.apache.struts.action.ActionForward;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

// REQUESTDISPATCHER_FILE_DISCLOSURE
public class FileDisclosureSpringFramework extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            String returnURL = request.getParameter("returnURL");

            /******Spring ModelAndView vulnerable code tests******/
            // ruleid: java_inject_rule-FileDisclosureSpringFramework
            ModelAndView mv = new ModelAndView(returnURL); //BAD

            // ruleid: java_inject_rule-FileDisclosureSpringFramework
            ModelAndView mv2 = new ModelAndView(returnURL, new HashMap()); //BAD

            // ruleid: java_inject_rule-FileDisclosureSpringFramework
            ModelAndView mv3 = new ModelAndView(returnURL, "modelName", new Object()); //BAD

            ModelAndView mv4 = new ModelAndView();
            // ruleid: java_inject_rule-FileDisclosureSpringFramework
            mv4.setViewName(returnURL); //BAD

            // ok: java_inject_rule-FileDisclosureSpringFramework
            ModelAndView mv5 = new ModelAndView("viewName", returnURL, new Object()); //OK

            // Create a look up table or pull from a data source
            HashMap<String, String> lookupTable = new HashMap<>();
            lookupTable.put("key1", "/ServletSample/simpleForm.jsp");
            // Look up resource to redirect to from the user input
            String redirectValue = lookupTable.getOrDefault(returnURL, "/ServletSample/index.html");
            ModelAndView mv6 = new ModelAndView();
            // ok: java_inject_rule-FileDisclosureSpringFramework
            mv6.setViewName(redirectValue);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
