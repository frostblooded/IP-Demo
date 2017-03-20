package bg.elsys.ip.rest.resources;

import bg.elsys.ip.rest.UsersSingleton;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Path("oauth/google")
public class GoogleLogin {
    public final static String CLIENT_ID = "880159598819-t6qjmm204p3u82ugq7mfr3m8rp4l4p9f.apps.googleusercontent.com";

    @POST
    @Path("login")
    public Response requestCode() throws URISyntaxException {
        String outsideUrl = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=" + CLIENT_ID
                + "&response_type=code"
                + "&scope=email"
                + "&access_type=offline"
                + "&redirect_uri=http://localhost:8080/oauth_demo/access_token.html";

        URI uri = UriBuilder.fromUri(outsideUrl).build();
        return Response.seeOther(uri).build();
    }

    @POST
    @Path("logout")
    public Response logout() {
        UsersSingleton.getInstance().setCurrentUser(null);

        String redirectUrl = "http://localhost:8080/oauth_demo/";
        URI uri = UriBuilder.fromUri(redirectUrl).build();
        return Response.seeOther(uri).build();
    }

    @POST
    @Path("receive_email")
    public Response receiveEmail(@FormParam("email") String email,
                                 @FormParam("access_token") String accessToken) {
        UsersSingleton.getInstance().handleGoogleUser(email, accessToken);

        String url = "http://localhost:8080/oauth_demo/";
        URI uri = UriBuilder.fromUri(url).build();
        return Response.seeOther(uri).build();
    }
}
