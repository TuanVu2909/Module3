package Date25_05;

public class Student {
    private  int id;
    private String name;
    ClassStudent classStudent;

    public Student(int id, String name, ClassStudent classStudent) {
        this.id = id;
        this.name = name;
        this.classStudent = classStudent;
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

    public ClassStudent getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(ClassStudent classStudent) {
        this.classStudent = classStudent;
    }

    @Override
    public String toString() {
        return
                 id + ". " + name + ", " + classStudent.getName() ;
    }
}
