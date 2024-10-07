package ParkingFacility;

import java.sql.*;

import static ParkingFacility.Main.CONNECTION_STRING;

public class Discount {

    private String discountID;
    private String typeDiscount;
    private double percentage;

    //set
    public Discount(String discountID, String typeDiscount, double percentage){
        this.discountID = discountID;
        this.typeDiscount = typeDiscount;
        this.percentage = percentage;
    }

    public void displayDiscountRate(){

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            statement = conn.prepareStatement("SELECT TypeDiscount, Percentage FROM Discounts WHERE DiscountID=?");

            // Establecer el parámetro en la consulta preparada
            statement.setString(1, this.discountID);

            results = statement.executeQuery();

            // Procesar los resultados
            while (results.next()) {
                this.typeDiscount = results.getString("TypeDiscount");
                this.percentage = results.getDouble("Percentage");
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar los recursos en el orden correcto, manejar excepciones si es necesario
            try {
                if (results != null) {
                    results.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println(this.typeDiscount + ": \t" + this.percentage);
    }

    public void updateDiscountRate(double discountRate){
        try {
            // Setting yhe connection
            try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
                conn.setAutoCommit(false);

                // Using PreparedStatement for avoiding SQL Injection
                String updateSql = "UPDATE Discounts SET Percentage = ? WHERE DiscountID = ?";
                try (PreparedStatement updateStatement = conn.prepareStatement(updateSql)) {
                    // Set the parameters
                    updateStatement.setDouble(1, discountRate);
                    updateStatement.setString(2, this.discountID);

                    // Run the update
                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        // If the update went OK, get the results
                        String selectSql = "SELECT Percentage FROM Discounts WHERE DiscountID = ?";
                        try (PreparedStatement selectStatement = conn.prepareStatement(selectSql)) {
                            // Set the parameter for the SELECT query
                            selectStatement.setString(1, this.discountID);

                            // Run the SELECT query
                            try (ResultSet resultSet = selectStatement.executeQuery()) {
                                if (resultSet.next()) {
                                    // Process the results
                                    this.percentage = resultSet.getDouble("Percentage");
                                    System.out.println("Discount updated successfully");
                                    displayDiscountRate();
                                }
                            }
                        }
                    }

                    // Confirm the transaction
                    conn.commit();
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public double getDiscountRate(){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            statement = conn.prepareStatement("SELECT TypeDiscount, Percentage FROM Discounts WHERE DiscountID=?");

            // Establecer el parámetro en la consulta preparada
            statement.setString(1, this.discountID);

            results = statement.executeQuery();

            // Procesar los resultados
            while (results.next()) {
                this.typeDiscount = results.getString("TypeDiscount");
                this.percentage = results.getDouble("Percentage");
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar los recursos en el orden correcto, manejar excepciones si es necesario
            try {
                if (results != null) {
                    results.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return this.percentage;
    }



}
