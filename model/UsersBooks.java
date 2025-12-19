package model;

import java.sql.Date;

public class UsersBooks {
    private int id;
    private int userId;
    private int bookId;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;

    public UsersBooks(){}

    public UsersBooks(int id, int userId, int bookId, Date issueDate, Date dueDate, Date returnDate){
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public UsersBooks(int userId, int bookId, Date issueDate, Date dueDate, Date returnDate){
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

    public Date getIssueDate(){
        return this.issueDate;
    }
    public void setIssueDate(Date issueDate){
        this.issueDate = issueDate;
    }

    public Date getDueDate(){
        return this.dueDate;
    }
    public void setDueDate(Date dueDate){
        this.dueDate = dueDate;
    }

    public Date getReturnDate(){
        return this.returnDate;
    }
    public void setReturnDate(Date returnDate){
        this.returnDate = returnDate;
    }
}
