package ParkingFacility.BParkingType;

public class BPremiumParking implements ParkingBuilder{

    private ParkingType parkingType;

    public BPremiumParking(){

        this.parkingType = new ParkingType();
    }

    public void buildParkingTypeID() { parkingType.setParkingTypeID("dab5a94d-72a5-4558-abbf-5562599fb977"); }
    public void buildParkingTypeName() { parkingType.setParkingTypeName("Premium Parking"); }
    public void buildCapacityFacility() { parkingType.setCapFacility(30);}
    public void buildCapacityBooking() { parkingType.setCapBooking(10); }
    public void buildRate(){ parkingType.setRate(25.5); }
    public ParkingType getParkingType() { return this.parkingType; }
}