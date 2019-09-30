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


/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class Multi_Thread implements Runnable {

    private final SocketForClient sc;
    private final Database database;
    private String clienttype;

    Multi_Thread(Socket sc, Database db) throws IOException {
        this.sc = new SocketForClient(sc);
        this.database = db;
        clienttype = null;

    }

    @Override
    public void run() {

        clienttype = sc.readData();
        System.out.println(clienttype);
        while (true) {
            String data = sc.readData();
            if (data == null) {
                break;
            }

            String code = data.substring(0, 8);
            System.out.println(data + " " + code);
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
                    
                    int x, y, temp,z;
                    x = data.indexOf(']', 9);
                    y = data.indexOf(']',x+1);
                    z = data.lastIndexOf(']');
                    String probfname = data.substring(9, x);
                    String inpfname = data.substring(x+2,y);
                    String outpfname = data.substring(y+2,z);
                    System.out.println("ekhane "+outpfname);
                    
                    
                    {
                        try {
                            sc.saveProblem(probfname, inpfname, outpfname);
                        } catch (IOException | ClassNotFoundException ex) {
                            Logger.getLogger(Multi_Thread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
              
                    break;
                default:
                    break;
            }

        }
        System.out.println("Thread is done");
    }

}
