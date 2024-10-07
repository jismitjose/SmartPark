package Worker;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.text.SimpleDateFormat;

import java.util.Scanner;

import DataValidation.D_VAndFuntion;
import DatabaseConnection.*;

public class Users {
    private int ID;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String telephone;
    private String address;
    private String TipeWorker;

    // Constructor
    public Users(int ID, String username, String password, String name, String lastname, String email, String telephone, String address, String TypeWorker) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.TipeWorker = TypeWorker;
    }

    // Getters and setters (puedes generar estos métodos automáticamente en la mayoría de los IDEs)

    // CRUD Operations
    // CREATE
    public static void createUsers(String username, String password, String name, String lastname, String email, String telephone, String address, String TipeWorker) throws SQLException {
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users\n" +
                     "(User, Password, DateCreate, DateModify, Name, LastName, Email, Telephone, Address, TipeWork)\n" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, username);
            statement.setString(2, password);

            // Formatear la fecha en el formato deseado
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDate = dateFormat.format(new Timestamp(System.currentTimeMillis()));
            statement.setString(3, formattedDate);
            statement.setString(4, formattedDate);
            statement.setString(5, name);
            statement.setString(6, lastname);
            statement.setString(7, email);
            statement.setString(8, telephone);
            statement.setString(9, address);
            statement.setString(10, TipeWorker);
            statement.executeUpdate();
        } finally {
            Conection.closeConnection();
        }
    }
    // READ
    public static Users readUsersById(int usersId) throws SQLException {
        String query = "SELECT * FROM Users WHERE ID = ?";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, usersId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String user = resultSet.getString("User");
                    String password = resultSet.getString("Password");
                    String dateCreateStr = resultSet.getString("DateCreate");

                    if (user != null) {
                        System.out.println("Usuario encontrado:");
                        System.out.println("ID: " + usersId);
                        System.out.println("Username: " + user);
                        System.out.println("Password: " + password);
                        System.out.println("DateCreate: " +  dateCreateStr);
                        // Otros campos del usuario
                    } else {
                        System.out.println("No se encontró ningún usuario con el ID proporcionado.");
                    }
                }
            }
        } finally {
            Conection.closeConnection();
        }
        return null; // Devolver null si no se encuentra el usuario con el ID proporcionado
    }
    public static void readAllUsers() throws SQLException {
        String query = "SELECT * FROM Users";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            // Imprimir encabezados de la tabla
            System.out.printf("%-5s %-20s %-20s %-20s%n", "ID", "Username", "Password", "DateCreate");
            System.out.println("-----------------------------------------------------------");

            while (resultSet.next()) {
                int usersId = resultSet.getInt("ID");
                String user = resultSet.getString("User");
                String password = resultSet.getString("Password");
                String dateCreateStr = resultSet.getString("DateCreate");

                // Imprimir fila de la tabla
                System.out.printf("%-5d %-20s %-20s %-20s%n", usersId, user, password, dateCreateStr);
            }
        } finally {
            Conection.closeConnection();
        }
    }
    // UPDATE
    public static void updateUsers(int ID, String newPassword, String newName, String newLastName, String newEmail, String newTelephone, String newAddress, String newTypeWorker) throws SQLException {
        String query = "UPDATE Users SET Password = ?, DateModify = ?, Name = ?, LastName = ?, Email = ?, Telephone = ?, Address = ?, LastLogin = ? WHERE ID = ?";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newPassword);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setString(3, newName);
            statement.setString(4, newLastName);
            statement.setString(5, newEmail);
            statement.setString(6, newTelephone);
            statement.setString(7, newAddress);
            statement.setString(8, newTypeWorker);
            statement.setInt(9, ID);

            statement.executeUpdate();
        } finally {
            Conection.closeConnection();
        }
    }
    public static void UpdateUserPrint() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        // Obtener el ID del usuario que se actualizará
        int userId =  D_VAndFuntion.validateInt("Ingrese el ID del usuario a actualizar: ");
        scanner.nextLine();

        boolean validate = Users.validateId(userId);

        if (validate == true) {

            readUsersById(userId);
            // Obtener los nuevos valores del usuario
            String newPassword = D_VAndFuntion.ValidateText("Ingrese la nueva contraseña: ");
            String newName = D_VAndFuntion.ValidateText("Ingrese el nuevo nombre: ");
            String newLastName  = D_VAndFuntion.ValidateText("Ingrese el nuevo apellido: ");
            String newEmail = D_VAndFuntion.ValidateText("Ingrese el nuevo correo electrónico: ");
            String newTelephone = D_VAndFuntion.ValidateText("Ingrese el nuevo número de teléfono: ");
            String newAddress = D_VAndFuntion.ValidateText("Ingrese la nueva dirección: ");
            String newTypeWorker = D_VAndFuntion.ValidateText("Ingrese el nuevo tipo de trabajador: ");

            try {
                updateUsers(userId, newPassword, newName, newLastName, newEmail, newTelephone, newAddress, newTypeWorker);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Usuario actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún usuario con el ID proporcionado.");
        }

    }
    // DELETE
    public static void deleteUsers() throws SQLException {

            int userId =  D_VAndFuntion.validateInt("Ingrese el ID del usuario a actualizar: ");
            boolean validate =  validateId(userId);

            if (validate == true) {
                String query = "DELETE FROM Users WHERE ID = ?";
                try (Connection connection = Conection.Openconextion();
                     PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setInt(1, userId);

                    int rowsDeleted = statement.executeUpdate();

                    if (rowsDeleted > 0) {
                        System.out.println("Usuario eliminado correctamente con ID: " + userId);
                    } else {
                        System.out.println("No se encontró ningún usuario con el ID proporcionado.");
                    }
                } finally {
                    Conection.closeConnection();
                }
            }




    }

    public static boolean validateId (int userId) {
        try {
            // Consulta SQL para seleccionar todos los registros de la tabla Users
            String query = "SELECT * FROM Users WHERE ID = ?";
            try (Connection connection = Conection.Openconextion();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {

                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
