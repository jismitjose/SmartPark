package ParkingFacility.BParkingType;

public class ParkingType {
    String parkingTypeID;
    String parkingTypeName;
    int capFacility;
    int capBooking;
    static double rate;

    public void setParkingTypeID(String parkingTypeID) { this.parkingTypeID = parkingTypeID; }
    public void setParkingTypeName(String parkingTypeName) { this.parkingTypeName = parkingTypeName; }
    public void setCapFacility(int capFacility) { this.capFacility = capFacility; }
    public void setCapBooking(int capBooking) { this.capBooking = capBooking; }
    public void setRate(double rate) { this.rate = rate; }


    public String getParkingTypeID() { return this.parkingTypeID; }
    public String getParkingTypeName() { return this.parkingTypeName; }
    public int getCapFacility() { return this.capFacility; }
    public int getCapBooking() { return this.capBooking; }
    public static double getRate() { return rate; }

    public void displayRates(){
        System.out.println(getParkingTypeName() + "\t" + getRate());
    }

    public void displayParkingTypeInfo(){

        System.out.println(getParkingTypeName() + "\t" + getCapFacility() + "\t" + getCapBooking() + "\t" + getRate());
    }
}
