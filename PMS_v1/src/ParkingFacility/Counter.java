package ParkingFacility;

import ParkingFacility.BParkingType.ParkingType;

public class Counter {
    private String licensePlateCar;
    private ParkingType parkingType;

    public void setLicensePlateCar(String licensePlateCar) { this.licensePlateCar = licensePlateCar; }
    public void setParkingType(ParkingType parkingType) { this.parkingType = parkingType; }

    public String getLicensePlateCar() {return this.licensePlateCar; }
    public ParkingType getParkingType() { return this.parkingType; }

//*public getTicket(){
    public Ticket getTicket() {
         Ticket ticket = new Ticket(licensePlateCar, parkingType);
        return ticket;
    }

//  public printTicket(){
    public void printTicket() {
        Ticket ticket = getTicket();
        System.out.println("Ticket Information:\n" + ticket.toString());
    }

}
