package MCR.windows;

import MCR.entities.*;
import MCR.entities.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

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

    // TODO Will be replaced by the real data
    private int increment = 0;

    private ArrayList<Client> clients;
    private ArrayList<Flight> flights;

    public static ClientManagerFrame getInstance() {
        if (instance == null) {
            instance = new ClientManagerFrame();
        }
        return instance;
    }

    private ClientManagerFrame() {
        frame = new JFrame();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        lclient = new JLabel("Client");
        lcredits = new JLabel("Credits");
        lflight = new JLabel("Flight");

        cclient = new JComboBox();
        initClients();
        cflight = new JComboBox();
        initFlights();

        ctype = new JComboBox();
        updateFlightType();
        cflight.addActionListener(_ -> updateFlightType());


        bdetails = new JButton("Details");
        bdetails.addActionListener(_ -> createObserverDetails());

        badd = new JButton("Add");

        bbookcash = new JButton("Book (Cash)");
        bbookmiles = new JButton("Book (Miles)");
        bstatuses = new JButton("Statuses");
        bstatuses.addActionListener(_ -> createObserverStatus());
        bquit = new JButton("Quit");
        bquit.addActionListener(_ -> System.exit(0));
        tcredits = new JTextField(10);

        badd.addActionListener(_ -> getCurrentClient().setMoney(Integer.parseInt(tcredits.getText())));

        initFrame();
        initPanels();
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
    }

    private void updateFlightType() {
        ctype.removeAllItems();
        double currentFlight = flights.get(cflight.getSelectedIndex()).getPrice();
        for (TicketType type : TicketType.values()) {
            double price = currentFlight * type.multiplicator();
            ctype.addItem(type.toString() + " " + price + "$");
        }
    }

    private void initClients(){
        clients = new ArrayList<Client>(2);
        clients.add(new Client("Mathieu", "Rabot"));
        clients.add(new Client("Florian", "Duruz"));
        clients.sort(Client::compareTo);
        clients.forEach(client -> cclient.addItem(client.toString()));
    }

    private void initFlights(){
        flights = new ArrayList<>(2);
        flights.add(new Flight("LX1", 300, 1200));
        flights.add(new Flight("LX2", 1000, 8000));
        flights.forEach(flight -> cflight.addItem(flight.getName()));
    }

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

    private Client getCurrentClient() {
        return clients.get(cclient.getSelectedIndex());
    }

    private void createObserverDetails() {
        Client current = getCurrentClient();
        current.addObserver(new ClientDetailsFrame(current));
    }

    private void createObserverStatus() {
        ClientStatusFrame csf = new ClientStatusFrame(clients);
        for (Client current : clients) {
            current.addObserver(csf);
        }
    }
}
