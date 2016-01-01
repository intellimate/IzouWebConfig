package org.intellimate.izou.addon.izouwebcontrol.webserver;

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
    private static String HOSTNAME;
    private static boolean HOSTNAME_SET = false;
    private static String HOST_PATH;
    private static boolean HOST_PATH_SET = false;
    public static final int PORT = 7777;
    private static String HOST_DEV_PATH = "./src/main/html/";


    private HashMap<String, RequestHandler> requestHandlers;
    private Context context;

    /**
     * Creates a new web-server that hosts files in the html folder
     *
     * @param context the context of the addOn
     */
    public WebServer(Context context) {
        super(HOSTNAME, PORT, new File(HOST_DEV_PATH), false);

        this.context = context;

        requestHandlers = new HashMap<>();
        IzouDashboardHandler izouDashboardHandler = new IzouDashboardHandler(context);
        requestHandlers.put(izouDashboardHandler.getClassName(), izouDashboardHandler);

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
            // Get parameters of request
            Map<String, String> params = new HashMap<>();
            try {
                session.parseBody(params);
            } catch (IOException | ResponseException e) {
                e.printStackTrace();
            }

            // Delegate request to correct class
            String requestHandlerName = session.getUri();
            requestHandlerName = requestHandlerName.substring(1);
            RequestHandler requestHandler = requestHandlers.get(requestHandlerName);

            // If the request handler was specified, do the default stuff
            if (requestHandler != null) {
                return requestHandler.POST(session);
            } else {
                return super.serve(session);
            }
        }

        if (Method.GET.equals(method)) {
            // Get parameters of request
            Map<String, String> params = new HashMap<>();
            try {
                session.parseBody(params);
            } catch (IOException | ResponseException e) {
                e.printStackTrace();
            }

            // Delegate request to correct class
            String requestHandlerName = session.getUri();
            requestHandlerName = requestHandlerName.substring(1);
            RequestHandler requestHandler = requestHandlers.get(requestHandlerName);

            // If the request handler was specified, do the default stuff
            if (requestHandler != null) {
                return requestHandler.GET(session);
            } else {
                return super.serve(session);
            }
        }

        return super.serve(session);
    }

    /**
     * This is a one time setter for the hostname, which makes hostname effectively final
     *
     * @param hostname the location where the server should host the website (usually your own ip)
     */
    public static void setHOSTNAME(String hostname) {
        if (!HOSTNAME_SET) {
            HOSTNAME_SET = true;
            HOSTNAME = hostname;
        }
    }

    /**
     * This is a one time setter for the hosting path, which makes the hosting path effectively final
     *
     * @param hostPath the location of the folder that the server should serve (where the index.html file is located)
     */
    public static void setHOST_PATH(String hostPath) {
        if (!HOST_PATH_SET) {
            HOST_PATH_SET = true;
            HOSTNAME = hostPath;
        }
    }
}
