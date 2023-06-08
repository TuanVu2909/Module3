package product_category.model;

public class Cart {
    Long id;
    int quantityCart;
    Product product;

    public Cart(Long id, int quantityCart, Product product) {
        this.id = id;
        this.quantityCart = quantityCart;
        this.product = product;
    }

    public Cart( int quantityCart, Product product) {
        this.quantityCart = quantityCart;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantityCart() {
        return quantityCart;
    }

    public void setQuantityCart(int quantityCart) {
        this.quantityCart = quantityCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
