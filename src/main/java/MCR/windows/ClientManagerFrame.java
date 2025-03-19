package MCR.windows;

import javax.swing.*;
import java.awt.*;

public class ClientManagerFrame implements Displayer {
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private static ClientManagerFrame instance = null;
    private final JFrame frame;
    private final JPanel panel;

    public static ClientManagerFrame getInstance() {
        if (instance == null) {
            instance = new ClientManagerFrame();
        }
        return instance;
    }

    private ClientManagerFrame() {
        frame = new JFrame();
        panel = new JPanel();
        initFrame();
        initPanel();
        frame.add(panel);
    }

    private void initFrame() {
        frame.setTitle("Clients Manager");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initPanel() {
        panel.setFocusable(true);
        panel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        panel.setVisible(true);
    }

    @Override
    public int getWidth() {
        return panel.getWidth();
    }

    @Override
    public int getHeight() {
        return panel.getHeight();
    }

    @Override
    public Graphics2D getGraphics() {
        return (Graphics2D) panel.getGraphics();
    }

    @Override
    public void repaint() {
        //maybe
    }

    @Override
    public void setTitle(String title) {
        frame.setTitle(title);
    }
}
