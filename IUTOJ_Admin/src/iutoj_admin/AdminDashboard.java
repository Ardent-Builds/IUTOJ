/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import newproblem.NewProblem;
import newsubmission.NewSubmission;

/**
 *
 * @author KAWSAR
 */

public class AdminDashboard extends javax.swing.JFrame {

    /**
     * Creates new form UserDashboard
     */
    private final AdminSocket adminsocket;
    private File problem, inputs, outputs;
    Login parent;

    public AdminDashboard(AdminSocket adminsocket, Login parent) {
        initComponents();
        this.adminsocket = adminsocket;
        this.parent = parent;
        setBackground(new Color(0, 0, 0));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 1 ? new Color(242, 242, 242) : Color.WHITE);
                return c;
            }

        };
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        //TableCellRenderer cellRenderer = StatusTable.getCellRenderer(2, 3);

        StatusTable.setDefaultRenderer(Object.class,centerRenderer);
        StatusTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        StatusTable.setRowHeight(25);
        StatusTable.setRowHeight(25);
        JTableHeader statustableheader = StatusTable.getTableHeader();
        ((DefaultTableCellRenderer) statustableheader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        ProblemsetTable.setDefaultRenderer(Object.class, centerRenderer);
        ProblemsetTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        ProblemsetTable.setRowHeight(25);
        JTableHeader problemsettableheader = ProblemsetTable.getTableHeader();
        ((DefaultTableCellRenderer) problemsettableheader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //ProblemsetTable.getTableHeader().setOpaque(false);

        DelProblemsetTable.setDefaultRenderer(Object.class, centerRenderer);
        DelProblemsetTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        DelProblemsetTable.setRowHeight(25);
        ProblemsetTable.setRowHeight(25);
        JTableHeader delproblemsettableheader = DelProblemsetTable.getTableHeader();
        ((DefaultTableCellRenderer) delproblemsettableheader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //DelProblemsetTable.getTableHeader().setBackground(new Color(0, 181, 204));
        //DelProblemsetTable.getTableHeader().setBackground(new Color(255, 255, 255));

        MyProblemsTable.setDefaultRenderer(Object.class, centerRenderer);
        MyProblemsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        MyProblemsTable.setRowHeight(25);
        ProblemsetTable.setRowHeight(25);
        JTableHeader myproblemstableheader = MyProblemsTable.getTableHeader();
        ((DefaultTableCellRenderer) myproblemstableheader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //MyProblemsTable.getTableHeader().setBackground(new Color(0, 181, 204));
        //MyProblemsTable.getTableHeader().setBackground(new Color(255, 255, 255));

        ProblemsetTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1 && !evt.isConsumed()) {
                    evt.consume();
                    int row = ProblemsetTable.rowAtPoint(evt.getPoint());
                    int col = ProblemsetTable.columnAtPoint(evt.getPoint());

                    if (row >= 0 && (col == 0 || col == 1)) {
                        DefaultTableModel tablemodel = (DefaultTableModel) ProblemsetTable.getModel();
                        String temp = tablemodel.getValueAt(row, 0).toString();
                        int x = temp.indexOf('<', 9);
                        String problemid = temp.substring(9, x);

                        adminsocket.sendData("ProbFile[" + problemid + "]");
                        NewProblem problem = adminsocket.getProblem();
                        try {
                            FileOutputStream fos = new FileOutputStream(problemid + ".pdf");
                            fos.write(problem.getProb());
                            fos.close();
                        } catch (FileNotFoundException ex) {
                            System.out.println("At probshow problem write Err: " + ex.getMessage());
                        } catch (IOException ex) {
                            System.out.println("At probshow problem write Err: " + ex.getMessage());
                        }
                        
                        ProblemShow problemshow = new ProblemShow();
                        problemshow.viewPdf(new File(problemid + ".pdf"));
                    }
                }
            }
        });

        MyProblemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1 && !evt.isConsumed()) {
                    evt.consume();
                    int row = MyProblemsTable.rowAtPoint(evt.getPoint());
                    int col = MyProblemsTable.columnAtPoint(evt.getPoint());

                    if (row >= 0 && (col == 0 || col == 1)) {
                        DefaultTableModel tablemodel = (DefaultTableModel) MyProblemsTable.getModel();
                        String temp = tablemodel.getValueAt(row, 0).toString();
                        int x = temp.indexOf('<', 9);
                        String problemid = temp.substring(9, x);

                        adminsocket.sendData("ProbFile[" + problemid + "]");
                        NewProblem problem = adminsocket.getProblem();
                        try {
                            FileOutputStream fos = new FileOutputStream(problemid + ".pdf");
                            fos.write(problem.getProb());
                            fos.close();
                        } catch (FileNotFoundException ex) {
                            System.out.println("At probshow problem write Err: " + ex.getMessage());
                        } catch (IOException ex) {
                            System.out.println("At probshow problem write Err: " + ex.getMessage());
                        }
                        ProblemShow problemshow = new ProblemShow();
                        problemshow.viewPdf(new File(problemid + ".pdf"));
                    }
                }
            }
        });

        StatusTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1 && !evt.isConsumed()) {
                    evt.consume();
                    int row = StatusTable.rowAtPoint(evt.getPoint());
                    int col = StatusTable.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col == 0) {
                        SubmissionShow subshow = new SubmissionShow();
                        DefaultTableModel tablemodel = (DefaultTableModel) StatusTable.getModel();
                        String temp = tablemodel.getValueAt(row, 0).toString();
                        int x = temp.indexOf('<', 9);
                        String submissionid = temp.substring(9, x);
                        subshow.setSubDetailsTable(submissionid, tablemodel.getValueAt(row, 2), tablemodel.getValueAt(row, 3), tablemodel.getValueAt(row, 4), tablemodel.getValueAt(row, 5), tablemodel.getValueAt(row, 6), tablemodel.getValueAt(row, 1));

                        adminsocket.sendData("SrcCode-[" + submissionid + "]");
                        NewSubmission submission = adminsocket.getSubmission();
                        subshow.setSourceCOde(submission);

                    } else if (row >= 0 && col == 3) {
                        DefaultTableModel tablemodel = (DefaultTableModel) StatusTable.getModel();
                        String temp = tablemodel.getValueAt(row, 3).toString();
                        int x = temp.indexOf('-',9);
                        String problemid = temp.substring(9, x);

                        adminsocket.sendData("ProbFile[" + problemid + "]");
                        NewProblem problem = adminsocket.getProblem();
                        try {
                            FileOutputStream fos = new FileOutputStream(problemid + ".pdf");
                            fos.write(problem.getProb());
                            fos.close();
                        } catch (FileNotFoundException ex) {
                            System.out.println("At probshow problem write Err: " + ex.getMessage());
                        } catch (IOException ex) {
                            System.out.println("At probshow problem write Err: " + ex.getMessage());
                        }

                        ProblemShow problemshow = new ProblemShow();
                        problemshow.viewPdf(new File(problemid + ".pdf"));
                    }
                }
            }
        });

        DelProblemsetTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1 && !evt.isConsumed()) {
                    evt.consume();
                    int row = DelProblemsetTable.rowAtPoint(evt.getPoint());
                    int col = DelProblemsetTable.columnAtPoint(evt.getPoint());
                    if (row >= 0 && (col == 0 || col == 1)) {
                        DefaultTableModel tablemodel = (DefaultTableModel) DelProblemsetTable.getModel();
                        String temp = tablemodel.getValueAt(row, 0).toString();
                        int x = temp.indexOf('<', 9);
                        String problemid = temp.substring(9, x);

                        adminsocket.sendData("ProbFile[" + problemid + "]");
                        NewProblem problem = adminsocket.getProblem();
                        try {
                            FileOutputStream fos = new FileOutputStream(problemid + ".pdf");
                            fos.write(problem.getProb());
                            fos.close();
                        } catch (FileNotFoundException ex) {
                            System.out.println("At probshow problem write Err: " + ex.getMessage());
                        } catch (IOException ex) {
                            System.out.println("At probshow problem write Err: " + ex.getMessage());
                        }
                        ProblemShow problemshow = new ProblemShow();
                        problemshow.viewPdf(new File(problemid + ".pdf"));
                    }
                    
                    else if (row >= 0 && col == 3){

                        DefaultTableModel tablemodel = (DefaultTableModel) DelProblemsetTable.getModel();
                        String temp;
                        int x;
                        temp = tablemodel.getValueAt(row, 0).toString();
                        x = temp.indexOf('<', 9);
                        String problemid = temp.substring(9, x);
                        temp = tablemodel.getValueAt(row, 1).toString();
                        x = temp.indexOf('<', 9);
                        String problemname = temp.substring(9, x);

                        if (JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to delete this problem: " + problemid + "-" + problemname + "?", "Delete Problem", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            adminsocket.sendData("DelProb-[" + problemid + "]");

                            Object[][] table;
                            table = adminsocket.getProblemTable();
                            if (table == null) {
                                JOptionPane.showMessageDialog(null, "Table Not found", "Table Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                String[] columns = {"Problem ID", "Problem Name", "Problem Setter", " "};
                                DefaultTableModel model = new DefaultTableModel(table, columns) {
                                    public boolean isCellEditable(int row, int col) {
                                        return false;
                                    }
                                };
                                DelProblemsetTable.setModel(model);
                            }
                        }

                    }
                }
            }
        });

        this.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        AdminDashboardDesktopPane = new javax.swing.JDesktopPane();
        AdminDashboardTabSwitcher = new javax.swing.JTabbedPane();
        HomePanel = new javax.swing.JPanel();
        WelcomeLabel = new javax.swing.JLabel();
        LogOutButton = new javax.swing.JButton();
        ProblemsetPanel = new javax.swing.JPanel();
        ProblemSetjScrollPane = new javax.swing.JScrollPane();
        ProblemsetTable = new javax.swing.JTable();
        MyProblemsPanel = new javax.swing.JPanel();
        MyProblemsjScrollPane = new javax.swing.JScrollPane();
        MyProblemsTable = new javax.swing.JTable();
        ManagePanel = new javax.swing.JPanel();
        ManagePanelTabSwitcher = new javax.swing.JTabbedPane();
        AddProblemPanel = new javax.swing.JPanel();
        AddOutputButton = new javax.swing.JButton();
        ChProblemStatementButton = new javax.swing.JButton();
        AddInputButton = new javax.swing.JButton();
        MemoryLimitLabel = new javax.swing.JLabel();
        ProblemNameLabel = new javax.swing.JLabel();
        TimeLimitLabel = new javax.swing.JLabel();
        txtProblemName = new javax.swing.JTextField();
        txtTimeLimit = new javax.swing.JTextField();
        txtMemoryLimit = new javax.swing.JTextField();
        SubmitButton = new javax.swing.JButton();
        DeleteProblemPanel = new javax.swing.JPanel();
        DelProblemSetjScrollPane = new javax.swing.JScrollPane();
        DelProblemsetTable = new javax.swing.JTable();
        StatusPanel = new javax.swing.JPanel();
        StatusScrollPane = new javax.swing.JScrollPane();
        StatusTable = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(0, 181, 204));

        AdminDashboardDesktopPane.setBackground(new java.awt.Color(255, 255, 255));

        AdminDashboardTabSwitcher.setBackground(new java.awt.Color(255, 255, 255));
        AdminDashboardTabSwitcher.setForeground(new java.awt.Color(0, 181, 204));
        AdminDashboardTabSwitcher.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        AdminDashboardTabSwitcher.setFont(new java.awt.Font("Segoe UI Emoji", 0, 29)); // NOI18N
        AdminDashboardTabSwitcher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdminDashboardTabSwitcherMouseClicked(evt);
            }
        });

        HomePanel.setBackground(new java.awt.Color(255, 255, 255));
        HomePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        WelcomeLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 36)); // NOI18N
        WelcomeLabel.setForeground(new java.awt.Color(0, 181, 204));
        WelcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        WelcomeLabel.setText("Welcome To IUTOJ");
        HomePanel.add(WelcomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 137, -1, 167));

        LogOutButton.setBackground(new java.awt.Color(0, 181, 204));
        LogOutButton.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        LogOutButton.setForeground(new java.awt.Color(0, 181, 204));
        LogOutButton.setText("Log Out");
        LogOutButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 181, 204)));
        LogOutButton.setContentAreaFilled(false);
        LogOutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });
        HomePanel.add(LogOutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 520, 90, 40));

        AdminDashboardTabSwitcher.addTab("Home", HomePanel);

        ProblemsetPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ProblemSetjScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        ProblemSetjScrollPane.setFont(new java.awt.Font("Segoe UI Emoji", 1, 25)); // NOI18N

        ProblemsetTable.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        ProblemsetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Problem ID", "Problem Name", "Problem Setter"
            }
        ));
        ProblemsetTable.setFocusable(false);
        ProblemsetTable.setGridColor(new java.awt.Color(255, 255, 255));
        ProblemsetTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        ProblemsetTable.setOpaque(false);
        ProblemsetTable.setRequestFocusEnabled(false);
        ProblemsetTable.setRowHeight(25);
        ProblemsetTable.setRowSelectionAllowed(false);
        ProblemsetTable.setSelectionBackground(new java.awt.Color(0, 181, 204));
        ProblemsetTable.setShowHorizontalLines(false);
        ProblemsetTable.getTableHeader().setReorderingAllowed(false);
        ProblemsetTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                ProblemsetTableComponentResized(evt);
            }
        });
        ProblemSetjScrollPane.setViewportView(ProblemsetTable);
        ProblemsetTable.getAccessibleContext().setAccessibleDescription("");

        ProblemsetPanel.add(ProblemSetjScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 620));

        AdminDashboardTabSwitcher.addTab("Problemset", ProblemsetPanel);

        MyProblemsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MyProblemsjScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        MyProblemsjScrollPane.setFont(new java.awt.Font("Segoe UI Emoji", 1, 25)); // NOI18N

        MyProblemsTable.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        MyProblemsTable.setForeground(new java.awt.Color(0, 0, 51));
        MyProblemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Problem ID", "Problem Name", "Problem Setter"
            }
        ));
        MyProblemsTable.setFocusable(false);
        MyProblemsTable.setGridColor(new java.awt.Color(255, 255, 255));
        MyProblemsTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        MyProblemsTable.setOpaque(false);
        MyProblemsTable.setRequestFocusEnabled(false);
        MyProblemsTable.setRowHeight(25);
        MyProblemsTable.setRowSelectionAllowed(false);
        MyProblemsTable.setSelectionBackground(new java.awt.Color(0, 181, 204));
        MyProblemsTable.setShowHorizontalLines(false);
        MyProblemsTable.getTableHeader().setReorderingAllowed(false);
        MyProblemsTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                MyProblemsTableComponentResized(evt);
            }
        });
        MyProblemsjScrollPane.setViewportView(MyProblemsTable);

        MyProblemsPanel.add(MyProblemsjScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 620));

        AdminDashboardTabSwitcher.addTab("My Problems", MyProblemsPanel);

        ManagePanel.setBackground(new java.awt.Color(255, 255, 255));
        ManagePanel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        ManagePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ManagePanelTabSwitcher.setForeground(new java.awt.Color(0, 181, 204));
        ManagePanelTabSwitcher.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        ManagePanelTabSwitcher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ManagePanelTabSwitcherMouseClicked(evt);
            }
        });

        AddProblemPanel.setBackground(new java.awt.Color(255, 255, 255));
        AddProblemPanel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        AddOutputButton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        AddOutputButton.setText("Add Output (*.txt)");
        AddOutputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddOutputButtonActionPerformed(evt);
            }
        });

        ChProblemStatementButton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ChProblemStatementButton.setText("Choose Problem Statement (*.pdf,*.txt)");
        ChProblemStatementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChProblemStatementButtonActionPerformed(evt);
            }
        });

        AddInputButton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        AddInputButton.setText("Add Input (*.txt)");
        AddInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddInputButtonActionPerformed(evt);
            }
        });

        MemoryLimitLabel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        MemoryLimitLabel.setForeground(new java.awt.Color(0, 181, 204));
        MemoryLimitLabel.setText("Memory Limit (KB):");

        ProblemNameLabel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        ProblemNameLabel.setForeground(new java.awt.Color(0, 181, 204));
        ProblemNameLabel.setText("Problem Name:");

        TimeLimitLabel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        TimeLimitLabel.setForeground(new java.awt.Color(0, 181, 204));
        TimeLimitLabel.setText("Time Limit (ms):");

        SubmitButton.setBackground(new java.awt.Color(0, 181, 204));
        SubmitButton.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        SubmitButton.setForeground(new java.awt.Color(0, 181, 204));
        SubmitButton.setText("Submit");
        SubmitButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 181, 204)));
        SubmitButton.setContentAreaFilled(false);
        SubmitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SubmitButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddProblemPanelLayout = new javax.swing.GroupLayout(AddProblemPanel);
        AddProblemPanel.setLayout(AddProblemPanelLayout);
        AddProblemPanelLayout.setHorizontalGroup(
            AddProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProblemPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(AddProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddProblemPanelLayout.createSequentialGroup()
                        .addComponent(ProblemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProblemName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ChProblemStatementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddInputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddOutputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AddProblemPanelLayout.createSequentialGroup()
                        .addComponent(TimeLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddProblemPanelLayout.createSequentialGroup()
                        .addComponent(MemoryLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMemoryLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddProblemPanelLayout.createSequentialGroup()
                .addContainerGap(459, Short.MAX_VALUE)
                .addComponent(SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(369, 369, 369))
        );
        AddProblemPanelLayout.setVerticalGroup(
            AddProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProblemPanelLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(ChProblemStatementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(AddInputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(AddOutputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(AddProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProblemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProblemName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TimeLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MemoryLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMemoryLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ManagePanelTabSwitcher.addTab("Add Problem", AddProblemPanel);

        DelProblemSetjScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        DelProblemSetjScrollPane.setFont(new java.awt.Font("Segoe UI Emoji", 1, 25)); // NOI18N

        DelProblemsetTable.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        DelProblemsetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Problem ID", "Problem Name", "Problem Setter", ""
            }
        ));
        DelProblemsetTable.setFocusable(false);
        DelProblemsetTable.setGridColor(new java.awt.Color(255, 255, 255));
        DelProblemsetTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        DelProblemsetTable.setOpaque(false);
        DelProblemsetTable.setRequestFocusEnabled(false);
        DelProblemsetTable.setRowHeight(25);
        DelProblemsetTable.setRowSelectionAllowed(false);
        DelProblemsetTable.setSelectionBackground(new java.awt.Color(0, 181, 204));
        DelProblemsetTable.setShowHorizontalLines(false);
        DelProblemsetTable.getTableHeader().setReorderingAllowed(false);
        DelProblemsetTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                DelProblemsetTableComponentResized(evt);
            }
        });
        DelProblemSetjScrollPane.setViewportView(DelProblemsetTable);

        javax.swing.GroupLayout DeleteProblemPanelLayout = new javax.swing.GroupLayout(DeleteProblemPanel);
        DeleteProblemPanel.setLayout(DeleteProblemPanelLayout);
        DeleteProblemPanelLayout.setHorizontalGroup(
            DeleteProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
            .addGroup(DeleteProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DeleteProblemPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DelProblemSetjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        DeleteProblemPanelLayout.setVerticalGroup(
            DeleteProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
            .addGroup(DeleteProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DeleteProblemPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DelProblemSetjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        ManagePanelTabSwitcher.addTab("Delete Problem", DeleteProblemPanel);

        ManagePanel.add(ManagePanelTabSwitcher, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 610));

        AdminDashboardTabSwitcher.addTab("Manage", ManagePanel);

        StatusPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        StatusScrollPane.setFont(new java.awt.Font("Segoe UI Emoji", 1, 25)); // NOI18N

        StatusTable.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        StatusTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "When", "Who", "Problem", "Lang", "Verdict", "Time"
            }
        ));
        StatusTable.setFocusable(false);
        StatusTable.setGridColor(new java.awt.Color(255, 255, 255));
        StatusTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        StatusTable.setOpaque(false);
        StatusTable.setRequestFocusEnabled(false);
        StatusTable.setRowHeight(25);
        StatusTable.setRowSelectionAllowed(false);
        StatusTable.setSelectionBackground(new java.awt.Color(0, 181, 204));
        StatusTable.setShowHorizontalLines(false);
        StatusTable.getTableHeader().setReorderingAllowed(false);
        StatusTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                StatusTableComponentResized(evt);
            }
        });
        StatusScrollPane.setViewportView(StatusTable);

        StatusPanel.add(StatusScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 620));

        AdminDashboardTabSwitcher.addTab("Status", StatusPanel);

        javax.swing.GroupLayout AdminDashboardDesktopPaneLayout = new javax.swing.GroupLayout(AdminDashboardDesktopPane);
        AdminDashboardDesktopPane.setLayout(AdminDashboardDesktopPaneLayout);
        AdminDashboardDesktopPaneLayout.setHorizontalGroup(
            AdminDashboardDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminDashboardTabSwitcher)
        );
        AdminDashboardDesktopPaneLayout.setVerticalGroup(
            AdminDashboardDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminDashboardDesktopPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(AdminDashboardTabSwitcher, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        AdminDashboardDesktopPane.setLayer(AdminDashboardTabSwitcher, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminDashboardDesktopPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(AdminDashboardDesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AdminDashboardTabSwitcherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminDashboardTabSwitcherMouseClicked
        int x = AdminDashboardTabSwitcher.getSelectedIndex();
        switch (x) {

            case 1:

                adminsocket.sendData("PrbTable[null]");
                Object[][] table = adminsocket.getProblemTable();
                if (table == null) {
                    JOptionPane.showMessageDialog(null, "Table Not found", "Table Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String[] columns = {"Problem ID", "Problem Name", "Problem Setter"};
                    DefaultTableModel tablemodel = new DefaultTableModel(table, columns) {

                        public boolean isCellEditable(int row, int col) {
                            return false;
                        }
                    };
                    ProblemsetTable.setModel(tablemodel);
                }

                break;
            case 2:
                adminsocket.sendData("PrbTable[My]");
                table = adminsocket.getProblemTable();
                if (table == null) {
                    JOptionPane.showMessageDialog(null, "Table Not found", "Table Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String[] columns = {"Problem ID", "Problem Name", "Problem Setter"};
                    DefaultTableModel tablemodel = new DefaultTableModel(table, columns) {
                        public boolean isCellEditable(int row, int col) {
                            return false;
                        }
                    };
                    MyProblemsTable.setModel(tablemodel);
                }
                break;
            case 4:

                adminsocket.sendData("StTable-[nullad]");
                table = adminsocket.getStatusTable();
                if (table == null) {
                    JOptionPane.showMessageDialog(null, "Table Not found", "Table Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String[] columns = {"#", "When", "Who", "Problem", "Lang", "Verdict", "Time"};
                    DefaultTableModel tablemodel = new DefaultTableModel(table, columns) {
                        public boolean isCellEditable(int row, int col) {
                            return false;
                        }
                    };

                    StatusTable.setModel(tablemodel);
                }
                break;
            default:
                break;

        }

    }//GEN-LAST:event_AdminDashboardTabSwitcherMouseClicked

    private void StatusTableComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_StatusTableComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusTableComponentResized

    private void DelProblemsetTableComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_DelProblemsetTableComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_DelProblemsetTableComponentResized

    private void SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitButtonActionPerformed

        String problemname = txtProblemName.getText();
        String timelimit = txtTimeLimit.getText();
        String memorylimit = txtMemoryLimit.getText();

        //        if(problem!=null && inputs!=null && outputs!=null){
        try {
            adminsocket.sendData("AddProb-[" + problem.getName() + "][" + inputs.getName() + "][" + outputs.getName() + "]");
            if (adminsocket.addProblem(problem, inputs, outputs, "null", problemname, timelimit, memorylimit) > 0) {
                JOptionPane.showMessageDialog(null, "Problem file Sent", "Status", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        //        }
    }//GEN-LAST:event_SubmitButtonActionPerformed

    private void AddInputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddInputButtonActionPerformed
        JFileChooser filemanager = new JFileChooser("Documents");

        filemanager.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filemanager.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents", "txt"));
        filemanager.showOpenDialog(this);
        inputs = filemanager.getSelectedFile();

        if (inputs != null) {
            AddInputButton.setText(inputs.getName());
        } else {
            JOptionPane.showMessageDialog(null, "Input files missing", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AddInputButtonActionPerformed

    private void ChProblemStatementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChProblemStatementButtonActionPerformed
        JFileChooser filemanager = new JFileChooser("Documents");

        filemanager.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filemanager.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        filemanager.showOpenDialog(this);
        problem = filemanager.getSelectedFile();
        if (problem != null) {
            ChProblemStatementButton.setText(problem.getName());

        } else {
            JOptionPane.showMessageDialog(null, "Problem file missing", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_ChProblemStatementButtonActionPerformed

    private void AddOutputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddOutputButtonActionPerformed
        JFileChooser filemanager = new JFileChooser("Documents");

        filemanager.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filemanager.addChoosableFileFilter(new FileNameExtensionFilter("Text Document", "txt"));
        filemanager.showOpenDialog(this);
        outputs = filemanager.getSelectedFile();
        if (outputs != null) {
            AddOutputButton.setText(outputs.getName());

        } else {
            JOptionPane.showMessageDialog(null, "Output file missing", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AddOutputButtonActionPerformed

    private void MyProblemsTableComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_MyProblemsTableComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_MyProblemsTableComponentResized

    private void ProblemsetTableComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_ProblemsetTableComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_ProblemsetTableComponentResized

    private void LogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutButtonActionPerformed
               parent.setVisible(true);
               this.dispose();
    }//GEN-LAST:event_LogOutButtonActionPerformed

    private void ManagePanelTabSwitcherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManagePanelTabSwitcherMouseClicked
        int x = ManagePanelTabSwitcher.getSelectedIndex();
        if (x == 1) {
            Object[][] table;
            adminsocket.sendData("PrbTable[MyDel]");
            table = adminsocket.getProblemTable();
            if (table == null) {
                JOptionPane.showMessageDialog(null, "Table Not found", "Table Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String[] columns = {"Problem ID", "Problem Name", "Problem Setter", " "};
                DefaultTableModel tablemodel = new DefaultTableModel(table, columns) {
                    public boolean isCellEditable(int row, int col) {
                        return false;
                    }
                };
                DelProblemsetTable.setModel(tablemodel);
            }
        }
    }//GEN-LAST:event_ManagePanelTabSwitcherMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddInputButton;
    private javax.swing.JButton AddOutputButton;
    private javax.swing.JPanel AddProblemPanel;
    private javax.swing.JDesktopPane AdminDashboardDesktopPane;
    private javax.swing.JTabbedPane AdminDashboardTabSwitcher;
    private javax.swing.JButton ChProblemStatementButton;
    private javax.swing.JScrollPane DelProblemSetjScrollPane;
    private javax.swing.JTable DelProblemsetTable;
    private javax.swing.JPanel DeleteProblemPanel;
    private javax.swing.JPanel HomePanel;
    private javax.swing.JButton LogOutButton;
    private javax.swing.JPanel ManagePanel;
    private javax.swing.JTabbedPane ManagePanelTabSwitcher;
    private javax.swing.JLabel MemoryLimitLabel;
    private javax.swing.JPanel MyProblemsPanel;
    private javax.swing.JTable MyProblemsTable;
    private javax.swing.JScrollPane MyProblemsjScrollPane;
    private javax.swing.JLabel ProblemNameLabel;
    private javax.swing.JScrollPane ProblemSetjScrollPane;
    private javax.swing.JPanel ProblemsetPanel;
    private javax.swing.JTable ProblemsetTable;
    private javax.swing.JPanel StatusPanel;
    private javax.swing.JScrollPane StatusScrollPane;
    private javax.swing.JTable StatusTable;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JLabel TimeLimitLabel;
    private javax.swing.JLabel WelcomeLabel;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField txtMemoryLimit;
    private javax.swing.JTextField txtProblemName;
    private javax.swing.JTextField txtTimeLimit;
    // End of variables declaration//GEN-END:variables
}
