package Date29_05.model;

import java.time.LocalDateTime;

public class Product {
    private Long id;
    String name;
    double price;
    LocalDateTime dateTime;
    Category category;

    public Product(Long id, String name, double price, LocalDateTime dateTime, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dateTime = dateTime;
        this.category = category;
    }

    public Product(Long id, String name, double price, LocalDateTime dateTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
