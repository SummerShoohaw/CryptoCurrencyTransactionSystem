/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Wallet;

import Business.Investor.Customer;
import java.util.ArrayList;

/**
 *
 * @author lm
 */
public class WalletDirectory {
    private ArrayList<Wallet> walletList;
    private final String coinType;

    public ArrayList<Wallet> getWalletList() {
        return walletList;
    }

    public String getCoinType() {
        return coinType;
    }
    
    public WalletDirectory(String coinType){
        this.coinType = coinType;
        walletList = new ArrayList<>();
    }
    
    public Wallet addWallet(Customer c){
        Wallet newWallet = new Wallet(c);
        walletList.add(newWallet);
        return newWallet;
    }
    
    public Wallet searchWalletViaCustomer(Customer c){
        for(Wallet w:walletList){
            if(w.getCustomer() == c)
                return w;
        }
        return null;
    }
}
