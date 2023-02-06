package com.example.suplychainmanagement;
import java.security.spec.RSAOtherPrimeInfo;
import java.sql.*;

public class database {
    private static final String DataBaseURL="jdbc:mysql://localhost:3306/supply_chain_dec";
    private static final String username="root";
    private static final String password="Sprsh@6455";

    public Statement getstatement(){
        Statement statement=null;
        Connection conn;
        //statement, connection are class where connection connects to sql database
        try {
            conn=DriverManager.getConnection(DataBaseURL,username,password);
            statement=conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
        return statement;

    }
    //resultset stores the table from database to use it in interface
    // we execute the query in resultset to store value
    public ResultSet getQueryTable(String query){
        Statement statement=getstatement();
        try {
            return statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

   /* public static void main(String[] args) {
        database DataBase=new database();
        ResultSet rs=DataBase.getQueryTable("SELECT email,password FROM SUPPLY_CHAIN");
        try {
            while (rs.next()){
                System.out.println(rs.getString("email")+" "+rs.getString("password"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    public int executeUpdateQuery(String query){
        Statement statement=getstatement();
        try {
            return statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
