package ParkingFacility.BWashingService;

public class BDeluxeWashing implements IWashingBuilder{

    private static WashingServiceType washingServiceType;

    public BDeluxeWashing(){

        this.washingServiceType = new WashingServiceType();
    }

    public void buildWashingServiceTypeID() { washingServiceType.setWashingServiceTypeID(""); }
    public void buildWashingServiceTypeName() { washingServiceType.setWashingServiceTypeName("Deluxe Washing"); }
    public void buildCapacity() { washingServiceType.setCapacity(5);}
    public void buildRate(){ washingServiceType.setRate(30); }

    public WashingServiceType getWashingServiceType() { return this.washingServiceType; }

}