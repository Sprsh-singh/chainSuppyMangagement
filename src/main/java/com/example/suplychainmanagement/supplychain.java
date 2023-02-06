package com.example.suplychainmanagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.jar.Attributes;

public class supplychain extends Application {

    public static final int hieght=400, width=700, headerbar=70;

    GridPane bodyPane=new GridPane();
    Login login=new Login();

    database Database=new database();

    ProductDetail productDetail=new ProductDetail();

    Button globalLoginButton;
    Label globalMessageLabel;

    String customerEmail=null;

    private GridPane headerBar(){ //headerbar creation
        TextField searchField=new TextField();
        Button searchButton=new Button("Search");
        globalLoginButton=new Button("login");
        globalMessageLabel=new Label();

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String ProductName=searchField.getText();
                productDetail.getProductsByName(ProductName);

                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetail.getProductsByName(ProductName));
            }
        });

        globalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(LoginPage());
                globalLoginButton.setDisable(true);
            }
        });


        GridPane gridPane=new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),headerbar);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
//        gridPane.setStyle("-fx-background-color:#C0C0C0");
        gridPane.setAlignment(Pos.BOTTOM_LEFT);
        gridPane.add(searchField,0,1);
        gridPane.add(searchButton,1,1);
        gridPane.add(globalLoginButton,90,0);
        gridPane.add(globalMessageLabel,32,0);

        return gridPane;
    }


    private GridPane LoginPage(){ //main body
        Label emaillabel=new Label("Email");
        Label passwordlabel=new Label("Password");

        TextField emailField=new TextField();
        PasswordField passwordField=new PasswordField();
        Button loginButton=new Button("Login");
        Label messageLabel=new Label();
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email=emailField.getText();
                String password=passwordField.getText();
                customerEmail=email;

                String name=String.format("SELECT name FROM SUPPLY_CHAIN");
                try {
                    ResultSet resultSet=Database.getQueryTable(name);
                    while (resultSet.next()){
                        name=resultSet.getString("name");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

              //  messageLabel.setText(email + password);
                if (login.CustomerLogin(email, password)){
  //                  messageLabel.setText("login successful")
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetail.getAllProducts());
                    globalMessageLabel.setText("Welcome"+" "+name );
                    globalMessageLabel.setTextFill(Color.BLUEVIOLET);
                    globalMessageLabel.setFont(new Font("Ariel Black", 20));
                    globalMessageLabel.setAlignment(Pos.CENTER);
                }else {
                    messageLabel.setText("login failed");
                }
            }
        });

        GridPane gridPane=new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(emaillabel,0,0);
        gridPane.add(emailField,1,0);
        gridPane.add(passwordlabel,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(loginButton,1,2);
        gridPane.add(messageLabel,1,3);
        return gridPane;
    }

    private GridPane footerBar(){

        //Button AddTOButton=new Button("Add to Cart");
        Button BuyNowButton=new Button("Buy Now");
        Label BuyNowMessage=new Label();

        BuyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                product selectedProduct=productDetail.getSelectedProduct();
                if(buyNow.placeOrder(customerEmail,selectedProduct)){
                    BuyNowMessage.setText("order placed");
                }else {
                    BuyNowMessage.setText("can't place this order");
                }
            }
        });

        GridPane gridPane=new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),headerbar);
        gridPane.setHgap(60);
        gridPane.setVgap(5);
//        gridPane.setStyle("-fx-background-color:#C0C0C0");
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setTranslateY(headerbar+hieght+10);
//        gridPane.add(AddTOButton,0,0);
        gridPane.add(BuyNowButton,1,0);
        gridPane.add(BuyNowMessage,1,1);


        return gridPane;
    }


    private Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(width,hieght+2*headerbar);
        bodyPane.setMinSize(width,hieght);
        bodyPane.setTranslateY(headerbar);

        bodyPane.getChildren().addAll(productDetail.getAllProducts());
        root.getChildren().addAll(headerBar(),bodyPane, footerBar());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
      //  FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}