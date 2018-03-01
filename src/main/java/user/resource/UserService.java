package user.resource;

import user.model.UserInfo;

public interface UserService {
    void registerUser(UserInfo user);

    String login(UserInfo user);
}
