/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestTest;

import Business.Account.Account;
import Business.EcoSystem;
import Business.Enterprise.AbstractEnterprise;
import Business.Enterprise.BrokerEnterprise;
import Business.Enterprise.CoinEnterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Enterprise.ExchangeEnterprise;
import Business.Investor.Customer;
import Business.Network.Network;
import Business.Network.NetworkDirectory;
import Business.Organization.Broker.MngRequest;
import Business.Organization.Exchange.MatcherOrganization;
import com.db4o.cs.monitoring.Networking;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lm
 */
public class MultiCoinTest {
    // network c1
    BrokerEnterprise eb1;
    MngRequest mngReqOrg1;
    BrokerEnterprise eb;
    MngRequest mngReqOrg;
    //network c2
    BrokerEnterprise eb3;
    MngRequest mngReqOrg3;
    MatcherOrganization m1;
    MatcherOrganization m2;
    Network c1;
    Network c2;
    
    @Before
    public void buildSystem(){
        EcoSystem system = EcoSystem.getEcoSystem();
        c1 = system.createAndAddNetwork("c1");
        c2 = system.createAndAddNetwork("c2");
        EnterpriseDirectory ed1 = c1.getEnterpriseDir();
        EnterpriseDirectory ed2 = c2.getEnterpriseDir();
        CoinEnterprise ce1 = (CoinEnterprise) ed1.addEnterprise("c1", AbstractEnterprise.Type.CoinEnterprise);
        ExchangeEnterprise ee1 = (ExchangeEnterprise) ed1.addEnterprise("Exchange", AbstractEnterprise.Type.Exchange);
        m1 = (MatcherOrganization) ee1.getOrgDir().addOrg("Match");
        
        CoinEnterprise ce2 = (CoinEnterprise) ed2.addEnterprise("c2", AbstractEnterprise.Type.CoinEnterprise);
        ExchangeEnterprise ee2 = (ExchangeEnterprise) ed2.addEnterprise("Exchangec2", AbstractEnterprise.Type.Exchange);
        m2 = (MatcherOrganization) ee2.getOrgDir().addOrg("Matchc2");
        
        
        eb1 = (BrokerEnterprise) ed1.addEnterprise("Broker", AbstractEnterprise.Type.Broker);      
        mngReqOrg1= (MngRequest) eb1.getOrgDir().addOrg("MngRequest");
        eb = (BrokerEnterprise) ed1.addEnterprise("Broker", AbstractEnterprise.Type.Broker);
        mngReqOrg = (MngRequest) eb.getOrgDir().addOrg("MngRequest");
        
        eb3 = (BrokerEnterprise) ed2.addEnterprise("Broker", AbstractEnterprise.Type.Broker);      
        mngReqOrg3= (MngRequest) eb3.getOrgDir().addOrg("MngRequest");
       
    }
    
    @Test
    public void RequestTest(){
        System.out.println("buy request from customer 1  broker1");
        Customer cus1 = new Customer("1");
        cus1.sendReq("buy", 200, mngReqOrg1, c1.getName(), 10);
        Customer cus2 = new Customer("2");
        System.out.println("sell request from customer 1  broker3");
        cus1.sendReq("sell", 200, mngReqOrg3, c2.getName(), 10);
        System.out.println("sell request from customer 2  broker1");
        cus2.sendReq("sell", 150, mngReqOrg1, c1.getName(), 10);
        System.out.println("buy request from customer 3  broker1");
        Customer cus3 = new Customer("3");
        cus3.sendReq("buy", 100, mngReqOrg1, c1.getName(), 10);
        System.out.println("buy request from customer 1  broker3");
        cus1.sendReq("buy", 400, mngReqOrg3, c2.getName(), 10);
        System.out.println("buy request from customer 2  broker3");
        cus2.sendReq("buy", 100, mngReqOrg3, c2.getName(), 10);
      
    } 
}
