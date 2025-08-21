package com.samrad.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String image;
    private String gender;
    private String category;

    public Product(int id, String name, double price, String image, String gender, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.gender = gender;
        this.category = category;
    }

    // getter و setter ها
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
