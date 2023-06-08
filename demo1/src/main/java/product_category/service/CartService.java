package product_category.service;

import product_category.DAO.CartDAO;

import product_category.model.Cart;
import product_category.model.Category;
import product_category.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    private static CartService cartService;
    private final ProductService productService = ProductService.getInstance();
    private CartDAO cartDAO;
    private List<Cart> cartList;

    private CartService() {
        cartList = new ArrayList<>();
    }

    public static CartService getInstance() {
        if (cartService == null) {
            cartService = new CartService();
        }
        return cartService;
    }

    public List<Cart> findAll() {
        return cartDAO.findAll();
    }

    public Cart getById(Long id) {
        return cartDAO.getById(id);
    }

    public void addCart(HttpServletRequest request) {
        List<Cart> cartList = new ArrayList<>();
        int quantityId = Integer.parseInt(request.getParameter("name"));
        Long productId = Long.getLong(request.getParameter("product"));
        Product product = productService.getById(productId);
        Cart cart = new Cart(quantityId,product);
        cartList.add(cart);
    }
    public void updateCard(HttpServletRequest request) {
        List<Cart> cartList = new ArrayList<>();
        String id = request.getParameter("id");
        int quantityId = Integer.parseInt(request.getParameter("name"));
        Long productId = Long.getLong(request.getParameter("product"));
        Product product = productService.getById(productId);
        if (id != null){
            Long updateId = Long.parseLong(id);
            cartList.add(new Cart(updateId,quantityId,product));
        }
    }

    public void deleteCart(Long id) {
        cartDAO.deleteCart(id);
    }
    public boolean checkById(Long id){
        Cart cart = cartService.getById(id);
        return cart != null;
    }
}
