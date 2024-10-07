package Worker;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import DataValidation.D_VAndFuntion;
import DatabaseConnection.Conection;
import ParkingFacility.Main;

public class Login {
    public static Boolean Access() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login");

        boolean x = false;
        Main.cargoLaboral = null; // Declaración de la variable


        while(!x){
            String username = D_VAndFuntion.ValidateText("Enter username: ");
            String password = D_VAndFuntion.ValidateText("Enter password: ");

            try (Connection connection = Conection.Openconextion();

                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE User = ? AND Password = ?")) {
                {
                    statement.setString(1, username);
                    statement.setString(2, password);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            Main.cargoLaboral = resultSet.getString("TipeWork"); // Obtener el valor de la columna TipeWork
                            // Usuario autenticado

                            connection.close();
                            updateUsers(username);
                            System.out.println("Login successful!");
                            x = true;

                        } else {
                            System.out.println("Login failed. Username or password is incorrect.");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return x;
    }

    public static void updateUsers(String User) throws SQLException {
        String query = "UPDATE Users SET LastLogin = ? WHERE User = ?";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            statement.setString(2, User);

            statement.executeUpdate();
        } finally {
            Conection.closeConnection();
        }
    }


}

