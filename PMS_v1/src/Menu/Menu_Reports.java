package Menu;
import DataValidation.D_VAndFuntion;
import java.util.Scanner;
import static ParkingFacility.Main.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Menu_Reports {
    public static void MainMenuReports(){
        // creating the options for menu reports
        boolean quit = false;
        byte choiceMenuReports = 0;           // variable for selecting the option inside the Service Rates menu
        do {
            System.out.println("MAIN MENU REPORTS");
            System.out.println("1. Parking slots occupancy report");
            System.out.println("2. Invoice reports");
            System.out.println("3. Return to previous menu");

            // using try catch to verify the user is typing a valid option
            Scanner scanner1 = new Scanner(System.in);
            try {
                choiceMenuReports = scanner1.nextByte();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }

            if (choiceMenuReports >= 1 && choiceMenuReports <= 3) {
                switch (choiceMenuReports) {
                    // user wants to generate parking slots occupancy report
                    case 1: {
                        MenuParkingSlotsReports();
                        break;
                    }
                    // user wants to generate invoice reports
                    case 2: {
                        MenuInvoiceReports();
                        break;
                    }
                    case 3: {
                        quit = true;
                    }
                }
            } else {
                System.out.println("Please enter a valid input: ");
            }
        } while (!quit);
    }

    public static void MenuParkingSlotsReports(){
        // creating the options for sub-menu setting the rates
        boolean quit = false;
        byte choiceMenuOccupancy = 0;           // variable for selecting the option inside the Service Rates menu
        do {
            System.out.println("PARKING SLOTS OCCUPANCY REPORTS");
            System.out.println("Please select the service you want to update the rate");
            System.out.println("1. Parking slots facility report");
            System.out.println("2. Parking slots prebooking report");
            System.out.println("3. Return to previous menu");

            // using try catch to verify the user is typing a valid option
            Scanner scanner1 = new Scanner(System.in);
            try {
                choiceMenuOccupancy = scanner1.nextByte();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }

            if (choiceMenuOccupancy >= 1 && choiceMenuOccupancy <= 3) {
                switch (choiceMenuOccupancy) {
                    // user wants to generate the occupancy reports
                    case 1: {
                        // parking slots facility
                        generateOccupancyReportFacility();
                        break;
                    }
                    // parking slots prebooking
                    case 2: {
                        System.out.println("add menu for parking slots booking report here");
                        break;
                    }
                    // return to the previous menu
                    case 3: {
                        quit = true;
                    }
                }
            } else {
                System.out.println("Please enter a valid input: ");
            }
        } while (!quit);

    }

    public static void generateOccupancyReportFacility(){

        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = conn.createStatement()) {

            String query = "SELECT PSF.ParkingServiceID, " +
                    "       PS.ParkingTypeName, " +
                    "       SUM(CASE WHEN PSF.parkingSlotState = 0 THEN 1 ELSE 0 END) AS EmptySlots, " +
                    "       SUM(CASE WHEN PSF.parkingSlotState = 1 THEN 1 ELSE 0 END) AS UsedSlots " +
                    "FROM ParkingSlotsFacility PSF " +
                    "JOIN ParkingServices PS ON PSF.ParkingServiceID = PS.ParkingTypeID " +
                    "GROUP BY PSF.ParkingServiceID";

            ResultSet results = statement.executeQuery(query);

            // Processing the results and presenting them in console
            while (results.next()) {
                String parkingServiceID = results.getString("ParkingServiceID");
                String parkingTypeName = results.getString("ParkingTypeName");
                int empty = results.getInt("EmptySlots");
                int used = results.getInt("UsedSlots");

                System.out.println("Parking Type Name: " + parkingTypeName);
                System.out.println("Empty Parking Slots: " + empty);
                System.out.println("Used Parking Slots: " + used);
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void MenuInvoiceReports(){


    }

}
