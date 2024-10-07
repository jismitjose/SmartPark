package ParkingFacility.BWashingService;

public interface IWashingBuilder {

    public void buildWashingServiceTypeID();
    public void buildWashingServiceTypeName();
    public void buildCapacity();
    public void buildRate();

    public WashingServiceType getWashingServiceType();
}
