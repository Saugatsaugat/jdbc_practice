package model;

import java.sql.Date;

public class Books {
    private int id;
    private String title;
    private String author;
    private String category;
    private int quantity;
    private Date addedDate;

    public Books() {
    }

    public Books(int id, String title, String author, String category, int quantity, Date addedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.addedDate = addedDate;
    }

    public Books(String title, String author, String category, int quantity, Date addedDate) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.addedDate = addedDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getAddedDate(){
        return this.addedDate;
    }
    public void setAddedDate(Date addedDate){
        this.addedDate = addedDate;
    }
}
