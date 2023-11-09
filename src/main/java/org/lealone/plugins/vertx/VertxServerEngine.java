/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package org.lealone.plugins.vertx;

import org.lealone.plugins.service.http.HttpServerEngine;
import org.lealone.server.ProtocolServer;

public class VertxServerEngine extends HttpServerEngine {

    public static final String NAME = "Vertx";

    public VertxServerEngine() {
        super(NAME);
    }

    @Override
    protected ProtocolServer createProtocolServer() {
        return new VertxServer();
    }
}
