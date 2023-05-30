package Date29_05.service;

import Date29_05.model.Category;
import Date29_05.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManage {
    private final List<Product> products;
    private static ProductManage productManage;

    private ProductManage() {
        products = new ArrayList<>();
    }

    public static ProductManage getInstance() {
        if (productManage == null) {
            productManage = new ProductManage();
        }
        return productManage;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void deleteProduct(Long id) {
        Product product = getById(id);
        if (product != null) {
            products.remove(product);
        }
    }
    public  void deleteByCategory(Category category){
        List<Product> categoryDelete = new ArrayList<>();
        for (Product product: products){
            if (product.getCategory().equals(category)){
                categoryDelete.add(product);
            }
        }
        products.removeAll(categoryDelete);
    }
    public List<Product> searchProduct(String name){
        List<Product> productSearch = new ArrayList<>();
        for (Product product : products){
            if (product.getName().contains(name)){
                productSearch.add(product);
            }
        }
        return productSearch;
    }
}


