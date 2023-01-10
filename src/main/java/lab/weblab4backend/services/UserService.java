package lab.weblab4backend.services;


import lab.weblab4backend.dao.TokenDao;
import lab.weblab4backend.dao.UserDao;
import lab.weblab4backend.model.Token;
import lab.weblab4backend.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.ZonedDateTime;

public class UserService {

    private final UserDao dao = new UserDao();

    public UserService() {
    }

    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }
    public User findByPhone(String phone) {
        return dao.findByPhone(phone);
    }

    public void add(String username, String password, String phone) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPhone(phone);
            user.setPassword(PasswordService.hashFromPassword(password));
            this.dao.save(user);
        } catch (Exception e) {

        }
    }

}