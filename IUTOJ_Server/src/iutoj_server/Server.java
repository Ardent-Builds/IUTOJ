/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class Server implements Runnable {

    private final ServerSocket ss;
    private final Database database;
    private boolean serverrunning;

    public Server(int port) throws IOException, SQLException {
        this.ss = new ServerSocket(port);
        ss.setSoTimeout(10000);
        database = new Database();
        serverrunning = true;
    }
    
    public void stopServer(){
        serverrunning = false;
    }
    
    @Override
    public void run() {
         //To change body of generated methods, choose Tools | Templates.
        System.out.println("server is running");
        
        while (true) {
            
            try {
                Socket sc = ss.accept();
                Thread t = new Thread(new Multi_Thread(sc,database));
                t.start();
                System.out.println("Client "+ t.getId() + " connected");
            } catch (IOException ex) {
                System.out.println("New Thread Error "+ex);
                if(serverrunning == false)
                    break;
            }
            if(serverrunning == false)
                    break;
        }
        try {
            ss.close();
            database.conn.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("server didn't closed "+ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
