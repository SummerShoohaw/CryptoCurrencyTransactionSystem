/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Account;

import Business.EcoSystem;
import Business.Enterprise.Role.AbstractRole;
import java.util.ArrayList;
import javax.management.relation.Role;
import javax.swing.JOptionPane;

/**
 *
 * @author lm
 */
public class AccountDirectory {
    private ArrayList<Account> userAccountList;
    
    public AccountDirectory() {
        userAccountList = new ArrayList();
    }

    public ArrayList<Account> getUserAccountList() {
        
        return userAccountList;
        
    }
    
    public Account authenticateUser(String username, String password){
        for(Account u: userAccountList)
            //System.out.println(u.getUsername() + ": "+ u.getUsername());
        for (Account ua : userAccountList)
            if (ua.getUsername().equals(username) && ua.getPassword().equals(password)){
                return ua;
            }
        return null;
    }
    
    
    public int checkDuplicate(String username, String password){
        int count = 0;
        for (Account ua : userAccountList)
            if (ua.getUsername().equals(username) && ua.getPassword().equals(password)){
                count++;
            }
        return count;
    }
    
    public Account createAccount(String username, String password, AbstractRole role){
        Account userAccount = new Account();
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setRole(role);
        userAccount.setCus();
        if(EcoSystem.getEcoSystem().getAllUser().contains(username)){
            JOptionPane.showMessageDialog(null,"sorry, this username has already been registered.");
            System.out.println("duplicate username:"+username);
            return null;
        }
        userAccountList.add(userAccount);
        EcoSystem.getEcoSystem().addUser(username);
        return userAccount;
    }
    
    public boolean checkIfUsernameIsUnique(String username){
        for (Account ua : userAccountList){
            if (ua.getUsername().equals(username))
                return false;
        }
        return true;
    }
}
