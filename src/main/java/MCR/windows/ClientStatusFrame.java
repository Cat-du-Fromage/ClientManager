// ================================================================================
// File : ClientStatusFrame.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.windows;

import MCR.entities.Client;
import MCR.entities.StatusType;
import MCR.entities.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * ClientStatusFrame is a GUI class that displays the status of multiple clients.
 * It extends Observer to update the displayed information when a client's status changes.
 */
public class ClientStatusFrame extends Observer {
    private final int WINDOW_WIDTH = 250;
    private final int WINDOW_HEIGHT = 150;
    private final JFrame frame;
    private final HashMap<Integer, JLabel> clientsLabels = new HashMap<Integer, JLabel>();
    private final ArrayList<Client> clients;

    /**
     * Constructor for ClientStatusFrame.
     * Initializes the frame and its components.
     *
     * @param clients The list of clients whose statuses are to be displayed.
     */
    public ClientStatusFrame(ArrayList<Client> clients) {
        frame = new JFrame();

        this.clients = clients;
        initFrame();
        initPanels();
        updateFields(clients);
    }

    /**
     * Initializes the JFrame with properties.
     */
    private void initFrame() {
        frame.setTitle("Clients Manager");
        frame.setResizable(false);
        frame.setFocusable(false);
        frame.setLayout(new GridLayout(6, 1));
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Client client : clients) {
                    client.removeObserver(ClientStatusFrame.this);
                }
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Initializes the panels for each client.
     */
    private void initPanels() {
        for (int i = 0; i < clients.size(); i++) {
            JLabel client = new JLabel();
            clientsLabels.put(clients.get(i).getUniqueId(), client);
            frame.add(client);
        }
    }

    /**
     * Updates the displayed information for all clients.
     *
     * @param clients The list of clients whose statuses are to be updated.
     */
    private void updateFields(ArrayList<Client> clients) {
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            updateField(client);
        }
    }

    /**
     * Updates the displayed information for a specific client.
     *
     * @param client The client whose status is to be updated.
     */
    private void updateField(Client client) {
        JLabel label = clientsLabels.get(client.getUniqueId());
        Color color;
        label.setText(client.getLastName() + " " + client.getName() + " " + client.getStatus());
        switch (client.getStatus()) {
            case StatusType.SILVER -> color = Color.decode("#C0C0C0");
            case StatusType.GOLD -> color = Color.decode("#FFD700");
            case StatusType.PLATINUM -> color = Color.decode("#00FFFF");
            default -> color = Color.BLACK;
        }
        label.setForeground(color);
    }

    /**
     * Updates the displayed information when the client changes.
     * This method is called by the subject (Client) when it notifies its observers.
     *
     * @param subject The subject (Client) that has changed.
     */
    @Override
    public void update(Subject subject) {
        Client updatedClient = (Client) subject;
        for (Client c : clients) {
            if(c.getUniqueId() == updatedClient.getUniqueId()){
                updateField(updatedClient);
            }
        }
    }
}
