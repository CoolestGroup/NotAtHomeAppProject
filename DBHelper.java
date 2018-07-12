package com.example.keshe.notathome;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
    private static Connection con;

    public DBHelper() {
        this.con = getConnection();
    }

    private static Connection getConnection(){

        String driver_calss = "com.mysql.jdbc.Driver";
        String driver_url = "jdbc:mysql://192.168.0.101:3306/smarthome";
        String database_user = "root";
        String database_password = "root";
        try {
            Class.forName(driver_calss);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver failed1");
            e.printStackTrace();;
        } catch (Exception e) {
            System.out.println("Driver failed2");
            e.printStackTrace();
        }
        System.out.println("driver success");
        try {
            con = DriverManager.getConnection(driver_url, database_user, database_password);

        } catch (SQLException e) {
            System.out.println("Connection failed1");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Connection failed2");
            e.printStackTrace();
        }
        return con;
    }

    //注册
    public static int insert(String user, String password) {
        Connection conn = getConnection();
        //System.out.println("Connection success");
        int i = 0;
        String sql = "INSERT INTO user (name, password) VALUES (?,?)";
        PreparedStatement pstmt;
        try {
            pstmt =  conn.prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("insert failed!");
            e.printStackTrace();
        }
        return i;
    }

    //登录
    public static Boolean check(String name, String password) {
        Connection conn = getConnection();
        //System.out.println("Connection success");
        String sql = "SELECT * FROM user WHERE name = ?";
        PreparedStatement pstmt;
        try {
            pstmt =  conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String pwd = rs.getString("password");
                if(password.equals(pwd)) {
                    //用户名和密码都正确
                    //输出登录成功
                    System.out.println("login success");
                    return true;
                }
            }
            //查询失败，没有该用户或者密码错误

        } catch (SQLException e) {
            System.out.println("check failed");
            e.printStackTrace();
        }
        //返回错误
        return false;
    }

}
