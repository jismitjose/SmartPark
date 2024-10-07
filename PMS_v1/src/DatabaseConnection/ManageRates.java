package DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ParkingFacility.BParkingType.ParkingType;
import static ParkingFacility.Main.CONNECTION_STRING;
import static ParkingFacility.Main.DB_NAME;
import ParkingFacility.BParkingType.*;
import ParkingFacility.BWashingService.CasterWashing;

public class ManageRates {

    // this function get the information for an specific parking type, depending on the uuid
    public static void updateParkingRate(String uuid, Caster caster, double rate) {

        try {
            // Setting yhe connection
            try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
                conn.setAutoCommit(false);

                // Using PreparedStatement for avoiding SQL Injection
                String updateSql = "UPDATE ParkingServices SET Rate = ? WHERE ParkingTypeID = ?";
                try (PreparedStatement updateStatement = conn.prepareStatement(updateSql)) {
                    // Set the parameters
                    updateStatement.setDouble(1, rate);
                    updateStatement.setString(2, uuid);

                    // Run the update
                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        // If the update went OK, get the results
                        String selectSql = "SELECT Rate FROM ParkingServices WHERE ParkingTypeID = ?";
                        try (PreparedStatement selectStatement = conn.prepareStatement(selectSql)) {
                            // Set the parameter for the SELECT query
                            selectStatement.setString(1, uuid);

                            // Run the SELECT query
                            try (ResultSet resultSet = selectStatement.executeQuery()) {
                                if (resultSet.next()) {
                                    // Process the results
                                    caster.getParkingType().setRate(resultSet.getDouble("Rate"));
                                    System.out.println("Rate updated succesfully");
                                    caster.getParkingType().displayRates();
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


    public static void updateWashingServiceRate(String uuid, CasterWashing caster, double rate) {

        try {
            // Setting yhe connection
            try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
                conn.setAutoCommit(false);

                // Using PreparedStatement for avoiding SQL Injection
                String updateSql = "UPDATE WashingServices SET Rate = ? WHERE WashingServiceID = ?";
                try (PreparedStatement updateStatement = conn.prepareStatement(updateSql)) {
                    // Set the parameters
                    updateStatement.setDouble(1, rate);
                    updateStatement.setString(2, uuid);

                    // Run the update
                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        // If the update went OK, get the results
                        String selectSql = "SELECT Rate FROM WashingServices WHERE WashingServiceID = ?";
                        try (PreparedStatement selectStatement = conn.prepareStatement(selectSql)) {
                            // Set the parameter for the SELECT query
                            selectStatement.setString(1, uuid);

                            // Run the SELECT query
                            try (ResultSet resultSet = selectStatement.executeQuery()) {
                                if (resultSet.next()) {
                                    // Process the results
                                    caster.getWashingServiceType().setRate(resultSet.getDouble("Rate"));
                                    System.out.println("Rate updated succesfully");
                                    caster.getWashingServiceType().displayRates();
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



}
