package org.intellimate.izou.addon.izouwebconfig;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.SimpleWebServer;
import org.intellimate.izou.sdk.Context;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class starts the webserver
 */
public class WebServer extends SimpleWebServer {
    public static final String HOSTNAME = "192.168.1.15";
    public static final int PORT = 7777;
    public static String HOST_PATH;
    private static String HOST_DEV_PATH = "./src/main/resources/html/";

    private Context context;

    /**
     * Creates a new web-server that hosts files in the html folder
     *
     * @param context the context of the addOn
     */
    public WebServer(Context context) {
        super(HOSTNAME, PORT, new File(HOST_DEV_PATH), false);

        this.context = context;

        try {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IOException e) {
            context.getLogger().error("Unable to start izou config server", e);
        }
    }

    /**
     * This server method manages and delegates all POST and GET requests
     *
     * @param session the current http session
     * @return a response from the server (200, 404, etc)
     */
    @Override
    public Response serve (IHTTPSession session) {
        Method method = session.getMethod();

        if (Method.POST.equals(method)) {
            Map<String, String> params = new HashMap<>();
            try {
                session.parseBody(params);
            } catch (IOException | ResponseException e) {
                e.printStackTrace();
            }

            params = session.getParms();

            if (params.get("answer").equals("yes")) {
                return newFixedLengthResponse(Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT, "success");
            } else {
                return newFixedLengthResponse(Response.Status.UNAUTHORIZED, NanoHTTPD.MIME_PLAINTEXT, "error");
            }
        }

        return super.serve(session);
    }
}
