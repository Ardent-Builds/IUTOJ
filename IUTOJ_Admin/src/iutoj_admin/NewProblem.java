/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_admin;

import java.io.Serializable;



/**
 *
 * @author KAWSAR
 */
public class NewProblem implements Serializable {
    
    private String problemid;
    private String problemname;
    private String timelimit;
    private String memorylimit;
    private byte[] prob;
    private byte[] inp;
    private byte[] outp;
    
    public void setProblemID(String problemid) {
        this.problemid = problemid;
    }
    public String getProblemID() {
        return problemid;
    }
    
    public void setProblemName(String problemname) {
        this.problemname = problemname;
    }
    public String getProblemName() {
        return problemname;
    }
    
    public void setTimeLimit(String timelimit) {
        this.timelimit = timelimit;
    }
    public String getTimeLimit() {
        return timelimit;
    }
    
    public void setMemoryLimit(String memorylimit) {
        this.memorylimit = memorylimit;
    }
    public String getMemoryLimit() {
        return memorylimit;
    }
    
    public void setProb(byte[] prob) {
        this.prob = prob;
    }
    public byte[] getProb() {
        return prob;
    }
    
    public void setInp(byte[] inp) {
        this.inp = inp;
    }
    public byte[] getInp() {
        return inp;
    }
    
     public void setOutp(byte[] outp) {
        this.outp = outp;
    }
    public byte[] getOutp() {
        return outp;
    }

    

    
}
