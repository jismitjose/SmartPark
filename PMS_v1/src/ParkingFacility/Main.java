package ParkingFacility;
import DataValidation.D_VAndFuntion;
import Menu.Menu_Manager;
import Menu.Menu_PMS;
import Menu.Menu_Ticket;
import ParkingFacility.BParkingType.*;
import Menu.Menu_Rates;
import DatabaseConnection.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import DatabaseConnection.GetInfoFromTable;
import Menu.Menu_Rates.*;
import ParkingFacility.BWashingService.*;
import ParkingFacility.StateGate.Gate;

import java.time.LocalDateTime;

public class Main {
    // Creating parking type objects
    public static ParkingBuilder bStandardParking = new BStandardParking();
    public static Caster casterStdParking = new Caster(bStandardParking);
    public static ParkingBuilder bPremiumParking = new BPremiumParking();
    public static Caster casterPrmParking = new Caster(bPremiumParking);
    public static ParkingBuilder bAccessibleParking = new BAccessibleParking();
    public static Caster casterAccParking = new Caster(bAccessibleParking);

    // creating washing service type objects
    public static IWashingBuilder bStandardWashing = new BStandardWashing();
    public static CasterWashing casterStdWashing = new CasterWashing(bStandardWashing);
    public static IWashingBuilder bPremiumWashing = new BPremiumWashing();
    public static CasterWashing casterPrmWashing = new CasterWashing(bPremiumWashing);
    public static IWashingBuilder bDeluxeWashing = new BDeluxeWashing();
    public static CasterWashing casterDlxWashing = new CasterWashing(bDeluxeWashing);

    // creating discount types
    public static Discount managerDisc = new Discount("c0663da4-5772-4364-a3dd0-f08d70f44f21", "Manager discount", 0.1);
    public static Discount partnerDisc = new Discount("959f82a1-043d-4368-a36c2-b46b97893b2f", "Business partners", 0.5);
    public static Discount officeDisc = new Discount("fc95d6a9-f4cc-429e-a929-d66d74673d26", "Building offices", 0.122);

    public static final String DB_NAME = "SmartParking.db";
    public static String CONNECTION_STRING;

    public static String stdPrkgUUID = "3377c27f-6be4-40bd-aa08-66e329d4c000";
    public static String prmPrkgUUID = "dab5a94d-72a5-4558-abbf-5562599fb977";
    public static String accPrkgUUID = "6d63d7f2-b29a-44a9-a298-4e7b4e9af81f";
    public static String stdSrvcUUID = "7f6fe816-0467-4669-a3d6-29b380cd6bd1";
    public static String prmSrvcUUID = "2093b37c-1650-4022-a36b8-6d6d4d316617";
    public static String delSrvcUUID = "aeeaaf28-09c0-4b85-a3587-635b742ac0e6";

    public static LocalDateTime defaultDate = LocalDateTime.of(1999,1,1, 0, 0);
    public static Ticket globalTicket = new Ticket(0, 0, defaultDate, defaultDate,"", 0, "", "");
    public static Gate parkingGate = new Gate();

    public static String cargoLaboral = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

       Class.forName("org.sqlite.JDBC");
       String currentDirectory =  System.getProperty("user.dir");
       CONNECTION_STRING = "jdbc:sqlite:" + currentDirectory + File.separator + "SmartParking" + File.separator + "SmartParking.db";

        casterStdParking.CreateParkingType();
        casterPrmParking.CreateParkingType();
        casterAccParking.CreateParkingType();

        //Create washing service types
        casterStdWashing.CreateWashingServiceType();
        casterPrmWashing.CreateWashingServiceType();
        casterDlxWashing.CreateWashingServiceType();

        Menu_PMS.MainMenuPMS();

    }

}