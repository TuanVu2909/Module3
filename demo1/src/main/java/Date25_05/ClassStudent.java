package Date25_05;

import java.util.List;

public class ClassStudent {
    private int id;
    private String name;

    public ClassStudent() {

    }

    public ClassStudent(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "ClassStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
