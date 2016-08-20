package bedon.com.DAO;

import bedon.com.entities.MyFile;
import bedon.com.entities.User;

import java.util.List;

/**
 * Created by bedon on 24.07.2016.
 */
public interface MyFileDAO {
    void add(MyFile file);
    List<MyFile> list(User user);
    List<MyFile> getFileByName(User user, String name);
    MyFile getFile(int id);
    void delete(int id);
}
