package Date29_05.controller;

import Date29_05.model.Category;
import Date29_05.model.Product;
import Date29_05.service.CategoryManage;
import Date29_05.service.ProductManage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/Products")
public class ProductServlet extends HttpServlet {
    private final ProductManage productManage = ProductManage.getInstance();
    private final CategoryManage categoryManage = CategoryManage.getInstance();
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
            case "search":
                search(request, response);
                break;
        }
    }
        private void findAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setAttribute("students", productManage.getProducts());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/home.jsp");
            requestDispatcher.forward(request, response);
        }

        private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setAttribute("categories", categoryManage.getCategories());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/create.jsp");
            requestDispatcher.forward(request, response);
        }

        private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Integer.parseInt(request.getParameter("price"));
            LocalDateTime dateTime = LocalDateTime.now();
            Long categoryId = Long.parseLong(request.getParameter("categoryId"));

            Category category =categoryManage.getById(categoryId);
            if (category != null) {
                Product product = new Product(id,name,price,dateTime);
                productManage.addProduct(product);
                response.sendRedirect("/Category");
            } else {
                response.sendRedirect("/404.jsp");
            }
        }

        private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long id = Long.parseLong(request.getParameter("id"));
            Product product = productManage.getById(id);
            if (product != null) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/update.jsp");
                request.setAttribute("product", product);
                request.setAttribute("category", categoryManage.getCategories());
                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("/404.jsp");
            }
        }

        private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Integer.parseInt(request.getParameter("age"));
            LocalDateTime dateTime = LocalDateTime.now();
            Long categoryId = Long.parseLong(request.getParameter("category"));

            Category category = categoryManage.getById(categoryId);
            Product product = productManage.getById(id);

            if (product != null && category != null) {
                product.setName(name);
                product.setPrice(price);
                product.setDateTime(dateTime);
                response.sendRedirect("/Products");
            } else {
                response.sendRedirect("/404.jsp");
            }
        }

        private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Long id = Long.parseLong(request.getParameter("id"));
            productManage.deleteProduct(id);
            response.sendRedirect("/Categories");
        }

        private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String search = request.getParameter("search");
            List<Product> productList = productManage.searchProduct(search);
            request.setAttribute("product",productList );
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/student/home.jsp");
            requestDispatcher.forward(request, response);
        }
    }


