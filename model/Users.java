package model;

import java.time.LocalDate;

public class Users {
    private int id;
    private String name;
    private String email;
    private LocalDate joinedDate;

    public Users(){

    }

    public Users(String name, String email, LocalDate joinedDate){
        this.name = name;
        this.email = email;
        this.joinedDate = joinedDate;
    }

    public Users(int id, String name, String email, LocalDate joinedDate){
        this.id = id;
        this.name = name;
        this.email = email;
        this.joinedDate = joinedDate;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public LocalDate getJoinedDate(){
        return this.joinedDate;
    }
    public void setJoinedDate(LocalDate joinedDate){
        this.joinedDate = joinedDate;
    }

}
