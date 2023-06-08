package product_category.controller;

import product_category.service.CartService;
import product_category.service.CategoryService;
import product_category.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = ProductService.getInstance();
    private final CategoryService categoryService = CategoryService.getInstance();
    private final CartService cartService = CartService.getInstance();

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
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/bai_tap_quan/product/home.jsp");
        request.setAttribute("products", productService.findAll());
        requestDispatcher.forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("category", categoryService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/bai_tap_quan/product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long categoryId = Long.parseLong(request.getParameter("category"));
        if (categoryService.checkById(categoryId)) {
            productService.save(request);
            response.sendRedirect("products");
        } else {
            response.sendRedirect("404.jsp");
        }
    }

    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        if (productService.checkById(id)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/bai_tap_quan/product/update.jsp");
            request.setAttribute("product", productService.getById(id));
            request.setAttribute("category", categoryService.findAll());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/bai_tap_quan/404.jsp");
        }
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long categoryId = Long.parseLong(request.getParameter("category"));
        Long id = Long.parseLong(request.getParameter("id"));

        if (productService.checkById(id) && categoryService.checkById(categoryId)) {
            productService.save(request);
            response.sendRedirect("products");
        } else {
            response.sendRedirect("/bai_tap_quan/404.jsp");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productService.delete(request);
        response.sendRedirect("products");
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {;
        request.setAttribute("products", productService.searchName(request));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/bai_tap_quan/product/home.jsp");
        requestDispatcher.forward(request, response);
    }
}
