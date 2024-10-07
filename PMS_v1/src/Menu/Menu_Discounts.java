package Menu;

import DataValidation.D_VAndFuntion;
import DatabaseConnection.GetInfoFromTable;

import java.util.Scanner;

import static DatabaseConnection.ManageRates.updateParkingRate;
import static DatabaseConnection.ManageRates.updateWashingServiceRate;
import static ParkingFacility.Main.*;
import static ParkingFacility.Main.casterDlxWashing;

public class Menu_Discounts {
    public static void MainMenuDiscounts(){
        // creating the options for menu discounts
        boolean quit = false;
        byte choiceMenuDiscount = 0;           // variable for selecting the option inside the Discounts Rates menu
        do {
            System.out.println("MAIN MENU DISCOUNTS");
            System.out.println("1. Show discounts");
            System.out.println("2. Update discount rates");
            System.out.println("3. Return to previous menu");

            // using try catch to verify the user is typing a valid option
            Scanner scanner1 = new Scanner(System.in);
            try {
                choiceMenuDiscount = scanner1.nextByte();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }

            if (choiceMenuDiscount >= 1 && choiceMenuDiscount <= 3) {
                switch (choiceMenuDiscount) {
                    // user wants to display the discount rates
                    case 1: {
                        System.out.println( "Discount\tRate");
                        displayDiscounts();
                        break;
                    }
                    // user wants to set the rate
                    case 2: {
                        MenuSetDiscounts();
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

    // SubMenu for setting the rates
    public static void MenuSetDiscounts(){
        // creating the options for sub-menu setting the discounts
        boolean quit = false;
        byte choiceMenuSetDiscount = 0;           // variable for selecting the option inside the Service Rates menu
        do {
            System.out.println("UPDATE DISCOUNTS");
            System.out.println("Please select the service you want to update the rate");
            System.out.println("1. Manager discount");
            System.out.println("2. Business partners discount");
            System.out.println("3. Building offices discount");
            System.out.println("4. Return to previous menu");

            // using try catch to verify the user is typing a valid option
            Scanner scanner1 = new Scanner(System.in);
            try {
                choiceMenuSetDiscount = scanner1.nextByte();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }

            if (choiceMenuSetDiscount >= 1 && choiceMenuSetDiscount <= 4) {
                switch (choiceMenuSetDiscount) {
                    // user wants to update manager discount rate
                    case 1: {
                        // manager discount
                        double newRate = D_VAndFuntion.getPositiveDouble("Please enter the new rate: ");
                        managerDisc.updateDiscountRate(newRate);
                        break;
                    }
                    // user wants to update business partner discount
                    case 2: {
                        double newRate = D_VAndFuntion.getPositiveDouble("Please enter the new rate: ");
                        partnerDisc.updateDiscountRate(newRate);
                        break;
                    }
                    // user wants to update building offices discount
                    case 3: {
                        double newRate = D_VAndFuntion.getPositiveDouble("Please enter the new rate: ");
                        officeDisc.updateDiscountRate(newRate);

                        break;
                    }
                    // user wants to return to the previous menu
                    case 4: {
                        quit = true;
                    }
                }
            } else {
                System.out.println("Please enter a valid input: ");
            }
        } while (!quit);

    }

    //Display the discount rates
    public static void displayDiscounts(){
        managerDisc.displayDiscountRate();
        partnerDisc.displayDiscountRate();
        officeDisc.displayDiscountRate();
    }


}
