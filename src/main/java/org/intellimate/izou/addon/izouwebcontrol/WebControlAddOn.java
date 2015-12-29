package org.intellimate.izou.addon.izouwebcontrol;

import org.intellimate.izou.activator.ActivatorModel;
import org.intellimate.izou.addon.izouwebcontrol.webserver.WebServer;
import org.intellimate.izou.events.EventsControllerModel;
import org.intellimate.izou.output.OutputExtensionModel;
import org.intellimate.izou.output.OutputPluginModel;
import org.intellimate.izou.sdk.addon.AddOn;
import org.intellimate.izou.sdk.contentgenerator.ContentGenerator;
import ro.fortsoft.pf4j.Extension;

import java.io.File;

/**
 * Example addOn for Izou, PLEASE DELETE THIS PACKAGE ON FINAL DISTRIBUTION. It is only meant as a model for
 * how addOns are structured. It is not meant to be included in the final addOn. 
 */
@Extension
public class WebControlAddOn extends AddOn {
    /**
     * The ID of the WebConfigAddOn
     */
    private static final String ID = WebControlAddOn.class.getCanonicalName();

    /**
     * The default constructor for AddOns
     */
    public WebControlAddOn() {
        super(ID);
    }

    /**
     * This method gets called before registering
     */
    @Override
    public void prepare() {
        WebServer.HOST_PATH = getContext().getFiles().getLibLocation() +
                getContext().getAddOn().getPlugin().getPluginPath() + File.separator + "classes" + File.separator
                + "html" + File.separator;
    }

    /**
     * Use this method to register (if needed) your Activators.
     *
     * @return Array containing Instances of Activators
     */
    @Override
    public ActivatorModel[] registerActivator() {
        ActivatorModel[] activators = new ActivatorModel[1];
        activators[0] = new WebControlActivator(getContext());
        return activators;
    }

    /**
     * Use this method to register (if needed) your ContentGenerators.
     *
     * @return Array containing Instances of ContentGenerators
     */
    @Override
    public ContentGenerator[] registerContentGenerator() {
        return null;
    }

    /**
     * Use this method to register (if needed) your EventControllers.
     *
     * @return Array containing Instances of EventControllers
     */
    @Override
    public EventsControllerModel[] registerEventController() {
        return null;
    }

    /**
     * Use this method to register (if needed) your OutputPlugins.
     *
     * @return Array containing Instances of OutputPlugins
     */
    @Override
    public OutputPluginModel[] registerOutputPlugin() {
        return null;
    }

    /**
     * Use this method to register (if needed) your Output.
     *
     * @return Array containing Instances of OutputExtensions
     */
    @Override
    public OutputExtensionModel[] registerOutputExtension() {
       return null;
    }
}
