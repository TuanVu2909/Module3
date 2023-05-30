package Date25_05;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

import static Date25_05.CreatStudent.studentList;

@WebServlet(name = "DeleteStudent", value = "/DeleteStudent")
public class DeleteStudent extends HttpServlet {
    int index;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            int id = Integer.parseInt(request.getParameter("deleteId"));
            for (int i =0; i< studentList.size(); i++){
                if (id == studentList.get(i).getId()){
                    index = i;
                    studentList.remove(index);
                }
            }
            request.setAttribute("arr",studentList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Danh_Sach_Hoc_Vien.jsp");
            requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }
}
