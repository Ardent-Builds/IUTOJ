/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author KAWSAR
 */
public class IUTOJ_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        // TODO code application logic here
        Server server = new Server(1235);
        Thread service = new Thread(server);
   
        ServerGUI serverStarter = new ServerGUI();
        serverStarter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                service.start();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                server.stopServer();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                server.stopServer();
            }

            @Override
            public void windowIconified(WindowEvent e) {
                return;
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                return;
            }

            @Override
            public void windowActivated(WindowEvent e) {
                return;
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
               return;
            }
        });
        serverStarter.startGUI();
    }
    
}
