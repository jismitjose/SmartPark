package Menu;

import ParkingFacility.Main;
import Worker.Login;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu_PMS {

    //Main menu for PMS options
    public static void MainMenuPMS() throws SQLException {
        // creating the options for menu rates
        boolean quit = false;
        byte choiceMenuPMS = 0;           // variable for selecting the option inside the menu
        do {
            System.out.println("WELCOME TO SMART PARKING");
            System.out.println("1. Online booking");
            System.out.println("2. Accessing parking facility");
            System.out.println("3. Check-out");

            if (Main.cargoLaboral == null){
                System.out.println("4. Login Users");
            }
            else{
                System.out.println("4. Log Out");
                System.out.println("5. Sistem for " + Main.cargoLaboral);
            }

            System.out.println("0. Exit");

            // using try catch to verify the user is typing a valid option
            Scanner scanner1 = new Scanner(System.in);
            try {
                choiceMenuPMS = scanner1.nextByte();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }

            if (choiceMenuPMS >= 0 && choiceMenuPMS <= 5) {
                switch (choiceMenuPMS) {
                    // user wants to perform an online booking
                    case 1: {
                        Menu_Booking.MainMenuBooking();
                        break;
                    }
                    // user wants to access the parking facility
                    case 2: {
                        Menu_Ticket.MainMenuTicket();
                        break;
                    }
                    // the user wants to checkout and exit the facility
                    case 3: {
                        Menu_Checkout.MainMenuCheckout();
                        break;
                    }
                    // the manager wants to access the system
                    case 4: {

                        if (Main.cargoLaboral == null){

                            if (Login.Access()) {
                                System.out.println(Main.cargoLaboral);

                                if (Main.cargoLaboral.equals("Manager")){
                                    Menu_Manager.MainMenuManager();
                                }
                                else{
                                    Menu_Workers.MainMenuWorker();
                                }
                            }
                            }
                            else {
                                System.out.println("Log out Succesful");
                                Main.cargoLaboral = null;
                            }
                        break;
                    }
                    case 5: {

                        if (Main.cargoLaboral != null) {
                            if (Main.cargoLaboral.equals("Manager")){
                                Menu_Manager.MainMenuManager();
                            } else {
                                Menu_Workers.MainMenuWorker();
                            }
                        }
                        break;
                    }

                    // exit the application
                    case 0: {
                        quit = true;
                    }
                }
            } else {
                System.out.println("Please enter a valid input: ");
            }
        } while (!quit);
    }
}
