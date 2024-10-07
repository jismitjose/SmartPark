package Menu;
import DataValidation.D_VAndFuntion;
import DatabaseConnection.GetInfoFromTable;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static DatabaseConnection.ManageRates.updateParkingRate;
import static DatabaseConnection.ManageRates.updateWashingServiceRate;
import static ParkingFacility.Main.*;
import static ParkingFacility.Main.stdPrkgUUID;
import static ParkingFacility.Main.prmPrkgUUID;
import static ParkingFacility.Main.accPrkgUUID;
import static ParkingFacility.Main.stdSrvcUUID;
import static ParkingFacility.Main.prmSrvcUUID;
import static ParkingFacility.Main.delSrvcUUID;
import Online.QRCode;
import ParkingFacility.Ticket;
import com.sun.tools.javac.Main;

public class Menu_Ticket {

    public static String qrCode;
    public static void MainMenuTicket() throws SQLException {

        System.out.println("PARKING FACILITY");
        System.out.println();

        selectParkingService();
        askLicensePlate();

        boolean quitWash = false;
        byte choiceWashing = 0;
        do {
            System.out.println("Do you want to add a washing service?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            // using try catch to verify the user is typing a valid option
            Scanner scanner1 = new Scanner(System.in);
            try {
                choiceWashing = scanner1.nextByte();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }

            if (choiceWashing >= 1 && choiceWashing <= 2) {
                switch (choiceWashing) {
                    // user wants to add a washing service
                    case 1: {
                        quitWash = true;
                        globalTicket.setWashingCar(1);
                        selectWashingService();
                        globalTicket.setDateCheck_in(LocalDateTime.now());
                        Ticket.createTicket(qrCode);
                        globalTicket.displayTicketIn();
                        break;
                    }
                    // user dont want to add a washing service
                    case 2: {
                        quitWash = true;
                        globalTicket.setWashingCar(0);
                        globalTicket.setDateCheck_in(LocalDateTime.now());
                        Ticket.createTicket(qrCode);
                        globalTicket.displayTicketIn();

                        break;
                    }
                }

                parkingGate.control();
                System.out.println(" . ");
                System.out.println(" .. ");
                System.out.println(" ... ");
                System.out.println(" .... ");
                System.out.println(" ..... ");
                System.out.println(" ...... ");
                System.out.println(" ..... ");
                System.out.println(" .... ");
                System.out.println(" ... ");
                System.out.println(" .. ");
                System.out.println(" . ");
                parkingGate.control();

            } else {
                System.out.println("Please enter a valid input: ");
            }
        } while (!quitWash);
    }

    public static void selectParkingService(){
        byte choiceParkingService =0;
        do {
            System.out.println("Select the parking service type: ");
            System.out.println("1. Standard Parking: ");
            System.out.println("2. Premium Parking: ");
            System.out.println("3. Accessible Parking: ");
            System.out.println("4. Booking Access: ");
            System.out.println("5. Return to previous menu \t");

            Scanner scanner2 = new Scanner(System.in);

            try {
                choiceParkingService = scanner2.nextByte();

            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }
        } while (choiceParkingService < 0 || choiceParkingService > 5);

        if (choiceParkingService >= 1 || choiceParkingService <= 5) {

            switch (choiceParkingService) {

                case 1: {
                    //Checking if there are available spaces in the selected category
                    int freeSlots = GetInfoFromTable.getFreeParkingSlotsCount(stdPrkgUUID);
                    if(freeSlots >=1 ){
                        //setting the parking service ID within the object Ticket
                        globalTicket.setParkingServiceID(stdPrkgUUID);
                        //looking for a parking slot number
                        int parkingSlot = GetInfoFromTable.getFreeParkingSlotsCount(stdPrkgUUID);
                        //assigning the slot number to the object ticket
                        globalTicket.setParkingSlotNumber(parkingSlot);
                        //changing the state of the slot in the DB
                        GetInfoFromTable.updateParkingSlotState(parkingSlot,1);
                    }
                    else{
                        System.out.println("There are no available parking slots in this category. Please select another category");
                    }
                }break;
                case 2: {
                    int freeSlots = GetInfoFromTable.getFreeParkingSlotsCount(prmPrkgUUID);
                    if(freeSlots >=1 ){
                        //setting the parking service ID within the object Ticket
                        globalTicket.setParkingServiceID(prmPrkgUUID);
                        //looking for a parking slot number
                        int parkingSlot = GetInfoFromTable.getFreeParkingSlotsCount(prmPrkgUUID);
                        //assigning the slot number to the object ticket
                        globalTicket.setParkingSlotNumber(parkingSlot);
                        //changing the state of the slot in the DB
                        GetInfoFromTable.updateParkingSlotState(parkingSlot,1);

                    }
                    else{
                        System.out.println("There are no available parking slots in this category. Please select another category");
                    }
                }break;
                case 3: {
                    int freeSlots = GetInfoFromTable.getFreeParkingSlotsCount(accPrkgUUID);
                    if(freeSlots >=1 ){
                        //setting the parking service ID within the object Ticket
                        globalTicket.setParkingServiceID(accPrkgUUID);
                        //looking for a parking slot number
                        int parkingSlot = GetInfoFromTable.getFreeParkingSlotsCount(accPrkgUUID);
                        //assigning the slot number to the object ticket
                        globalTicket.setParkingSlotNumber(parkingSlot);
                        //changing the state of the slot in the DB
                        GetInfoFromTable.updateParkingSlotState(parkingSlot,1);
                    }
                    else{
                        System.out.println("There are no available parking slots in this category. Please select another category");
                    }
                }break;
                case 4: {
                    accessBookingReservation();
                }break;
                case 5: {
                    System.out.println("Return MAIN MENU SERVICE");
                }
                break;
            }

        } else {
            System.out.println("Please enter a valid input: ");
        }
    }

    public static void accessBookingReservation() {
        qrCode = null;
        Boolean Enter = false;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Please enter your QR code or write 'X4' to return: ");

            try {
                qrCode = scanner.nextLine();

                if (qrCode.equalsIgnoreCase("X4")) {
                    // Si el código es 'X4', salimos del bucle
                    break;
                } else if (QRCode.isCodeExists(qrCode)) {
                    Enter = true;
                    break;
                } else {
                    System.out.println("QR code not found. Please enter a valid code.");
                }

            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option.");
                scanner.nextLine(); // Limpiar el buffer del scanner para evitar un bucle infinito
            }
        } while (!Enter);


    }

    public static void askLicensePlate() {
        String licencePlateCar = D_VAndFuntion.ValidateLicencePlate("Enter the car's licence place: ");
        globalTicket.setLicencePlateCar(licencePlateCar);
    }

    public static void selectWashingService(){
        byte choiceWashingService = 0;
        do {
            System.out.println("CHOICE A WASHING SERVICE TYPE");
            System.out.println("1. Standard washing service: ");
            System.out.println("2. Premium washing service: ");
            System.out.println("3. Deluxe washing service: ");
            System.out.println("4. Return to previous menu \t");

            Scanner scanner4 = new Scanner(System.in);

            try {
                choiceWashingService = scanner4.nextByte();

            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }
        } while (choiceWashingService < 0 || choiceWashingService > 4);

        if (choiceWashingService >= 1 || choiceWashingService <= 4) {

            switch (choiceWashingService) {

                case 1: {
                    globalTicket.setWashingServiceID(stdSrvcUUID);
                }
                break;
                case 2: {
                    globalTicket.setWashingServiceID(prmSrvcUUID);
                }
                break;
                case 3: {
                    globalTicket.setWashingServiceID(delSrvcUUID);
                }
                break;
                case 4: {
                    System.out.println("Return MAIN MENU SERVICE");
                }
                break;
            }
        } else {
            System.out.println("Please enter a valid input: ");
        }
    }
}
