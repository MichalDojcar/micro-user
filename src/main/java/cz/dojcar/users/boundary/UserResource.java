package cz.dojcar.users.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cz.dojcar.users.control.UserConnector;
import cz.dojcar.users.entity.User;

@Stateless
@Path("users")
public class UserResource {

    private UserConnector userConnector;

    public UserResource() {}

    @Inject
    public UserResource(UserConnector userConnector) {
        this.userConnector = userConnector;
    }

    @GET
    @Path("{id}")
    public User getUser(@PathParam("id") Long id) throws Exception {
        if (id == null)
            throw new NotFoundException();

        return userConnector.getById(id);
    }
}
