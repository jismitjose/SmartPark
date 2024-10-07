package Online;

import java.sql.*;
import java.time.LocalDateTime;

import DatabaseConnection.Conection;
import ParkingFacility.BParkingType.ParkingType;

public class preBooking {

    private Client client;
    private LocalDateTime dateCheckIn;
    private LocalDateTime dateCheckOut;
    private ParkingType typeParkingSlot;
    private String parkingSlotID;
    private String licensePlateCar;
    private QRCode qrcode;

    public void setClient (Client client) { this.client = client; }
    public void setDateCheckIn (LocalDateTime dateCheckIn) { this.dateCheckIn = dateCheckIn; }
    public void setDateCheckOut (LocalDateTime dateCheckIn) { this.dateCheckIn = dateCheckIn; }
    //public void setParkingtype (ParkingType parkingType) { this.parkingType = parkingType; }
    public void setParkingSlotID (String parkingSlotID) { this.parkingSlotID = parkingSlotID; }
    public void setLicensePlateCar(String licensePlateCar) {this.licensePlateCar = licensePlateCar; }
    public void setQrcode(QRCode qrcode){ this.qrcode = qrcode; }

    // getters
    public Client getClient(){ return this.client; }
    public LocalDateTime getDateCheckIn() { return this.dateCheckIn; }
    public LocalDateTime getDateCheckOut() { return this.dateCheckOut; }
    public ParkingType getTypeParkingSlot() { return this.typeParkingSlot; }
    public String getParkingSlotID() { return this.parkingSlotID; }
    public String getLicensePlateCar() { return licensePlateCar; }
    public QRCode getQrcode() { return qrcode; }



    public preBooking(Client client, LocalDateTime checkIn, LocalDateTime checkOut, ParkingType parkingType,
                            String parkingID, String licensePlate, String TypeParking){

        this.client = client;
        this.dateCheckIn = checkIn;
        this.dateCheckOut = checkOut;
        this.typeParkingSlot = parkingType;
        this.parkingSlotID = parkingID;
        this.licensePlateCar = licensePlate;
        qrcode = new QRCode(TypeParking);
    }

    public static int getRandomFreeParkingSlot(String parkingServiceID) {
        int randomSlotNumber = -1;
        String query = "SELECT ParkingSlotNumber FROM ParkingSlotsFacility WHERE ParkingServiceID = ? AND parkingSlotState = 0 ORDER BY RANDOM() LIMIT 1";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, parkingServiceID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    randomSlotNumber = resultSet.getInt("ParkingSlotNumber");
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return randomSlotNumber;
    }

    public static void updateParkingSpot(int parkingFacilityID, String TypeParking, String abilible) throws SQLException {

        int parkingSlotState = (abilible.equals("reservar")) ? 1 : 0;

        String query = "UPDATE " + TypeParking + " SET parkingSlotState = ? WHERE ParkingSlotFacilityID = ?";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, parkingSlotState);
            statement.setInt(2, parkingFacilityID);

            statement.executeUpdate();
        } finally {
            Conection.closeConnection();
        }
    }

}
