/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewUI.DisplayPanel;

import Business.Account.Account;
import Business.EcoSystem;
import Business.Investor.Miner;
import Business.Network.Network;
import Interface.CustomerRole.AddWalletJPanel;
import Interface.CustomerRole.PriceChartJPanel;
import Interface.CustomerRole.SubmitRequestJPanel;
import Interface.FeedbackMessage.FeedbackMessageJPanel;
import NewUI.LayoutMnger;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author summershoohaw
 */
public class MinerWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form MinerWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private Account account;
    private Miner miner;
    public MinerWorkAreaJPanel(LayoutMnger bot, Account account) {
        initComponents();
        userProcessContainer = bot.getUps();
        this.account = account;
        miner = (Miner)account.getCus();
        initCoinBox();
        
    }

    public void initCoinBox(){
        coinBox.removeAllItems();
        coinBox.addItem("Please select");
        int count = 0;
        for(Network nw:EcoSystem.getEcoSystem().getNetworkDirectory().getNetworkDirectory()){
            if(EcoSystem.getEcoSystem().getWalletDirectory(nw.getCoinType()).searchWalletViaCustomer(miner) != null)
                coinBox.addItem(nw.getCoinType());
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

        jSeparator2 = new javax.swing.JSeparator();
        transjButton = new javax.swing.JButton();
        btnAddWallet = new javax.swing.JButton();
        mineBtn = new javax.swing.JButton();
        coinBox = new javax.swing.JComboBox<>();
        balanceBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        historyjButton1 = new javax.swing.JButton();
        balanceBtn1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        transjButton.setText("Submit Buy/Sell Application");
        transjButton.setActionCommand("Submit Buy/Sell Request");
        transjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transjButtonActionPerformed(evt);
            }
        });

        btnAddWallet.setText("Add Wallet");
        btnAddWallet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWalletActionPerformed(evt);
            }
        });

        mineBtn.setText("Start Mining");
        mineBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mineBtnActionPerformed(evt);
            }
        });

        coinBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        balanceBtn.setText("Check my Crypto");
        balanceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceBtnActionPerformed(evt);
            }
        });

        jButton1.setText("Check my dollar wallet");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        historyjButton1.setText("View Transaction History");
        historyjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyjButton1ActionPerformed(evt);
            }
        });

        balanceBtn1.setText("View Price Chart");
        balanceBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceBtn1ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mineBtn)
                    .addComponent(transjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAddWallet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(historyjButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(balanceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(balanceBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coinBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnAddWallet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(transjButton)
                .addGap(18, 18, 18)
                .addComponent(historyjButton1)
                .addGap(18, 18, 18)
                .addComponent(balanceBtn)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(balanceBtn1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(coinBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mineBtn)
                .addContainerGap(132, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void transjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transjButtonActionPerformed
        // TODO add your handling code here:
        SubmitRequestJPanel srjp = new SubmitRequestJPanel(userProcessContainer, account);
        userProcessContainer.add("SubmitRequestJPanel", srjp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_transjButtonActionPerformed

    private void btnAddWalletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWalletActionPerformed
        // TODO add your handling code here:
        AddWalletJPanel addWallet = new AddWalletJPanel(userProcessContainer, account);
        userProcessContainer.add("AddWalletJPanel", addWallet);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_btnAddWalletActionPerformed

    private void mineBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mineBtnActionPerformed
        // TODO add your handling code here:
        String selectedType = (String)coinBox.getSelectedItem();
        if(selectedType.equals("Please select")){
            JOptionPane.showMessageDialog(null,"Please select a coin type");
        }
        else{
            if(miner.enableAutoMiner(selectedType))
                enterMiningPanel(selectedType);
        }
    }//GEN-LAST:event_mineBtnActionPerformed

    private void balanceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceBtnActionPerformed
        // TODO add your handling code here:
        CustomerBalancePanel balancePanel = new CustomerBalancePanel(account.getCus(),userProcessContainer);
        userProcessContainer.add("CheckBalanceJPanel",balancePanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_balanceBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DollarPanel dollarPanel = new DollarPanel(account.getCus(),userProcessContainer);
        userProcessContainer.add("dollarPanel",dollarPanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void historyjButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyjButton1ActionPerformed
        // TODO add your handling code here:
        //        ViewRequestJPanel vrjp = new ViewRequestJPanel(userProcessContainer, account);
        FeedbackMessageJPanel feedbackjp = new FeedbackMessageJPanel(userProcessContainer, null, null, this.account);
        //        userProcessContainer.add("ViewRequestJPanel", vrjp);
        userProcessContainer.add("FeedbackMessageJPanel", feedbackjp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_historyjButton1ActionPerformed

    private void balanceBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceBtn1ActionPerformed
        // TODO add your handling code here:
        PriceChartJPanel pricePanel = new PriceChartJPanel(userProcessContainer, account);
        userProcessContainer.add("PriceChartJPanel",pricePanel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_balanceBtn1ActionPerformed

    public void enterMiningPanel(String coinType){
        MiningPanel miningPanel = new MiningPanel(userProcessContainer, coinType,miner);
        userProcessContainer.add("miningpanel",miningPanel);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton balanceBtn;
    private javax.swing.JButton balanceBtn1;
    private javax.swing.JButton btnAddWallet;
    private javax.swing.JComboBox<String> coinBox;
    private javax.swing.JButton historyjButton1;
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton mineBtn;
    private javax.swing.JButton transjButton;
    // End of variables declaration//GEN-END:variables
}
