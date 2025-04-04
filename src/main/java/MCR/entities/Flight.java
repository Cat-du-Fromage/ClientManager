// ================================================================================
// File : Flight.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.entities;

/**
 * Represents a flight with its basic information including name, price, and distance in miles.
 * This class provides access to flight details and a formatted string representation.
 */
public class Flight {
    private String name;
    private double price;
    private double miles;

    /**
     * Constructs a new Flight with the specified parameters.
     * @param name  the name or identifier of the flight (e.g., flight number)
     * @param price the base price of the flight ticket
     * @param miles the distance of the flight in miles
     */
    public Flight(String name, double price, double miles) {
        this.name = name;
        this.price = price;
        this.miles = miles;
    }

    /**
     * Returns the name of the flight.
     * @return the flight name/identifier
     */
    public String getName() {return name;}

    /**
     * Returns the base price of the flight ticket.
     * @return the flight price
     */
    public double getPrice() {return price;}

    /**
     * Returns the distance of the flight in miles.
     * @return the flight distance in miles
     */
    public double getMiles() {return miles;}

    /**
     * Returns a string representation of the flight in the format:
     * @return a formatted string representing the flight
     */
    @Override
    public String toString() {
        return name + " (" + (int)(miles) + " miles)";
    }
}
