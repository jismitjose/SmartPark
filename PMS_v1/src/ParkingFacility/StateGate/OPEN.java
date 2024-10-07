package ParkingFacility.StateGate;

public class OPEN extends State{
    private static OPEN instance = new OPEN();
    private OPEN(){}
    @Override
    public void control(Gate gate) {
        gate.setState(CLOSE.Instance());
        System.out.println("GATE CLOSED");
    }
    public static OPEN Instance(){
        return instance;
    }
}
