/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Wallet;

import Business.Investor.Customer;

/**
 *
 * @author lm
 */
public class Wallet {

    private Customer customer;
    private final String walletID;
    
    public  Wallet(Customer c) {
        this.customer = c;
        
        //should re-define walletID since it should be unique.
        walletID = String.valueOf(Math.random()*1000000);
    }

    public Customer getCustomer() {
        return this.customer;
    }
    
    public boolean equals(Wallet that){
        return (this.walletID.equals(that.walletID));
    }
    
}
