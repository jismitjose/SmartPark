package ParkingFacility.BWashingService;
import ParkingFacility.*;

public class CasterWashing {
    private IWashingBuilder washingBuilder;
    public CasterWashing(IWashingBuilder washingBuilder){ this.washingBuilder = washingBuilder; }


    public WashingServiceType getWashingServiceType() {
        return this.washingBuilder.getWashingServiceType();
    }

    public void CreateWashingServiceType() {
        this.washingBuilder.buildWashingServiceTypeID();
        this.washingBuilder.buildWashingServiceTypeName();
        this.washingBuilder.buildCapacity();
        this.washingBuilder.buildRate();

    }
}
