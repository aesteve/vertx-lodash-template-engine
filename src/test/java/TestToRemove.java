import java.util.HashMap;
import java.util.Map;

import io.vertx.core.Vertx;
import io.vertx.ext.web.impl.Utils;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class TestToRemove {
    public static void main(String... args) throws Exception {
        Vertx vertx = Vertx.vertx();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine nashorn = mgr.getEngineByName("nashorn");
        ScriptContext ctx = nashorn.getContext();
        Bindings bindings = ctx.getBindings(ScriptContext.ENGINE_SCOPE);
        nashorn.eval(Utils.readFileToString(vertx, "externallibs/lodash.min.js"), bindings);
        String tpl = Utils.readFileToString(vertx, "hello.tpl");
        bindings.put("vertxTemplate", tpl);
        nashorn.eval("compiled = _.template(vertxTemplate);", bindings);
        Object compiled = nashorn.get("compiled");
        System.out.println("compiled = " + compiled.getClass());
        Map<String, String> props = new HashMap<String, String>();
        props.put("name", "world");
        bindings.put("props", props);
        Object propsJs = nashorn.eval("var MapType = Java.type('java.util.HashMap');new MapType(props);", bindings);
        System.out.println(propsJs.getClass());
        System.out.println(((ScriptObjectMirror) compiled).call(compiled, propsJs));
    }
}
