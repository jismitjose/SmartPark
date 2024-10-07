package ParkingFacility.BWashingService;
import ParkingFacility.BParkingType.ParkingType;

public class BStandardWashing implements IWashingBuilder{

    private static WashingServiceType washingServiceType;

    public BStandardWashing(){

        this.washingServiceType = new WashingServiceType();
    }

    public void buildWashingServiceTypeID() { washingServiceType.setWashingServiceTypeID(""); }
    public void buildWashingServiceTypeName() { washingServiceType.setWashingServiceTypeName("Standard Washing"); }
    public void buildCapacity() { washingServiceType.setCapacity(5);}
    public void buildRate(){ washingServiceType.setRate(20); }

    public WashingServiceType getWashingServiceType() { return this.washingServiceType; }

}
