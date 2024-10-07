package ParkingFacility.BParkingType;

public class BStandardParking implements ParkingBuilder{

    private ParkingType parkingType;

    public BStandardParking(){

        this.parkingType = new ParkingType();
    }

    public void buildParkingTypeID() { parkingType.setParkingTypeID("3377c27f-6be4-40bd-aa08-66e329d4c000"); }
    public void buildParkingTypeName() { parkingType.setParkingTypeName("Standard Parking"); }
    public void buildCapacityFacility() { parkingType.setCapFacility(0);}
    public void buildCapacityBooking() { parkingType.setCapBooking(0); }
    public void buildRate(){ parkingType.setRate(0); }
    public ParkingType getParkingType() { return this.parkingType; }
}
