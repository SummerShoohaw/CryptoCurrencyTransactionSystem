/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author lm
 */
public class ExchangeEnterprise extends AbstractEnterprise{
    private static ExchangeEnterprise e;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    ExchangeEnterprise(String name){
        super(AbstractEnterprise.Type.Exchange);
        this.name = name;
        //can be modified
//        this.buyList = new LinkedList();
//        this.sellList = new LinkedList();
    }
    public static ExchangeEnterprise getExchangeInstance(String name){
        if(e == null){
            e = new ExchangeEnterprise(name);
        }
        return e;
        
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
    
    @Override
    public ArrayList<String> getSupportedOrganizationList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
