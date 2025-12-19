package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import database.DatabaseCredentials;
import model.Books;
import model.UsersBooks;

public class UsersBooksService {

    /*
     * This method will return UsersBooks object with the given id.
     */
    public UsersBooks get(int id) {
        UsersBooks usersBooks = new UsersBooks();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if (con != null) {
                String selectQuery = "SELECT * from users_books where id=? ;";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
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
                pstmt.close();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersBooks;
    }

    /*
     * This method will return list of UsersBooks.
     */
    public List<UsersBooks> getAll() {
        List<UsersBooks> usersBooksList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if (con != null) {
                String selectQuery = "SELECT * from users_books;";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    int bookId = rs.getInt("book_id");
                    LocalDate issuedDate = rs.getDate("issue_date").toLocalDate();
                    LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                    LocalDate returnDate = null;

                    if (rs.getDate("return_date") != null) {
                        returnDate = rs.getDate("return_date").toLocalDate();
                    }

                    UsersBooks usersBooks = new UsersBooks();

                    usersBooks.setId(id);
                    usersBooks.setUserId(userId);
                    usersBooks.setBookId(bookId);
                    usersBooks.setIssueDate(issuedDate);
                    usersBooks.setDueDate(dueDate);
                    usersBooks.setReturnDate(returnDate);

                    usersBooksList.add(usersBooks);
                }
                pstmt.close();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersBooksList;
    }

    /*
     * This method will create a UsersBooks.
     */
    public void create(UsersBooks usersBooks) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            con.setAutoCommit(false);

            if (con != null) {
                // check if book is available
                BooksService booksService = new BooksService();
                Books books = booksService.get(usersBooks.getBookId());

                if (books.getQuantity() < 1) {
                    System.out.println("Insufficient Quantity. No book in stock.");
                    return;
                }

                List<UsersBooks> records = getAll();
                List<UsersBooks> bookAlreadyTaken = records.stream()
                        .filter(x -> Objects.equals(x.getBookId(), usersBooks.getBookId())
                                && x.getReturnDate() == null)
                        .toList();
                if (bookAlreadyTaken.size() > 0) {
                    System.out.println("User has taken this book and have not returned yet.");
                    return;
                }

                int oldQuant = books.getQuantity();
                int newQuant = oldQuant - 1;

                String insertQuery = "INSERT INTO users_books (user_id, book_id, issue_date, due_date) values(?, ?, ?, ?);";

                PreparedStatement pstmt = con.prepareStatement(insertQuery);
                pstmt.setInt(1, usersBooks.getUserId());
                pstmt.setInt(2, usersBooks.getBookId());
                pstmt.setDate(3, java.sql.Date.valueOf(usersBooks.getIssueDate()));
                pstmt.setDate(4, java.sql.Date.valueOf(usersBooks.getDueDate()));

                if (pstmt.executeUpdate() > 0) {
                    String updateQuery = "UPDATE books set quantity=? where id=? ;";
                    PreparedStatement pstmtUpdate = con.prepareStatement(updateQuery);
                    pstmtUpdate.setInt(1, newQuant);
                    pstmtUpdate.setInt(2, usersBooks.getBookId());
                    if (pstmtUpdate.executeUpdate() > 0) {
                        con.commit();
                        System.out.println("Data inserted Successfully");
                    } else {
                        System.out.println("Error!!! Contact Admin!!!");
                    }
                    pstmtUpdate.close();
                } else {
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
     * This method will update UsersBooks.
     */
    public void update(UsersBooks usersBooks) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if (con != null) {
                String updateQuery = "UPDATE users_books set user_id=?, book_id=?, issue_date=?, due_date=? where id=? ;";
                PreparedStatement pstmt = con.prepareStatement(updateQuery);
                pstmt.setInt(1, usersBooks.getUserId());
                pstmt.setInt(2, usersBooks.getBookId());
                pstmt.setDate(3, java.sql.Date.valueOf(usersBooks.getIssueDate()));
                pstmt.setDate(4, java.sql.Date.valueOf(usersBooks.getDueDate()));
                pstmt.setInt(5, usersBooks.getId());

                if (pstmt.executeUpdate() > 0) {
                    System.out.println("Data Updated successfully");
                } else {
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
     * This method will return list of UsersBooks.
     */
    public List<UsersBooks> findRecord(String by, LocalDate date) {
        List<UsersBooks> usersBooksList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL,
                    DatabaseCredentials.DATABASE_USER,
                    DatabaseCredentials.DATABASE_PASS);

            if (con != null) {
                String selectQuery = "SELECT * from users_books where " + by + "=?;";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                pstmt.setDate(1, java.sql.Date.valueOf(date));
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    int bookId = rs.getInt("book_id");
                    LocalDate issuedDate = rs.getDate("issue_date").toLocalDate();
                    LocalDate dueDate = rs.getDate("due_date").toLocalDate();

                    LocalDate returnDate = null;
                    if (rs.getDate("return_date") != null) {
                        returnDate = rs.getDate("return_date").toLocalDate();
                    }

                    UsersBooks usersBooks = new UsersBooks();

                    usersBooks.setId(id);
                    usersBooks.setUserId(userId);
                    usersBooks.setBookId(bookId);
                    usersBooks.setIssueDate(issuedDate);
                    usersBooks.setDueDate(dueDate);
                    usersBooks.setReturnDate(returnDate);

                    usersBooksList.add(usersBooks);
                }
                pstmt.close();
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersBooksList;
    }

}
