/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Investor;

import Business.EcoSystem;
import Business.Network.Network;
import Business.Network.TransactionQueue;
import Business.Organization.Currency.Repository;
import Business.Organization.Currency.Repository.Block;
import Business.Transaction;
import Business.Transaction.UTXO;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author lm
 */
public class Miner extends Customer{
    
    private int myGuess;
    private static boolean guessFound = false;
    private static Miner successMiner;

    public static Miner getSuccessMiner() {
        return successMiner;
    }
    
    
    public Miner(String name){
        super(name);
    }
    
    public Repository getRepository(String coinType){
        return EcoSystem.getEcoSystem().getRepository(coinType);
    }
    
    
    public boolean enableAutoMiner(String coinType){
        
        Network n = null;
        for(Network nw:EcoSystem.getEcoSystem().getNetworkDirectory().getNetworkDirectory()){
            if (nw.getCoinType().equals(coinType)){
                n = nw;
                break;
            }
        }
        System.out.println("cointype is null?:"+coinType);
        System.out.println("repository is null?:"+this.getRepository(coinType));
        System.out.println("last block is null?:"+this.getRepository(coinType).getLastBlock().getOwner());
        boolean isFull = this.getRepository(coinType).getLastBlock().capacityRemain();
        //System.out.println(isFull);
        //System.out.println(n.getTempTransactionQueue().isEmpty());
        if(isFull && !n.getTempTransactionQueue().isEmpty()){
            System.out.println("still have place to write transaction, tempTransactionQueue have transaction, auto miner will be started soon");
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null,"nothing to mine yet, plz try again later");
            return false;
        }
    }
    
    //the autoMiner stops when it succssfully resolves the math puzzle.
    //and the miner has to turn it on again by clicking the button
//    public void autoMiner(String coinType){
//        int key = this.getRepository(coinType).getKey();
//        System.out.println("Auto-miner started");
//        while(myGuess != key){
//            if(guessFound)
//                return;
//            myGuess = (int)(Math.random()*500);
//        }
//        System.out.println("Congrats, mining successfully, the miner is "+this.getName()+", new block will be created soon");
//        this.getRepository(coinType).createNewBlock(this);
//        System.out.println("new block for coin type:"+coinType+" created successfully, block owner is:"+this.getName());
//        successMiner = this;
//        guessFound = true;
//    }
    
    public void mineSuccess(String coinType){
        this.getRepository(coinType).createNewBlock(this);
        successMiner = this;
        TransactionQueue tq = EcoSystem.getEcoSystem().getNetwork(coinType).getTempTransactionQueue();
        while(!tq.isEmpty())
            try{
                if(this.getRepository(coinType).getLastBlock().capacityRemain()){
                    System.out.println("Block is full");
                    return;
                }
                else
                    this.checkValidAndWrite(coinType, tq.dequeue());
            }catch(IllegalArgumentException e){
                System.out.println("invalid transaction");
            }
    }
    
    public void checkValidAndWrite(String coinType,Transaction t) throws IllegalArgumentException{
        boolean isValid = false;
        System.out.println("start check transaction, receipt number is "+t.getReceipt());
        Repository r = this.getRepository(coinType);
        ArrayList<UTXO> utxoList = new ArrayList<>();
        System.out.println("Start finding utxo of this seller");
        for(Block b = r.getLastBlock();;b = b.getPreviousBlock()){
            for(Transaction trans:b.getTransactionHistory()){
                for(UTXO utxo:trans.getOutput()){
                    System.out.println("check if utxo is null:"+utxo.getUtxoNumber());
                    System.out.println("check if utxo wallet is null:"+utxo.getWallet());
                    System.out.println("check if transaction wallet-out(payer) is null:"+t.getwOut());
                    if(!utxo.isPaid() && utxo.getWallet().equals(t.getwOut())){
                        utxoList.add(utxo);
                    }
                }
            }
            if(b.getPreviousBlock() == null)
                break;
        }
        System.out.println("hahaha");
        int tempAmount = 0;
        if(!utxoList.isEmpty()){
            System.out.println("utxo list found, not empty, now check balance is enough or not");
            for(UTXO ut:utxoList){
                tempAmount += ut.getAmount();
                if(tempAmount >= t.getAmount()){
                    isValid = true;
                    break;
                }
            }
        }
        
        if(isValid){
            System.out.println("balance enough to pay, now start payment");
            
            utxoList.sort(new Comparator(){
                @Override
                public int compare(Object o1, Object o2) {
                    UTXO utxo1 = (UTXO)o1;
                    UTXO utxo2 = (UTXO)o2;
                    if(utxo1.getAmount() > utxo2.getAmount()) return -1;
                    else if(utxo1.getAmount() < utxo2.getAmount()) return 1;
                    else return 0;
                }
            });
            
            double transAmount = t.getAmount();
            System.out.println("Transaction amount is:"+transAmount);
            ArrayList<UTXO> inList = new ArrayList<>();
            ArrayList<UTXO> outList = new ArrayList<>();
            //Algorithms not implemented yet: find a suitable 
            //combination of utxo in this list that fix transaction's amount
            System.out.println("start creating transaction input and output");
            double i = 0;
            for(UTXO u:utxoList){
                if(i < transAmount){
                    i += u.getAmount();
                    u.setIsPaid(true);
                    inList.add(u);
                }
                if(i == transAmount){
                    outList.add(new UTXO(t.getwIn(),i));
                    break;
                }
                else if(i > transAmount){
                    UTXO out1 = new UTXO(t.getwIn(),transAmount);
                    UTXO out2 = new UTXO(t.getwOut(),i-transAmount);
                    outList.add(out1);
                    outList.add(out2);
                    break;
                }
            }
        
            System.out.println("input and output all set");
            t.setInput(inList);
            t.setOutput(outList);
            
            //should check whether this miner is valid to write.
            //not implemented yet.
            System.out.println("start writing transaction into the block, the block owner is "+r.getLastBlock().getOwner().getName());
            double dollarNeed = t.getAmount()*t.getPrice();
            t.getwIn().getCustomer().addDollarBalance(-dollarNeed);
            t.getwOut().getCustomer().addDollarBalance(dollarNeed);
            r.getLastBlock().writeTransaction(t);
        }
        else{
            throw new IllegalArgumentException("transaction not valid!!!");
        }
    }
    
    
}
