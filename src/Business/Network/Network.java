/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Network;

import Business.Account.AccountDirectory;
import Business.EcoSystem;
import Business.Enterprise.AbstractEnterprise;
import Business.Enterprise.BrokerEnterprise;
import Business.Enterprise.CoinEnterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Enterprise.ExchangeEnterprise;
import Business.Investor.Miner;

/**
 *
 * @author lm
 */
public class Network {
    private EnterpriseDirectory enterpriseDir;
    private String name;
    private TransactionQueue tempTransactionQueue;
    private AccountDirectory accountDir;
    private String coinType;

    public TransactionQueue getTempTransactionQueue() {
        return tempTransactionQueue;
    }
    
    public EnterpriseDirectory getEnterpriseDir() {
        return enterpriseDir;
    }

    public void setEnterpriseDir(EnterpriseDirectory enterpriseDir) {
        this.enterpriseDir = enterpriseDir;
    }

    public String getName() {
        return name;
    }

    public AccountDirectory getAccountDir() {
        return accountDir;
    }

    public void setAccountDir(AccountDirectory accountDir) {
        this.accountDir = accountDir;
    }


    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Network(String areaName){
       this.enterpriseDir = new EnterpriseDirectory();
       this.name = areaName;
       this.coinType = areaName;
       tempTransactionQueue = new TransactionQueue(coinType);
    }

    public String getCoinType() {
        return coinType;
    }
    
    public void StartWriteTrans() throws Exception{
        System.out.println("Start write transactions");
        Miner m = Miner.getSuccessMiner();
        if(m != null)
            while(!tempTransactionQueue.isEmpty()){
                if(EcoSystem.getEcoSystem().getRepository(coinType).getLastBlock().capacityRemain()){
                    System.out.println("block is full, break");
                    return;
                }
                m.checkValidAndWrite(coinType, tempTransactionQueue.dequeue());
            }
        else
            System.out.println("we dont have a successful miner yet!");
    }
    
    public CoinEnterprise getCoinEnter(){
        for(AbstractEnterprise enter:enterpriseDir.getEnterpriseDir()){
            //System.out.println("enter null?: "+enter.getName());
            if(enter.getType().getValue().equals("CoinEnterprise")){
                //System.out.println("coinType: "+coinType+", found: "+enter.getName());
                return (CoinEnterprise)enter;
            }
        }
        return null;
    }
    
    public BrokerEnterprise getBrokerEnter(){
        for(AbstractEnterprise enter:enterpriseDir.getEnterpriseDir()){
            if(enter.getType().getValue().equals("Broker"))
                return (BrokerEnterprise)enter;
        }
        return null;
    }
    
     public ExchangeEnterprise getExchangeEnter(){
        for(AbstractEnterprise enter:enterpriseDir.getEnterpriseDir()){
            if(enter.getType().getValue().equals("Exchange"))
                return (ExchangeEnterprise)enter;
        }
        return null;
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
    
}
