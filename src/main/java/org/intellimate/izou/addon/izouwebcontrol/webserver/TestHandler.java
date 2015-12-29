package org.intellimate.izou.addon.izouwebcontrol.webserver;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;

import java.util.Map;

import static fi.iki.elonen.SimpleWebServer.newFixedLengthResponse;

/**
 * Created by julianbrendl on 12/28/15.
 */
public class TestHandler extends RequestHandler {

    public TestHandler() {
        super(TestHandler.class.getName());
    }

    @Override
    public Response POST(IHTTPSession session) {
            Map<String, String> params = session.getParms();

            if (params.get("answer").equals("yes")) {
                return newFixedLengthResponse(Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT, "success");
            } else {
                return newFixedLengthResponse(Response.Status.UNAUTHORIZED, NanoHTTPD.MIME_PLAINTEXT, "error");
            }
    }

    @Override
    public Response GET(IHTTPSession session) {
        return newFixedLengthResponse(Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT, "success");
    }
}
