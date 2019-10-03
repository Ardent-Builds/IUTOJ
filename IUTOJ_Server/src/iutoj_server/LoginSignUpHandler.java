/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class LoginSignUpHandler {

    private final String data, clienttype;
    private final Database database;

    public LoginSignUpHandler(String data, String clienttype, Database db) {

        this.data = data;
        this.clienttype = clienttype;
        this.database = db;
    }

    public boolean isValid() {

        int x = data.indexOf(']', 9);
        int y = data.lastIndexOf(']');
        String usrnm = data.substring(9, x);
        String pswd = data.substring(x + 2, y);
        System.out.println(usrnm + " " + pswd);

        if (clienttype.equals("Admin")) {
            String tmp = database.getAdminPassword(usrnm);
            
            if(tmp.equals(pswd))
                return true;
            else
                return false;
        }
        else if( clienttype.equals("User"))
        {
            String tmp = database.getUserPassword(usrnm);
            
            if(tmp.equals(pswd))
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    
    public boolean SignUp(){
        int x = data.indexOf(']', 9);
        int y = data.lastIndexOf(']');
        String usrnm = data.substring(9, x);
        String pswd = data.substring(x + 2, y);
        System.out.println(usrnm + " " + pswd);
        
        if (clienttype.equals("Admin")) {
           if(database.updateAdmin(usrnm, pswd)){
               return true;
           } 
        }
        else if( clienttype.equals("User"))
        {
            if(database.updateUser(usrnm, pswd)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean doesExist() {

        int x = data.indexOf(']', 9);
        int y = data.lastIndexOf(']');
        String usrnm = data.substring(9, x);
        String pswd = data.substring(x + 2, y);
        System.out.println(usrnm + " " + pswd);

        if (clienttype.equals("Admin")) {
            String tmp = database.getAdminPassword(usrnm);
            
            if(tmp.equals("No#Data"))
                return false;
            else
                return true;
        }
        else if( clienttype.equals("User"))
        {
            String tmp = database.getUserPassword(usrnm);
            
            if(tmp.equals("No#Data"))
                return false;
            else
                return true;
        }
        else
            return false;
    }

}
