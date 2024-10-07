package Menu;

import java.sql.SQLException;
import java.util.Scanner;
import Online.QRCode;
import Online.preBooking;

public class Menu_Booking {

    public static String MyQr1;
    public static void MainMenuBooking() throws SQLException {
        int type = 0; String TypeParking = null;

        int choice;
        boolean isReserved = false;

        do {
            System.out.println("1. Reserve");
            System.out.println("2. Get QR Code");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    if (!isReserved) {

                        System.out.println("Chose what tipe of Parking do you want");
                        System.out.println("1 - Primiun");
                        System.out.println("2 - Standar");
                        System.out.println("3 - Facility");
                        System.out.println("4 - Return");

                            type = scanner.nextInt();
                            switch (type) {
                                case 1:
                                    TypeParking = "3377c27f-6be4-40bd-aa08-66e329d4c000";
                                    break;
                                case 2:
                                    TypeParking = "dab5a94d-72a5-4558-abbf-5562599fb977";
                                    break;
                                case 3:
                                    TypeParking = "6d63d7f2-b29a-44a9-a298-4e7b4e9af81f";
                                    break;
                                case 4:
                                    System.out.println("Return.");
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                                    break;
                            }

                        if (TypeParking != null){

                            int number = preBooking.getRandomFreeParkingSlot(TypeParking);

                            preBooking.updateParkingSpot(number,"ParkingSlotsBooking","reservar");

                            System.out.println("Reservation successful.");
                            System.out.println("-----------------------");
                            System.out.println("Your Parking Spot is:" + number);
                            System.out.println("Now you can get the QR code!");
                            isReserved = true;
                            QRCode MyQr = new QRCode(TypeParking);
                            MyQr1 = MyQr.getCode();


                        }

                    } else {
                        System.out.println("You already have an active reservation.");
                    }
                    break;
                case 2:
                    if (isReserved) {
                        System.out.println(MyQr1);
                        System.out.println("QR code obtained. Enjoy your reservation!");
                    } else {
                        System.out.println("You need to make a reservation first.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (choice != 3);
    }
}