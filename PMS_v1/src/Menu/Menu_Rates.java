package Menu;
import DataValidation.D_VAndFuntion;
import DatabaseConnection.GetInfoFromTable;
import ParkingFacility.BParkingType.Caster;
import ParkingFacility.BParkingType.ParkingType;
import static DatabaseConnection.ManageRates.updateParkingRate;
import static DatabaseConnection.ManageRates.updateWashingServiceRate;
import static ParkingFacility.Main.*;

import DatabaseConnection.ManageRates.*;
import java.util.Scanner;
import DataValidation.D_VAndFuntion.*;

public class Menu_Rates {

    //Main menu for "RATES"
    public static void MainMenuRates(){
        // creating the options for menu rates
        boolean quit = false;
        byte choiceMenuRates = 0;           // variable for selecting the option inside the Service Rates menu
        do {
            System.out.println("MAIN MENU RATES");
            System.out.println("1. Show services rates");
            System.out.println("2. Update services rates");
            System.out.println("3. Return to previous menu");

            // using try catch to verify the user is typing a valid option
            Scanner scanner1 = new Scanner(System.in);
            try {
                choiceMenuRates = scanner1.nextByte();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }

            if (choiceMenuRates >= 1 && choiceMenuRates <= 3) {
                switch (choiceMenuRates) {
                    // user wants to display the service rates
                    case 1: {
                        System.out.println( "Service Name \tRate");
                        displayParkingRates();
                        break;
                    }
                    // user wants to set the rate
                    case 2: {
                        MenuSetRates();
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
    public static void MenuSetRates(){
        // creating the options for sub-menu setting the rates
        boolean quit = false;
        byte choiceMenuSetRates = 0;           // variable for selecting the option inside the Service Rates menu
        do {
            System.out.println("UPDATE RATES");
            System.out.println("Please select the service you want to update the rate");
            System.out.println("1. Standard Parking");
            System.out.println("2. Premium Parking");
            System.out.println("3. Accessible Parking");
            System.out.println("4. Standard Washing");
            System.out.println("5. Premium Washing");
            System.out.println("6. Deluxe Washing");
            System.out.println("7. Return to previous menu");

            // using try catch to verify the user is typing a valid option
            Scanner scanner1 = new Scanner(System.in);
            try {
                choiceMenuSetRates = scanner1.nextByte();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }

            if (choiceMenuSetRates >= 1 && choiceMenuSetRates <= 7) {
                switch (choiceMenuSetRates) {
                    // user wants to update service rate for Standard Parking Service
                    case 1: {
                        // standard parking
                        double newRate = D_VAndFuntion.getPositiveDouble("Please enter the new rate: ");
                        updateParkingRate(stdPrkgUUID, casterStdParking, newRate);
                        break;
                    }
                    // user wants to update service rate for Premium Parking Service
                    case 2: {
                        double newRate = D_VAndFuntion.getPositiveDouble("Please enter the new rate: ");
                        updateParkingRate(prmPrkgUUID, casterPrmParking, newRate);
                        break;
                    }
                    // user wants to update service rate for Accessible Parking Service
                    case 3: {
                        double newRate = D_VAndFuntion.getPositiveDouble("Please enter the new rate: ");
                        updateParkingRate(accPrkgUUID, casterAccParking, newRate);

                        break;
                    }
                    // user wants to update service rate for standard washing Service
                    case 4: {
                        double newRate = D_VAndFuntion.getPositiveDouble("Please enter the new rate: ");
                        updateWashingServiceRate(stdSrvcUUID,casterStdWashing,newRate);
                        break;
                    }
                    // user wants to update service rate for Premium washing Service
                    case 5: {
                        double newRate = D_VAndFuntion.getPositiveDouble("Please enter the new rate: ");
                        updateWashingServiceRate(prmSrvcUUID,casterPrmWashing,newRate);
                        break;
                    }
                    // user wants to update service rate for deluxe washing Service
                    case 6: {
                        double newRate = D_VAndFuntion.getPositiveDouble("Please enter the new rate: ");
                        updateWashingServiceRate(delSrvcUUID,casterDlxWashing,newRate);
                        break;
                    }
                    case 7: {
                        quit = true;
                    }
                }
            } else {
                System.out.println("Please enter a valid input: ");
            }
        } while (!quit);

    }

    //Display the parking rates for all 3 paring types
    public static void displayParkingRates(){
        GetInfoFromTable.getParkingTypeInfo(stdPrkgUUID, casterStdParking);
        casterStdParking.getParkingType().displayRates();

        GetInfoFromTable.getParkingTypeInfo(prmPrkgUUID, casterPrmParking);
        casterPrmParking.getParkingType().displayRates();

        GetInfoFromTable.getParkingTypeInfo(accPrkgUUID, casterAccParking);
        casterAccParking.getParkingType().displayRates();

        GetInfoFromTable.getWashingServicesInfo(stdSrvcUUID,casterStdWashing);
        casterStdWashing.getWashingServiceType().displayRates();

        GetInfoFromTable.getWashingServicesInfo(prmSrvcUUID,casterPrmWashing);
        casterPrmWashing.getWashingServiceType().displayRates();

        GetInfoFromTable.getWashingServicesInfo(delSrvcUUID,casterDlxWashing);
        casterDlxWashing.getWashingServiceType().displayRates();
    }


}
