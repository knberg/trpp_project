package barbershop.service;

import barbershop.dao.entity.User;
import barbershop.dao.entity.UserRegistration;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getByLogin(String login);
    void createUser(UserRegistration registration, String cookie);
}
