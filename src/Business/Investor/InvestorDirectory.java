/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Investor;

import Business.EcoSystem;
import Business.Wallet.Wallet;
import java.util.ArrayList;

/**
 *
 * @author lm
 */
public class InvestorDirectory {
    
    private ArrayList<Customer> investorList;
    
    public InvestorDirectory(){
        investorList = new ArrayList<>();
    }

    public ArrayList<Customer> getInvestorList() {
        return investorList;
    }
    
    public Wallet searchWallet(Customer c,String coinType){
        return EcoSystem.getEcoSystem().getWalletDirectory(coinType).searchWalletViaCustomer(c);
    }
    
}
