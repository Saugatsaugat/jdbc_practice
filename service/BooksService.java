package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseCredentials;
import model.Books;

public class BooksService {

    /*
    This method will return Books object with the given id.
    */
    public Books get(int id) {
        Books books = new Books();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String selectQuery = "SELECT * from books where id=? ;";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                pstmt.setInt(0, id);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    LocalDate addedDate = rs.getDate("added_date").toLocalDate();

                    books.setId(id);
                    books.setTitle(title);
                    books.setAuthor(author);
                    books.setCategory(category);
                    books.setQuantity(quantity);
                    books.setAddedDate(addedDate);
                }
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    /*
    This method will return list of Books.
    */
    public List<Books> getAll() {
        List<Books> booksList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String selectQuery = "SELECT * from books;";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()){
                    Books books = new Books();

                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    LocalDate addedDate = rs.getDate("added_date").toLocalDate();

                    books.setId(id);
                    books.setTitle(title);
                    books.setAuthor(author);
                    books.setCategory(category);
                    books.setQuantity(quantity);
                    books.setAddedDate(addedDate);

                    booksList.add(books);
                }
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksList;
    }

    /*
    This method will create a Book.
    */
    public void create(Books books) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String insertQuery = "INSERT INTO books (title, author, category, quantity, addedDate) values(?, ?, ?, ?, ?);";
                PreparedStatement pstmt = con.prepareStatement(insertQuery);
                pstmt.setString(0, books.getTitle());
                pstmt.setString(1, books.getAuthor());
                pstmt.setString(2, books.getCategory());
                pstmt.setInt(3, books.getQuantity());
                pstmt.setDate(4, java.sql.Date.valueOf(books.getAddedDate()));

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
    This method will update book.
    */
    public void update(Books books) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if(con != null){
                String updateQuery = "UPDATE books set title=?, author=?, category=?,quantity=?, added_date=? where id=? ;";
                PreparedStatement pstmt = con.prepareStatement(updateQuery);
                pstmt.setString(0, books.getTitle());
                pstmt.setString(1, books.getAuthor());
                pstmt.setString(2, books.getCategory());
                pstmt.setInt(3, books.getQuantity());
                pstmt.setDate(4, java.sql.Date.valueOf(books.getAddedDate()));
                pstmt.setInt(5, books.getId());

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
