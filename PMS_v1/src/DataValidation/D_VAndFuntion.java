package DataValidation;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D_VAndFuntion {
    public static double getPositiveDouble(String textToDisplay) {
        Scanner scanner = new Scanner(System.in);
        double number = -1;  // Initialize with an invalid value

        while (true) {
            try {
                System.out.print(textToDisplay);
                String input = scanner.nextLine();
                number = Double.parseDouble(input);

                // Check if the number is positive
                if (number >= 0) {
                    break;  // Exit the loop if the number is valid
                } else {
                    System.out.println("Please enter a positive double. ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number. ");
            }
        }
        return number;
    }



    public static String ValidateLicencePlate(String prompt) {
        Scanner scanner = new Scanner(System.in);

        String licensePlate = null;
        boolean isValid = false;

        while (!isValid) {
            System.out.println(prompt);
            licensePlate = scanner.nextLine();
            isValid = Pattern.matches("^[A-Za-z][0-9][A-Za-z] [A-Za-z]{3}$", licensePlate);

            if (!isValid) {
                System.out.println("Please enter a valid license plate. Example 'A9A AAA' ");
            }
        }
        return licensePlate;
    }

    public static String ValidateText(String txt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(txt);
        boolean containsOnlyLetters = false;

        while (!containsOnlyLetters) {
            txt = new Scanner(System.in).nextLine();
            containsOnlyLetters = Pattern.matches("^[a-zA-Z0-9@._]+$", txt);

            if (!containsOnlyLetters) {
                System.out.println("El texto contiene caracteres que no son letras. " + txt);
            }
        }
        return txt;
    }
    public static int validateInt(String txt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(txt);
        boolean containsOnlyIntegers = false;

        while (!containsOnlyIntegers) {
            txt = scanner.nextLine();
            containsOnlyIntegers = Pattern.matches("^[+-]?\\d+$", txt);

            if (!containsOnlyIntegers) {
                System.out.println("La entrada no es un número entero. ");
            }
        }
        return Integer.parseInt(txt);
    }
    public static String validateDate(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        boolean isValidDate = false;
        String inputDate = "";

        while (!isValidDate) {
            inputDate = scanner.nextLine();
            isValidDate = Pattern.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", inputDate);

            if (!isValidDate) {
                System.out.println("Fecha no válida. Debe seguir el formato DD/MM/AAAA.");
                System.out.println(prompt);
            }
        }
        return inputDate;
    }

    public static void PrintHeadH1(String txt){
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println(txt);
        System.out.println("-------------------------------------");
        System.out.println();

    }


}
