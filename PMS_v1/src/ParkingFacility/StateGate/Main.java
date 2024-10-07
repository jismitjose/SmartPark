package ParkingFacility.StateGate;

import java.util.Scanner;

public class Main {
    public static void main() {

        //código para probar que el gate sí está funcionando con el state design pattern
        Gate gate = new Gate();
        Scanner scanner = new Scanner(System.in);

            gate.control();
            System.out.print("Press 'Enter' " );
            //String temp=scanner.nextLine();
            System.out.println("You entered : ");
            gate.control();

    }
}