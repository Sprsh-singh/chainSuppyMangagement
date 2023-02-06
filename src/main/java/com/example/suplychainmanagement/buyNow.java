package com.example.suplychainmanagement;

public class buyNow {
    public static boolean placeOrder(String customerEmail,product Product){
        database Database=new database();
        String query=String.format("INSERT INTO orders(Customer_id,Product_id) VALUES ((SELECT customer_ID FROM SUPPLY_CHAIN WHERE email='%s'),%s)",customerEmail,Product.getId());
        int rowcount=0;
        try {
            rowcount=Database.executeUpdateQuery(query);

        }catch (Exception e){
            e.printStackTrace();
        }

        return rowcount!=0;
    }
}
