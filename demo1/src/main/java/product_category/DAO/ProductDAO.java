package product_category.DAO;

import product_category.DAO.connection.MyConnection;
import product_category.model.Category;
import product_category.model.Product;
import product_category.service.CategoryService;
import product_category.service.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static ProductDAO productDAO;
    private final Connection connection ;
    private final CategoryService categoryService = CategoryService.getInstance();

    private final String SELECT_ALL = "select * from product;";
    private final String SELECT_BY_ID = "select * from product where id = ?;";
    private final String INSERT_INTO = "insert into product(name,price,quantity,category_id) value (?,?,?,?);";
    private final String UPDATE_BY_ID = "update product set name = ?,price = ?,quantity = ?, category_id = ? where id = ?;";
    private final String DELETE_BY_ID = "delete from product where id = ?";
    private final String SEARCH_NAME = "select * from product where name like ?;";

    public ProductDAO() {
       connection = MyConnection.getConnection();
    }
    public  static ProductDAO getInstance(){
        if (productDAO == null){
            productDAO = new ProductDAO();
        }
        return productDAO;
    }

    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int  quantity = resultSet.getInt("quantity");
                Long categoryId = resultSet.getLong("category_id");
                Category category = categoryService.getById(categoryId) ;
                Product product = new Product(id,name,price,quantity,category);
                productList.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productList;
    }

    public Product findById(Long id) {
        Product product = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int  quantity = resultSet.getInt("quantity");
                Long categoryId = resultSet.getLong("category_id");
                Category category = categoryService.getById(categoryId);
                product = new Product(id,name,price,quantity,category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    public void addProduct(Product product) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setLong(4, product.getCategory().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setLong(5,product.getId());
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setLong(4,product.getCategory().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteById(Long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Product> searchName(String name){
        List<Product> productList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NAME)) {
            preparedStatement.setString(1,"%" + name + "%" );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name1 = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int  quantity = resultSet.getInt("quantity");
                Long categoryId = resultSet.getLong("category_id");
                Category category = categoryService.getById(categoryId);
                Product product = new Product(id,name1,price,quantity,category);
                productList.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productList;
    }

}
