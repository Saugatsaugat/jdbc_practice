package model;

import java.time.LocalDate;

public class UsersBooks {
    private int id;
    private int userId;
    private int bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public UsersBooks(){}

    public UsersBooks(int id, int userId, int bookId, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate){
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public UsersBooks(int userId, int bookId, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate){
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getUserId(){
        return this.userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getBookId(){
        return this.bookId;
    }
    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public LocalDate getIssueDate(){
        return this.issueDate;
    }
    public void setIssueDate(LocalDate issueDate){
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate(){
        return this.dueDate;
    }
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate(){
        return this.returnDate;
    }
    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
    }
}
