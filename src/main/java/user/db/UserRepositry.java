package user.db;

import org.springframework.data.jpa.repository.JpaRepository;
import user.model.UserInfo;

import java.util.List;

public interface UserRepositry extends JpaRepository<UserInfo,Long>{
    UserInfo findByname(String name);
}
