package io.vertx.ext.web.templ;

public interface LodashTemplateEngine extends TemplateEngine {
    /**
     * Default max number of templates to cache
     */
    int DEFAULT_MAX_CACHE_SIZE = 10000;

    /**
     * Default template extension
     */
    String DEFAULT_TEMPLATE_EXTENSION = "js";

}
