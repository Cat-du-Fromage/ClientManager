package MCR;

import MCR.windows.ClientManagerFrame;

public class ClientManagerApp {
    private final ClientManagerFrame frame;

    public static void main(String[] args) {
        new ClientManagerApp();
    }

    private ClientManagerApp() {
        frame = ClientManagerFrame.getInstance();
    }
}