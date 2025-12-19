import java.sql.SQLException;

import database.DatabaseConnection;

public class LibrarySystem {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        // Check the database connection
        DatabaseConnection.checkConnection();
        
    }
}
