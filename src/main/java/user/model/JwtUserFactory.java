package user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {
    private static final List<String> defaultRoles = new ArrayList<>();
    public static JwtUser create(UserInfo userInfo){
        return new JwtUser(userInfo.getName(),userInfo.getPwd(),mapToGrantedAuthorities(defaultRoles));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    {
        defaultRoles.add("ROLE_USER");
//        defaultRoles.add("ROLE_ADMIN");
    }
}
