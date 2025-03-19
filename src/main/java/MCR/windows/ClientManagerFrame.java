package MCR.windows;

import javax.swing.*;
import java.awt.*;

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
        cclient = new JComboBox(new String[] {"Client1", "Client2", "Client3", "Client4", "Client5"});
        cflight = new JComboBox(new String[] {"flight1", "flight2", "flight3", "flight4", "flight5"});
        ctype = new JComboBox(new String[] {"type1", "type2"});
        bdetails = new JButton("Details");
        badd = new JButton("Add");
        bbookcash = new JButton("Book (Cash)");
        bbookmiles = new JButton("Book (Miles)");
        bstatuses = new JButton("Statuses");
        bquit = new JButton("Quit");
        tcredits = new JTextField(10);

        initFrame();
        initPanels();
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
    }

    private void initFrame() {
        frame.setTitle("Clients Manager");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLayout(new GridLayout(4, 1));
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        frame.pack();
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
}
