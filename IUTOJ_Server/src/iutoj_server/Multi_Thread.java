/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class Multi_Thread implements Runnable {

    private final ClientSocket client;
    private final Database database;
    private String type;

    Multi_Thread(Socket client, Database db) throws IOException {
        this.client = new ClientSocket(client);
        this.database = db;
        type = null;
        
    }

    @Override
    public void run() {
        
        type = client.readData();
        System.out.println(type);
        while (true) {
            String data = client.readData();
            if(data==null)
                break;

            String code = data.substring(0, 8);
            System.out.println(data + " " + code);
            if (code.equals("Login---")) {
                LoginSignUpHandler loginhandler = new LoginSignUpHandler(data, type, database);
                if(loginhandler.isValid()){
                    client.sendData("LoginTrue");
                }
                else{
                    client.sendData("LoginFlse");
                }
            }
            else if(code.equals("SignUp--")){
                LoginSignUpHandler signUPhandler = new LoginSignUpHandler(data, type, database);
                if(signUPhandler.SignUp()){
                    client.sendData("SignUpTr");
                }
                else{
                    client.sendData("SignUpFl");
                }
            }
            else if(code.equals("AddProb-")){
                AddProblemHandler addproblemhandler = new AddProblemHandler(data, clienttype, database);
            }

        }
        System.out.println("Thread is done");
    }

}
