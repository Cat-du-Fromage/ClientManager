package MCR.entities;

public class Flight {
    private String name;
    private double price;
    private double miles;

    public Flight(String name, double price, double miles) {
        this.name = name;
        this.price = price;
        this.miles = miles;
    }

    public String getName() {return name;}
    public double getPrice() {return price;}
    public double getMiles() {return miles;}

    @Override
    public String toString() {
        return name + " (" + miles + " miles)";
    }
}
