/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Account;

import Business.EcoSystem;
import Business.Enterprise.AbstractEnterprise;
import Business.Enterprise.Request.RequestDirectory;
import Business.Enterprise.Role.AbstractRole;
import Business.Investor.Customer;
import Business.Investor.Miner;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Wallet.WalletDirectory;
import javax.management.relation.Role;

/**
 *
 * @author lm
 */
public class Account {
    private String username;
    private String password;
    private AbstractRole role;
    private RequestDirectory buyReqDir;
    private RequestDirectory sellReqDir;
    private Customer cus;
    //private double dollarBalance;

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public void setCus() {
        Customer cus = null;
        if(role.toString().equals("Customer"))
            cus= new Customer(getUsername());
        else
            cus = new Miner(getUsername());
        this.cus = cus;
    }
    
    public RequestDirectory getBuyReqDir() {
        return buyReqDir;
    }

    public RequestDirectory getSellReqDir() {
        return sellReqDir;
    }

//    public double getDollarBalance() {
//        return dollarBalance;
//    }

    public Account() {
        buyReqDir = new RequestDirectory("buy",null);
        sellReqDir = new RequestDirectory("sell",null);
        //dollarBalance = 0;
    }
    
//    public void addDollarBalance(double dollarBalance) {
//        this.dollarBalance += dollarBalance;
//    }
    public void changeMyOrgaName(String name){
    
    
        //Step1: Check in the system admin user account directory if you have the user
        Account userAccount = EcoSystem.getEcoSystem().getAccountDir().authenticateUser(username, password);
        //System.out.println("check duplicate in system admin:"+EcoSystem.getEcoSystem().getAccountDir().checkDuplicate(userName, password));


        if(userAccount==null){
            //Step 2: Go inside each network and check each enterprise
            for(Network network:EcoSystem.getEcoSystem().getNetworkDirectory().getNetworkDirectory()){
                //Step 2.a: check against each enterprise
                for(AbstractEnterprise enterprise:network.getEnterpriseDir().getEnterpriseDir()){
                    userAccount=enterprise.getAccountDir().authenticateUser(username, password);
                    //System.out.println("check duplicate in enterprise:"+enterprise.getAccountDir().checkDuplicate(userName, password));
                    if(userAccount==null){
                        //Step 3:check against each organization for each enterprise
                        for(Organization organization:enterprise.getOrgDir().getOrgList()){
                            userAccount=organization.getAccountDir().authenticateUser(username, password);
                            //System.out.println("check duplicate in organization:"+organization.getAccountDir().checkDuplicate(userName, password));
                            if(userAccount!=null){
                                organization.setName(name);
                                return;
                            }
                        }
                    }
                    else{
                        enterprise.setName(name);
                        return;
                    }
                }
            }
        }
//        if(userAccount==null){
//            JOptionPane.showMessageDialog(null, "Invalid credentials");
//            return;
//        }
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AbstractRole getRole() {
        return role;
    }

    public void setRole(AbstractRole role) {
        this.role = role;
    }

   
    
    @Override
    public String toString() {
        return username;
    }
}
