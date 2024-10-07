package ParkingFacility.BWashingService;
import ParkingFacility.BParkingType.ParkingType;

public class BPremiumWashing implements IWashingBuilder{

    private static WashingServiceType washingServiceType;

    public BPremiumWashing(){

        this.washingServiceType = new WashingServiceType();
    }

    public void buildWashingServiceTypeID() { washingServiceType.setWashingServiceTypeID(""); }
    public void buildWashingServiceTypeName() { washingServiceType.setWashingServiceTypeName("Premium Washing"); }
    public void buildCapacity() { washingServiceType.setCapacity(5);}
    public void buildRate(){ washingServiceType.setRate(25); }

    public WashingServiceType getWashingServiceType() { return this.washingServiceType; }

}

