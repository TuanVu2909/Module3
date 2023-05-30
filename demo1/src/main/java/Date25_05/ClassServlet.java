package Date25_05;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ClassServlet", value = "/ClassServlet")
public class ClassServlet extends HttpServlet {
    public  ClassManage classManage = new ClassManage();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       request.setAttribute("arrayClass",classManage.classStudents);
       RequestDispatcher requestDispatcher = request.getRequestDispatcher("CreatStudent.jsp");
       requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
