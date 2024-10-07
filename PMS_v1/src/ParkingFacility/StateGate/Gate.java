package ParkingFacility.StateGate;

public class Gate {
    State current ;
    public State getState() {
        return this.current;
    }
    public void setState(State state) {
        this.current = state;
    }
    public Gate(){
        this.current= CLOSE.Instance();
    }
    public void control(){ //what control does depends on the internal state of this object
        this.current.control(this);
    }
}

