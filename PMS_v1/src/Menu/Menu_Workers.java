package Menu;

import java.sql.SQLException;
import java.util.Scanner;

import DataValidation.D_VAndFuntion;
import Worker.Login;
import Worker.Users;

public class Menu_Workers {

    public static void MainMenuWorker() throws SQLException {



            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("Parking Management System Menu: Worker");
                System.out.println("1. Service Rates");
                System.out.println("2. Reports");
                System.out.println("0. Exit");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1: {
                        Menu_Rates.MainMenuRates();
                        break;
                    }
                    case 2:
                    {
                        Menu_Reports.MainMenuReports();
                        break;
                    }
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } while (choice != 0);

    }
}
