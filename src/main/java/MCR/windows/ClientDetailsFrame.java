// ================================================================================
// File : ClientDetailsFrame.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR.windows;

import MCR.entities.Client;
import MCR.entities.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ClientDetailsFrame is a GUI class that displays the details of a client.
 * It extends Observer to update the displayed information when the client changes.
 */
public class ClientDetailsFrame extends Observer {
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 300;
    private final JFrame frame;
    private final JLabel lclient, fclient, cclient, mclient, sclient, lflight;

    private Client client;

    /**
     * Constructor for ClientDetailsFrame.
     * Initializes the frame and its components.
     *
     * @param client The client whose details are to be displayed.
     */
    public ClientDetailsFrame(Client client) {
        this.client = client;
        frame = new JFrame();
        lclient = new JLabel();
        fclient = new JLabel();
        cclient = new JLabel();
        mclient = new JLabel();
        sclient = new JLabel();
        lflight = new JLabel();

        updateFields(client);
        initFrame();
        initPanels();
    }

    /**
     * Initializes the JFrame with properties.
     */
    private void initFrame() {
        frame.setTitle("Details of client");
        frame.setResizable(false);
        frame.setLayout(new GridLayout(6, 1));
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Ajout d'un WindowListener pour détecter la fermeture
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.removeObserver(ClientDetailsFrame.this);
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Initializes the panels and adds them to the frame.
     */
    private void initPanels() {
        frame.add(lclient);
        frame.add(cclient);
        frame.add(fclient);
        frame.add(mclient);
        frame.add(sclient);
        frame.add(lflight);
    }

    /**
     * Updates the displayed information when the client changes.
     * This method is called by the subject (Client) when it notifies its observers.
     *
     * @param subject The subject (Client) that has changed.
     */
    @Override
    public void update(Subject subject) {
        this.client = (Client) subject;
        updateFields(this.client);
    }

    /**
     * Updates the labels with the client's information.
     *
     * @param client The client whose information is to be displayed.
     */
    private void updateFields(Client client) {
        lclient.setText("Last name: " + client.getLastName());
        fclient.setText("First name: " + client.getName());
        cclient.setText("Credits: " + (int)client.getMoney());
        mclient.setText("Nb miles: " + (int)client.getMiles());
        sclient.setText("Status: " + client.getStatus());
        lflight.setText("Last action: " + client.getLastAction());
    }
}
