/* JEP 372 - Removal of Nashorn breaks this (kind of) test */

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornExample {

    public static void main(String[] args) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        Object result = engine.eval(
                "var greeting='hello world';" +
                        "print(greeting);" +
                        "greeting");
    }
}

