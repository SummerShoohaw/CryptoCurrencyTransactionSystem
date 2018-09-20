/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Investor;

import Business.EcoSystem;
import Business.Wallet.Wallet;

/**
 *
 * @author summershoohaw
 */
public class AdminMiner extends Miner{
    
    private String coinType;
    
    public AdminMiner(String name,String coinType){
        super(name);
        this.coinType = coinType;
    }
    
}
