package com.example.coffeetime_admin.model;

public class Product {

    String uid;
    String price;
    String name;
    String category;
    String photoURl;
    String stock;

    public Product() {
        this.uid = "";
        this.price = "0.0";
        this.name = "";
        this.category = "1";
        this.photoURl = "2";
        this.stock = "";
    }

    public Product(String uid, String price, String name, String category, String photoURl, String stock) {
        this.uid = uid;
        this.price = price;
        this.name = name;
        this.category = category;
        this.photoURl = photoURl;
        this.stock = stock;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String  getPrice() {
        return price;
    }

    public void setPrice(String  price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhotoURI() {
        return photoURl;
    }

    public void setPhotoURl(String photoURl) {
        this.photoURl = photoURl;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String  stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return  name  +"  S/.'"+ price;
    }


}
