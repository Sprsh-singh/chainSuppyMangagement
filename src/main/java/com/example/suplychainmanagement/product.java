package com.example.suplychainmanagement;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty quantity;

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    public int getQuantity() {
        return quantity.get();
    }


    public product(int id, String name, double price,int quantity){
        this.id=new SimpleIntegerProperty(id);
        this.name=new SimpleStringProperty(name);
        this.price=new SimpleDoubleProperty(price);
        this.quantity=new SimpleIntegerProperty(quantity);
    }

    public static ObservableList<product> getAllProducts(){
        database Database=new database();
        ObservableList<product> productList= FXCollections.observableArrayList();
        String SelectProducts="SELECT * FROM PRODUCT";
        try {
            ResultSet rs=Database.getQueryTable(SelectProducts);
            while (rs.next()){
                productList.add(new product(rs.getInt("product_id"),
                        rs.getString("productname"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    public static ObservableList<product> getProductsByName(String ProductName){
        database Database=new database();
        ObservableList<product> productList= FXCollections.observableArrayList();
        String SelectProducts=String.format("SELECT * FROM PRODUCT WHERE lower(productname) LIKE '%%%s%%'", ProductName.toLowerCase());
        try {
            ResultSet rs=Database.getQueryTable(SelectProducts);
            while (rs.next()){
                productList.add(new product(rs.getInt("product_id"),
                        rs.getString("productname"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }



}
