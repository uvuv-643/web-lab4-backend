package lab.weblab4backend.Providers;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    private final String ALLOWED_ORIGIN = "*";
    private final String ALLOWED_HEADERS = "origin, content-type, accept, authorization";
    private final String ALLOWED_METHODS = "GET, POST, PUT, DELETE, OPTION";
    private final Boolean ALLOW_CREDENTIALS = true;
    private final Integer MAX_AGE = 1209600;

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
        containerResponseContext.getHeaders().add("Access-Control-Allow-Credentials", ALLOW_CREDENTIALS);
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", ALLOWED_METHODS);
        containerResponseContext.getHeaders().add("Access-Control-Max-Age", MAX_AGE);
    }

}
