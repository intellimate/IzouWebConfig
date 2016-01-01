package org.intellimate.izou.addon.izouwebcontrol.webserver;

import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import org.intellimate.izou.sdk.Context;


/**
 * A RequestHandler takes a request from the web-server and executes it. This is pretty much the master-worker design
 * pattern where a request handler is the worker and the web server the master.
 */
public abstract class RequestHandler {
    private String subclassName;
    private Context context;

    /**
     * Creates a new RequestHandler object
     *
     * @param className the name of the subclass that is extending this RequestHandler
     * @param context the context of the addOn
     */
    public RequestHandler(String className, Context context) {
        subclassName = className;
        this.context = context;
    }

    /**
     * This method executes the POST request of an HTML request
     *
     * @param session The session of then request
     * @return a response (for example 200, 404 etc.)
     */
    public abstract Response POST(IHTTPSession session);

    /**
     * This method executes the GET request of an HTML request
     *
     * @param session The session of then request
     * @return a response (for example 200, 404 etc.)
     */
    public abstract Response GET(IHTTPSession session);

    /**
     * Gets the actual name of the subclass, not the full name but just the name (ex. not com.example.MyClass.java, but
     * just MyClass.java
     *
     * @return the actual name of the subclass, not the full name but just the name (ex. not com.example.MyClass.java,
     * but just MyClass.java
     */
    public String getClassName() {
        String[] nameParts = subclassName.split("\\.");
        return nameParts[nameParts.length - 1] + ".java";
    }

    /**
     * Gets the context of this addOn
     *
     * @return the context of this addOn
     */
    public Context getContext() {
        return context;
    }
}
