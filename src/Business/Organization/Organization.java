/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Account.AccountDirectory;
import Business.Enterprise.Request.BuyingRequest;
import Business.Enterprise.Request.Request;
import Business.Enterprise.Request.RequestDirectory;
import Business.Enterprise.Request.SellingRequest;
import Business.Enterprise.Role.AbstractRole;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author lm
 */
public abstract class Organization{
    
    //should be replaced by requestdirectory
    private RequestDirectory sellRD;
    private RequestDirectory buyRD;
    private AccountDirectory accountDir;
    private Queue messageQueue;
    public ArrayList<Request> hisBuyRequest;
    public ArrayList<Request> hisSellRequest;

    public Queue getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(Queue messageQueue) {
        this.messageQueue = messageQueue;
    }
    
    public AccountDirectory getAccountDir() {
        return accountDir;
    }
    private String name="";
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public RequestDirectory getSellRD() {
        return sellRD;
    }

    public void setSellRD(RequestDirectory sellRD) {
        this.sellRD = sellRD;
    }

    public RequestDirectory getBuyRD() {
        return buyRD;
    }

    public void setBuyRD(RequestDirectory buyRD) {
        this.buyRD = buyRD;
    }
    public Organization(String type){
        this.name = type;
        this.accountDir = new AccountDirectory();
        this.buyRD = new RequestDirectory("buy", this);
        this.sellRD = new RequestDirectory("sell", this);
        this.messageQueue = new LinkedList();
        this.hisBuyRequest = new ArrayList<Request>();
        this.hisSellRequest = new ArrayList<Request>();
    }
    
//    public Organization(String type){
//        this.accountDir = new AccountDirectory();
//    }
    
    public Organization(){
        this.accountDir = new AccountDirectory();
    }
    
//    public Organization(String name){
////        this.name = name;
//        this.accountDir = new AccountDirectory();
//        this.buyRD = new RequestDirectory("buy", this);
//        this.sellRD = new RequestDirectory("sell", this);
//        this.messageQueue = new LinkedList();
//    }
//    
//    public Organization(){
//        this.accountDir = new AccountDirectory();
//    }
    
    
    //all getters and setters should be renamed
   
    
    //removed customer and miner role(they are in higher level)
    //added admin role, every enterprise will have an admin organization
    //to modifiy its attributes(e.g. userAccountDirectory).
    public enum Type{
        MngRequest("MngRequest"),Block("Block"),Match("Match"),Admin("Admin");
        private String value;
        
        public String getValue() {
            return value;
        }
        
        private Type(String value){
            this.value = value;
           
        }
    }
    @Override
    public String toString (){
        return this.name;
    }
    //every subclass should implement below method to specify 
    //which role is supported in this specific organization
    //e.g. MatcherRole is supported in MatcherOrganization
    public abstract ArrayList<AbstractRole> getSupportedRole();
    
    
    
}
