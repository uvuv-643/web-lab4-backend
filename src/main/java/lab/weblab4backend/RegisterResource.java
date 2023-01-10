package lab.weblab4backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lab.weblab4backend.adapters.TimeAdapter;
import lab.weblab4backend.model.Hit;
import lab.weblab4backend.responses.RegistrationResponse;
import lab.weblab4backend.sequrity.Secured;
import lab.weblab4backend.services.HitService;
import lab.weblab4backend.services.PasswordService;
import lab.weblab4backend.services.SMSService;
import lab.weblab4backend.services.UserService;

import java.time.ZonedDateTime;
import java.util.List;

@Path("/register")
public class RegisterResource {

    private static final SMSService smsService = new SMSService();

    @POST
    @Produces("application/json")
    public Response authenticateUser(String authData) {
        String username, password, phone;
        Gson g = new Gson();
        JsonObject jsonObject = new JsonParser().parse(authData).getAsJsonObject();
        username = jsonObject.get("username").getAsString();
        password = jsonObject.get("password").getAsString();
        phone = jsonObject.get("phone").getAsString().replaceAll("[^0-9+]", "");
        RegistrationResponse response = new RegistrationResponse();
        try {
            UserService userService = new UserService();
            if (smsService.findSMS(phone).getToken().equals(jsonObject.get("code").getAsString())) {
                userService.add(username, password, phone);
                response.setSuccess(true);
            } else {
                response.setSuccess(false);
            }
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (Exception e) {
            response.setSuccess(false);
            return Response.status(Response.Status.OK).entity(response).build();

        }
    }

}