/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Currency;

import Business.Account.Account;
import Business.EcoSystem;
import Business.Enterprise.Role.AbstractRole;
import Business.Investor.AdminMiner;
import Business.Investor.Miner;
import Business.Organization.Currency.Repository.Block;
import Business.Organization.Organization;
import Business.Transaction;
import Business.Transaction.UTXO;
import Business.Wallet.Wallet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author lm
 */
public class Repository extends Organization implements Iterable<Block>{
    
    private Block lastBlock;
    private String coinType;
    private int[] mathPuzzle;
    private int[] key;
    private AdminMiner adminMiner;

    public String getCoinType() {
        return coinType;
    }

    public int[] getMathPuzzle() {
        return mathPuzzle;
    }

    public int[] getKey() {
        return key;
    }

    public AdminMiner getAdminMiner() {
        return adminMiner;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }
    
    public Repository(String orgName){
        super(orgName);
//        adminMiner = new AdminMiner("adminMiner",coinType);
//        Account newAccount = new Account();
//        newAccount.setCus(adminMiner);
//        newAccount.setUsername(coinType+"Admin");
//        newAccount.setPassword("password");
//        //createTheFirstBlock();
//        EcoSystem.getEcoSystem().getAccountDir().getUserAccountList().add(newAccount);
//        System.out.println(newAccount.getUsername()+" "+newAccount.getPassword());
        mathPuzzle = new int[100];
        for(int i = 0;i<mathPuzzle.length;i++){
            mathPuzzle[i] = i;
        }
        setNewKey();
        //adminMiner = new AdminMiner("adminMiner",coinType);
        System.out.println("BlockChain");
    }
    
    public void setAdminMiner(){
        adminMiner = new AdminMiner("adminMiner",coinType);
        String userName = coinType+"Admin";
        String password = "password";
        //createTheFirstBlock();
        Account minerAccount = EcoSystem.getEcoSystem().getAccountDir().createAccount(userName, password, new Business.Enterprise.Role.Miner());
        minerAccount.setCus(adminMiner);
        System.out.println("role should be miner, and it is: "+minerAccount.getRole().toString()+"hashcode for this:"+minerAccount.hashCode());
        System.out.println(minerAccount.getUsername()+" "+minerAccount.getPassword());
        EcoSystem.getEcoSystem().getWalletDirectory(coinType).addWallet(adminMiner);
    }
    
    private void setNewKey(){
        key = new int[2];
        key[0] = (int)(Math.random()*10);
        key[1] = (int)(Math.random()*10);
    }
    
    //Method to create the First Block
    public void createTheFirstBlock(){
        
        lastBlock = new Block();
        lastBlock.setUnwritable();
        System.out.println("first block created!,last block cannot be null");
    }
    
    public void createNewBlock(Miner m){
        lastBlock = new Block(m);
    }
    
    public void writeTransaction(Transaction t,Miner m){
        if(checkMinerValid(m)){
            lastBlock.writeTransaction(t);
        }
        else
            //show error message
            ;
    }
    
    public boolean checkMinerValid(Miner m){
        return m == lastBlock.getOwner();
    }
    
    public Block getLastBlock(){
        return lastBlock;
    }

    @Override
    public Iterator iterator(){
        return new IteratorCreater();
    }

    @Override
    public ArrayList<AbstractRole> getSupportedRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public class IteratorCreater implements Iterator{

            Block b = lastBlock;
            @Override
            public boolean hasNext() {
                return !b.isFirst;
            }

            @Override
            public Object next() {
                b = b.previousBlock;
                return b;
            }
    }
    
//inner class Block 
    public class Block {
    
        private Block previousBlock;
        private Block nextBlock;
        private Miner blockOwner;
        private final int capacity = 11;
        private int usedCapacity = 0;
        private ArrayList<Transaction> transactionHistory;
        private boolean isFirst = false;

        public ArrayList<Transaction> getTransactionHistory() {
            return transactionHistory;
        }

        public Block getPreviousBlock() {
            return previousBlock;
        }

        public Block getNextBlock() {
            return nextBlock;
        }
    
    
        public Block(Miner m){
            previousBlock = lastBlock;
            previousBlock.nextBlock = this;
            lastBlock = this;
            transactionHistory = new ArrayList<>();
            blockOwner = m;
            setNewKey();
            createCoinBaseTransaction(blockOwner);
        }
        
        //FirstBlock create
        public Block(){
            previousBlock = null;
            lastBlock = this;
            transactionHistory = new ArrayList<>();
            isFirst = true;
//            System.out.println("coin type is: "+coinType);
//            System.out.println("check null0"+EcoSystem.getEcoSystem().getNetwork(coinType));
//            System.out.println("check null1"+EcoSystem.getEcoSystem().getNetwork(coinType).getName());
//            System.out.println("check null1"+EcoSystem.getEcoSystem().getNetwork(coinType).getCoinEnter());
//            System.out.println("check null2"+EcoSystem.getEcoSystem().getNetwork(coinType).getCoinEnter().getName());
//            System.out.println("check null3"+EcoSystem.getEcoSystem().getRepository(coinType).getAdminMiner());
            blockOwner = adminMiner;
            setNewKey();
            createCoinBaseTransaction(blockOwner);
            //not finished
        }
        
        //this method for test only
        public void setUnwritable(){
            usedCapacity = capacity;
        }
        
        public boolean capacityRemain(){
            return capacity == usedCapacity;
        }
    
    
        //method to be called in constructor, whenever a new block is created,
        //the first transaction must be a coin base transaction, the transaction
        //will be created according to several args which will be 
        //defined in later programming
        private void createCoinBaseTransaction(Miner m){
            Wallet w = EcoSystem.getEcoSystem().getWalletDirectory(coinType).searchWalletViaCustomer(m);
            Transaction t = new Transaction(null,w,10,0);
            UTXO coinbase = new UTXO(w,10);
            t.getOutput().add(coinbase);
            this.transactionHistory.add(t);
            System.out.println("coinbase transaction created! the coin owner is"+w.getCustomer());  
        }
    
        //write a transaction into the block
        public void writeTransaction(Transaction t){
            //not finished!!!!!
            //should check if transaction history if full
            this.transactionHistory.add(t);
            System.out.println("    transaction writing successfully，transaction history list: "+transactionHistory.size());
            usedCapacity ++;
        } 
        
        public Miner getOwner(){
            return blockOwner;
        }
    }
}
