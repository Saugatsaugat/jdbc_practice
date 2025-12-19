package database;

import java.sql.*;

public class DatabaseConnection {

    public static void checkConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(DatabaseCredentials.JDBC_URL, DatabaseCredentials.DATABASE_USER,
                DatabaseCredentials.DATABASE_PASS);
        System.out.println("Connection Established");
        con.close();
    }
}
