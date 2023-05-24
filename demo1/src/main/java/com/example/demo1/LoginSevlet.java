package com.example.demo1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginSevlet", value = "/Login")
public class LoginSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "admin".equals(password)) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
//            dispatcher.forward(request,response);
            response.sendRedirect("Admin.jsp");
        } else {
            response.sendRedirect("User.jsp");
        }

    }
}
