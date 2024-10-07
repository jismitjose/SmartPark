package ParkingFacility.BParkingType;

public class Caster {

    private ParkingBuilder parkingBuilder;
    public Caster(ParkingBuilder parkingBuilder){ this.parkingBuilder = parkingBuilder; }


    public ParkingType getParkingType() {
        return this.parkingBuilder.getParkingType();
    }

    public void CreateParkingType() {
        this.parkingBuilder.buildParkingTypeID();
        this.parkingBuilder.buildParkingTypeName();
        this.parkingBuilder.buildCapacityFacility();
        this.parkingBuilder.buildCapacityBooking();
        this.parkingBuilder.buildRate();

    }
}
