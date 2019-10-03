/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.io.IOException;
import java.net.Socket;
import newproblem.NewProblem;
import newsubmission.NewSubmission;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class Multi_Thread implements Runnable {

    private final SocketForClient sc;
    private final Database database;
    private String clienttype;
    private String username;

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
                        int x = data.indexOf(']', 9);
                        username = data.substring(9, x);

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

                    System.out.println("AddProb- called");

                    NewProblem newproblem;

                    try {
                        newproblem = sc.saveProblem();
                        if (database.addProblemToDB(newproblem, username)) {
                            System.out.println("Problem Added");
                        } else {
                            System.out.println("Problem adding failed");
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        System.out.println("Problem Object reading err "+ex.getMessage());
                    }
                    break;
                    
                case "AddSub--":
                   System.out.println("AddSub-- called");
                   int submissionID;

                    NewSubmission newsubmission=null;
                
                    try {
                        newsubmission = sc.saveSubmission();
                        submissionID = database.addSubmissionToDB(newsubmission, username);
                        if(submissionID>-1) {
                         System.out.println("Problem Added");
                        } else {
                            System.out.println("Problem adding failed");
                        }   
                    } catch (IOException | ClassNotFoundException ex) {
                        System.out.println("Submission Object reading err "+ex.getMessage());
                        submissionID = -1;
                    }
                    if(newsubmission!=null){
                        NewProblem problem = database.getProblem(Integer.parseInt(newsubmission.getProblemID()));
                        Thread t = new Thread(new CompileAndRun(problem,newsubmission,submissionID,database));
                        t.start();
                    }
                    break;
            
                case "PrbTable":
                    
                    int x = data.indexOf(']', 9);
                    
                    String identifier = data.substring(9,x);
                    System.out.println(identifier);
                    
                    if(identifier.equals("My")) identifier = username;
                    
                    if(sc.sendProblemTable(database.getProblemTable(identifier))){
                        System.out.println("ProblemTableSend");
                    }
                    else{
                        System.out.println("ProblemTableSend Failed");
                    }
                    
                    break;
                default:
                    break;
            }

        }
        System.out.println("Thread is done");
    }

}
