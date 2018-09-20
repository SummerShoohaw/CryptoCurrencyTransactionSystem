/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Enterprise.EnterpriseDirectory;
import Business.Enterprise.ExchangeEnterprise;
import Business.Organization.Broker.MngRequest;
import Business.Organization.Currency.Repository;
import Business.Organization.Exchange.MatcherOrganization;
import Business.Investor.Customer;
import Business.Investor.Miner;
import java.util.ArrayList;

/**
 *
 * @author lm
 */
public class OrganizationDirectory {
    private ArrayList<Organization> orgList;
    public static MatcherOrganization matOrg;
    public ArrayList<Organization> getOrgList() {
        return orgList;
    }
   
    public OrganizationDirectory(){
        orgList = new ArrayList<Organization>();
    }
    
    public Organization addOrg(String name){
        Organization org;
        if(name.equals(Organization.Type.Block.getValue())){
            org = new Repository(name);
        }else if(name.equals(Organization.Type.MngRequest.getValue())){
            org = new MngRequest(name);
        }else{
//            org = MatcherOrganization.getMatcherInstance(name);
            org = new MatcherOrganization(name);
//            matOrg = (MatcherOrganization) org;
        }
        orgList.add(org);
        return org;
    }
     public Organization getOrganization(String name){
        for(Organization o: orgList){
            if(o.getName() == name){
                return o;
            }
        }
        return null;
    }
}
