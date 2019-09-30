/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_admin;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import newproblem.NewProblem;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class AdminSocket {

    private Socket adminsocket;
    private DataOutputStream dataout;
    private DataInputStream datain;
    private ObjectOutputStream objectout;

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
    
    public int addProblem(File problem, File inputs, File outputs, String problemid, String problemname, String timelimit, String memorylimit) throws IOException {
        
        try {
            FileInputStream probis = new FileInputStream(problem);
            byte prob[] = new byte[probis.available()];
            probis.read(prob);
            FileInputStream inpis = new FileInputStream(inputs);
            byte inp[] = new byte[inpis.available()];
            inpis.read(inp);
            FileInputStream outpis = new FileInputStream(outputs);
            byte outp[] = new byte[outpis.available()];
            outpis.read(outp);
            NewProblem newproblem = new NewProblem();
            newproblem.setProblemID(problemid);
            newproblem.setProblemName(problemname);
            newproblem.setTimeLimit(timelimit);
            newproblem.setMemoryLimit(memorylimit);
            newproblem.setProb(prob);
            newproblem.setInp(inp);
            newproblem.setOutp(outp);
            objectout.writeObject(newproblem);
            objectout.flush();
            return 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminSocket.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }

    public boolean connect(String add, int port) {
        try {
            adminsocket = new Socket(add, port);
            dataout = new DataOutputStream(adminsocket.getOutputStream());
            datain = new DataInputStream(adminsocket.getInputStream());
            objectout = new ObjectOutputStream(adminsocket.getOutputStream());
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
