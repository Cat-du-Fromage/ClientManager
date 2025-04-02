package MCR.windows;

import MCR.entities.Client;
import MCR.entities.Subject;

import javax.swing.*;
import java.awt.*;

public class ClientDetailsFrame extends Observer {
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 300;
    private final JFrame frame;
    private final JLabel lclient, fclient, cclient, mclient, sclient, lflight;

    private Client client;

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

    private void initFrame() {
        frame.setTitle("Details of client");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(6, 1));
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initPanels() {
        frame.add(lclient);
        frame.add(cclient);
        frame.add(fclient);
        frame.add(mclient);
        frame.add(sclient);
        frame.add(lflight);
    }

    @Override
    public void update(Subject subject) {
        this.client = (Client) subject;
        updateFields(this.client);
    }

    private void updateFields(Client client) {
        lclient.setText("Last name: " + client.getLastName());
        fclient.setText("First name: " + client.getName());
        cclient.setText("Credits: " + client.getMoney());
        mclient.setText("Nb miles: " + client.getMiles());
        sclient.setText("Status: " + client.getStatus());
        lflight.setText("Last action: " + client.getLastAction());
    }
}
