package ParkingFacility;
import DataValidation.D_VAndFuntion;
import ParkingFacility.BParkingType.ParkingType;

import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.util.Date;

import DatabaseConnection.*;



import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket {
    private static int ticketNumber;
    private static int parkingSlotNumber;
    private LocalDateTime dateCheck_in;
    private LocalDateTime dateCheck_out;
    private static String licencePlateCar;
    private int washingCar;
    private static String washingServiceID;
    private static String parkingServiceID;


    public Ticket(int TicketNumber, int ParkingSlotNumber, LocalDateTime DateCheck_in, LocalDateTime DateCheck_out, String LicencePlateCar, int washingCar, String WashingServiceID, String ParkingServiceID) {

        this.ticketNumber = TicketNumber;
        this.parkingSlotNumber = ParkingSlotNumber;
        this.dateCheck_in = DateCheck_in;
        this.dateCheck_out = DateCheck_out;
        this.licencePlateCar = LicencePlateCar;
        this.washingCar = washingCar;
        this.washingServiceID = WashingServiceID;
        this.parkingServiceID = ParkingServiceID;
    }

    public Ticket(String licensePlateCar, ParkingType parkingType) {
    }

    public int getTicketNumber() {return ticketNumber;}
    public void setTicketNumber(int TicketNumber) { this.ticketNumber = TicketNumber; }
    public int getParkingSlotNumber() { return parkingSlotNumber; }
    public void setParkingSlotNumber(int ParkingSlotNumber) {this.parkingSlotNumber = ParkingSlotNumber; }
    public LocalDateTime getDateCheck_in(){ return dateCheck_in; }
    public void setDateCheck_in (LocalDateTime DateCheck_in){ this.dateCheck_in= DateCheck_in; }
    public LocalDateTime getDateCheck_out(){ return dateCheck_out; }
    public void setDateCheck_out (LocalDateTime DateCheck_out) { this.dateCheck_out= DateCheck_out; }
    public String getLicencePlateCar(){ return licencePlateCar; }
    public void setLicencePlateCar (String LicencePlateCar){ this.licencePlateCar= LicencePlateCar; }
    public int getWashingCar(){ return washingCar; }
    public void setWashingCar(int WashingCar){ this.washingCar = WashingCar; }
    public String getWashingServiceID() { return washingServiceID; }
    public void setWashingServiceID(String WashingServiceID){ this.washingServiceID = WashingServiceID; }
    public String getParkingServiceID() { return parkingServiceID; }
    public void setParkingServiceID(String ParkingServiceID){ this.parkingServiceID = ParkingServiceID; }
    public void timeStay(){ System.out.println(); }


    public void displayTicketIn(){
        System.out.println("Ticket Number: " + this.ticketNumber);
        System.out.println("Parking Slot Number: " + this.parkingSlotNumber);
        System.out.println("Check-in Date: " + this.dateCheck_in);
        System.out.println("License Plate: " + this.licencePlateCar);
        System.out.println("Parking Service ID: " + this.parkingServiceID);
        if (this.washingCar == 1){
            System.out.println("Washing Service ID: " + this.washingServiceID);
        }
        else { System.out.println(); }
    }
    public void displayTicketOut(){
        System.out.println("Ticket Number: " + this.ticketNumber);
        System.out.println("Parking Slot Number: " + this.parkingSlotNumber);
        System.out.println("Check-in Date: " + this.dateCheck_in);
        System.out.println("Check-out Date: " + this.dateCheck_out);
        System.out.println("License Plate: " + this.licencePlateCar);
        System.out.println("Washing Car: " + this.washingCar);
        System.out.println("Washing Service ID: " + this.washingServiceID);
        System.out.println("Parking Service ID: " + this.parkingServiceID);
    }
    // CREATE
    public static void createTicket(String BookingNumber) throws SQLException {
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Tickets\n" +
                     "(LicensePlate, dateCheckin, dateCheckout, ParkingServiceID, WashingServiceID, ParkingSlotNumber, TicketBooking)\n" +
                     "VALUES (?, ?, ?, ?, ?, ? , ?)")) {


            if (BookingNumber != null){
                getParkingServiceIDByCode(BookingNumber);
            }

            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(currentDate);

            statement.setString(1, licencePlateCar);


            //Timestamp.valueOf(formattedDate)
            statement.setString(2, formattedDate);
            statement.setString(3, formattedDate);

            statement.setString(4, parkingServiceID);
            statement.setString(5, washingServiceID);
            statement.setInt(6, parkingSlotNumber);
            statement.setString(7, BookingNumber);
            statement.executeUpdate();

            getLastTicketNumber();

        } finally {
            Conection.closeConnection();
        }
    }
    public static void updateParkingSpot(String TypeParking) throws SQLException {

        String query = "UPDATE " + TypeParking + " SET parkingSlotState = ? WHERE ParkingSlotFacilityID = ?";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, 0);
            statement.setInt(2, parkingSlotNumber);

            statement.executeUpdate();
        } finally {
            Conection.closeConnection();
        }
    }



    // READ
    public static Ticket readTicketByNumber(int ticketNumber) throws SQLException {
        String query = "SELECT * FROM Tickets WHERE TicketNumber = ?";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, ticketNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    String licensePlate = resultSet.getString("LicensePlate");
                    String dateCheckin = resultSet.getString("dateCheckin");
                    String dateCheckout = resultSet.getString("dateCheckout");
                    String parkingServiceID = resultSet.getString("ParkingServiceID");
                    String washingServiceID = resultSet.getString("WashingServiceID");
                    int parkingSlotNumber = resultSet.getInt("ParkingSlotNumber");

                    System.out.println("Ticket encontrado:");
                    System.out.println("Número de ticket: " + ticketNumber);
                    System.out.println("Placa de licencia: " + licensePlate);
                    System.out.println("Fecha de check-in: " + dateCheckin);
                    System.out.println("Fecha de check-out: " + dateCheckout);
                    System.out.println("ID del servicio de estacionamiento: " + parkingServiceID);
                    System.out.println("ID del servicio de lavado: " + washingServiceID);
                    System.out.println("Número de espacio de estacionamiento: " + parkingSlotNumber);

                    // Otros campos del ticket
                } else {
                    System.out.println("No se encontró ningún ticket con el número proporcionado.");
                }
            }
        } finally {
            Conection.closeConnection();
        }
        return null; // Devolver null si no se encuentra el ticket con el número proporcionado
    }

    public static void readAllTickets() throws SQLException {
        String query = "SELECT * FROM Tickets";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            // Imprimir encabezados de la tabla
            System.out.printf("%-15s %-15s %-20s %-20s %-20s %-20s %-20s%n",
                    "TicketNumber", "LicensePlate", "dateCheckin", "dateCheckout",
                    "ParkingServiceID", "WashingServiceID", "ParkingSlotNumber");
            System.out.println("-----------------------------------------------------------");

            while (resultSet.next()) {
                int ticketNumber = resultSet.getInt("TicketNumber");
                String licensePlate = resultSet.getString("LicensePlate");
                String dateCheckin = resultSet.getString("dateCheckin");
                String dateCheckout = resultSet.getString("dateCheckout");
                String parkingServiceID = resultSet.getString("ParkingServiceID");
                String washingServiceID = resultSet.getString("WashingServiceID");
                int parkingSlotNumber = resultSet.getInt("ParkingSlotNumber");

                // Imprimir fila de la tabla
                System.out.printf("%-15d %-15s %-20s %-20s %-20s %-20s %-20d%n",
                        ticketNumber, licensePlate, dateCheckin, dateCheckout,
                        parkingServiceID, washingServiceID, parkingSlotNumber);
            }
        } finally {
            Conection.closeConnection();
        }
    }
    public static int getLastTicketNumber() throws SQLException {
        int lastTicketNumber = -1; // Valor predeterminado en caso de que no haya resultados
        String query = "SELECT * FROM Tickets ORDER BY TicketNumber DESC LIMIT 1";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                ticketNumber = resultSet.getInt("TicketNumber");
            }
        } finally {
            Conection.closeConnection();
        }
        return lastTicketNumber;
    }
    public static String getParkingServiceIDByCode(String code) {

        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT ParkingServiceID FROM BookingTickek WHERE TicketCode = ?")) {

            statement.setString(1, code);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    parkingServiceID = resultSet.getString("ParkingServiceID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones (puedes cambiar esto según tus necesidades)
        } finally {
            Conection.closeConnection();
        }

        return parkingServiceID;
    }
    // UPDATE
    private static void updateTicket(int ticketNumber, String newLicensePlate, String newDateCheckin, String newDateCheckout, String newParkingServiceID, String newWashingServiceID, int newParkingSlotNumber) throws SQLException {
        String query = "UPDATE Tickets SET LicensePlate = ?, dateCheckin = ?, dateCheckout = ?, ParkingServiceID = ?, WashingServiceID = ?, ParkingSlotNumber = ? WHERE TicketNumber = ?";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newLicensePlate);
            statement.setString(2, newDateCheckin);
            statement.setString(3, newDateCheckout);
            statement.setString(4, newParkingServiceID);
            statement.setString(5, newWashingServiceID);
            statement.setInt(6, newParkingSlotNumber);
            statement.setInt(7, ticketNumber);

            statement.executeUpdate();
        } finally {
            Conection.closeConnection();
        }
    }
    public static String updateTicketInvoice(int ticketNumber, String CheckOut) throws SQLException {
        String query = "UPDATE Tickets SET Invoice = ?, dateCheckout = ? WHERE TicketNumber = ?";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            int inv = getLastInvoiceNumber();
            inv += 1;
            String invoice = String.valueOf(inv);

            statement.setString(1, invoice);
            statement.setString(2, CheckOut);
            statement.setInt(3, ticketNumber);

            updateParkingSpot("ParkingSlotsBooking");

            statement.executeUpdate();

             return  invoice;
        } finally {
            Conection.closeConnection();
        }
    }
    public static int getLastInvoiceNumber() throws SQLException {
        int lastInvoiceNumber = -1; // Default value in case there are no results
        String query = "SELECT COALESCE(Invoice, 0) AS LastInvoice FROM Tickets ORDER BY TicketNumber DESC LIMIT 1";

        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                lastInvoiceNumber = resultSet.getInt("LastInvoice");
            }
        } finally {
            Conection.closeConnection();
        }

        return lastInvoiceNumber;
    }
    public static void updateTicketPrint() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // Obtener el número de ticket que se actualizará
        int ticketNumber = D_VAndFuntion.validateInt("Ingrese el número de ticket a actualizar: ");
        scanner.nextLine();

        boolean validate = validateTicketNumber(ticketNumber);

        if (validate) {
            readTicketByNumber(ticketNumber);

            // Obtener los nuevos valores del ticket
            String newLicensePlate = D_VAndFuntion.ValidateLicencePlate("Ingrese la nueva placa de licencia: ");
            String newDateCheckin = D_VAndFuntion.validateDate("Ingrese la nueva fecha de check-in: ");
            String newDateCheckout = D_VAndFuntion.validateDate("Ingrese la nueva fecha de check-out: ");
            String newParkingServiceID = D_VAndFuntion.ValidateText("Ingrese el nuevo ID del servicio de estacionamiento: ");
            String newWashingServiceID = D_VAndFuntion.ValidateText("Ingrese el nuevo ID del servicio de lavado: ");
            int newParkingSlotNumber = D_VAndFuntion.validateInt("Ingrese el nuevo número de espacio de estacionamiento: ");

            try {
                updateTicket(ticketNumber, newLicensePlate, newDateCheckin, newDateCheckout, newParkingServiceID, newWashingServiceID, newParkingSlotNumber);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Ticket actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún ticket con el número proporcionado.");
        }
    }
    public static void deleteTicket(int ticketNumber) throws SQLException {
        String query = "DELETE FROM Tickets WHERE TicketNumber = ?";
        try (Connection connection = Conection.Openconextion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, ticketNumber);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Ticket eliminado correctamente con número: " + ticketNumber);
            } else {
                System.out.println("No se encontró ningún ticket con el número proporcionado.");
            }
        } finally {
            Conection.closeConnection();
        }
    }
    public static boolean validateTicketNumber(int ticketNumber) {
        try {
            // Consulta SQL para seleccionar todos los registros de la tabla Tickets
            String query = "SELECT * FROM Tickets WHERE TicketNumber = ?";
            try (Connection connection = Conection.Openconextion();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, ticketNumber);
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Retorna true si al menos hay un resultado
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna false si hay algún error o si no hay resultados
        return false;
    }
    public static String getDateCheckin(int ticketNumber) {
        try {
            // SQL query to select dateCheckin for a specific ticket number
            String query = "SELECT dateCheckin FROM Tickets WHERE TicketNumber = ?";
            try (Connection connection = Conection.Openconextion();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, ticketNumber);
                try (ResultSet resultSet = statement.executeQuery()) {
                    // If a result is found, return the dateCheckin value
                    if (resultSet.next()) {
                        return resultSet.getString("dateCheckin");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return null if there is an error or no results are found
        return null;
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
}