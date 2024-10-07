package ParkingFacility.StateGate;

public class State {
    public void control(Gate gate) {

        gate.setState(OPEN.Instance());
        System.out.println("GATE OPEN");

    }

}
