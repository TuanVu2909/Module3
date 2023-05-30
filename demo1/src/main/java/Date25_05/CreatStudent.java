package Date25_05;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletStudent", value = "/view")
public class CreatStudent extends HttpServlet {
   public static List<Student> studentList = new ArrayList<>();
   public  ClassManage classManage = new ClassManage();
   public  ClassStudent classStudent;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Student student = new Student(1,"vũ",new ClassStudent(1,"c12"));
        studentList.add(student);
        request.setAttribute("arr",studentList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Danh_Sach_Hoc_Vien.jsp");
        requestDispatcher.forward(request,response);
    }
    public  void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("createId"));
        String name = request.getParameter("name");
        String className = request.getParameter("className");
        classStudent = classManage.getClassStudent(className);
        studentList.add(new Student(id,name,classStudent));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        create(request,response);
        request.setAttribute("arr",studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Danh_Sach_Hoc_Vien.jsp");
        dispatcher.forward(request,response);
    }
}
