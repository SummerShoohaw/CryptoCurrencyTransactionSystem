/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import java.util.ArrayList;

/**
 *
 * @author lm
 */

public class EnterpriseDirectory {
    
    private ArrayList<AbstractEnterprise> EnterpriseDir;
    public ArrayList<AbstractEnterprise> getEnterpriseDir() {
        return EnterpriseDir;
    }
    
    public EnterpriseDirectory(){
        this.EnterpriseDir = new ArrayList<AbstractEnterprise>();
    }
    
    public AbstractEnterprise addEnterprise(String name, AbstractEnterprise.Type type){
        AbstractEnterprise e;
        if(type.getValue().equals(AbstractEnterprise.Type.Broker.getValue())){
           e = new BrokerEnterprise(name);
        } else if(type.getValue().equals(AbstractEnterprise.Type.Exchange.getValue())){
   
           e = new ExchangeEnterprise(name);
        } else{
           e = new CoinEnterprise(name);
        } 
        
        // investor has been moved to EcoSystem level
        //else{
        //    e = new InvestorEnterprise();
        //}
        this.EnterpriseDir.add(e);
        return e;
    }
}
