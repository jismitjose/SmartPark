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

public class GetInfoFromTable {

    // this function get the information for an specific parking type, depending on the uuid
    public static void getParkingTypeInfo(String uuid, Caster caster){

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            statement = conn.prepareStatement("SELECT * FROM ParkingServices WHERE ParkingTypeID=?");

            // Establecer el parámetro en la consulta preparada
            statement.setString(1, uuid);

            results = statement.executeQuery();

            // Procesar los resultados
            while (results.next()) {
                caster.getParkingType().setParkingTypeID(results.getString("ParkingTypeID"));
                caster.getParkingType().setParkingTypeName(results.getString("ParkingTypeName"));
                caster.getParkingType().setCapFacility(results.getInt("CapFacility"));
                caster.getParkingType().setCapBooking(results.getInt("CapBooking"));
                caster.getParkingType().setRate(results.getDouble("Rate"));
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
    }


    public static void getWashingServicesInfo(String uuid, CasterWashing caster) {

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            statement = conn.prepareStatement("SELECT * FROM WashingServices WHERE WashingServiceID=?");

            // Establecer el parámetro en la consulta preparada
            statement.setString(1, uuid);

            results = statement.executeQuery();

            // Procesar los resultados
            while (results.next()) {
                caster.getWashingServiceType().setWashingServiceTypeID(results.getString("WashingServiceID"));
                caster.getWashingServiceType().setWashingServiceTypeName(results.getString("WashingServiceType"));
                caster.getWashingServiceType().setCapacity(results.getInt("Capacity"));
                caster.getWashingServiceType().setRate(results.getDouble("Rate"));
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
    }

    public static int getFreeParkingSlotsCount(String parkingServiceID) {
        int freeSlots = 0;

        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            String sql = "SELECT COUNT(*) AS FreeParkingSlots FROM ParkingSlotsFacility WHERE ParkingServiceID = ? AND parkingSlotState = 0";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, parkingServiceID);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        freeSlots = resultSet.getInt("FreeParkingSlots");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }

        return freeSlots;
    }

    public static int getRandomFreeParkingSlot(String parkingServiceID) {
        int randomSlotNumber = -1;

        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            String sql = "SELECT ParkingSlotNumber FROM ParkingSlotsFacility WHERE ParkingServiceID = ? AND parkingSlotState = 0 ORDER BY RANDOM() LIMIT 1";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, parkingServiceID);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        randomSlotNumber = resultSet.getInt("ParkingSlotNumber");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }

        return randomSlotNumber;
    }

    public static void updateParkingSlotState(int parkingSlotNumber, int newState) {
        int randomSlotNumber = -1;

        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            // Query for updating the state
            String sql = "UPDATE ParkingSlotsFacility SET parkingSlotState = ? WHERE ParkingSlotNumber = ?";

            // Prepare the SQL statement with the new state and parkingSlot number
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, newState);
                pstmt.setInt(2, parkingSlotNumber);

                // Execute the update
                pstmt.executeUpdate();
                //System.out.println("ParkingSlot state updated successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error changing ParkingSlot state: " + e.getMessage());
        }
    }





    // this function is used for testing the connection to the database.
    public static void testDBConnection(){
        Connection connection = null;

        try {
            // Establecer la conexión
            connection = DriverManager.getConnection("jdbc:sqlite:C:/SmartParking/SmartParking.db");

            // Consulta SQL para seleccionar todos los datos de ParkingServices
            String sql = "SELECT * FROM ParkingServices";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Ejecutar la consulta
                ResultSet resultSet = preparedStatement.executeQuery();

                // Procesar los resultados
                while (resultSet.next()) {
                    String parkingTypeID = resultSet.getString("ParkingTypeID");
                    String parkingTypeName = resultSet.getString("ParkingTypeName");
                    int capFacility = resultSet.getInt("CapFacility");
                    int capBooking = resultSet.getInt("CapBooking");
                    double rate = resultSet.getDouble("Rate");

                    // Imprimir o procesar los resultados según sea necesario
                    System.out.println("ParkingTypeID: " + parkingTypeID);
                    System.out.println("ParkingTypeName: " + parkingTypeName);
                    System.out.println("CapFacility: " + capFacility);
                    System.out.println("CapBooking: " + capBooking);
                    System.out.println("Rate: " + rate);
                    System.out.println("----------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }





}
