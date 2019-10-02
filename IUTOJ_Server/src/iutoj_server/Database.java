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
        conn = DriverManager.getConnection("jdbc:sqlite:src/Database/database.db");
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

    public synchronized String getClientPassword(String usrname) {
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

    public synchronized boolean updateClient(String usrname, String password) {

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

        String rowcountquery = "SELECT COUNT(*) AS ROWCOUNT FROM problemset";
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
    public synchronized boolean addSubmissionToDB(NewSubmission submission, String username) {
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
            System.out.println(submission.getCodeF());

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
            return true;

        } catch (SQLException ex) {
            System.out.println("Insert problem Err " + ex.getMessage());
            return false;
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

    public synchronized String[][] getProblemTable(String identifier) {
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
            String[][] table = new String[25][3];
            
            while(rs.next()){
                x = rs.getRow()-1;
                table[x][0] = Integer.toString(rs.getInt("ProblemID"));
                table[x][1] = rs.getString("ProblemName");
                table[x][2] = rs.getString("ProblemSetter");
                System.out.println(table[x][0]+" "+table[x][1]+' '+table[x][2]);
            }
            
            return table;
            
            
        } catch (SQLException ex) {
            System.out.println("DB getProblemTable err"+ ex.getMessage());
            return null;
        }

    }

    

}
