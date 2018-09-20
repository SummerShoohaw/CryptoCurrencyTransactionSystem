/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Investor;

import Business.EcoSystem;
import Business.Enterprise.BrokerEnterprise;
import Business.Enterprise.Request.BuyingRequest;
import Business.Enterprise.Request.Request;
import Business.Enterprise.Request.RequestDirectory;
import Business.Enterprise.Request.SellingRequest;
import Business.Enterprise.Role.AbstractRole;
import Business.Organization.Broker.MngRequest;
import Business.Organization.Currency.Repository;
import Business.Organization.Currency.Repository.Block;
import Business.Organization.Organization;
import Business.Transaction;
import Business.Transaction.UTXO;
import Business.Wallet.Wallet;
import java.util.ArrayList;

/**
 *
 * @author lm
 */
public class Customer extends Organization{
    private String name;
    private ArrayList<Request> hisRequest;
    private double dollarBalance;

    public double getDollarBalance() {
        return dollarBalance;
    }

    public void addDollarBalance(double dollarBalance) {
        System.out.println("~~~~~~");
        System.out.println("customer is:"+name+",currenct dollar balance is:"+this.dollarBalance+",he is paying/getting:"+dollarBalance);
        System.out.println("~~~~~~");
        this.dollarBalance += dollarBalance;
    }
    

    public ArrayList<Request> getHisRequest() {
        return hisRequest;
    }
    public String getName() {
        return name;
    }
    public Customer(String name){
        super(name);
        this.name = name;
        dollarBalance = 0;
        hisRequest = new ArrayList<Request>();
    }
    
    public void sendReq(String type, int price, MngRequest mngR, String coinType, double amount){
        Request r;
        if(type.toLowerCase().equals("buy")){
//            r = this.getBuyRD().addRequest(type, this, price, coinType, amount, null);
            RequestDirectory rd = mngR.getBuyRD();
            r = rd.addRequest(type, this, price, coinType, amount, null);
            mngR.hisBuyRequest.add(r);
            
            this.getBuyRD().addRequest(type, this, price, coinType, amount,r);
            mngR.autoSendBuyRequest(coinType);
        }else{
//            r = this.getSellRD().addRequest(type, this, price, coinType, amount, null);
            RequestDirectory rd = mngR.getSellRD();
            r = rd.addRequest(type, this, price, coinType, amount, null);
            mngR.hisSellRequest.add(r);
            this.getSellRD().addRequest(type, this, price, coinType, amount,r);
            mngR.autoSendSellRequest(coinType);
//            System.out.println(mngR.getSellRD().getRequestQueue().size());
        }
        System.out.println("number:" + r.getNumber());
        this.getHisRequest().add(r);
        
    }
    @Override
    public ArrayList<AbstractRole> getSupportedRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public double checkMyBalance(String coinType){
        Wallet w = EcoSystem.getEcoSystem().getInvestorDirectory().searchWallet(this, coinType);
        Repository r = EcoSystem.getEcoSystem().getRepository(coinType);
        double balance = 0;
        for(Block b = r.getLastBlock();;b = b.getPreviousBlock()){
            //System.out.println(b.getOwner().getName());
            for(Transaction t:b.getTransactionHistory()){
                System.out.println("transaction output arraylist size is: "+t.getOutput().size());
                for(UTXO u:t.getOutput()){
                    if(!u.isPaid() && u.getWallet().equals(w)){
                        balance += u.getAmount();
                    }
                }
            }
            if(b.getPreviousBlock()==null)
                break;
        }
        return balance;
    }
   
    
}
