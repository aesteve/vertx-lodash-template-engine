import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.impl.Utils;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class TestToRemove {
    public static void main(String... args) throws Exception {
        System.out.println(System.getProperty("java.version"));
        Vertx vertx = Vertx.vertx();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine nashorn = mgr.getEngineByName("nashorn");
        ScriptContext ctx = nashorn.getContext();
        Bindings bindings = ctx.getBindings(ScriptContext.ENGINE_SCOPE);
        nashorn.eval(Utils.readFileToString(vertx, "externallibs/lodash.min.js"), bindings);
        nashorn.eval("load(\"nashorn:mozilla_compat.js\");", bindings);
        String tpl = Utils.readFileToString(vertx, "hello.tpl");
        bindings.put("vertxTemplate", tpl);
        nashorn.eval("compiled = _.template(vertxTemplate);", bindings);
        Object compiled = nashorn.get("compiled");
        System.out.println("compiled = " + compiled.getClass());
        JsonObject json = new JsonObject();
        json.put("name", "world");
        bindings.put("props", json);
        System.out.println(((ScriptObjectMirror) compiled).call(compiled, bindings.get("props")));
    }
}
