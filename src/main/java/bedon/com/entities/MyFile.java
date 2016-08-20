package bedon.com.entities;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class MyFile {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String originalName;
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public MyFile() {
    }

    public MyFile(String name, String originalName, String path) {
        this.name = name;
        this.originalName = originalName;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
