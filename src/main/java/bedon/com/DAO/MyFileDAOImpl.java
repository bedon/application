package bedon.com.DAO;

import bedon.com.entities.MyFile;
import bedon.com.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MyFileDAOImpl implements MyFileDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public MyFile getFile(int id) {
        try {
            return entityManager.find(MyFile.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try {
            entityManager.getTransaction().begin();
            MyFile file = entityManager.find(MyFile.class, id);
            File f = new File(file.getPath());
            f.delete();
            entityManager.remove(file);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void add(MyFile file) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(file);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<MyFile> list(User user) {
        Query query = entityManager.createQuery("SELECT file FROM MyFile file where file.user.id = :id", MyFile.class);
        query.setParameter("id", user.getId());
        return (List<MyFile>) query.getResultList();
    }

    @Override
    public List<MyFile> getFileByName(User user, String name) {
        List<MyFile> fileList;

        Query query = entityManager.createQuery("SELECT file FROM MyFile file where file.user.id = :id", MyFile.class);
        query.setParameter("id", user.getId());
        fileList = (List<MyFile>) query.getResultList();

        List<MyFile> fileList1 = new LinkedList<MyFile>();

        for (MyFile file : fileList){
            if (file.getName().equals(name))
                fileList1.add(file);
        }

        return fileList1;
    }
}
