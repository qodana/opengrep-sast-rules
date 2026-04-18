// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/script/ScriptEngineSample.java
// hash: a7694d0
package script;

import javax.script.*;

// ref: java_script_rule-ScriptInjection
public class ScriptInjection {
    public static void scripting(String userInput) throws ScriptException {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByExtension("js");
        // ruleid: java_script_rule-ScriptInjection
        Object result = scriptEngine.eval("test=1;" + userInput);

    }

    //The potential injection will require manual review of the code flow but some false positive can be avoid.
    public static void scriptingSafe() throws ScriptException {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByExtension("js");

        String code = "var test=3;test=test*2;";
        // ok: java_script_rule-ScriptInjection
        Object result = scriptEngine.eval(code);
    }

    public void evalWithBindings(String userFirstName, String userLastName) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        // Create bindings to pass into our script, forcing the values to be String.
        Bindings bindings = engine.createBindings();
        bindings.put("fname", new String(userFirstName));
        bindings.put("lname", new String(userLastName));
        // Example script that concatenates a greeting with the user-supplied input first/last name
        String script = "var greeting='Hello ';" +
                // fname and lname variables will be resolved by bindings defined above
                "greeting += fname + ' ' + lname;" +
                // prints greeting
                "greeting";
        // ok: java_script_rule-ScriptInjection
        Object result = engine.eval(script, bindings);
        System.out.println(result);
    }

    public void invokeFunctionUsage(String input) throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("javascript");
        String script = "function greetOne(name) {\n" +
                "    return \"Hello, \" + name + \"!\";\n" +
                "}";
        // Evaluate the JavaScript code
        engine.eval(script);

        // Check if the engine supports the Invocable interface
        if (engine instanceof Invocable) {
            Invocable invocable = (Invocable) engine;
            // Invoke the "greet" function from the JavaScript file
            // ok: java_script_rule-ScriptInjection
            String resultOne = (String) invocable.invokeFunction("greetOne", "John");
            // ruleid: java_script_rule-ScriptInjection
            String resultTwo = (String) invocable.invokeFunction("greetOne", input);
            // Display the result
            System.out.println(resultOne);
            System.out.println(resultTwo);
        }
    }

    public void invokeMethodUsage(String input) throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("javascript");
        String script = "var obj = {\n" +
                "    greetTwo: function (name) {\n" +
                "        return 'Hello, ' + name;\n" +
                "    }\n" +
                "}";
        engine.eval(script);

        // Get the object from the script
        Object obj = engine.get("obj");
        // Check if the engine supports the Invocable interface
        if (engine instanceof Invocable) {
            Invocable invocable = (Invocable) engine;
            // ok: java_script_rule-ScriptInjection
            Object resultOne = invocable.invokeMethod(obj, "greetTwo", "World");
            // ruleid: java_script_rule-ScriptInjection
            Object resultTwo = invocable.invokeMethod(obj, "greetTwo", input);
            // Print the result
            System.out.println(resultOne);
            System.out.println(resultTwo);
        }
    }
}
