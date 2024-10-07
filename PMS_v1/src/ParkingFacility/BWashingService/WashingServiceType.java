package ParkingFacility.BWashingService;

public class WashingServiceType {
    private String washingServiceTypeID;
    private String washingServiceTypeName;
    private int capacity;
    private static double rate;

    // set
    public void setWashingServiceTypeID(String WashingServiceTypeID) { this.washingServiceTypeID = WashingServiceTypeID; }
    public void setWashingServiceTypeName(String WashingServiceTypeName) { this.washingServiceTypeName = WashingServiceTypeName; }
    public void setCapacity(int Capacity)
    {
        this.capacity = Capacity;
    }

    public void setRate (double Rate) {
        rate = Rate;
    }

    //get
    public String getWashingServiceTypeID() { return this.washingServiceTypeID;}
    public String getWashingServiceTypeName(){return this.washingServiceTypeName;}
    public int getCapacity(){ return this.capacity; }
    public static double getRate(){ return rate; }

    public void displayRates(){
        System.out.println(getWashingServiceTypeName() + "\t" + getRate());
    }

    public void displayWashingServiceInfo(){

        System.out.println(getWashingServiceTypeName() + "\t" + getCapacity() + "\t" + getRate());
    }
}
