package technicalblog.services;

import org.springframework.stereotype.Service;
import technicalblog.model.User;

@Service
public class UserService {

    public boolean login(User user) {
        return user.getUsername().equals("validuser");
    }

}
