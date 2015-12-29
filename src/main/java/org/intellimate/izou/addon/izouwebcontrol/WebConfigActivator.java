package org.intellimate.izou.addon.izouwebcontrol;

import org.intellimate.izou.addon.izouwebcontrol.webserver.WebServer;
import org.intellimate.izou.sdk.Context;
import org.intellimate.izou.sdk.activator.Activator;
import org.intellimate.izou.sdk.util.ResourceUser;

/**
 * The activator starts the addOn. The addOn will run like a daemon in linux in the background of Izou and provide an
 * easy to use interface via a web server to configure izou.
 */
public class WebConfigActivator extends Activator implements ResourceUser {
    private WebServer server;

    /**
     * Creates a new WebConfigActivator
     * @param context the context of the addOn, for more info look at the context classes within izou and the SDKs
     */
    public WebConfigActivator(Context context) {
        super(context, WebConfigActivator.class.getCanonicalName());
    }

    /**
     * Starts the web server through the activator
     */
    @Override
    public void activatorStarts() {
        getContext().getLogger().debug("Starting Izou config server..");
        server = new WebServer(getContext());
        getContext().getLogger().debug("Izou config server running.");

        stop();
    }
}
