/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise.Role;

import Business.Account.Account;
import Business.EcoSystem;
import Business.Enterprise.AbstractEnterprise;
import Business.Organization.Organization;
import NewUI.LayoutMnger;
import javax.swing.JPanel;

/**
 *
 * @author lm
 */
public abstract class AbstractRole {
    public enum RoleType{
        Admin("Admin"),
        Customer("Customer"),
        Miner("Miner");
        
        
        private String value;
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public abstract JPanel createWorkArea(LayoutMnger bot, 
            Account account, 
            Organization organization, 
            AbstractEnterprise enterprise, 
            EcoSystem business);

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
