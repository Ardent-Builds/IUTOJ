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


/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class SocketForClient {
    private Socket socket;
    private DataOutputStream dataout;
    private DataInputStream datain;
    private ObjectInputStream objectin;
    
    
    public SocketForClient(Socket socket) throws IOException
    {
        this.socket = socket; 
        try {

            dataout = new DataOutputStream(socket.getOutputStream());
            datain = new DataInputStream(socket.getInputStream());
            objectin = new ObjectInputStream(socket.getInputStream());
          
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
    
    public void saveProblem(String probfname, String inpfname, String outpfname) throws IOException, ClassNotFoundException
    {
        System.out.println(probfname+" "+inpfname+" "+outpfname);
        NewProblem newproblem = (NewProblem) objectin.readObject();
        FileOutputStream probos = new FileOutputStream(probfname);
        FileOutputStream inpos = new FileOutputStream(inpfname);
        FileOutputStream outpos = new FileOutputStream(outpfname);
        probos.write(newproblem.getProb());
        inpos.write(newproblem.getInp());
        outpos.write(newproblem.getOutp());
        probos.close();
        inpos.close();
        outpos.close();
        String problemid = newproblem.getProblemID();
        String problemname = newproblem.getProblemName();
        String timelimit = newproblem.getTimeLimit();
        String memorylimit = newproblem.getMemoryLimit();
       
        
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
