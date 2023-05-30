package Date25_05;

import java.util.ArrayList;
import java.util.List;

public class ClassManage {
    List<ClassStudent> classStudents = new ArrayList<>();
    public ClassManage(){
        classStudents.add(new ClassStudent(1,"c12"));
        classStudents.add(new ClassStudent(2,"c13"));
        classStudents.add(new ClassStudent(3,"c14"));
        classStudents.add(new ClassStudent(4,"c15"));
    }

    public List<ClassStudent> getClassStudents() {
        return classStudents;
    }

    public void setClassStudents(List<ClassStudent> classStudents) {
        this.classStudents = classStudents;
    }
    public ClassStudent getClassStudent(String name){
        for (ClassStudent classStudent: classStudents){
            if (classStudent.getName().equals(name)){
                return classStudent;
            }
        }
        return  null;
    }
}
