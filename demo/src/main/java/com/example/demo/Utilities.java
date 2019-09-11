package com.example.demo;

import java.sql.*;

public class Utilities {

    public String getNextAvailableBase32Value() {
        String URL="localhost:3306";
        String USERNAME="root";
        String PASSWORD="root";
        String DATABASE="urls";

        int id=0;
        String nextAvailable = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://"+ URL + "/" + DATABASE,USERNAME,PASSWORD);

            PreparedStatement ps=con.prepareStatement("SELECT * FROM urls ORDER BY ID DESC LIMIT 1",Statement.RETURN_GENERATED_KEYS);
            ps.setString(2,"bb");

            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            nextAvailable = ps.toString();
            System.out.println("Last used value is " + nextAvailable);

            if(rs.next()){
                id=rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextAvailable;
    }
}
