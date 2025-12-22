package database;

public class DatabaseCredentials {
    public static final String DATABASE_NAME = "library_db";
    public static final int DATABASE_PORT = 3306;
    public static final String DATABASE_HOST = "localhost";
    public static final String DATABASE_USER = "test";
    public static final String DATABASE_PASS = "Test@123";
    // public static final String DATABASE_PASS1 = "Test@123#";

    public static final String JDBC_URL = "jdbc:mysql://" + DATABASE_HOST + ":" + DATABASE_PORT + "/" + DATABASE_NAME;

}