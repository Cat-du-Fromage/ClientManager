package MCR;

import MCR.windows.ClientManagerFrame;

public class ClientManagerApp {

    public static void main(String[] args) {
        new ClientManagerApp();
    }

    private ClientManagerApp() {
        ClientManagerFrame.getInstance();
    }
}