/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class Database {
    
    
    Connection conn;
    PreparedStatement prprdstmnt;
    Statement stmnt;
    public Database() throws SQLException{
        conn = DriverManager.getConnection("jdbc:sqlite:src/Database/database.db");
        prprdstmnt = null;
        stmnt = null;
    }
    
    
    public synchronized String getAdminPassword(String usrname){
        String query = "select password from Teacher where username = '"+usrname+"'";
        
        try {
            stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            if(rs.next()==false){
                return "NoData";
            }
            
            return rs.getString(1);
                    
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Statement error "+ex);
            return "NoData";
        }
        
    }
    
    public synchronized String getClientPassword(String usrname){
        String query = "select password from Student where username = '"+usrname+"'";
        
        try {
            stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            if(rs.next()==false){
                return "NO#Data";
            }
            
            return rs.getString(1);
                    
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Statement error "+ex);
            return "No$#Data";
        }
    }
    
    public synchronized boolean updateAdmin(String usrname, String password){
        
        String update = "INSERT INTO teacher(username,password) values(?,?)";
        
        try {
            prprdstmnt = conn.prepareStatement(update);
            prprdstmnt.setString(1, usrname);
            prprdstmnt.setString(2, password);
            prprdstmnt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public synchronized boolean updateClient(String usrname, String password){
        
        String update = "INSERT INTO student(username,password) values(?,?)";
        
        try {
            prprdstmnt = conn.prepareStatement(update);
            prprdstmnt.setString(1, usrname);
            prprdstmnt.setString(2, password);
            prprdstmnt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
}
