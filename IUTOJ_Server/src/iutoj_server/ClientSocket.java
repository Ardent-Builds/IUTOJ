/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class ClientSocket {
    private Socket socket;
    private DataOutputStream dataout;
    private DataInputStream datain;
    
    
    public ClientSocket(Socket socket) throws IOException
    {
        this.socket = socket; 
        try {

            dataout = new DataOutputStream(socket.getOutputStream());
            datain = new DataInputStream(socket.getInputStream());
          
        } catch (IOException ex) {
     
        }
    }
    
    
    public int  sendData(String data) 
    {
        try {
            socket.setSoTimeout(5000);
            dataout.writeUTF(data);
            return data.length();
        } catch (SocketException ex) {
            return -2;
        } catch (IOException ex) {
            return -1;
        }
        
    }
    
    public String readData()
    {
        try{
            socket.setSoTimeout(0);
            return datain.readUTF();
        } catch (IOException ex) {
            return null;
        }
    }
    
    
    public void close() throws IOException{
        socket.close();
    }
    
    
    
}
