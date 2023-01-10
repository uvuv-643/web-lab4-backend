package lab.weblab4backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import jakarta.json.Json;
import jakarta.jws.WebParam;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lab.weblab4backend.adapters.TimeAdapter;
import lab.weblab4backend.model.Hit;
import lab.weblab4backend.sequrity.Secured;
import lab.weblab4backend.services.HitService;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/points")
public class PointResource {

    @GET
    @Secured
    @Produces("application/json")
    public String get() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ZonedDateTime.class, new TimeAdapter());
        Gson g = builder.create();
        HitService hitService = new HitService();
        List<Hit> hits = hitService.getHits();
        return g.toJson(hits);
    }

    @POST
    @Secured
    @Produces("application/json")
    public String post(String text) {
        Gson g = new Gson();
        JsonObject jsonObject = new JsonParser().parse(text).getAsJsonObject();
        HitService hitService = new HitService();
        double x = Double.parseDouble(jsonObject.get("x").getAsString());
        double y = Double.parseDouble(jsonObject.get("y").getAsString());
        double r = Double.parseDouble(jsonObject.get("r").getAsString());
        hitService.add(x, y, r);
        return g.toJson("{success: " + x + "}");
    }

    @DELETE
    @Produces("application/json")
    public String delete() {
        HitService hitService = new HitService();
        hitService.clear();
        Gson g = new Gson();
        return g.toJson("{success: true}");
    }

}