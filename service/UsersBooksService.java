package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseCredentials;
import model.UsersBooks;

public class UsersBooksService {

    /*
    This method will return UsersBooks object with the given id.
    */
    public UsersBooks get(int id) {
        UsersBooks usersBooks = new UsersBooks();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String selectQuery = "SELECT * from users_books where id=? ;";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                pstmt.setInt(0, id);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    int userId = rs.getInt("user_id");
                    int bookId = rs.getInt("book_id");
                    LocalDate issuedDate = rs.getDate("issued_date").toLocalDate();
                    LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                    LocalDate returnDate = rs.getDate("return_date").toLocalDate();

                    usersBooks.setId(id);
                    usersBooks.setUserId(userId);
                    usersBooks.setBookId(bookId);
                    usersBooks.setIssueDate(issuedDate);
                    usersBooks.setDueDate(dueDate);
                    usersBooks.setReturnDate(returnDate);
                }
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersBooks;
    }

    /*
    This method will return list of UsersBooks.
    */
    public List<UsersBooks> getAll() {
        List<UsersBooks> usersBooksList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String selectQuery = "SELECT * from users_books;";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()){
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    int bookId = rs.getInt("book_id");
                    LocalDate issuedDate = rs.getDate("issued_date").toLocalDate();
                    LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                    LocalDate returnDate = rs.getDate("return_date").toLocalDate();

                    UsersBooks usersBooks = new UsersBooks();

                    usersBooks.setId(id);
                    usersBooks.setUserId(userId);
                    usersBooks.setBookId(bookId);
                    usersBooks.setIssueDate(issuedDate);
                    usersBooks.setDueDate(dueDate);
                    usersBooks.setReturnDate(returnDate);

                    usersBooksList.add(usersBooks);
                }
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersBooksList;
    }

    /*
    This method will create a UsersBooks.
    */
    public void create(UsersBooks usersBooks) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String insertQuery = "INSERT INTO users_books (user_id, book_id, issued_date, due_date) values(?, ?, ?, ?);";
                PreparedStatement pstmt = con.prepareStatement(insertQuery);
                pstmt.setInt(0, usersBooks.getUserId());
                pstmt.setInt(1, usersBooks.getBookId());
                pstmt.setDate(2, java.sql.Date.valueOf(usersBooks.getIssueDate()));
                pstmt.setDate(3, java.sql.Date.valueOf(usersBooks.getDueDate()));
                if(pstmt.executeUpdate()>0){
                    System.out.println("Data inserted successfully");
                }else{
                    System.out.println("Data insertion failed");
                }
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    This method will update UsersBooks.
    */
    public void update(UsersBooks usersBooks) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String updateQuery = "UPDATE users_books set user_id=?, book_id=?, issue_date=?, due_date=? where id=? ;";
                PreparedStatement pstmt = con.prepareStatement(updateQuery);
                pstmt.setInt(0, usersBooks.getUserId());
                pstmt.setInt(1, usersBooks.getBookId());
                pstmt.setDate(2, java.sql.Date.valueOf(usersBooks.getIssueDate()));
                pstmt.setDate(3, java.sql.Date.valueOf(usersBooks.getDueDate()));
                pstmt.setInt(4, usersBooks.getId());

                if(pstmt.executeUpdate() > 0){
                    System.out.println("Data Updated successfully");
                }else{
                    System.out.println("Data Update failed");
                }
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
