package org.intellimate.izou.addon.izouwebcontrol.webserver.RequestHandlers;

import com.google.gson.Gson;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import org.intellimate.izou.addon.izouwebcontrol.webserver.RequestHandler;
import org.intellimate.izou.sdk.Context;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static fi.iki.elonen.SimpleWebServer.newFixedLengthResponse;

/**
 * The DashboardHandler handles the content of the Izou Dashboard. It updates the dashboard continuously through
 * short polling on the client side.
 */
public class TemplateHandler extends RequestHandler {

    /**
     * Creates a new DashboardHandler
     *
     * @param context the context of the addOn
     */
    public TemplateHandler(Context context) {
        super(TemplateHandler.class.getName(), context);
    }

    @Override
    public Response POST(IHTTPSession session) {
        File libLocaion = getContext().getFiles().getLibLocation();

        String[] names = libLocaion.list();

        int i = 0;
        List<JsonObject> jsonList = new ArrayList<>();
        for (String name : names) {
            if (new File(libLocaion.getAbsolutePath() + File.separator + name).isDirectory())  {
                String[] addOnParts = name.split("-");
                String addOnName = addOnParts[0];
                String addOnVersion = addOnParts[1];

                JsonObject jsonObject;
                if (addOnName.toLowerCase().equals("sdk")) {
                    jsonObject = Json.createObjectBuilder()
                            .add("type", "sdk")
                            .add("addOnName", addOnName)
                            .add("version", addOnVersion)
                            .build();
                } else {
                    jsonObject = Json.createObjectBuilder()
                            .add("type", "addOn")
                            .add("addOnName", addOnName)
                            .add("version", addOnVersion)
                            .build();
                }

                jsonList.add(jsonObject);
                i++;
            }
        }

        String jsonData = new Gson().toJson(jsonList);

        return newFixedLengthResponse(Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT, jsonData);
    }

    @Override
    public Response GET(IHTTPSession session) {
        return newFixedLengthResponse(Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT, "success");
    }
}
