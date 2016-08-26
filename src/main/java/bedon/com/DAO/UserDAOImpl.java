package bedon.com.DAO;

import bedon.com.classes.EmailSender;
import bedon.com.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public void sendEmail(User user) {
        String subject = "Your password";
        String to = user.getEmail();
        EmailSender.sendPassword(EmailSender.USER_NAME, EmailSender.PASSWORD, to, subject, user.getPassword());
    }

    @Override
    public User selectUserByEmail(String email) {
        Query query = entityManager.createQuery("select u from User u where u.email like :email");
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public void addUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public boolean checkUser(User user) {
        Query query = entityManager.createQuery("SELECT u FROM User u", User.class);
        List<User> list = (List<User>) query.getResultList();
        for (User u : list) {
            if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword()))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkLogin(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u", User.class);
        List<User> list = (List<User>) query.getResultList();
        for (User u : list) {
            if (login.equals(u.getLogin()))
                return false;
        }
        return true;
    }

    @Override
    public User getUser(String userName) {
        Query query = entityManager.createQuery("select u from User u where u.login like :userName");
        query.setParameter("userName", userName);
        User user = (User) query.getSingleResult();
        return user;
    }
}
