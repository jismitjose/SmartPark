package ParkingFacility.BParkingType;

public class BAccessibleParking implements ParkingBuilder{

    private static ParkingType parkingType;

    public BAccessibleParking(){

        this.parkingType = new ParkingType();
    }

    public void buildParkingTypeID() { parkingType.setParkingTypeID("6d63d7f2-b29a-44a9-a298-4e7b4e9af81f"); }
    public void buildParkingTypeName() { parkingType.setParkingTypeName("Accessible Parking"); }
    public void buildCapacityFacility() { parkingType.setCapFacility(15);}
    public void buildCapacityBooking() { parkingType.setCapBooking(5); }
    public void buildRate(){ parkingType.setRate(10); }
    public ParkingType getParkingType() { return this.parkingType; }
}