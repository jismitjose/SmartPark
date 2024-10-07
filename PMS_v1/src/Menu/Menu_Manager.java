package Menu;

import java.sql.SQLException;
import java.util.Scanner;

import DataValidation.D_VAndFuntion;
import Worker.Login;
import Worker.Users;

public class Menu_Manager {
    private static Scanner scanner = new Scanner(System.in);
    public static void MainMenuManager() throws SQLException {



            Scanner scanner = new Scanner(System.in);
            int choice;

            do {

                System.out.println("Parking Management System Menu: Manager");
                System.out.println("1. Service Rates");
                System.out.println("2. Discounts");
                System.out.println("3. Reports");
                System.out.println("4. Users");
                System.out.println("0. Exit");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1: {
                        Menu_Rates.MainMenuRates();
                        break;
                    }
                    case 2:
                    {
                        Menu_Discounts.MainMenuDiscounts();
                        break;
                    }
                    case 3:
                    {
                        Menu_Reports.MainMenuReports();
                        break;
                    }
                    case 4:
                    {
                        Menu_WorkersPrint();
                        break;
                    }
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } while (choice != 0);

    }

    public static void Menu_WorkersPrint() throws SQLException {
        int choice;

        do {
            System.out.println("1. Create User");
            System.out.println("2. Show User");
            System.out.println("3. Update User");
            System.out.println("4. Detele User");
            System.out.println("5. Exit");
            System.out.print("Selecciona una opción: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de leer el entero

            switch (choice) {
                case 1:
                    createUserNew();
                    break;
                case 2:


                    do{
                        System.out.println("1. All User");
                        System.out.println("2. One User");
                        System.out.println("3. Return");

                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea después de leer el entero
                        switch (choice) {
                            case 1:

                                Users.readAllUsers();
                                break;
                            case 2:

                                int ID = D_VAndFuntion.validateInt("Enter the ID of the worker");
                                Users.readUsersById(ID);

                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid option. Try again.");
                                break;
                        }
                    }while (choice != 3);

                    break;
                case 3:
                    Users.UpdateUserPrint();
                    break;
                case 4:
                    Users.deleteUsers();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        } while (choice != 5);
    }

    private static void createUserNew() throws SQLException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter telephone: ");
        String telephone = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter type of worker: ");
        String typeWorker = scanner.nextLine();


        Users.createUsers(username, password, name, lastName, email, telephone, address, typeWorker);

    }
}

