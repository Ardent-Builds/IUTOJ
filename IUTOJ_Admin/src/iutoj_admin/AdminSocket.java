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
import newproblem.NewProblem;
import newsubmission.NewSubmission;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class AdminSocket {

    private Socket adminsocket;
    private DataOutputStream dataout;
    private DataInputStream datain;
    private ObjectOutputStream objectout;
    private ObjectInputStream objectin;

    public AdminSocket() throws IOException {
        this.adminsocket = new Socket();
    }

    public int sendData(String data) {
        try {
            adminsocket.setSoTimeout(5000);
            dataout.writeUTF(data);
            return data.length();
        } catch (SocketException ex) {
            System.out.println("SocketExceptionSendData "+ex.getMessage());
            return -2;
        } catch (IOException ex) {
            System.out.println("IOExceptionSendData "+ex.getMessage());
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
    
    public String[][] getProblemTable(){
        String[][] table;
        
        try{
            table = (String[][]) objectin.readObject();
            return table;
        } catch (IOException ex) {
            System.out.println("SocketGetProblem I/O Err "+ex.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("SocketGetProblem ClassNotFound Err "+ex.getMessage());
            return null;
        }
    }
    
    public String[][] getStatusTable() {
        String[][] table;
        
        try{
            table = (String[][]) objectin.readObject();
            return table;
        } catch (IOException ex) {
            System.out.println("SocketGetProblem I/O Err "+ex.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("SocketGetProblem ClassNotFound Err "+ex.getMessage());
            return null;
        }
    }
    
    public String[][] getStandingsTable() {
        String[][] table;
        
        try{
            table = (String[][]) objectin.readObject();
            return table;
        } catch (IOException ex) {
            System.out.println("SocketGetProblem I/O Err "+ex.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("SocketGetProblem ClassNotFound Err "+ex.getMessage());
            return null;
        }
    }
    
    public boolean connect(String add, int port) {
        try {
            adminsocket = new Socket(add, port);
            dataout = new DataOutputStream(adminsocket.getOutputStream());
            datain = new DataInputStream(adminsocket.getInputStream());
            objectout = new ObjectOutputStream(adminsocket.getOutputStream());
            objectin = new ObjectInputStream(adminsocket.getInputStream());
            
            dataout.writeUTF("Admin");
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public void close() throws IOException {
        adminsocket.close();
    }

    NewSubmission getSubmission() {
        try {
            return (NewSubmission) objectin.readObject();
        } catch (IOException ex) {
            System.out.println("AdminSocket Reading Submission I/O Err: "+ex.getMessage() );
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("AdminSocket Reading ClassNotFound Err: "+ex.getMessage() );
            return null;
        }
    }
    
    NewProblem getProblem() {
        try {
            return (NewProblem) objectin.readObject();
        } catch (IOException ex) {
            System.out.println("AdminSocket getProblem I/O Err: "+ex.getMessage() );
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("AdminSocket getProblem ClassNotFound Err: "+ex.getMessage() );
            return null;
        }
    }

}
