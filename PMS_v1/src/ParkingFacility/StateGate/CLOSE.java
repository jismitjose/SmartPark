package ParkingFacility.StateGate;

public class CLOSE extends State{
    private static CLOSE instance = new CLOSE();
    //singleton design pattern : one copy of the object needed.
    private CLOSE(){}


    public static CLOSE Instance(){
        return instance;
    }
}
