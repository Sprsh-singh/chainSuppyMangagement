package com.example.suplychainmanagement;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetail {

    public TableView<product> productTable;

    public Pane getAllProducts(){
        TableColumn id=new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name=new TableColumn("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price=new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn quantity=new TableColumn("quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

 //       ObservableList<product> data= FXCollections.observableArrayList();
//        data.add(new product(1,"TATA",1,800000.00));
//        data.add(new product(1,"Suzuki",1,900000.00));

        ObservableList<product> products=product.getAllProducts();


        productTable=new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price,quantity);
        productTable.setMinSize(supplychain.width, supplychain.hieght);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane TablePane=new Pane();
        TablePane.setMinSize(supplychain.width, supplychain.hieght);
        TablePane.getChildren().add(productTable);
        return TablePane;

    }

    public Pane getProductsByName(String ProductName){
        TableColumn id=new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name=new TableColumn("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price=new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn quantity=new TableColumn("quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //       ObservableList<product> data= FXCollections.observableArrayList();
//        data.add(new product(1,"TATA",1,800000.00));
//        data.add(new product(1,"Suzuki",1,900000.00));

        ObservableList<product> products=product.getProductsByName(ProductName);


        productTable=new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price,quantity);
        productTable.setMinSize(supplychain.width, supplychain.hieght);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane TablePane=new Pane();
        TablePane.setMinSize(supplychain.width, supplychain.hieght);
        TablePane.getChildren().add(productTable);
        return TablePane;

    }

    public product getSelectedProduct(){
        try {
            product SelectedProduct=productTable.getSelectionModel().getSelectedItem();
            return SelectedProduct;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
