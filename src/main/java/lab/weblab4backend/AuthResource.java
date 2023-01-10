package lab.weblab4backend;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lab.weblab4backend.model.User;
import lab.weblab4backend.services.PasswordService;
import lab.weblab4backend.services.TokenService;
import lab.weblab4backend.services.UserService;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

@Path("/auth")
public class AuthResource {

    @POST
    @Produces("application/json")
    public Response authenticateUser(String authData) {
        String phone, password;
        Gson g = new Gson();
        JsonObject jsonObject = new JsonParser().parse(authData).getAsJsonObject();
        phone = jsonObject.get("phone").getAsString();
        password = jsonObject.get("password").getAsString();
        try {
            authenticate(phone, password);
            String token = issueToken(phone);
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void authenticate(String phone, String password) throws Exception {
        phone = phone.replaceAll("[^0-9+]", "");
        UserService userService = new UserService();
        User user = userService.findByPhone(phone);
        if (user != null) {
            if (!user.getPassword().equals(PasswordService.hashFromPassword(password))) {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    private String issueToken(String username) throws Exception {

        byte[] array = new byte[16]; // length is bounded by 7
        new Random().nextBytes(array);
        String token = PasswordService.hashFromPassword(new String(Arrays.toString(array)));

        TokenService tokenService = new TokenService();
        tokenService.add(token);

        return token;

        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    }
}
