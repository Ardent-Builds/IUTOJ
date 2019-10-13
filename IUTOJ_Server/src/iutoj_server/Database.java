/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import newproblem.NewProblem;
import newsubmission.NewSubmission;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class Database {

    Connection conn;
    PreparedStatement prprdstmnt;
    Statement stmnt;

    public Database() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite::resource:Database/database.db");
        prprdstmnt = null;
        stmnt = null;
    }

    public synchronized String getAdminPassword(String usrname) {
        String query = "select password from Teacher where username = '" + usrname + "'";

        try {
            stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            if (rs.next() == false) {
                return "No#Data";
            }

            return rs.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Statement error " + ex);
            return "No#Data";
        }

    }

    public synchronized String getUserPassword(String usrname) {
        String query = "select password from Student where username = '" + usrname + "'";

        try {
            stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            if (rs.next() == false) {
                return "No#Data";
            }

            return rs.getString(1);

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Statement error " + ex);
            return "No#Data";
        }
    }

    public synchronized boolean updateAdmin(String usrname, String password) {

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

    public synchronized boolean updateUser(String usrname, String password) {

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

    public synchronized boolean addProblemToDB(NewProblem problem, String username) {

        String rowcountquery = "SELECT MAX(ProblemID) AS ROWCOUNT FROM problemset";
        String update = "INSERT INTO problemset(ProblemID, ProblemName, ProblemSetter, TimeLimit, MemoryLimit, ProblemStatement, Inputs, Outputs) VALUES(?,?,?,?,?,?,?,?)";
        int rowcount = 0;

        try {
            stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(rowcountquery);

            if (rs.next()) {
                rowcount = rs.getInt("rowcount");
            }
        } catch (SQLException ex) {
            System.out.println("RowCountErr " + ex.getMessage());
        }

        try {
            System.out.println(username + ' ' + problem.getOutp().length);

            prprdstmnt = conn.prepareStatement(update);

            prprdstmnt.setInt(1, rowcount + 1);
            prprdstmnt.setString(2, problem.getProblemName());
            prprdstmnt.setString(3, username);
            prprdstmnt.setInt(4, Integer.parseInt(problem.getTimeLimit()));
            prprdstmnt.setInt(5, Integer.parseInt(problem.getMemoryLimit()));
            prprdstmnt.setBytes(6, problem.getProb());
            prprdstmnt.setBytes(7, problem.getInp());
            prprdstmnt.setBytes(8, problem.getOutp());

            prprdstmnt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Insert problem Err " + ex.getMessage());
            return false;
        }
    }
    
    public synchronized void deleteProblem(int problemid){
        String update = "DELETE FROM PROBLEMSET WHERE PROBLEMID = ?";
        System.out.println(update);
        try{
            prprdstmnt = conn.prepareStatement(update);
            prprdstmnt.setInt(1, problemid);
            prprdstmnt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Delete Problem " + ex.getMessage());
        }
        
    }
    public synchronized int addSubmissionToDB(NewSubmission submission, String username) {
         String rowcountquery = "SELECT COUNT(*) AS ROWCOUNT FROM submissions";
         String update = "INSERT INTO Submissions(SubmissionID, ProblemID, Language, SubmittedBy, CodeFile, TimeOfSubmission, TimeTaken, Verdict) VALUES(?,?,?,?,?,?,?,?)";
         int rowcount = 0;
         
          try {
            stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(rowcountquery);

            if (rs.next()) {
                rowcount = rs.getInt("rowcount");
            }
        } catch (SQLException ex) {
            System.out.println("RowCountErr " + ex.getMessage());
        }

        try {
            String timeStamp = new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss").format(Calendar.getInstance().getTime());

            prprdstmnt = conn.prepareStatement(update);

            prprdstmnt.setInt(1, rowcount + 1);
            prprdstmnt.setInt(2, Integer.parseInt(submission.getProblemID()));
            prprdstmnt.setString(3, submission.getLanguage());
            prprdstmnt.setString(4, username);
            prprdstmnt.setBytes(5, submission.getCodeF());
            prprdstmnt.setString(6,timeStamp);
            prprdstmnt.setInt(7, -1);
            prprdstmnt.setString(8, "Not Judged");
           

            prprdstmnt.executeUpdate();
            return rowcount+1;

        } catch (SQLException ex) {
            System.out.println("Insert problem Err " + ex.getMessage());
            return -1;
        }
         
    }
    
    public synchronized void updateVerdict(int sumbimmissionID, String verdict, int timetaken){
        String update = "UPDATE Submissions SET Verdict = ?, TimeTaken = ? WHERE SubmissionID = ?";
        System.out.println("DB Update verdict called");
        
        try{
            prprdstmnt = conn.prepareStatement(update);
            prprdstmnt.setString(1, verdict);
            prprdstmnt.setInt(3, sumbimmissionID);
            prprdstmnt.setInt(2, timetaken);
            
            prprdstmnt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("DB updateVerdict Err: "+ex.getMessage());
        }
        
        
    }

    public synchronized NewProblem getProblem(int problemID) {

        String query = "SELECT * FROM PROBLEMSET WHERE PROBLEMID = " + problemID;

        try {
            stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);

            if (rs.next() == false) {
                return null;
            }

            NewProblem problem = new NewProblem();
            problem.setProblemName(rs.getString("ProblemName"));
            problem.setProblemID(Integer.toString(rs.getInt("ProblemID")));
            problem.setTimeLimit(Integer.toString(rs.getInt("TimeLimit")));
            problem.setMemoryLimit(Integer.toString(rs.getInt("MemoryLimit")));
            problem.setProb(rs.getBytes("ProblemStatement"));
            problem.setInp(rs.getBytes("inputs"));
            problem.setOutp(rs.getBytes("outputs"));

            return problem;

        } catch (SQLException ex) {
            System.out.println("problem query Err " + ex.getMessage());
            return null;
        }

    }
    
    public synchronized NewSubmission getSubmission(int submissionID) {

        String query = "SELECT * FROM Submissions WHERE SubmissionID = " + submissionID;

        try {
            stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(query);

            if (rs.next() == false) {
                return null;
            }

            NewSubmission submission = new NewSubmission();
            
            submission.setProblemID(rs.getString("ProblemID"));
            submission.setLanguage(rs.getString("Language"));
            submission.setCodeF(rs.getBytes("CodeFile"));

            return submission;

        } catch (SQLException ex) {
            System.out.println("problem query Err " + ex.getMessage());
            return null;
        }

    }

    public synchronized String[][] getProblemTable(String identifier, String identifier2) {
        String query;
        if (identifier.equals("null")) {
            query = "SELECT ProblemID, ProblemName, ProblemSetter FROM Problemset";
        } else {
            query = "SELECT ProblemID, ProblemName, ProblemSetter FROM Problemset WHERE ProblemSetter = '" + identifier + "'";
        }
        
        ResultSet rs;
        try{
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery(query);
            
            int x;
            if(identifier2.equals("MyDel")){
                String[][] table = new String[40][4];
                while(rs.next()){
                    x = rs.getRow()-1;
                    table[x][0] ="<HTML><U><FONT COLOR='BLUE'>"+Integer.toString(rs.getInt("ProblemID"))+"</FONT></U></HTML>";
                    table[x][1] ="<HTML><U><FONT COLOR='BLUE'>"+rs.getString("ProblemName")+"</FONT></U></HTML>";
                    table[x][2] = rs.getString("ProblemSetter");
                    table[x][3] = "<HTML><U><FONT COLOR='RED'>DELETE</FONT></U></HTML>";
                
                System.out.println(table[x][0]+" "+table[x][1]+" "+table[x][2]+" "+table[x][3]);
                }
                return table;
            }
            else{
                String[][] table = new String[40][3];
            
                while(rs.next()){
                    x = rs.getRow()-1;
                    table[x][0] ="<HTML><U><FONT COLOR='BLUE'>"+Integer.toString(rs.getInt("ProblemID"))+"</FONT></U></HTML>";
                    table[x][1] ="<HTML><U><FONT COLOR='BLUE'>"+rs.getString("ProblemName")+"</FONT></U></HTML>";
                    table[x][2] = rs.getString("ProblemSetter");
                
                System.out.println(table[x][0]+" "+table[x][1]+" "+table[x][2]);
                }
                return table;
            }
            
            
            
            
        } catch (SQLException ex) {
            System.out.println("DB getProblemTable err"+ ex.getMessage());
            return null;
        }

    }
    
    public synchronized String[][] getStatusTable(String identifier) {
        String query;
        System.out.println(identifier);
        if (identifier.equals("nullad") || identifier.equals("nullus")) {
            query = "SELECT Submissions.SubmissionID AS SubmissionID, Submissions.SubmittedBy AS SubmittedBy, Submissions.ProblemID AS ProblemID, Problemset.ProblemName AS ProblemName, Submissions.Language AS Language, Submissions.TimeOfSubmission AS TimeOfSubmission, Submissions.Verdict AS Verdict, Submissions.TimeTaken AS TimeTaken FROM Submissions, Problemset WHERE Submissions.ProblemID=Problemset.ProblemID";
        } else {
            query = "SELECT Submissions.SubmissionID AS SubmissionID, Submissions.SubmittedBy AS SubmittedBy, Submissions.ProblemID AS ProblemID, Problemset.ProblemName AS ProblemName, Submissions.Language AS Language, Submissions.TimeOfSubmission AS TimeOfSubmission, Submissions.Verdict AS Verdict, Submissions.TimeTaken AS TimeTaken FROM Submissions, Problemset WHERE Submissions.ProblemID=Problemset.ProblemID and Submissions.Submittedby = '"+identifier+"'";
        }
        
        ResultSet rs;
        try{
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery(query);
            
            int x;
            String[][] table = new String[40][7];
            
            while(rs.next()){
                x = rs.getRow()-1;
                if(identifier.equals("nullus")){
                    table[x][0] = Integer.toString(rs.getInt("SubmissionID"));
                }
                else{
                    table[x][0] ="<HTML><U><FONT COLOR='BLUE'>"+Integer.toString(rs.getInt("SubmissionID"))+"</FONT></U></HTML>";
                }
                table[x][1] = rs.getString("TimeOfSubmission");
                table[x][2] = rs.getString("SubmittedBy");
                table[x][3] ="<HTML><U><FONT COLOR='BLUE'>"+Integer.toString(rs.getInt("ProblemID"))+"-"+ rs.getString("ProblemName")+"</FONT></U></HTML>";
                table[x][4] = rs.getString("Language");
                table[x][5] = rs.getString("Verdict");
                table[x][6] = rs.getString("TimeTaken");
                System.out.println(table[x][0]+" "+table[x][1]+" "+table[x][2]+" "+table[x][3]+" "+table[x][4]+" "+table[x][5]+" "+table[x][6]);
            }
            
            return table;
            
            
        } catch (SQLException ex) {
            System.out.println("DB getProblemTable err"+ ex.getMessage());
            return null;
        }

    }
    
    

    

}
