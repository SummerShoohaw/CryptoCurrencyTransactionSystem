/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.CustomerRole;

import Business.Account.Account;
import Business.EcoSystem;
import Business.Enterprise.AbstractEnterprise;
import Business.Enterprise.BrokerEnterprise;
import Business.Enterprise.CoinEnterprise;
import Business.Network.Network;
import Business.Network.NetworkDirectory;
import Business.Organization.Broker.MngRequest;
import Business.Organization.Organization;
import Business.Wallet.Wallet;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author huali
 */
public class SubmitRequestJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SubmitRequestJPanel
     */
    JPanel userProcessContainer;
    Account account;
    
    public SubmitRequestJPanel(JPanel userProcessContainer, Account account) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        populateComboBox();
    }
    
    public void populateComboBox(){
        coinJComboBox.removeAllItems();
        brokerJComboBox.removeAllItems();
        ArrayList<Network> networkDir = EcoSystem.getEcoSystem().getNetworkDirectory().getNetworkDirectory();
        for(Network network: networkDir){
            for(Wallet w:network.getCoinEnter().getWalletDirectory().getWalletList()){
                if(w.getCustomer().equals(this.account.getCus())){
                    coinJComboBox.addItem(network.getCoinEnter());
                    break;
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        brokerJComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        buySellJComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        priceJTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        coinJComboBox = new javax.swing.JComboBox();
        backjButton1 = new javax.swing.JButton();
        createUserJButton = new javax.swing.JButton();
        AmountText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jLabel5.setText("Broker");

        brokerJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        brokerJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brokerJComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setText("Buy/Sell");

        buySellJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Buy", "Sell" }));
        buySellJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buySellJComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Amount");

        jLabel7.setText("Coin Type");

        coinJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        coinJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coinJComboBoxActionPerformed(evt);
            }
        });

        backjButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backjButton1.setText("<< Back");
        backjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backjButton1ActionPerformed(evt);
            }
        });

        createUserJButton.setText("Submit");
        createUserJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserJButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Preferred Price");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(backjButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createUserJButton)
                .addGap(75, 75, 75))
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AmountText, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(coinJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(priceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buySellJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(brokerJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(192, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(coinJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(brokerJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(buySellJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AmountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createUserJButton)
                    .addComponent(backjButton1))
                .addGap(49, 49, 49))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void brokerJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brokerJComboBoxActionPerformed
 
    }//GEN-LAST:event_brokerJComboBoxActionPerformed

    private void buySellJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buySellJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buySellJComboBoxActionPerformed

    private void coinJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coinJComboBoxActionPerformed
        // TODO add your handling code here:
        brokerJComboBox.removeAllItems();
        ArrayList<Network> networkDir = EcoSystem.getEcoSystem().getNetworkDirectory().getNetworkDirectory();
        for(Network network: networkDir){
            if(network.getCoinEnter().equals(coinJComboBox.getSelectedItem())){
                for(AbstractEnterprise e: network.getEnterpriseDir().getEnterpriseDir()){
                    if(e.getType().getValue().equals("Broker")){
//                        Organization org = ((BrokerEnterprise)e).getOrgDir().getOrgList().get(0);
                        brokerJComboBox.addItem((BrokerEnterprise)e);
                    }
                    
                }
                
            }
        }
    }//GEN-LAST:event_coinJComboBoxActionPerformed

    private void backjButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backjButton1ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backjButton1ActionPerformed

    private void createUserJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserJButtonActionPerformed
        double dollarBalance = account.getCus().getDollarBalance();
        double dollarNeed = Double.parseDouble(priceJTextField.getText())*Double.parseDouble(AmountText.getText());
        String type = (String) buySellJComboBox.getSelectedItem();
        if(type.equals("Buy") && dollarBalance < dollarNeed*1.01){
            JOptionPane.showMessageDialog(null,"Dear customer, you dont have enough dollar in your account");
            return;
        }
        else if(type.equals("Sell") && dollarBalance<dollarNeed*0.01){
            JOptionPane.showMessageDialog(null,"Dear customer, you dont have enough dollar in your account");
            return;
        }
        BrokerEnterprise be = (BrokerEnterprise) brokerJComboBox.getSelectedItem();
        MngRequest mngR = (MngRequest) be.getOrgDir().getOrgList().get(0);
        CoinEnterprise ce = (CoinEnterprise) coinJComboBox.getSelectedItem();
        
        int selection = JOptionPane.showConfirmDialog(null, "Request Confirm","Confirmation",JOptionPane.YES_NO_OPTION);
        if(selection == JOptionPane.YES_OPTION){
            try{
                this.account.getCus().sendReq(type.toLowerCase(), Integer.parseInt(priceJTextField.getText()), mngR, ce.getCoinType(), Double.parseDouble(AmountText.getText()));
                be.addDollarBalance(dollarNeed*0.005);
                account.getCus().addDollarBalance(-dollarNeed*0.005);
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Please enter valid number");
            }
        }
    }//GEN-LAST:event_createUserJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AmountText;
    private javax.swing.JButton backjButton1;
    private javax.swing.JComboBox brokerJComboBox;
    private javax.swing.JComboBox buySellJComboBox;
    private javax.swing.JComboBox coinJComboBox;
    private javax.swing.JButton createUserJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField priceJTextField;
    // End of variables declaration//GEN-END:variables
}