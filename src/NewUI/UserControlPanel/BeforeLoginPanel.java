/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewUI.UserControlPanel;

import Business.Account.Account;
import Business.EcoSystem;
import Business.Enterprise.AbstractEnterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import NewUI.DisplayPanel.RegisterPanel;
import NewUI.LayoutMnger;
import NewUI.NewJFrame;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author summershoohaw
 */
public class BeforeLoginPanel extends javax.swing.JPanel {

    /**
     * Creates new form BeforeLogin
     */
    private LayoutMnger top;
    private LayoutMnger bot;
    private LayoutMnger right;
    private LayoutMnger total;
    
    public BeforeLoginPanel(LayoutMnger bot,LayoutMnger top,LayoutMnger right,LayoutMnger total) {
        initComponents();
        this.top = top;
        this.bot = bot;
        this.right = right;
        this.total = total;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pwTxt = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        newBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel1.setText("User Name");

        jLabel2.setText("Password");

        loginBtn.setText("Log in");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        newBtn.setText("I'm new User");
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(userTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pwTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newBtn)))
                .addContainerGap(277, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pwTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginBtn)
                            .addComponent(newBtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:
        // Get user name
        String userName = userTxt.getText();
        // Get Password
        char[] passwordCharArray = pwTxt.getPassword();
        String password = String.valueOf(passwordCharArray);

        //Step1: Check in the system admin user account directory if you have the user
        Account userAccount = EcoSystem.getEcoSystem().getAccountDir().authenticateUser(userName, password);
        System.out.println("check duplicate in system admin:"+EcoSystem.getEcoSystem().getAccountDir().checkDuplicate(userName, password));

        AbstractEnterprise inEnterprise=null;
        Organization inOrganization=null;

        if(userAccount==null){
            //Step 2: Go inside each network and check each enterprise
            for(Network network:EcoSystem.getEcoSystem().getNetworkDirectory().getNetworkDirectory()){
                //Step 2.a: check against each enterprise
                for(AbstractEnterprise enterprise:network.getEnterpriseDir().getEnterpriseDir()){
                    userAccount=enterprise.getAccountDir().authenticateUser(userName, password);
                    System.out.println("check duplicate in enterprise:"+enterprise.getAccountDir().checkDuplicate(userName, password));
                    if(userAccount==null){
                        //Step 3:check against each organization for each enterprise
                        for(Organization organization:enterprise.getOrgDir().getOrgList()){
                            userAccount=organization.getAccountDir().authenticateUser(userName, password);
                            System.out.println("check duplicate in organization:"+organization.getAccountDir().checkDuplicate(userName, password));
                            if(userAccount!=null){
                                inEnterprise = enterprise;
                                inOrganization = organization;
                                break;
                            }
                        }

                    }
                    else{
                        inEnterprise=enterprise;
                        break;
                    }
                    if(inOrganization!=null){
                        break;
                    }
                }
                if(inEnterprise!=null){
                    break;
                }
            }
        }

        if(userAccount==null){
            JOptionPane.showMessageDialog(null, "Invalid credentials");
            return;
        }
        else{
            
            //System.out.println("!!!!!current role is: "+userAccount.hashCode());
            JPanel newWorkArea = userAccount.getRole().createWorkArea(right, userAccount, inOrganization, inEnterprise, EcoSystem.getEcoSystem());
            NewJFrame.getjSplitPane2().setVisible(true);
            total.getLayout().show(total.getUps(),"card3");
            bot.next(newWorkArea);
            top.next(new AfterLoginPanel(userAccount,bot,top,right,total));
        }

    }//GEN-LAST:event_loginBtnActionPerformed

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
        // TODO add your handling code here:
        //NewJFrame.getjSplitPane2().setVisible(true);
        total.next(new RegisterPanel(bot,top,right,total));
    }//GEN-LAST:event_newBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton newBtn;
    private javax.swing.JPasswordField pwTxt;
    private javax.swing.JTextField userTxt;
    // End of variables declaration//GEN-END:variables
}