package product_category.DAO;

import product_category.DAO.connection.MyConnection;
import product_category.model.Cart;
import product_category.model.Product;
import product_category.service.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    private final Connection connection = MyConnection.getConnection();
    private final ProductService productService = ProductService.getInstance();
    public CartDAO() {

    }
    private final String SELECT_ALL = "select * from cart;";
    private final String SELECT_BY_ID = "select * from cart where id =?;";
    private final String INSERT_INTO = "insert into cart(quantity_cart,product_id) value (?,?);";
    private final String UPDATE_CART = "update cart set quantity_cart=?, product_id= ? where id= ?;";
    private final String DELETE_CART = "delete from cart where id =?;";

    public List<Cart> findAll(){
        List<Cart> cartList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                int quantityCart = resultSet.getInt("quantity_cart");
                Long product_id = resultSet.getLong("product_id");
                Product product = productService.getById(product_id);
                Cart cart = new Cart(id,quantityCart,product);
                cartList.add(cart);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cartList;
    }
    public Cart getById(Long id){
        Cart cart = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               int quantity = resultSet.getInt("quantity_cart");
               Long productId = resultSet.getLong("product_id");
               Product product = productService.getById(productId);
               cart = new Cart(quantity,product);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return cart;
    }
    public void addCart (Cart cart){
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setInt(1,cart.getQuantityCart());
            preparedStatement.setLong(2,cart.getProduct().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateCart(Cart cart){
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART)) {
            preparedStatement.setInt(1,cart.getQuantityCart());
            preparedStatement.setLong(2,cart.getProduct().getId());
            preparedStatement.setLong(3,cart.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteCart(Long id){
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART)) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
