/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Investor.Customer;
import Business.Organization.OrganizationDirectory;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author lm
 */
public class BrokerEnterprise extends AbstractEnterprise{
    
    
    public BrokerEnterprise(String name) {
        super(AbstractEnterprise.Type.Broker);
        setName(name);
       
    }
    
    //move to organization level
//    public void addBuyRequest(Customer cus, int price){
//        super.addBuyRequest(new Node(cus, price), price);
//        
//    }
//    
//    public void addSellRequest(Customer cus, int price){
//        super.addSellRequest(new Node(cus, price),  price);
//    }
    
    /*
       send feedback to customer(info come from exchange)
    */
    public void sendCustomerInfo(String info){
        
    }
    @Override
    public String toString(){
        return this.getName();
    }
    
    @Override
    public ArrayList<String> getSupportedOrganizationList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
