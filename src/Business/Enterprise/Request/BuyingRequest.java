/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise.Request;

import Business.Enterprise.AbstractEnterprise;
import Business.Investor.Customer;
import Business.Organization.Organization;

/**
 *
 * @author lm
 */
public class BuyingRequest extends Request{
    
    public BuyingRequest(Customer cus, int price, Organization org, String coinType, double amount, int num){
        super(cus, price, org, coinType, amount, num, "buy");
    }
    
    public static int sortRequest(Object o1, Object o2) {
        Request r1 = (Request)o1;
        Request r2 = (Request)o2;
        if(r1.getPrice()<r2.getPrice()){ return 1;}
        if(r1.getPrice()>r2.getPrice()){ return -1;}
        else{ return 0;}
    }

   
    
}
