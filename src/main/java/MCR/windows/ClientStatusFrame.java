package MCR.windows;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ClientStatusFrame extends Observer {
    private final int WINDOW_WIDTH = 250;
    private final int WINDOW_HEIGHT = 150;
    private final JFrame frame;
    private final LinkedList<JLabel> clientsLabels = new LinkedList<>();

    private final ClientManagerFrame cmf;

    public ClientStatusFrame(ClientManagerFrame cmf) {
        this.cmf = cmf;
        frame = new JFrame();

        initFrame();
        initPanels();
        updateFields(cmf);
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
        for (Integer i : cmf.getClients()) {
            JLabel client = new JLabel();
            clientsLabels.add(client);
            frame.add(client);
        }
    }

    private void updateFields(ClientManagerFrame cmf) {
        for (int i = 0; i < cmf.getClients().size(); i++) {
            clientsLabels.get(i).setText("Client " + i + ": " + cmf.getClients().get(i));
        }
    }

    @Override
    public void update() {
        updateFields(cmf);
    }
}
