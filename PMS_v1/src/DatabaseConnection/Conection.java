package DatabaseConnection;
import java.sql.*;
import java.io.File;

public class Conection {
    public static Connection connection = null;
    public static Connection Openconextion(){
        try {
            Class.forName("org.sqlite.JDBC");
            String currentDirectory =  System.getProperty("user.dir");
            String databasePath = currentDirectory + File.separator + "SmartParking" + File.separator + "SmartParking.db";

            connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}