package lab.weblab4backend.sequrity;

import jakarta.annotation.Priority;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import lab.weblab4backend.dao.TokenDao;
import lab.weblab4backend.model.Token;
import org.hibernate.internal.util.ZonedDateTimeComparator;

import java.io.IOException;
import java.time.ZonedDateTime;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
       String token = authorizationHeader.substring("Bearer".length()).trim();
        try {
            validateToken(token);
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void validateToken(String token) throws Exception {
        TokenDao tokenDao = new TokenDao();
        Token token1 = tokenDao.findByToken(token);
        if (token1 == null) {
            throw new Exception();
        }
        if (token1.getCreatedAt().compareTo(ZonedDateTime.now()) <= 0) {
            throw new Exception();
        }
    }
}