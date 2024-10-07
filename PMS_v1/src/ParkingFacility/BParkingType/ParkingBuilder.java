package ParkingFacility.BParkingType;

public interface ParkingBuilder {

    public void buildParkingTypeID();
    public void buildParkingTypeName();
    public void buildCapacityFacility();
    public void buildCapacityBooking();
    public void buildRate();
    public ParkingType getParkingType();
}
