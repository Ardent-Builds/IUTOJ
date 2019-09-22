/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class Server implements Runnable {

    private final ServerSocket server;
    private Socket client;
    private final Database database;

    public Server(int port) throws IOException, SQLException {
        this.server = new ServerSocket(port);
        client = null;
        database = new Database();
    }
    
    @Override
    public void run() {
         //To change body of generated methods, choose Tools | Templates.
        System.out.println("server is running");
        
        while (true) {
            
            try {
                Thread t = new Thread(new Multi_Thread(server.accept(),database));
                t.start();
                System.out.println("Client "+ t.getId() + " connected");
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("New Thread Error "+ex);
                break;
            }
        }
        try {
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("server didn't closed "+ex);
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
