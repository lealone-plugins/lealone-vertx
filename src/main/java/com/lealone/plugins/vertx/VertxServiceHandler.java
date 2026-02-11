/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.plugins.vertx;

import java.util.Map;

import com.lealone.common.logging.Logger;
import com.lealone.common.logging.LoggerFactory;
import com.lealone.plugins.service.ServiceHandler;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;

public class VertxServiceHandler extends ServiceHandler implements Handler<SockJSSocket> {

    private static final Logger logger = LoggerFactory.getLogger(VertxServiceHandler.class);

    public VertxServiceHandler(Map<String, String> config) {
        super(config);
    }

    @Override
    public void handle(SockJSSocket sockJSSocket) {
        sockJSSocket.exceptionHandler(t -> {
            logger.error("sockJSSocket exception", t);
        });

        sockJSSocket.handler(buffer -> {
            String command = buffer.getString(0, buffer.length());
            Buffer ret = Buffer.buffer(executeService(command));
            sockJSSocket.end(ret);
        });
    }
}
