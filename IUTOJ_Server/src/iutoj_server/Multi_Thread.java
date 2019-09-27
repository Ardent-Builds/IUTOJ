/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.io.IOException;
import java.net.Socket;

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
            if(data==null)
                break;

            String code = data.substring(0, 8);
            System.out.println(data + " " + code);
            if (code.equals("Login---")) {
                LoginSignUpHandler loginhandler = new LoginSignUpHandler(data, clienttype, database);
                if(loginhandler.isValid()){
                    sc.sendData("LoginTrue");
                }
                else{
                    sc.sendData("LoginFalse");
                }
            }
            else if(code.equals("SignUp--")){
                LoginSignUpHandler signUPhandler = new LoginSignUpHandler(data, clienttype, database);
                
                if(signUPhandler.doesExist()){
                    sc.sendData("Exist---");
                }
                else if(signUPhandler.SignUp()){
                    sc.sendData("SignUpTr");
                }
                else{
                    sc.sendData("SignUpFl");
                }
            }

        }
        System.out.println("Thread is done");
    }

}
