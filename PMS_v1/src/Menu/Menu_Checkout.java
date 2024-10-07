package Menu;

import ParkingFacility.BParkingType.ParkingType;
import ParkingFacility.BWashingService.WashingServiceType;
import ParkingFacility.Ticket;

import java.sql.SQLException;
import java.util.Scanner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;

public class Menu_Checkout {

    private static Scanner scanner = new Scanner(System.in);
    private static double Parking_Rate;
    private static double Washing_Rate;
    private static double TimeXRate;
    private static double Total;
    private static double cashAmount;
    private static Boolean Recive = false;
    private static String Invoice;

    private static long secondsElapsed;

    // Checkout menu options
    public static void MainMenuCheckout() throws SQLException {
        boolean quitCheckout = false;
        byte choiceMenuCheckout = 0;

        do {
            System.out.println("CHECK-OUT MENU");
            System.out.println("1. Check Out");
            System.out.println("2. Print Receipt");
            System.out.println("3. Go back to Main Menu");
            System.out.println("0. Exit");

            Scanner scanner = new Scanner(System.in);
            try {
                choiceMenuCheckout = scanner.nextByte();
            } catch (java.util.InputMismatchException e) {
                System.err.println("Please enter a valid option: ");
            }

            if (choiceMenuCheckout >= 0 && choiceMenuCheckout <= 3) {
                switch (choiceMenuCheckout) {
                    case 1: {
                        processPayment();
                        break;
                    }
                    case 2: {
                        printReceipt();
                        break;
                    }
                    case 3: {
                        quitCheckout = true;
                        break;
                    }
                    case 0: {
                        System.out.println("Exiting Check-out menu.");
                    }
                }
            } else {
                System.out.println("Please enter a valid input: ");
            }

        } while (!quitCheckout);
    }

    // Function to simulate payment processing
    private static void processPayment() throws SQLException {


        Parking_Rate = ParkingType.getRate();
        Washing_Rate = WashingServiceType.getRate();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Show the number of your ticket please :");
        int ticket = scanner.nextInt();
        String ChekIn = Ticket.getDateCheckin(ticket);



        if (ChekIn != null){

            // Convert the stored timestamp string to a Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date storedTimestamp = null;
            try {
                storedTimestamp = dateFormat.parse(ChekIn);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            // Get the current date and time
            Date currentDate = new Date();

            // Calculate the time difference in milliseconds
            long timeDifferenceInMillis = currentDate.getTime() - storedTimestamp.getTime();
            // Calculate the time difference in seconds
            secondsElapsed = timeDifferenceInMillis / 1000;


            Parking_Rate = calculateRatePerMinute(Parking_Rate /60);
            TimeXRate = calculateRatePerMinute( Parking_Rate * secondsElapsed );
            Total  = calculateRatePerMinute( TimeXRate + Washing_Rate  );

            System.out.println("Time (seconds) elapsed since stored timestamp: " + secondsElapsed + " Minuts ");
            System.out.println("Rate for Parking   : " + Parking_Rate);
            System.out.println("Time by rate       : " + TimeXRate );
            System.out.println("Washing Services   : " + Washing_Rate );
            System.out.println("Total Services      : " + Total );

            System.out.println("Select Payment Method:");
            System.out.println("1. Cash");
            System.out.println("2. Debit Card");
            System.out.println("3. Credit Card");

            int paymentMethodChoice = scanner.nextInt();

            switch (paymentMethodChoice) {
                case 1: {
                    payWithCash(Total);
                    break;
                }
                case 2: {
                    payWithDebitCard(Total);
                    break;
                }
                case 3: {
                    payWithCreditCard(Total);
                    break;
                }
                default: {
                    System.out.println("Invalid payment method choice.");
                }
            }

            Recive = true;
            String formattedDate = dateFormat.format(currentDate);
            Invoice = Ticket.updateTicketInvoice(ticket, formattedDate);
            printReceipt();

        }

    }

    // Function to simulate paying with cash
    private static void payWithCash(double totalAmount) {
        System.out.println("Amount to Pay: $" + totalAmount);

        // Prompt the user to enter the cash amount
        System.out.print("Enter the amount in cash: $");
        cashAmount = scanner.nextDouble();

        // Calculate and print the change
        double change = cashAmount - totalAmount;
        System.out.println("Processing cash payment...");
        System.out.println("Received: $" + cashAmount);
        System.out.println("Change: $" + change);
        System.out.println("Payment with cash successful!");
    }

    // Function to simulate paying with a debit card
    private static void payWithDebitCard(double totalAmount) {
        System.out.println("Amount to Pay: $" + totalAmount);

        // Simulate inserting the debit card
        System.out.println("Insert your debit card...");

        // Simulate processing the payment
        System.out.println("Processing debit card payment...");
        System.out.print("...");

        // Simulate successful payment
        System.out.println("\nDebit card payment successful!");
    }

    // Function to simulate paying with a credit card
    private static void payWithCreditCard(double totalAmount) {
        System.out.println("Amount to Pay: $" + totalAmount);

        // Simulate inserting the credit card
        System.out.println("Insert your credit card...");

        // Simulate processing the payment
        System.out.println("Processing credit card payment...");
        System.out.print("...");

        // Simulate successful payment
        System.out.println("\nCredit card payment successful!");
    }

    // Function to simulate receipt printing

    public static double calculateRatePerMinute(double parkingRate) {
        double ratePerMinute = parkingRate / 60;

        // Use DecimalFormat to round the result to two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.parseDouble(decimalFormat.format(ratePerMinute));
    }

    public static void printReceipt(){
        if (Recive){
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Invoice:  " + Invoice );
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Time (seconds) elapsed since stored timestamp: " + secondsElapsed + " Minuts ");
            System.out.println("Rate for Parking   : " + Parking_Rate);
            System.out.println("Time by rate       : " + TimeXRate );
            System.out.println("Washing Services   : " + Washing_Rate );
            System.out.println("Total Services      : " + Total );
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println(" Bye, Have a good day");
            System.out.println(" ");
            System.out.println(" ");
        }
        else {
            System.out.println("You dont have any recibe ");
        }

    }


}