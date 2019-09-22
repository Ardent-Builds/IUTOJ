/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_admin;

import java.io.IOException;

/**
 *
 * @author KAWSAR
 */
public class IUTOJ_Admin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        AdminSocket adminSocket = new AdminSocket();
        adminSocket.connect("localhost",1235);
        
        Login loginPage = new Login(adminSocket);
    }
    
}
