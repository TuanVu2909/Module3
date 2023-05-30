package Date25_05;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Date25_05.CreatStudent.studentList;

@WebServlet(name = "UpdateStudent", value = "/UpdateStudent")
public class UpdateStudent extends HttpServlet {
    public ClassManage classManage = new ClassManage();
    public ClassStudent classStudent;
    int index;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Student student = null;
        int id = Integer.parseInt(request.getParameter("updateId"));
        for (int i =0; i< studentList.size(); i++){
            if (id == studentList.get(i).getId()){
                student=studentList.get(i);
                index = i;
            }
        }
        if (student != null){
            request.setAttribute("student",student);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("UpdateStudent.jsp");
            requestDispatcher.forward(request,response);

        }else {
            response.sendRedirect("Danh_Sach_Hoc_Vien.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentList.get(index).setId(id);
        String name = request.getParameter("name");
        studentList.get(index).setName(name);
        String nameClass = request.getParameter("className");
        classStudent = classManage.getClassStudent(nameClass);
        studentList.get(index).setClassStudent(classStudent);
        request.setAttribute("arr",studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Danh_Sach_Hoc_Vien.jsp");
        dispatcher.forward(request,response);
    }
}
