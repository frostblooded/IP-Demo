package bg.elsys.ip.rest.resources;

import bg.elsys.ip.rest.User;
import bg.elsys.ip.rest.UsersSingleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public class UserResources {
    @GET
    @Path("current")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentUser() {
        User currentUser = UsersSingleton.getInstance().getCurrentUser();
        return Response.ok(currentUser).build();
    }
}
