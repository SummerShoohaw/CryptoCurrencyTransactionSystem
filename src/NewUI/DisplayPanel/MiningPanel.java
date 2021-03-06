/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewUI.DisplayPanel;

import Business.EcoSystem;
import Business.Investor.Miner;
import Business.MineButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author summershoohaw
 */
public class MiningPanel extends javax.swing.JPanel {

    /**
     * Creates new form MiningPanel
     */
    private MineButton[][] btnList;
    private JPanel userProcessContainer;
    private Miner miner;
    private String coinType;
    
    public MiningPanel(JPanel userProcessContainer,String coinType,Miner miner) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.miner = miner;
        btnList = new MineButton[10][10];
        this.coinType = coinType;
        this.setLayout(new BorderLayout());
        initButton();
        setKey(coinType);
        
    }

    public void initButton(){
        this.setLayout(new GridLayout(0,10));
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10;j++){
                MineButton btn = new MineButton(i,j);
                btn.value = "sorry";
                btnList[i][j] = btn;
                btn.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MineButton btn = (MineButton)e.getSource();
                        btn.setText(btn.value);
                        btn.setEnabled(false);
                        btn.setIsSelected(true);
                        if(btn.value.equals("Congrats")){
                            miner.mineSuccess(coinType);
                            System.out.println("successful miner is:"+Miner.getSuccessMiner().getName());
                            JOptionPane.showMessageDialog(null,"Congrats, you successfully solve the puzzle!");
                            back();
                        }
                    }
                });
                this.add(btn,BorderLayout.CENTER);
            }
        }
        this.add(backBtn,BorderLayout.WEST);
    }
    
    public void setKey(String coinType){
        int[] key = EcoSystem.getEcoSystem().getRepository(coinType).getKey();
        int x = key[0];
        int y = key[1];
        btnList[x][y].value = "Congrats";
    }
    
    public void back(){
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backBtn = new javax.swing.JButton();

        backBtn.setText("<<Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(363, 363, 363)
                .addComponent(backBtn)
                .addContainerGap(277, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(420, Short.MAX_VALUE)
                .addComponent(backBtn)
                .addGap(144, 144, 144))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    // End of variables declaration//GEN-END:variables
}
