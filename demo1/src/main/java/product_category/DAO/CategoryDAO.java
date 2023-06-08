package product_category.DAO;

import product_category.DAO.connection.MyConnection;
import product_category.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private final Connection connection ;

    private final String SELECT_ALL = "select * from category;";
    private final String SELECT_BY_ID = "select * from category where id = ?;";
    private final String INSERT_INTO = "insert into category(name) value (?);";
    private final String UPDATE_BY_ID = "update category set name = ? where id = ?;";
    private final String DELETE_BY_ID = "delete from category where id = ?";

    public CategoryDAO() {
        connection = MyConnection.getConnection();
    }

    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Category category = new Category(id,name);
                categoryList.add(category);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return categoryList;
    }

    public Category findById(Long id) {
        Category category = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                category = new Category(id,name);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
       return category;
    }

    public void addCategory(Category category) {
      try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
          preparedStatement.setString(1, category.getName());
          preparedStatement.executeUpdate();
      }catch (SQLException e){
          e.printStackTrace();
      }
    }

    public void updateCategory(Category category) {
       try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID)) {
           preparedStatement.setLong(2,category.getId());
           preparedStatement.setString(1,category.getName());
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


}
