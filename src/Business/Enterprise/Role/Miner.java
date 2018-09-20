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
import NewUI.DisplayPanel.MinerWorkAreaJPanel;
import NewUI.LayoutMnger;
import javax.swing.JPanel;

/**
 *
 * @author lm
 */
public class Miner extends AbstractRole{

    @Override
    public JPanel createWorkArea(LayoutMnger bot, Account account, Organization organization, AbstractEnterprise enterprise, EcoSystem system) {
        return new MinerWorkAreaJPanel(bot, account);
    }
    
    @Override
    public String toString() {
        return "Miner";
}
    
}
