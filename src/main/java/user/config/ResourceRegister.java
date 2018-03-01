package user.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import user.resource.UserAction;

import javax.ws.rs.ApplicationPath;
@Component
@ApplicationPath("/")
public class ResourceRegister extends ResourceConfig {
    public ResourceRegister(){
        register(UserAction.class);
    }
}
