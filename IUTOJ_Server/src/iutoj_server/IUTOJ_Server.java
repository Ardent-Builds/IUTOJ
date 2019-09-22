/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

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
        Thread server = new Thread(new Server(1235));
        server.start();
        ServerGUI serverStarter = new ServerGUI();
    }
    
}
