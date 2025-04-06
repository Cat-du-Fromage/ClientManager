// ================================================================================
// File : ClientManagerFrame.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.windows;

import MCR.entities.*;
import MCR.entities.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * ClientManagerFrame is a GUI class that manages client interactions and flight bookings.
 * It provides functionalities to add credits, book flights, and view client statuses.
 */
public class ClientManagerFrame {
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 300;
    private static ClientManagerFrame instance = null;
    private final JFrame frame;
    private final JPanel panel1, panel2, panel3, panel4;
    private final JLabel lclient, lcredits, lflight;
    private final JComboBox cclient, cflight, ctype;
    private final JButton bdetails, badd, bbookcash, bbookmiles, bstatuses, bquit;
    private final JTextField tcredits;

    private ArrayList<Client> clients;
    private ArrayList<Flight> flights;

    /**
     * Singleton pattern to ensure only one instance of ClientManagerFrame exists.
     * @return the single instance of ClientManagerFrame
     */
    public static ClientManagerFrame getInstance() {
        if (instance == null) {
            instance = new ClientManagerFrame();
        }
        return instance;
    }

    /**
     * Private constructor to initialize the GUI components and layout.
     */
    private ClientManagerFrame() {
        frame = new JFrame();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        lclient = new JLabel("Client");
        lcredits = new JLabel("Credits");
        lflight = new JLabel("Flight");

        cclient = initClients();

        cflight = initFlights();

        ctype = new JComboBox();
        updateFlightType();
        cflight.addActionListener(_ -> updateFlightType());


        bdetails = new JButton("Details");
        bdetails.addActionListener(_ -> createObserverDetails());

        badd = new JButton("Add");

        bbookcash = new JButton("Book (Cash)");
        bbookcash.addActionListener(_ -> onMoneyBook());

        bbookmiles = new JButton("Book (Miles)");
        bbookmiles.addActionListener(_ -> onMilesBook());

        bstatuses = new JButton("Statuses");
        bstatuses.addActionListener(_ -> createObserverStatus());
        bquit = new JButton("Quit");
        bquit.addActionListener(_ -> System.exit(0));
        tcredits = new JTextField(10);

        badd.addActionListener(_ -> addCurrency());

        initFrame();
        initPanels();
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
    }

    
    /**
     * Updates the flight type based on the selected flight.
     * It removes all items from the JComboBox and adds new items based on the selected flight's price.
     * The price is calculated using the TicketType's money multiplicator.
     */
    private void updateFlightType() {
        ctype.removeAllItems();
        double currentFlight = flights.get(cflight.getSelectedIndex()).getPrice();
        for (TicketType type : TicketType.values()) {
            int price = (int)(currentFlight * type.moneyMultiplicator());
            ctype.addItem(type.toString() + " " + price + "$");
        }
    }

    /**
     * Initializes the JComboBox with client names.
     * It creates a list of clients, sorts them, and adds them to the JComboBox.
     * @return the initialized JComboBox with client names
     */
    private JComboBox initClients() {
        JComboBox result = new JComboBox();
        clients = new ArrayList<Client>(2);
        clients.add(new Client("Mathieu", "Rabot"));
        clients.add(new Client("Florian", "Duruz"));
        clients.sort(Client::compareTo);
        clients.forEach(client -> result.addItem(client.toString()));
        return result;
    }

    /**
     * Initializes the JComboBox with flight names.
     * It creates a list of flights and adds them to the JComboBox.
     * @return the initialized JComboBox with flight names
     */
    private JComboBox initFlights() {
        JComboBox result = new JComboBox();
        flights = new ArrayList<>(2);
        flights.add(new Flight("LX1", 300, 1200));
        flights.add(new Flight("LX2", 1000, 8000));
        flights.forEach(flight -> result.addItem(flight.toString()));
        return result;
    }

    /**
     * Initializes the JFrame with properties.
     * Sets the title, default close operation, layout, size, and visibility.
     */
    private void initFrame() {
        frame.setTitle("Clients Manager");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLayout(new GridLayout(4, 1));
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Initializes the panels and adds components to them.
     * Each panel contains specific components related to client management.
     */
    private void initPanels() {
        panel1.add(lclient);
        panel1.add(cclient);
        panel1.add(bdetails);

        panel2.add(lcredits);
        panel2.add(tcredits);
        panel2.add(badd);

        panel3.add(lflight);
        panel3.add(cflight);
        panel3.add(ctype);
        panel3.add(bbookcash);
        panel3.add(bbookmiles);

        panel4.add(bstatuses);
        panel4.add(bquit);

    }

    /**
     * Adds currency to the selected client's account.
     * It retrieves the amount from the text field, validates it, and updates the client's account.
     */
    private void addCurrency() {
        String text = tcredits.getText();
        if(text != null && text.matches("\\d+"))
        {
            int amount = Integer.parseInt(text);
            getCurrentClient().updateCredit(amount);
            getCurrentClient().setLastAction("Credits added : " + amount);
        }
    }

    /**
     * Books a flight for the selected client using cash.
     * It checks if the client has enough credits and updates their account accordingly.
     */
    private void onMoneyBook() {
        String text;
        int moneyPrice = getFlightMoneyPrice();
        if(getCurrentClient().getMoney() < moneyPrice){
            text = "Not enough credits (" + moneyPrice + " needed) to book " + getCurrentFlight().getName() + " in " + getCurrentTicketType().name() + " class";
            getCurrentClient().setLastAction(text);
        } else {
            int price = -moneyPrice;
            int milesAdded = (int)(getCurrentTicketType().coefficient() * getCurrentFlight().getMiles());
            text = "Booked " + getCurrentFlight().getName() + " in " + getCurrentTicketType().name() + ", using credits";
            getCurrentClient().updateInfos(price, milesAdded, text);
        }
    }

    /**
     * Books a flight for the selected client using miles.
     * It checks if the client has enough miles and updates their account accordingly.
     */
    private void onMilesBook() {
        String text;
        int milesPrice = getFlightMilesPrice();
        if(getCurrentClient().getMiles() < milesPrice){
            text = "Not enough miles (" + milesPrice + " needed) to book " + getCurrentFlight().getName() + " in " + getCurrentTicketType().name() + " class";
            getCurrentClient().setLastAction(text);
        } else {
            int milesDecreased = -milesPrice;
            text = "Booked " + getCurrentFlight().getName() + " in " + getCurrentTicketType().name() + ", using miles";
            getCurrentClient().updateInfos(0, milesDecreased, text);
        }
    }

    /**
     * Gets the currently selected client from the JComboBox.
     * @return the currently selected client
     */
    private Client getCurrentClient() {
        return clients.get(cclient.getSelectedIndex());
    }

    /**
     * Gets the currently selected flight from the JComboBox.
     * @return the currently selected flight
     */
    private Flight getCurrentFlight() {
        return flights.get(cflight.getSelectedIndex());
    }

    /**
     * Gets the currently selected ticket type from the JComboBox.
     * @return the currently selected ticket type
     */
    private TicketType getCurrentTicketType() {
        return TicketType.values()[ctype.getSelectedIndex()];
    }

    /**
     * Gets the price of the flight in money based on the selected ticket type.
     * @return the price of the flight in money
     */
    private int getFlightMoneyPrice() {
        return (int)(getCurrentFlight().getPrice() * getCurrentTicketType().moneyMultiplicator());
    }

    /**
     * Gets the price of the flight in miles based on the selected ticket type.
     * @return the price of the flight in miles
     */
    private int getFlightMilesPrice() {
        return (int)(getCurrentFlight().getMiles() * getCurrentTicketType().milesMultiplicator());
    }

    /**
     * Creates an observer for the client details.
     * It adds a new ClientDetailsFrame as an observer to the currently selected client.
     */
    private void createObserverDetails() {
        Client current = getCurrentClient();
        current.addObserver(new ClientDetailsFrame(current));
    }

    /**
     * Creates an observer for the client status.
     * It adds a new ClientStatusFrame as an observer to each client in the list.
     */
    private void createObserverStatus() {
        ClientStatusFrame csf = new ClientStatusFrame(clients);
        for (Client current : clients) {
            current.addObserver(csf);
        }
    }
}
