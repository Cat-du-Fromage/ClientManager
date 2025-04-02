package MCR.windows;

import MCR.entities.Client;
import MCR.entities.StatusType;
import MCR.entities.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ClientStatusFrame extends Observer {
    private final int WINDOW_WIDTH = 250;
    private final int WINDOW_HEIGHT = 150;
    private final JFrame frame;
    private final LinkedList<JLabel> clientsLabels = new LinkedList<>();

    private final ArrayList<Client> clients;

    public ClientStatusFrame(ArrayList<Client> clients) {
        frame = new JFrame();

        this.clients = clients;
        initFrame();
        initPanels();
        updateFields(clients);
    }

    private void initFrame() {
        frame.setTitle("Clients Manager");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(false);
        frame.setLayout(new GridLayout(6, 1));
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initPanels() {
        for (int i = 0; i < clients.size(); i++) {
            JLabel client = new JLabel();
            clientsLabels.add(client);
            frame.add(client);
        }
    }

    private void updateFields(ArrayList<Client> clients) {
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            JLabel label = clientsLabels.get(i);
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
    }

    @Override
    public void update(Subject subject) {
        Client updatedClient = (Client) subject;
        for (Client c : clients) {
            if(c.getUniqueId() == updatedClient.getUniqueId()){
                c.setStatus(updatedClient.getStatus());
            }
        }
        updateFields(clients);
    }
}
