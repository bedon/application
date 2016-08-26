package bedon.com.DAO;

import bedon.com.entities.User;

public interface UserDAO {
    void addUser(User user);
    boolean checkUser(User user);
    boolean checkLogin(String userName);
    User getUser(String userName);
    void sendEmail(User user);
    User selectUserByEmail(String email);
}
