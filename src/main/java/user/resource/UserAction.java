package user.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user.model.UserInfo;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
@Component
@Path("/user")
@Consumes(APPLICATION_JSON)
//@Produces(APPLICATION_JSON)
public class UserAction {
    @Autowired
    UserService userService;
    @POST
    @Path("register")
    public String register(UserInfo user){
        userService.registerUser(user);
        return "";
    }
    @POST
    @Path("login")
    @Produces(APPLICATION_JSON)
    public String login(UserInfo user)
    {
        return userService.login(user);
    }
    @GET
    @Path("test")
    public String test(){
        return "success";
    }


}
