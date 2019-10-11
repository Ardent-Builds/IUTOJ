/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import newproblem.NewProblem;
import newsubmission.NewSubmission;

/**
 *
 * @author ASADUZZAMAN HEROK
 */
public class CompileAndRun implements Runnable {

    final private NewProblem problem;
    final private NewSubmission submission;
    private ProcessBuilder compile, run, compare;
    private File inputs, validoutputs, useroutputs, submissionfile;
    final String folderpath;
    int submissionID;
    long timetaken;
    Database db;

    public CompileAndRun(NewProblem problem, NewSubmission submission, int submissionID, Database db) {
        this.problem = problem;
        this.submission = submission;
        this.submissionID = submissionID;
        this.db = db;
        this.timetaken = -1;

        folderpath = "CompilerDir/";
        new File("CompilerDir").mkdir();

    }

    private int compileCpp() {
        submissionfile = new File(folderpath + submissionID + ".cpp");
        try {
            FileOutputStream fos = new FileOutputStream(submissionfile);
            fos.write(submission.getCodeF());
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("At CompileCPP FOS Err " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("At CompileCPP FWrite Err " + ex.getMessage());
        }

        compile = new ProcessBuilder("g++", folderpath + submissionID + ".cpp", "-o", folderpath + submissionID);
        try {
            Process p = compile.start();
            try {
                p.waitFor(10, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                p.destroy();
                return -1;
            }
            return p.exitValue();

        } catch (IOException ex) {
            System.out.println("At CompileCPP CompileProcess Err " + ex.getMessage());
            return -2;
        }

    }

    private int compileJava() {
        submissionfile = new File(folderpath + submissionID + ".java");
        try {
            FileOutputStream fos = new FileOutputStream(submissionfile);
            fos.write(submission.getCodeF());
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("At CompileJava FOS Err " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("At CompileJava FWrite Err " + ex.getMessage());
        }

        compile = new ProcessBuilder("javac", folderpath + submissionID + ".java");
        try {
            Process p = compile.start();
            try {
                p.waitFor(10, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                p.destroy();
                return -1;
            }
            return p.exitValue();

        } catch (IOException ex) {
            System.out.println("At CompileCPP CompileProcess Err " + ex.getMessage());
            return -2;
        }

    }

    private int compileC() {
        submissionfile = new File(folderpath + submissionID + ".c");
        try {
            FileOutputStream fos = new FileOutputStream(submissionfile);
            fos.write(submission.getCodeF());
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("At CompileJava FOS Err " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("At CompileJava FWrite Err " + ex.getMessage());
        }

        compile = new ProcessBuilder("gcc", folderpath + submissionID + ".c", "-o", folderpath + submissionID);
        try {
            Process p = compile.start();
            try {
                p.waitFor(10, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                p.destroy();
                return -1;
            }
            return p.exitValue();

        } catch (IOException ex) {
            System.out.println("At CompileCPP CompileProcess Err " + ex.getMessage());
            return -2;
        }

    }

    private int runCppC() {

        run = new ProcessBuilder(folderpath + submissionID + ".exe");
        run.redirectInput(inputs);
        run.redirectOutput(useroutputs);
        System.out.println(Integer.parseInt(problem.getTimeLimit()));
        try {
            long starttime = System.nanoTime();
            Process p = run.start();
            try {
                if (!p.waitFor(Integer.parseInt(problem.getTimeLimit()), TimeUnit.MILLISECONDS)) {
                    long stoptime = System.nanoTime();
                    p.destroy();
                    timetaken = (stoptime - starttime)/1000000;
                    return -1;
                }
            } catch (InterruptedException ex) {
                System.out.println("At runCppC runtime TLE Err " + ex.getMessage());
                return -1;
            }
            long stoptime = System.nanoTime();
            timetaken = (stoptime-starttime)/1000000;
            return p.exitValue();

        } catch (IOException ex) {
            System.out.println("At runCppC runtime Err " + ex.getMessage());
            return -2;
        }
    }

    private int runJava() {

        run = new ProcessBuilder("java", folderpath + Integer.toString(submissionID));
        run.redirectInput(inputs);
        run.redirectOutput(useroutputs);

        try {
            long starttime = System.nanoTime();
            Process p = run.start();
            try {
                if (!p.waitFor(Integer.parseInt(problem.getTimeLimit()), TimeUnit.MILLISECONDS)) {
                    long stoptime = System.nanoTime();
                    p.destroy();
                    timetaken = (stoptime - starttime)/1000000;
                    return -1;
                }
            } catch (InterruptedException ex) {
                p.destroy();
                return -1;
            }
            long stoptime = System.nanoTime();
            timetaken = (stoptime - starttime)/1000000;
            return p.exitValue();

        } catch (IOException ex) {
            System.out.println("At runJava runtime Err " + ex.getMessage());
            return -2;
        }
    }

    @Override
    public void run() {
        System.out.println(submission.getLanguage());
        int iofilestate;

        inputs = new File(folderpath + submissionID + ".in");
        validoutputs = new File(folderpath + submissionID + ".out");
        useroutputs = new File(folderpath + "u" + submissionID + ".out");

        try {
            FileOutputStream fosInp = new FileOutputStream(inputs);
            FileOutputStream fosOup = new FileOutputStream(validoutputs);
            fosInp.write(problem.getInp());
            fosOup.write(problem.getOutp());
            fosInp.close();
            fosOup.close();
            iofilestate = 0;
        } catch (FileNotFoundException ex) {
            iofilestate = -1;
            System.out.println("At Compiler IO file Err " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("At Compiler IO file Err " + ex.getMessage());
            iofilestate = -1;
        }

        if (iofilestate < 0) {
            return;
        }
        boolean ErrBeforeCompareOutput = false;
        switch (submission.getLanguage()) {
            case "C":
                if (compileC() != 0) {
                    db.updateVerdict(submissionID, "Compilation Error",-1);
                    ErrBeforeCompareOutput = true;
                } else {
                    int vr = runCppC();
                    System.out.println(vr);
                    if (vr == -1) {
                        db.updateVerdict(submissionID, "Time Limit Exceeded",(int)timetaken);
                        ErrBeforeCompareOutput = true;
                    } else if (vr != 0) {
                        db.updateVerdict(submissionID, "Run Time Error",-1);
                        ErrBeforeCompareOutput = true;
                    }
                }

                break;
            case "C++":
                if (compileCpp() != 0) {
                    db.updateVerdict(submissionID, "Compilation Error",-1);
                    ErrBeforeCompareOutput = true;
                 
                }else{
                   int vr = runCppC();
                    System.out.println(vr);
                    if (vr == -1) {
                        db.updateVerdict(submissionID, "Time Limit Exceeded",(int)timetaken);
                        ErrBeforeCompareOutput = true;
                    } else if (vr != 0) {
                        db.updateVerdict(submissionID, "Run Time Error",-1);
                        ErrBeforeCompareOutput = true;
                    }
                }
                break;
            case "Java":
                if (compileJava() != 0) {
                    db.updateVerdict(submissionID, "Compilation Error",-1);
                    ErrBeforeCompareOutput = true;
                } else {
                    int vr = runJava();
                    if (vr == -1) {
                        db.updateVerdict(submissionID, "Time Limit Exceeded",(int)timetaken);
                        ErrBeforeCompareOutput = true;
                    } else if (vr != 0) {
                        db.updateVerdict(submissionID, "Run Time Error",-1);
                        ErrBeforeCompareOutput = true;
                    }
                }
                break;
            default:
                break;

        }

        if (!ErrBeforeCompareOutput) {
            int comparisonResult;
            compare = new ProcessBuilder("fc", validoutputs.getAbsolutePath(), useroutputs.getAbsolutePath());

            try {
                Process p = compare.start();
                p.waitFor(2, TimeUnit.MINUTES);
                comparisonResult = p.exitValue();

                //System.out.println(comparisonResult);
                if (comparisonResult == 0) {
                    db.updateVerdict(submissionID, "Accepted",(int)timetaken);
                } else if (comparisonResult == 1) {
                    db.updateVerdict(submissionID, "Wrong Answer",(int)timetaken);
                } else {
                    System.out.println("Output File Missing");
                }
            } catch (IOException ex) {
                System.out.println("Output Compare Process Err: " + ex.getMessage());
            } catch (InterruptedException ex) {
                System.out.println("Too long to compare outputs");
            }
        }
        
        validoutputs.delete();
        inputs.delete();
        useroutputs.delete();
        submissionfile.delete();
        new File(folderpath + submissionID + ".exe").delete();
    }

}
