/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise.Request;

import Business.Investor.Customer;
import Business.Organization.Organization;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author lm
 */
public class RequestDirectory{
    private PriorityQueue requestQueue;
    public void setRequestQueue(PriorityQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

   
    public PriorityQueue getRequestQueue() {
        return requestQueue;
    }
     public Organization getOrg() {
        return org;
    }
    private Organization org;

    public void setOrg(Organization org) {
        this.org = org;
    }
    public RequestDirectory(String name){
        requestQueue = new PriorityQueue<Request>(10, new Comparator(){
            @Override
            public int compare(Object r1, Object r2) {
                int i = 1000;
                if(Request.Type.Buy.getValue() == name){
                    i = BuyingRequest.sortRequest(r1, r2);
                    
                }else{
                    i= SellingRequest.sortRequest(r1, r2);
                    
                }
                
                return i;
              
            }
            
        });
    }
    public RequestDirectory(String name, Organization o){
        this(name);
        this.org = o;
        
    }
    
    public Request addRequest(String name, Customer cus, int price, String coinType, double amount, Request r){
        
        Organization org = this.getOrg();
        
        int num = (int)(Math.random()*100);
        if(Request.Type.Buy.getValue().equals(name)){
            if(r ==null){
            r = new BuyingRequest(cus, price, org, coinType, amount, num);}
            requestQueue.add((BuyingRequest)r);
        }else{
            if(r == null){
            r = new SellingRequest(cus, price, org, coinType, amount, num);}
            requestQueue.add((SellingRequest)r);
        }
        return r;
    }
    
}
