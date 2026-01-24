package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.User;

/**
 * User Data Access Object
 */
public class UserDao {
        // PostgreSQL JDBC 드라이버와 연결 정보 (환경에 맞게 수정)
        private static final String DRIVER = "org.postgresql.Driver";
        private static final String URL = "jdbc:postgresql://localhost:5432/spring-framework-db";
        private static final String USER = "user";
        private static final String PASSWORD = "password";

        public static void main(String[] args) {
                UserDao dao = new UserDao();
                User user = new User();
                user.setId("whiteshp");
                user.setName("back");
                user.setPassword("123");
                try {
                        dao.add(user);
                        System.out.println(user.getId() + " 등록성공");
                        User user2 = dao.get(user.getId());
                        System.out.println(user2.getName());
                        System.out.println(user2.getName());
                        System.out.println(user2.getPassword());
                        System.out.println(user2.getId() + "조회성공");
                } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                }
        }

        public void add(User user) throws ClassNotFoundException, SQLException {
                Class.forName(DRIVER);
                Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = c.prepareStatement(
                                "INSERT INTO \"user\" (\"userid\", \"name\", \"password\") VALUES (?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                ps.executeUpdate();
                ps.close();
                c.close();
        }

        public User get(String id) throws ClassNotFoundException, SQLException {
                Class.forName(DRIVER);
                Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = c.prepareStatement(
                                "SELECT * FROM \"user\" WHERE \"userid\" = ?");
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                rs.next();
                User user = new User();
                user.setId(rs.getString("userid"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                rs.close();
                ps.close();
                c.close();
                return user;
        }
}