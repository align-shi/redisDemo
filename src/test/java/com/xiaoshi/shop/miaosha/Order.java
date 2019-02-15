package com.xiaoshi.shop.miaosha;

import java.sql.*;

/**
 * @description: 下单业务实现
 * @author: xiaopang
 * @create: 2019-01-28 14:44
 **/
public class Order {

    public  Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://122.115.57.139:3306/shop?useSSL=false&characterEncoding=UTF-8", "root", "xiaoshi");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public String buy(String customerName) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        int version = 0;
        String sql = "select item_sum,version from item_sum where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
                version = rs.getInt(2);
            }
            //如果此时还有库存 则执行下单操作
            if (count > 0) {
                if (doOrder(conn, ps, rs,count,customerName,version)) {
                    return "下单成功";
                }
            } else {
                return "库存不足";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn, ps, rs);
        }
        return "出现未知错误";
    }

    public  boolean doOrder(Connection conn,PreparedStatement ps,ResultSet rs ,int count,String name ,int version) {

        String sql = "insert into order_user(username,create_time) values (?,?) ";

        String sqlUpdate = "update item_sum set item_sum = ?,version = ? where id = ?";
        try {
            if (check(conn, ps, rs, version, name)) {
                conn.setAutoCommit(false);
                ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setDate(2,new Date(System.currentTimeMillis()));
                int result = ps.executeUpdate();
                if (result > 0) {
                    ps = conn.prepareStatement(sqlUpdate);
                    ps.setInt(1, count - 1);
                    ps.setInt(2, version + 1);
                    ps.setInt(3,1);
                    if (ps.executeUpdate() > 0) {
                        conn.commit();
                        return true;
                    }
             }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean check(Connection conn, PreparedStatement ps, ResultSet rs, int version, String name) {
        String sql = "select version from item_sum where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
               if(rs.getInt(1) == version){
                   return true;
               }else {

               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void close(Connection conn, Statement smts, ResultSet resultSet) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (smts != null) {
            try {
                smts.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
