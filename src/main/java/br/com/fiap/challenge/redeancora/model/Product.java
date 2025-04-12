package br.com.fiap.challenge.redeancora.model;

public class Product {

    private String id;
    private String name;
    private String description;
    private String brand;
    private String application;
    private String code;
    private double price;

    public Product(String id, String name, String description, String brand, String application, String code, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.application = application;
        this.code = code;
        this.price = price;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public String getApplication() {
        return application;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }
}
