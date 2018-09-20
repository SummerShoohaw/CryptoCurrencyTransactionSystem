package Business;

import Business.Enterprise.Role.SystemAdminRole;
import Business.Account.Account;
import Business.Enterprise.Role.AbstractRole;

/**
 *
 * 4.21 Ecosystem 16.48
 */
public class ConfigureASystem {
    
    public static EcoSystem configure(){
        
        EcoSystem system = EcoSystem.getEcoSystem();
        
        //Create a network
        //create an enterprise
        //initialize some organizations
        //have some employees 
        //create user account
        
        
        AbstractRole r = new SystemAdminRole();
        system.getAccountDir().createAccount("sysadmin", "sysadmin", r);
        return system;
    }
    
}
