/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_user;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class UserSocket {

    private Socket usersocket;
    private DataOutputStream dataout;
    private DataInputStream datain;
    private ObjectOutputStream objectout;
    private ObjectInputStream objectin;

    public UserSocket() throws IOException {
        this.usersocket = new Socket();
    }

    public int sendData(String data) {
        try {
            usersocket.setSoTimeout(5000);
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
            usersocket.setSoTimeout(3000);
            return datain.readUTF();
        } catch (IOException ex) {
            return null;
        }
    }
    public boolean connect(String add, int port) {
        try {
            usersocket = new Socket(add, port);
            dataout = new DataOutputStream(usersocket.getOutputStream());
            datain = new DataInputStream(usersocket.getInputStream());
            objectout = new ObjectOutputStream(usersocket.getOutputStream());
            objectin = new ObjectInputStream(usersocket.getInputStream());
            dataout.writeUTF("User");
            return true;
        } catch (IOException ex) {
            return false;
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
    public void close() throws IOException {
        usersocket.close();
    }

}
