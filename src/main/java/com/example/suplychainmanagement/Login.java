package com.example.suplychainmanagement;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {

    public static byte[] getSHA(String input){
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    String getEncryptedPassword(String password){
        try {
            BigInteger number=new BigInteger(1,getSHA(password));
            StringBuilder hexString=new StringBuilder(number.toString(16));
            return hexString.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean CustomerLogin(String email,String password){
        String query=String.format("select * from supply_chain where email='%s' and password='%s'",email , password);
        //running query for checking if the entered details matches to the database, '%s' is used to check
        try {
            database DB=new database();
            ResultSet rs= DB.getQueryTable(query);
            if (rs!=null && rs.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

  /*  public static void main(String[] args) {
        Login login=new Login();
        System.out.println(login.CustomerLogin("sparsh2020@gmail.com", "qwer123"));
    }*/

}
