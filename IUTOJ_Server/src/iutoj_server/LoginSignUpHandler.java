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

    private final String data, type;
    private final Database database;

    public LoginSignUpHandler(String data, String type, Database db) {

        this.data = data;
        this.type = type;
        this.database = db;
    }

    public boolean isValid() {

        int x = data.indexOf(']', 9);
        int y = data.lastIndexOf(']');
        String usrnm = data.substring(9, x);
        String pswd = data.substring(x + 2, y);
        System.out.println(usrnm + " " + pswd);

        if (type.equals("Admin")) {
            String tmp = database.getAdminPassword(usrnm);
            
            if(tmp.equals(pswd))
                return true;
            else
                return false;
        }
        else if( type.equals("Client"))
        {
            String tmp = database.getClientPassword(usrnm);
            
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
        
        if (type.equals("Admin")) {
           if(database.updateAdmin(usrnm, pswd)){
               return true;
           } 
        }
        else if( type.equals("Client"))
        {
            if(database.updateClient(usrnm, pswd)) {
                return true;
            }
        }
        return false;
    }

}
