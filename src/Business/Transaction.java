/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Wallet.Wallet;
import java.util.ArrayList;

/**
 *
 * @author lm
 */
public class Transaction {

    private final String receiptNumber;
    private int price;

    public int getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReceipt() {
        return receiptNumber;
    }
    
    
    /*  in              out   
        zhangsan  $$ |  lisi $$ 
    */
    public static class UTXO{
        private Wallet w;
        private double amount;
        private boolean ispaid = false;
        private final int utxoNumber;
        
        public UTXO(Wallet w, double amount){
            this.w = w;
            this.amount=amount;
            utxoNumber = (int)(Math.random()*1000);
        }

        public void setIsPaid(boolean ispaid) {
            this.ispaid = ispaid;
        }

        public int getUtxoNumber() {
            return utxoNumber;
        }

        public Wallet getWallet() {
            return w;
        }

        public double getAmount() {
            return amount;
        }

        public boolean isPaid() {
            return ispaid;
        }
        
        
        
    }
    private ArrayList<UTXO> input;
    private ArrayList<UTXO> output;
    public ArrayList<UTXO> getInput() {
        return input;
    }

    public void setInput(ArrayList<UTXO> input) {
        this.input = input;
    }

    public ArrayList<UTXO> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<UTXO> output) {
        this.output = output;
    }

    public Wallet getwIn() {
        return wIn;
    }

    public Wallet getwOut() {
        return wOut;
    }
    
    private Wallet wIn;
    private Wallet wOut;
    private double amount;
    
    public Transaction(Wallet wIn, Wallet wOut, double amount,int price){
        receiptNumber = String.valueOf((int)(Math.random()*10000));
        this.wIn = wIn;
        this.wOut = wOut;
        this.amount = amount;
        input = new ArrayList<>();
        output = new ArrayList<>();
        this.price = price;
    }
}
