/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_admin;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.JProgressBar;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class AdminSocket {

    private Socket adminsocket;
    private DataOutputStream dataout;
    private DataInputStream datain;

    public AdminSocket() throws IOException {
        this.adminsocket = new Socket();
    }

    public int sendData(String data) {
        try {
            adminsocket.setSoTimeout(5000);
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
            adminsocket.setSoTimeout(3000);
            return datain.readUTF();
        } catch (IOException ex) {
            return null;
        }
    }
    
    public boolean sendFile(File file){
        
        long filesize = file.length();
        long sentfilesize = 0;
        byte [] data = new byte[1024];
        JProgressBar progressbar = new JProgressBar();
        progressbar.setMaximum(100);
        progressbar.setMinimum(0);
        progressbar.setValue(0);
        progressbar.setVisible(true);
        
        
        
        try {
            FileInputStream fis = new FileInputStream(file);
            while(sentfilesize<filesize){
                sentfilesize+= fis.read(data);
                progressbar.setValue((int) (sentfilesize*100/filesize));
                progressbar.updateUI();
                dataout.write(data);
                dataout.flush();
            }  
            fis.close();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        } 
    }
    public boolean connect(String add, int port) {
        try {
            adminsocket = new Socket(add, port);
            dataout = new DataOutputStream(adminsocket.getOutputStream());
            datain = new DataInputStream(adminsocket.getInputStream());
            dataout.writeUTF("Admin");
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public void close() throws IOException {
        adminsocket.close();
    }

}
