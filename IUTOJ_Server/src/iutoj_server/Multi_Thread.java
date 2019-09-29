/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
<<<<<<< Updated upstream
=======
import newproblem.NewProblem;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
        type = null;
        
=======
        clienttype = null;

>>>>>>> Stashed changes
    }

    @Override
    public void run() {
<<<<<<< Updated upstream
        
        type = client.readData();
        System.out.println(type);
        while (true) {
            String data = client.readData();
            if(data==null)
=======

        clienttype = sc.readData();
        System.out.println(clienttype);
        while (true) {
            String data = sc.readData();
            if (data == null) {
>>>>>>> Stashed changes
                break;
            }

            String code = data.substring(0, 8);
            System.out.println(data + " " + code);
<<<<<<< Updated upstream
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
=======
            switch (code) {
                case "Login---":
                    LoginSignUpHandler loginhandler = new LoginSignUpHandler(data, clienttype, database);
                    if (loginhandler.isValid()) {
                        sc.sendData("LoginTrue");
                    } else {
                        sc.sendData("LoginFalse");
                    }
                    break;
                case "SignUp--":
                    LoginSignUpHandler signUPhandler = new LoginSignUpHandler(data, clienttype, database);
                    if (signUPhandler.doesExist()) {
                        sc.sendData("Exist---");
                    } else if (signUPhandler.SignUp()) {
                        sc.sendData("SignUpTr");
                    } else {
                        sc.sendData("SignUpFl");
                    }
                    break;
                case "AddProb-":
                    
                    int x, y, temp;
                    x = data.indexOf(']', 9);
                    y = data.lastIndexOf(']');
                    String probfname = data.substring(9, x);
                    long  probfsize = Integer.parseInt(data.substring(x + 2, y));
                    System.out.println(probfname+ " " + probfsize);
                    
                    temp=y+1;
                    x = data.indexOf(']', temp);
                    y = data.lastIndexOf(']');
                    String inpfname = data.substring(temp, x);
                    long  inpfsize = Integer.parseInt(data.substring(x + 2, y));
                    System.out.println(inpfname+ " " + inpfsize);
                    
                    temp=y+1;
                    x = data.indexOf(']', temp);
                    y = data.lastIndexOf(']');
                    String outpfname = data.substring(temp, x);
                    long  outpfsize = Integer.parseInt(data.substring(x + 2, y));
                    System.out.println(outpfname+ " " + outpfsize);
                    
                    {
                        try {
                            sc.saveProblem(probfname, probfsize, inpfname, inpfsize, outpfname, outpfsize);
                        } catch (IOException ex) {
                            Logger.getLogger(Multi_Thread.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Multi_Thread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
              
                    break;
                default:
                    break;
>>>>>>> Stashed changes
            }
            else if(code.equals("AddProb-")){
                AddProblemHandler addproblemhandler = new AddProblemHandler(data, clienttype, database);
            }

        }
        System.out.println("Thread is done");
    }

}
