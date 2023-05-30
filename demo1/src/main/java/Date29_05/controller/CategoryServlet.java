package Date29_05.controller;

import Date29_05.model.Category;
import Date29_05.service.CategoryManage;
import Date29_05.service.ProductManage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CategoryServlet", urlPatterns = "/Categories")
public class CategoryServlet extends HttpServlet {
    public class ClassesServlet extends HttpServlet {

        private final CategoryManage categoryManage = CategoryManage.getInstance();
        private final ProductManage productManage = ProductManage.getInstance();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    createGet(request, response);
                    break;
                case "update":
                    updateGet(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
                default:
                    findAdd(request, response);
            }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    createPost(request, response);
                    break;
                case "update":
                    updatePost(request, response);
                    break;
            }
        }

        private void findAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/home.jsp");
            request.setAttribute("categories", categoryManage.getCategories());
            requestDispatcher.forward(request, response);
        }

        private void createGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.sendRedirect("/category/create.jsp");
        }

        private void createPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");

            Category category = new Category(id, name);
            categoryManage.addCategory(category);
            response.sendRedirect("/category");
        }

        private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long id = Long.parseLong(request.getParameter("id"));

            Category category = categoryManage.getById(id);

            if (category != null) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/update.jsp");
                request.setAttribute("categories", category);
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("/404.jsp");
            }
        }

        private void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");

            Category classes = categoryManage.getById(id);

            if (classes != null) {
                classes.setName(name);
                response.sendRedirect("/category");
            } else {
                response.sendRedirect("/404.jsp");
            }
        }

        private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Long id = Long.parseLong(request.getParameter("id"));

            Category category = categoryManage.getById(id);
            if (category != null) {
                categoryManage.deleteById(id);
                productManage.deleteByCategory(category);
                response.sendRedirect("/category");
            } else {
                response.sendRedirect("/404.jsp");
            }
        }
    }
}
