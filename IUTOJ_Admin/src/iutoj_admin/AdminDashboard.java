/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_admin;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    
    public AdminDashboard(AdminSocket adminsocket) {
        initComponents();
        this.adminsocket = adminsocket;
        
        setBackground(new Color(0,0,0));
        
        StatusTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,20));
        StatusTable.setRowHeight(25);
        //ProblemsetTable.getTableHeader().setOpaque(false);
        ProblemsetTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,20));
        ProblemsetTable.getTableHeader().setBackground(new Color(0,181,204));
        ProblemsetTable.getTableHeader().setBackground(new Color(255,255,255));
        ProblemsetTable.setRowHeight(25);
        
        DelProblemsetTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,20));
        DelProblemsetTable.getTableHeader().setBackground(new Color(0,181,204));
        DelProblemsetTable.getTableHeader().setBackground(new Color(255,255,255));
        DelProblemsetTable.setRowHeight(25);
        
        MyProblemsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,20));
        MyProblemsTable.getTableHeader().setBackground(new Color(0,181,204));
        MyProblemsTable.getTableHeader().setBackground(new Color(255,255,255));
        MyProblemsTable.setRowHeight(25);
        
        ProblemsetTable.setBackground(new Color(0,181,204));
        
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
        ProblemIDLabel = new javax.swing.JLabel();
        TimeLimitLabel = new javax.swing.JLabel();
        txtProblemName = new javax.swing.JTextField();
        txtProblemID = new javax.swing.JTextField();
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

        ProblemsetTable.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        ProblemsetTable.setForeground(new java.awt.Color(0, 181, 204));
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
        ProblemsetTable.setSelectionBackground(new java.awt.Color(102, 255, 102));
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

        MyProblemsTable.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        MyProblemsTable.setForeground(new java.awt.Color(0, 181, 204));
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
        MyProblemsTable.setSelectionBackground(new java.awt.Color(102, 255, 102));
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
        MemoryLimitLabel.setText("Memory Limit (kB):");

        ProblemNameLabel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        ProblemNameLabel.setForeground(new java.awt.Color(0, 181, 204));
        ProblemNameLabel.setText("Problem Name:");

        ProblemIDLabel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        ProblemIDLabel.setForeground(new java.awt.Color(0, 181, 204));
        ProblemIDLabel.setText("Problem ID:");

        TimeLimitLabel.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        TimeLimitLabel.setForeground(new java.awt.Color(0, 181, 204));
        TimeLimitLabel.setText("Time Limit (ms):");

        txtProblemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProblemNameActionPerformed(evt);
            }
        });

        txtProblemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProblemIDActionPerformed(evt);
            }
        });

        txtTimeLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimeLimitActionPerformed(evt);
            }
        });

        txtMemoryLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMemoryLimitActionPerformed(evt);
            }
        });

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
                        .addComponent(TimeLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddProblemPanelLayout.createSequentialGroup()
                        .addComponent(ProblemIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProblemID, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddProblemPanelLayout.createSequentialGroup()
                        .addComponent(ProblemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProblemName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddProblemPanelLayout.createSequentialGroup()
                        .addComponent(MemoryLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMemoryLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ChProblemStatementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddInputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddOutputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddProblemPanelLayout.createSequentialGroup()
                .addContainerGap(456, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProblemIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProblemID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TimeLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddProblemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MemoryLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMemoryLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(SubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ManagePanelTabSwitcher.addTab("Add Problem", AddProblemPanel);

        DelProblemSetjScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        DelProblemSetjScrollPane.setFont(new java.awt.Font("Segoe UI Emoji", 1, 25)); // NOI18N

        DelProblemsetTable.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        DelProblemsetTable.setForeground(new java.awt.Color(0, 181, 204));
        DelProblemsetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Problem ID", "Problem Name"
            }
        ));
        DelProblemsetTable.setFocusable(false);
        DelProblemsetTable.setGridColor(new java.awt.Color(255, 255, 255));
        DelProblemsetTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        DelProblemsetTable.setOpaque(false);
        DelProblemsetTable.setRequestFocusEnabled(false);
        DelProblemsetTable.setRowHeight(25);
        DelProblemsetTable.setSelectionBackground(new java.awt.Color(102, 255, 102));
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

        StatusTable.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        StatusTable.setForeground(new java.awt.Color(0, 181, 204));
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
        StatusTable.setSelectionBackground(new java.awt.Color(102, 255, 102));
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

    private void StatusTableComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_StatusTableComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusTableComponentResized

    private void DelProblemsetTableComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_DelProblemsetTableComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_DelProblemsetTableComponentResized

    private void SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubmitButtonActionPerformed

    private void txtMemoryLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMemoryLimitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMemoryLimitActionPerformed

    private void txtTimeLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimeLimitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimeLimitActionPerformed

    private void txtProblemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProblemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProblemIDActionPerformed

    private void txtProblemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProblemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProblemNameActionPerformed

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
//        super.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_LogOutButtonActionPerformed

 
 

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
    private javax.swing.JLabel ProblemIDLabel;
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
    private javax.swing.JTextField txtProblemID;
    private javax.swing.JTextField txtProblemName;
    private javax.swing.JTextField txtTimeLimit;
    // End of variables declaration//GEN-END:variables
}
