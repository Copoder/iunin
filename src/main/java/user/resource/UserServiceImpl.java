package user.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import user.db.UserRepositry;
import user.jwt.JwtTokenUtil;
import user.model.JwtUserFactory;
import user.model.UserInfo;

@Service
public class UserServiceImpl implements UserService{
    AuthenticationManager authenticationManager;
    UserRepositry UserRepositry;
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepositry mUserRepositry,
                           JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.UserRepositry = mUserRepositry;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void registerUser(UserInfo user) {
        if(user.getName().isEmpty() || user.getPwd().isEmpty()){

        }else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            final String rawPassword = user.getPwd();
            user.setPwd(encoder.encode(rawPassword));
            UserRepositry.save(user);
        }
    }

    @Override
    public String login(UserInfo user) {
        String token = "";
        try {

            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(user.getName(),user.getPwd());
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = JwtUserFactory.create(user);
            token = jwtTokenUtil.generateToken(userDetails);
        }catch (BadCredentialsException e){
            return e.toString();
        }
        return token;
    }
}
