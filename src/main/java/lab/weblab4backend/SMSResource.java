package lab.weblab4backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lab.weblab4backend.model.SMS;
import lab.weblab4backend.model.User;
import lab.weblab4backend.responses.SendSmsResponse;
import lab.weblab4backend.services.SMSService;
import lab.weblab4backend.services.UserService;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Random;

@Path("/sms")
public class SMSResource {

    private final static SMSService smsService = new SMSService();
    private final static UserService userService = new UserService();

    private String getBasicAuthentication() {
        try {
            String user = "artem_zinatulin643@mail.ru";
            String password = "wAh-ua-hNqlgQq4xjAzcPyd9n9m08zdB";
            String userAndPassword = user + ":" + password;
            byte[] userAndPasswordBytes = userAndPassword.getBytes(StandardCharsets.UTF_8);
            return "Basic " + Base64.getEncoder().encodeToString(userAndPasswordBytes);
        } catch (Exception ignored) {
            return "";
        }
    }


    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response sendSmsToUser(final SMSRequest request) {
        request.phone = request.phone.replaceAll("[^0-9+]", "");
        User user = userService.findByPhone(request.phone);
        SendSmsResponse response = new SendSmsResponse();
        if (user == null) {
            response.setCorrectPhone(true);
            SMS lastSms = smsService.findSMS(request.phone);
            if (lastSms != null && lastSms.getCreatedAt().plusMinutes(1).compareTo(ZonedDateTime.now()) >= 0) {
                response.setResendAfter(lastSms.getCreatedAt().plusMinutes(1).toEpochSecond() - ZonedDateTime.now().toEpochSecond());
            } else {
                String token = Integer.toString((int)(Math.random() * 9000) + 1000);
                smsService.add(request.phone, token);
                Client client = ClientBuilder.newClient();
                Response responseSms = client
                    .target("https://gate.smsaero.ru/v2/sms/send")
                    .queryParam("number", request.phone)
                    .queryParam("text", "Ваш код для регистрации - " + token)
                    .queryParam("sign", "SMS Aero")
                    .request("application/json")
                    .header(HttpHeaders.AUTHORIZATION, this.getBasicAuthentication())
                    .get();
                response.setStatus(responseSms.getStatus() == 200);
            }
        }
        return Response.status(Response.Status.OK).entity(response).build();
    }
}