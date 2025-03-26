package MCR.windows;

import javax.swing.*;
import java.awt.*;

public class ClientDetailsFrame extends Observer {
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 300;
    private final JFrame frame;
    private final JLabel lclient, fclient, cclient, mclient, sclient, lflight;

    private final ClientManagerFrame cmf;

    public ClientDetailsFrame(ClientManagerFrame cmf) {
        this.cmf = cmf;
        frame = new JFrame();
        lclient = new JLabel();
        fclient = new JLabel();
        cclient = new JLabel();
        mclient = new JLabel();
        sclient = new JLabel();
        lflight = new JLabel();

        updateFields(cmf);
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
    public void update() {
        updateFields(cmf);
    }

    private void updateFields(ClientManagerFrame cmf) {
        lclient.setText("Last name: " + cmf.getIncrement());
        fclient.setText("First name: " + cmf.getIncrement());
        cclient.setText("Credits: " + cmf.getIncrement());
        mclient.setText("Nb miles: " + cmf.getIncrement());
        sclient.setText("Status: " + cmf.getIncrement());
        lflight.setText("Last action: " + cmf.getIncrement());
    }
}
