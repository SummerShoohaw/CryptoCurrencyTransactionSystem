/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Broker;

import Business.EcoSystem;
import static Business.EcoSystem.ecoSystem;
import Business.Enterprise.AbstractEnterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Enterprise.ExchangeEnterprise;
import Business.Enterprise.Request.Request;
import Business.Enterprise.Role.AbstractRole;
import Business.Enterprise.Role.MngRequestAdmin;
import Business.Investor.Customer;
import Business.Network.Network;
import Business.Organization.Exchange.MatcherOrganization;
import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import static Business.Organization.OrganizationDirectory.matOrg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author lm
 */
public class MngRequest extends Organization{

//    public MatcherOrganization getMatchOrg() {
//        return matchOrg;
//    }
//    private MatcherOrganization matchOrg;
    

    
    public MngRequest(String name){
        super(name);
        
//        this.matchOrg = matchOrg;
    }
    
    /*
       send feedback to customer(info come from exchange)
    */
    public void sendCustomerInfo(Request r, HashMap<Request, String> feedInfo){
        if(feedInfo.get(r).contains("-1")){
            r.setStatus("Match Failed");
            r.getCus().getMessageQueue().add(r);
//            r.getCus().getMessageQueue().add("Match Failed");
            System.out.println("Match Failed");
        }
        else{
            r.setStatus(feedInfo.get(r));
            r.getCus().getMessageQueue().add(r);
//            r.getCus().getMessageQueue().add("Transaction Success, Price is " + feedInfo.get(r));
            System.out.println(feedInfo.get(r));
        }
    }
    
    public MatcherOrganization getMatchOrg(String coinType){
        Network currentNetwork =null;
        for(Network n:ecoSystem.getNetworkDirectory().getNetworkDirectory()){
            if(n.getCoinType().equals(coinType)){
                currentNetwork = n;
                break;
            }
        }
        AbstractEnterprise exchange=null;
        for(AbstractEnterprise e:currentNetwork.getEnterpriseDir().getEnterpriseDir()){
            if(e.getType().getValue().equals("Exchange")){
                exchange = e;
                break;
            }
        }
        matOrg = (MatcherOrganization) exchange.getOrgDir().getOrgList().get(0);
//        if(OrganizationDirectory.matOrg == null){
//        ExchangeEnterprise exchange= EnterpriseDirectory.ex;
//        matOrg = (MatcherOrganization) exchange.getOrgDir().getOrgList().get(0);}
        return matOrg;
    }
    /*
        auto send request to exchange
    */
    public void checkQueue(){
        //  choice 1
        while(!this.getMessageQueue().isEmpty()){
            HashMap<Request,String> feedInfo = (HashMap<Request, String>) this.getMessageQueue().poll(); 
            for(Request r: feedInfo.keySet()){
                sendCustomerInfo(r, feedInfo);
                System.out.println(r.getCus().getName() + ":" + feedInfo.get(r));
            }
            
        }
    }
    
    public void autoSendBuyRequest(String coinType){
        //  choice 1
        checkQueue();
         //  choice 1
        MatcherOrganization m = getMatchOrg(coinType);
        PriorityQueue<Request> q = getBuyRD().getRequestQueue();
        if(m.getBuyRD().getRequestQueue().size()<10){
            Request r = q.poll();
            m.hisBuyRequest.add(r);
            m.enqueueBuyRequest(r);
            double dollarNeed = r.getPrice()*r.getAmount();
            EcoSystem.getEcoSystem().getNetwork(coinType).getExchangeEnter().addDollarBalance(dollarNeed*0.005);
            r.getCus().addDollarBalance(-dollarNeed*0.005);
        }
        
    }
    
    public void autoSendSellRequest(String coinType){
        //  choice 1
        checkQueue();
         //  choice 1
        MatcherOrganization m = getMatchOrg(coinType);
        if(m.getSellRD().getRequestQueue().size()<10){
            PriorityQueue<Request> q = getSellRD().getRequestQueue();
            Request r = q.poll();
            double dollarNeed = r.getPrice()*r.getAmount(); 
            m.hisSellRequest.add(r);
            m.enqueueSellRequest(r);
            r.getCus().addDollarBalance(-dollarNeed*0.005);
            EcoSystem.getEcoSystem().getNetwork(coinType).getExchangeEnter().addDollarBalance(dollarNeed*0.005);
        }
        
    }
    
    @Override
    public String toString(){
        return this.getName();
    }

   
   @Override
    public ArrayList<AbstractRole> getSupportedRole() {
       ArrayList<AbstractRole> roles = new ArrayList();
        roles.add(new MngRequestAdmin());
        return roles;
    }
}
