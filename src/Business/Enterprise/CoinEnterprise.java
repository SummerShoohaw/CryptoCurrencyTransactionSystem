/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Investor.AdminMiner;
import Business.Organization.Currency.Repository;
import Business.Wallet.WalletDirectory;
import java.util.ArrayList;

/**
 *
 * @author lm
 */
public class CoinEnterprise extends AbstractEnterprise{
    
    private String coinType;
    private WalletDirectory walletDirectory;
    private String name;
    //private AdminMiner adminMiner;
    
    
    CoinEnterprise(String name){
        super(AbstractEnterprise.Type.CoinEnterprise);
        this.name = name;
        setName(name);
        coinType = name;
        walletDirectory = new WalletDirectory(coinType);
        //adminMiner = new AdminMiner(coinType+"_adminMiner",coinType);
    }

//    public AdminMiner getAdminMiner() {
//        return adminMiner;
//    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public WalletDirectory getWalletDirectory() {
        return walletDirectory;
    }
    
    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public ArrayList<String> getSupportedOrganizationList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
