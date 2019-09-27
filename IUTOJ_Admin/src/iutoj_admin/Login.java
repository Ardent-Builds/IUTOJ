/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutoj_admin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author KAWSAR
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    
    private AdminSocket adminSocket;
    private SignUp signup;
    AdminDashboard dashboard;
    
    
    public Login(AdminSocket adminSocket) {
        initComponents();
        this.setVisible(true);
        this.adminSocket = adminSocket;
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LeftPanel = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        RightPanel = new javax.swing.JPanel();
        WelcomeLabel = new javax.swing.JLabel();
        closeLabel = new javax.swing.JLabel();
        minimizeLabel = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        LoginButton = new javax.swing.JButton();
        PasswordField = new javax.swing.JPasswordField();
        PasswordLabel = new javax.swing.JLabel();
        StudentIDLabel = new javax.swing.JLabel();
        leftSeparator = new javax.swing.JSeparator();
        orLabel = new javax.swing.JLabel();
        rightSeparator = new javax.swing.JSeparator();
        CrNewAccButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LeftPanel.setBackground(new java.awt.Color(0, 181, 204));
        LeftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/IUTOJ-Logo-1st-try-small.png"))); // NOI18N
        logoLabel.setText("jLabel1");
        LeftPanel.add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 400, 420));

        getContentPane().add(LeftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 560));

        RightPanel.setBackground(new java.awt.Color(255, 255, 255));
        RightPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        WelcomeLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 38)); // NOI18N
        WelcomeLabel.setForeground(new java.awt.Color(0, 181, 204));
        WelcomeLabel.setText("Admin Panel");
        RightPanel.add(WelcomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 240, 70));

        closeLabel.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        closeLabel.setForeground(new java.awt.Color(0, 181, 204));
        closeLabel.setText("x");
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLabelMouseClicked(evt);
            }
        });
        RightPanel.add(closeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 30, 20));

        minimizeLabel.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        minimizeLabel.setForeground(new java.awt.Color(0, 181, 204));
        minimizeLabel.setText("_");
        minimizeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeLabelMouseClicked(evt);
            }
        });
        RightPanel.add(minimizeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, -10, 30, 40));

        txtUsername.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(102, 102, 102));
        txtUsername.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        txtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsernameFocusLost(evt);
            }
        });
        RightPanel.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 270, 50));

        LoginButton.setBackground(new java.awt.Color(0, 181, 204));
        LoginButton.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        LoginButton.setForeground(new java.awt.Color(0, 181, 204));
        LoginButton.setText("Login");
        LoginButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 181, 204)));
        LoginButton.setContentAreaFilled(false);
        LoginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LoginButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        RightPanel.add(LoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 80, 40));

        PasswordField.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        PasswordField.setForeground(new java.awt.Color(102, 102, 102));
        PasswordField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        RightPanel.add(PasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 237, 270, 30));

        PasswordLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 20)); // NOI18N
        PasswordLabel.setForeground(new java.awt.Color(0, 181, 204));
        PasswordLabel.setText("Password");
        RightPanel.add(PasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 120, 30));

        StudentIDLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 20)); // NOI18N
        StudentIDLabel.setForeground(new java.awt.Color(0, 181, 204));
        StudentIDLabel.setText("Username");
        RightPanel.add(StudentIDLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 120, 30));
        RightPanel.add(leftSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 180, 20));

        orLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        orLabel.setForeground(new java.awt.Color(102, 102, 102));
        orLabel.setText("or");
        RightPanel.add(orLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 420, 50, 20));
        RightPanel.add(rightSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, 170, 40));

        CrNewAccButton.setBackground(new java.awt.Color(0, 181, 204));
        CrNewAccButton.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        CrNewAccButton.setForeground(new java.awt.Color(0, 181, 204));
        CrNewAccButton.setText("Create New Account");
        CrNewAccButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 181, 204)));
        CrNewAccButton.setContentAreaFilled(false);
        CrNewAccButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CrNewAccButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CrNewAccButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrNewAccButtonButtonActionPerformed(evt);
            }
        });
        RightPanel.add(CrNewAccButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 270, 40));

        getContentPane().add(RightPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 530, 560));

        setSize(new java.awt.Dimension(954, 556));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        String username = txtUsername.getText();
        String password = PasswordField.getText();
        String dataout = "Login---["+username+"]["+password+"]";
        System.out.println(dataout);
        if(adminSocket.sendData(dataout)<0){
            JOptionPane.showMessageDialog(null,"Timeout sending data!!!","Timeout",JOptionPane.ERROR_MESSAGE);
        }
        
        String datain=adminSocket.readData();
        System.out.println("data read");
        if(datain != null){
            if(datain.equals("LoginTrue")){
                JOptionPane.showMessageDialog(null,"Login Successfull!","Status",JOptionPane.INFORMATION_MESSAGE);
                dashboard = new AdminDashboard(adminSocket);
                this.setVisible(false);
            }
            else if(datain.equals("LoginFalse")){
                JOptionPane.showMessageDialog(null,"Invalid Username and Password","Status",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Timeout reading data!","Timeout",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void closeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLabelMouseClicked
        try{
            adminSocket.close();
        }catch(IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);       
    }//GEN-LAST:event_closeLabelMouseClicked

    private void minimizeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeLabelMouseClicked
        this.setExtendedState(JFrame.ICONIFIED);      // TODO add your handling code here:
    }//GEN-LAST:event_minimizeLabelMouseClicked

    private void txtUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusGained
        if(txtUsername.getText().equals("Enter Username")) 
        {
            txtUsername.setText("");
        }
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameFocusGained

    private void txtUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusLost
        if(txtUsername.getText().equals("")) 
        {
            txtUsername.setText("Enter Username");
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameFocusLost

    private void CrNewAccButtonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrNewAccButtonButtonActionPerformed
        //String
        signup = new SignUp(adminSocket);
        signup.setVisible(rootPaneCheckingEnabled);
        
    }//GEN-LAST:event_CrNewAccButtonButtonActionPerformed
    
    static int xx, yy;
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
         // TODO add your handling code here:
        xx=evt.getX();
        yy=evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x, y;
        x=evt.getXOnScreen();
        y=evt.getYOnScreen();
        this.setLocation(x-xx, y-yy);// TODO add your handling code here:
    }//GEN-LAST:event_formMouseDragged

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CrNewAccButton;
    private javax.swing.JPanel LeftPanel;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JPanel RightPanel;
    private javax.swing.JLabel StudentIDLabel;
    private javax.swing.JLabel WelcomeLabel;
    private javax.swing.JLabel closeLabel;
    private javax.swing.JSeparator leftSeparator;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel minimizeLabel;
    private javax.swing.JLabel orLabel;
    private javax.swing.JSeparator rightSeparator;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
