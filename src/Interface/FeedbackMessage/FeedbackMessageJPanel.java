/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.FeedbackMessage;

import Business.Account.Account;
import Business.Enterprise.Request.BuyingRequest;
import Business.Enterprise.Request.Request;
import Business.Enterprise.Request.SellingRequest;
import Business.Organization.Broker.MngRequest;
import Business.Organization.Exchange.MatcherOrganization;
import Business.Organization.Organization;
import java.awt.CardLayout;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lm
 */
public class FeedbackMessageJPanel extends javax.swing.JPanel {

    /**
     * Creates new form FeedbackMessageJPanel
     */
    
    private JPanel userProcessContainer;
    private Organization org;
    private Account account;
    public FeedbackMessageJPanel(JPanel userProcessContainer, Organization org, String type, Account account) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        if(org!= null && org.getName().toLowerCase().contains("match")){
            this.org = (MatcherOrganization)org;
        }else if(org != null && org.getName().toLowerCase().contains("request")){
            this.org = (MngRequest)org;
        }else{
            this.account = account;        }
        if(type == "buy"){
            populateBuyTable();
        }else if(type == "sell"){
            populateSellTable();
        }else{
            populateHistoryTable();
        }
        
    }
    public void populateHistoryTable(){
        DefaultTableModel dtm = (DefaultTableModel) messageTable1.getModel();
        dtm.setRowCount(0);
        HashMap<Integer, Request> feedInfo = new HashMap<Integer, Request>();
       
        Object[] rList = this.account.getCus().getHisRequest().toArray();
        while(!this.account.getCus().getMessageQueue().isEmpty()){
             Request backRe = (Request)this.account.getCus().getMessageQueue().poll();
             feedInfo.put(backRe.getNumber(), backRe);
        }
        for(Object r: rList){
            Request req =  (Request)r;
            Object[] his = new Object[6];
            if(feedInfo.containsKey(req.getNumber())){
                req = feedInfo.get(req.getNumber());
            }
            his[0] = req.getCus().getName();
            his[1] = req.getPrice();
            his[2] = req.getAmount();
            his[3] = req.getCoinType();
            his[4] = req.getrType();
            his[5] = req.getStatus();
            dtm.addRow(his);
        }
    }
    public void populateBuyTable(){
        DefaultTableModel dtm = (DefaultTableModel) messageTable1.getModel();
        dtm.setRowCount(0);
        for(Object brd: this.org.hisBuyRequest){
             BuyingRequest br = (BuyingRequest) brd;
             Object[] obj = new Object[5];
             obj[0] = br.getCus().getName();
             obj[1] = br.getPrice();
             obj[2] = br.getAmount();
             obj[3] = br.getCoinType();
             obj[4] = br.getrType();
             dtm.addRow(obj);
        }
    }
    
    public void populateSellTable(){
        DefaultTableModel dtm = (DefaultTableModel) messageTable1.getModel();
        dtm.setRowCount(0);
        for(Object srd: this.org.hisSellRequest){
             SellingRequest sr = (SellingRequest) srd;
             Object[] obj = new Object[5];
             obj[0] = sr.getCus().getName();
             obj[1] = sr.getPrice();
             obj[2] = sr.getAmount();
             obj[3] = sr.getCoinType();
             obj[4] = sr.getrType();
             dtm.addRow(obj);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        messageTable1 = new javax.swing.JTable();
        backjButton1 = new javax.swing.JButton();

        messageTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Customer", "Price", "Amount", "CoinType", "Type", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(messageTable1);
        if (messageTable1.getColumnModel().getColumnCount() > 0) {
            messageTable1.getColumnModel().getColumn(0).setResizable(false);
            messageTable1.getColumnModel().getColumn(1).setResizable(false);
            messageTable1.getColumnModel().getColumn(2).setResizable(false);
            messageTable1.getColumnModel().getColumn(3).setResizable(false);
            messageTable1.getColumnModel().getColumn(4).setResizable(false);
            messageTable1.getColumnModel().getColumn(5).setResizable(false);
            messageTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        backjButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backjButton1.setText("<< Back");
        backjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backjButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(backjButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(backjButton1)
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backjButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backjButton1ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backjButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backjButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable messageTable1;
    // End of variables declaration//GEN-END:variables
}