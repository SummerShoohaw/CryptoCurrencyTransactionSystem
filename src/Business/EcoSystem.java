/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Account.Account;
import Business.Account.AccountDirectory;
import Business.Enterprise.AbstractEnterprise;
import Business.Enterprise.CoinEnterprise;
import Business.Enterprise.Role.AbstractRole;
import Business.Enterprise.Role.SystemAdminRole;
import Business.Investor.InvestorDirectory;
import Business.Network.Network;
import Business.Network.NetworkDirectory;
import Business.Organization.Currency.Repository;
import Business.Organization.Organization;
import Business.Wallet.WalletDirectory;
import java.util.ArrayList;

/**
 *
 * @author summershoohaw
 */
public class EcoSystem extends Organization{
    
    private NetworkDirectory networkDirectory;
    public static EcoSystem ecoSystem;
    private InvestorDirectory investorDirectory;
    private ArrayList<String> allUser;
    public InvestorDirectory getInvestorDirectory() {
        return investorDirectory;
    }
    
    private EcoSystem(){
        networkDirectory = new NetworkDirectory();
        investorDirectory = new InvestorDirectory();
        allUser = new ArrayList<String>();
    }
    public ArrayList<String> getAllUser(){
        return allUser;
    }
    
    public void addUser(String username){
        this.allUser.add(username);
    }
    
    public static EcoSystem getEcoSystem(){
        if(ecoSystem == null){
            ecoSystem = new EcoSystem();
            AbstractRole r = new SystemAdminRole();
            ecoSystem.getAccountDir().createAccount("sysadmin", "sysadmin", r);
        }
        return ecoSystem;
    }
    
    public Network createAndAddNetwork(String name){
        boolean find = false;
        for(Network n: this.networkDirectory.getNetworkDirectory()){
            if(n.getName().equals(name)){
                find = true;
                break;
            }
        }
        if(!find){
            Network network=new Network(name);
            this.networkDirectory.getNetworkDirectory().add(network);
            return network;
        }else{
            return null;
        }
        
        
    }

    public NetworkDirectory getNetworkDirectory() {
        return networkDirectory;
    }

    public void setNetworkDirectory(NetworkDirectory networkDirectory) {
        this.networkDirectory = networkDirectory;
    }
    
    public Repository getRepository(String coinType){
        for(Network n:networkDirectory.getNetworkDirectory()){
            if(n.getCoinType().equals(coinType)){
                for(AbstractEnterprise enter:n.getEnterpriseDir().getEnterpriseDir()){
                    if(enter.getName().equals(coinType)){
                        //System.out.println("haha,found coinenterprise");
                        CoinEnterprise cEnter = (CoinEnterprise)enter;
                        Repository r = (Repository)cEnter.getOrganization("Repository");
//                        if(r!=null)
                            return r;
//                        else
//                            System.out.println("cant find repostory in this coinenterprise");
                    }
                }
            }
        }
        //System.out.println("ooops,coin enterprise not found");
        return null;
    }
   
    public Network getNetwork(String coinType){
        for(Network n:networkDirectory.getNetworkDirectory()){
            if(n.getCoinType().equals(coinType)){
                //System.out.println("network coinType: "+n.getCoinType());
                return n;
            }
        }
        return null;
    }
    
    public WalletDirectory getWalletDirectory(String coinType){
        for(Network n:networkDirectory.getNetworkDirectory()){
            if(n.getCoinType().equals(coinType)){
                for(AbstractEnterprise enter:n.getEnterpriseDir().getEnterpriseDir()){
                    if(enter.getName().equals(coinType)){
                        CoinEnterprise cEnter = (CoinEnterprise)enter;
                        return cEnter.getWalletDirectory();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<AbstractRole> getSupportedRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
}
