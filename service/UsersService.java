package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseCredentials;
import model.Users;

public class UsersService {

    /*
    This method will return Users object with the given id.
    */
    public Users get(int id) {
        Users users = new Users();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String selectQuery = "SELECT * from users where id=? ;";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    LocalDate joinedDate = rs.getDate("joined_date").toLocalDate();

                    users.setId(id);
                    users.setName(name);
                    users.setEmail(email);
                    users.setJoinedDate(joinedDate);
                }
                pstmt.close();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /*
    This method will return list of Users.
    */
    public List<Users> getAll() {
        List<Users> usersList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String selectQuery = "SELECT * from users;";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    LocalDate joinedDate = rs.getDate("joined_date").toLocalDate();

                    Users users = new Users();
                    users.setId(id);
                    users.setName(name);
                    users.setEmail(email);
                    users.setJoinedDate(joinedDate);
                    usersList.add(users);
                }
                pstmt.close();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    /*
    This method will create a user.
    */
    public void create(Users users) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String insertQuery = "INSERT INTO users (name, email, joined_date) values(?, ?, ?);";
                PreparedStatement pstmt = con.prepareStatement(insertQuery);
                pstmt.setString(1, users.getName());
                pstmt.setString(2, users.getEmail());
                pstmt.setDate(3, java.sql.Date.valueOf(users.getJoinedDate()));
                if(pstmt.executeUpdate()>0){
                    System.out.println("Data inserted successfully");
                }else{
                    System.out.println("Data insertion failed");
                }
                pstmt.close();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    This method will update user.
    */
    public void update(Users users) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String updateQuery = "UPDATE users set name=?, email=?, joined_date=? where id=? ;";
                PreparedStatement pstmt = con.prepareStatement(updateQuery);
                pstmt.setString(1, users.getName());
                pstmt.setString(2, users.getEmail());
                pstmt.setDate(3, java.sql.Date.valueOf(users.getJoinedDate()));
                pstmt.setInt(4, users.getId());

                if(pstmt.executeUpdate() > 0){
                    System.out.println("Data Updated successfully");
                }else{
                    System.out.println("Data Update failed");
                }
                pstmt.close();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    This method will return User object if the email is of valid user.
    */
    public Users findUserByEmail(String userEmail) {
        Users users = new Users();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String selectQuery = "SELECT * from users where email=? ;";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                pstmt.setString(1, userEmail);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    LocalDate joinedDate = rs.getDate("joined_date").toLocalDate();

                    users.setId(id);
                    users.setName(name);
                    users.setEmail(email);
                    users.setJoinedDate(joinedDate);
                }
                pstmt.close();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
