/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Account.AccountDirectory;
import Business.Investor.Customer;
import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author lm
 */
public abstract class AbstractEnterprise  {
    //shoule be replaced to organization level, not in enterprise level
    public LinkedList buyList;
    public LinkedList sellList;
    private String name;
    private OrganizationDirectory orgDir;
    private AccountDirectory accountDir;
    private Type type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationDirectory getOrgDir() {
        return orgDir;
    }
    
    public AbstractEnterprise(Type type){
        this.orgDir = new OrganizationDirectory();
        this.accountDir = new AccountDirectory();
        this.type = type;
    }

    public String getName() {
        return name;
    }
    
    public Organization getOrganization(String name){
        for(Organization o:orgDir.getOrgList()){
            if(o.getName().contains(name)){
                return o;
            }
        }
        return null;
    }

    public AccountDirectory getAccountDir() {
        return accountDir;
    }

    
    /*
       Request structure.
    */
    //if use request class, below code can be deleted?
    public class Node{
        Customer cus;
        int price;
        Node(Customer cus, int price){
            this.cus = cus;
            this.price = price;
        }
        
        @Override
        public String toString(){
            return this.cus.getName();
        }
    }
    
    //update log(2018.4.11 20:48): delete value Invester("Investor"),reset the value into EcoSystem level. (xiashuhao)
    public enum Type{
        Broker("Broker"), Exchange("Exchange"), CoinEnterprise("CoinEnterprise");
        private String value;
        public String getValue() {
            return value;
        }
        private Type(String value){
            this.value = value;
        }
    }
    
    /*
        1.Buying request from customer to broker
        2. Buying request from broker to exchange
    */
    //can be deleted, set to abstract, define it in subclass
    public void addBuyRequest(Node node, int price){
        if(buyList.isEmpty()){
            buyList.add(node);
        }else{
            int size = buyList.size();
            for(int i=0; i<size; i++){
                Node n = (Node) buyList.get(i);
                if(n.price< price){
                    buyList.add(i, node);
                }
            }
        }
    }
    /*
        Selling request from customer to broker
        Selling request from broker to exchange
    */
    //can be deleted, set to abstract, define it in subclass
    public void addSellRequest(Node nodes, int price){
//        if(sellList.isEmpty()){
//            sellList.add(new BrokerEnterprise.Node(cus, price));
//        }else{
//            int size = sellList.size();
//            for(int i=0; i<size; i++){
//                BrokerEnterprise.Node n = (BrokerEnterprise.Node) sellList.get(i);
//                if(n.price< price){
//                    sellList.add(i, new BrokerEnterprise.Node(cus, price));
//                }
//            }
//        }
    }
    @Override
    public String toString(){
        return this.name;
    }
    
    //subclass should implement below method to specify which organization could be 
    //added to this specific enterprise.
    //(e.g. <Match><Admin> is the list for exchange enterprise)
    public abstract ArrayList<String> getSupportedOrganizationList();
}
