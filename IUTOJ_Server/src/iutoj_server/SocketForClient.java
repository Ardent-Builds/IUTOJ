/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import newproblem.NewProblem;
import newsubmission.NewSubmission;


/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class SocketForClient {
    private Socket socket;
    private DataOutputStream dataout;
    private DataInputStream datain;
    private ObjectInputStream objectin;
    private ObjectOutputStream objectout;
    
    
    public SocketForClient(Socket socket) throws IOException
    {
        this.socket = socket; 
        try {

            dataout = new DataOutputStream(socket.getOutputStream());
            datain = new DataInputStream(socket.getInputStream());
            objectin = new ObjectInputStream(socket.getInputStream());
            objectout = new ObjectOutputStream(socket.getOutputStream());
          
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
    
    public NewProblem saveProblem() throws IOException, ClassNotFoundException
    {
        NewProblem newproblem = (NewProblem) objectin.readObject();
        
        return newproblem;
        
    }
    
    public NewSubmission saveSubmission() throws IOException, ClassNotFoundException {
        NewSubmission newsubmission = (NewSubmission) objectin.readObject();
        
        return newsubmission;
    }
    
    public boolean sendProblem(NewProblem newproblem){
        try {
            objectout.writeObject(newproblem);
            return true;
        } catch (IOException ex) {
            System.out.println("SocketProblemSending Err "+ex.getMessage());
            return false;
        }
    }
    
    public boolean sendProblemTable(String[][] table){
        try{
            objectout.writeObject(table);
            return true;
        } catch (IOException ex) {
            System.out.println("SocketProblemTableSending Err "+ex.getMessage());
            return false;
        }
    }
    
    public boolean sendStatusTable(String[][] table){
        try{
            objectout.writeObject(table);
            return true;
        } catch (IOException ex) {
            System.out.println("SocketStatusTableSending Err "+ex.getMessage());
            return false;
        }
    }
    
    public File readFile(String fname, long size){
        try {
            FileOutputStream fos = new FileOutputStream(fname);
            byte[] data = new byte[1024];
            long receivedfilesize =0;
            
            while(receivedfilesize<size){
                receivedfilesize+=datain.read(data);
                fos.write(data);
            }
            fos.close();
            dataout.writeUTF("EOF-----"); 
            return new File(fname);
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            Logger.getLogger(SocketForClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    
    public void close() throws IOException{
        socket.close();
    }

    
    
    
    
}
