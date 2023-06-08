package product_category.service;

import product_category.DAO.ProductDAO;
import product_category.model.Category;
import product_category.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProductService {
    private static ProductDAO productDAO = ProductDAO.getInstance();
    private static ProductService productService;
    private static CategoryService categoryService = CategoryService.getInstance();

    private ProductService() {
        productDAO = new ProductDAO();
    }

    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public Product getById(Long id) {
        return productDAO.findById(id);
    }

    public void save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Long categoryId = Long.parseLong(request.getParameter("category"));
        Category category = categoryService.getById(categoryId);
        if (id != null) {
            Long idUpdate = Long.parseLong(id);
            productDAO.updateProduct(new Product(idUpdate,name,price,quantity,category));
        } else {
            productDAO.addProduct(new Product(name,price,quantity,category));
        }
    }

    public void delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (id != null) {
            Long idDelete = Long.parseLong(id);
            productDAO.deleteById(idDelete);
        }
    }

    public List<Product> searchName(HttpServletRequest request) {
        String name = request.getParameter("name");
        return productDAO.searchName(name);
    }
    public boolean checkById(Long id) {
        Product product = productDAO.findById(id);
        return product != null;
    }
}
