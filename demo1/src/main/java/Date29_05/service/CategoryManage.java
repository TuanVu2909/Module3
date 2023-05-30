package Date29_05.service;

import Date29_05.model.Category;
import Date29_05.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryManage {
    private final List<Category> categories;
    private static CategoryManage categoryManage;

    private CategoryManage() {
        categories = new ArrayList<>();
    }

    public static CategoryManage getInstance() {
        if (categoryManage == null) {
            categoryManage = new CategoryManage();
        }
        return categoryManage;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public Category getById(Long id) {
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }

    public void deleteById(Long id) {
        Category category = getById(id);
        if (category != null) {
            categories.remove(category);
        }
    }
}
