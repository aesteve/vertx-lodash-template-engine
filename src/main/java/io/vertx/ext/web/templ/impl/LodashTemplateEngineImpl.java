package io.vertx.ext.web.templ.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.LodashTemplateEngine;

public class LodashTemplateEngineImpl extends CachingTemplateEngine<String> implements LodashTemplateEngine {

    public LodashTemplateEngineImpl() {
        super(DEFAULT_TEMPLATE_EXTENSION, DEFAULT_MAX_CACHE_SIZE);
    }

    @Override
    public void render(RoutingContext context, String templateFileName, Handler<AsyncResult<Buffer>> handler) {

    }

}
