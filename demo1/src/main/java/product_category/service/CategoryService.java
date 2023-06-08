package product_category.service;


import product_category.DAO.CategoryDAO;
import product_category.model.Category;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CategoryService {
    private  CategoryDAO categoryDAO;
    private static CategoryService categoryService;

    private CategoryService() {
            categoryDAO = new CategoryDAO();
    }
    public  static CategoryService getInstance(){
        if (categoryService == null){
            categoryService = new CategoryService();
        }
        return categoryService;
    }
    public List<Category> findAll(){
        return categoryDAO.findAll();
    }
    public Category getById(Long id){
        return categoryDAO.findById(id);
    }
    public void save(HttpServletRequest request){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        if(id != null){
            Long idUpdate = Long.parseLong(id);
            categoryDAO.updateCategory(new Category(idUpdate,name));
        }else {
            categoryDAO.addCategory(new Category(name));
        }
    }
    public void deleteCategory(Long id){
        categoryDAO.deleteById(id);
    }
   public boolean checkById(Long id){
        Category category = categoryService.getById(id);
        return category != null;
   }
}
