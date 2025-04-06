// ================================================================================
// File : ClientManagerApp.java
// Project name : ClientManager
// Project members :
// - Florian Duruz, Mathieu Rabot
// File created by Florian Duruz, Mathieu Rabot
// ================================================================================
package MCR;

import MCR.windows.ClientManagerFrame;

/**
 * Main application class for the Client Manager.
 * Initializes the client manager frame.
 */
public class ClientManagerApp {

    /**
     * Main method to launch the Client Manager application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new ClientManagerApp();
    }

    /**
     * Private constructor to prevent instantiation.
     * Initializes the client manager frame.
     */
    private ClientManagerApp() {
        ClientManagerFrame.getInstance();
    }
}