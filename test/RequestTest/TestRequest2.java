/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestTest;

import Business.Account.Account;
import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Enterprise.AbstractEnterprise;
import Business.Enterprise.BrokerEnterprise;
import Business.Enterprise.CoinEnterprise;
import Business.Enterprise.EnterpriseDirectory;
import Business.Enterprise.ExchangeEnterprise;
import Business.Enterprise.Role.Admin;
import Business.Enterprise.Role.MatchAdmin;
import Business.Enterprise.Role.MngRequestAdmin;
import Business.Investor.Miner;
import Business.Network.Network;
import Business.Network.NetworkDirectory;
import Business.Organization.Broker.MngRequest;
import Business.Organization.Currency.Repository;
import Business.Organization.Exchange.MatcherOrganization;
import Business.Transaction;
import Business.Transaction.UTXO;
import Business.Wallet.Wallet;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lm
 */
public class TestRequest2 {
   
    BrokerEnterprise eb1;
    MngRequest mngReqOrg1;
    BrokerEnterprise eb;
    MngRequest mngReqOrg;
    
    BrokerEnterprise deb1;
    MngRequest dmngReqOrg1;
    BrokerEnterprise deb;
    MngRequest dmngReqOrg;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();
    Repository rb;
    Repository rd;
   
    @Before
    public void TestExchangetEnterprise(){
        EcoSystem.ecoSystem = dB4OUtil.retrieveSystem();
//        EnterpriseDirectory ed = new EnterpriseDirectory();
//        EnterpriseDirectory ed1 = new EnterpriseDirectory();
        NetworkDirectory nd = EcoSystem.getEcoSystem().getNetworkDirectory();
        System.out.println(nd.getNetworkDirectory().size());
        Network network = EcoSystem.getEcoSystem().createAndAddNetwork("Bcoin");
        
//        network.setCoinType("Bcoin");
        
        Network network1 = EcoSystem.getEcoSystem().createAndAddNetwork("Dcoin");
        
//        network1.setCoinType("Dcoin");
        
        EnterpriseDirectory ed = network.getEnterpriseDir();
        EnterpriseDirectory ed1 = network1.getEnterpriseDir();
        CoinEnterprise ce = (CoinEnterprise) ed.addEnterprise("Bcoin", AbstractEnterprise.Type.CoinEnterprise);
        
        ce.setCoinType("Bcoin");
        
        Account bce = ce.getAccountDir().createAccount("Bcoin", "123", new Admin());
        CoinEnterprise ce1 = (CoinEnterprise) ed1.addEnterprise("Dcoin", AbstractEnterprise.Type.CoinEnterprise);
        
        ce1.setCoinType("Dcoin");
        
        Account dce = ce1.getAccountDir().createAccount("Dcoin", "123", new Admin());
        ExchangeEnterprise ee = (ExchangeEnterprise) ed.addEnterprise("BcoinExchange", AbstractEnterprise.Type.Exchange);
        Account bee = ee.getAccountDir().createAccount("BcoinExchange", "123", new Admin());
        ExchangeEnterprise ee1 = (ExchangeEnterprise) ed1.addEnterprise("DcoinExchange", AbstractEnterprise.Type.Exchange);
        Account dee = ee1.getAccountDir().createAccount("DcoinExchange", "123", new Admin());
        MatcherOrganization m = (MatcherOrganization) ee.getOrgDir().addOrg("BcoinMatch");
        Account bm = m.getAccountDir().createAccount("BcoinMatch", "123", new MatchAdmin());
        MatcherOrganization m1 = (MatcherOrganization) ee1.getOrgDir().addOrg("DcoinMatch");
        Account dm = m1.getAccountDir().createAccount("DcoinMatch", "123", new MatchAdmin());
   
        eb1 = (BrokerEnterprise) ed.addEnterprise("BcoinBroker1", AbstractEnterprise.Type.Broker);
        Account bb = eb1.getAccountDir().createAccount("BcoinBroker1", "123", new Admin());
        mngReqOrg1= (MngRequest) eb1.getOrgDir().addOrg("MngRequest");
        Account br = mngReqOrg1.getAccountDir().createAccount("br1", "123", new MngRequestAdmin());
        eb = (BrokerEnterprise) ed.addEnterprise("BcoinBroker2", AbstractEnterprise.Type.Broker);
        Account bb1 = eb.getAccountDir().createAccount("BcoinBroker2", "123", new Admin());
        mngReqOrg = (MngRequest) eb.getOrgDir().addOrg("MngRequest");
        Account bb2 = mngReqOrg.getAccountDir().createAccount("br2", "123", new MngRequestAdmin());
        
        deb1 = (BrokerEnterprise) ed1.addEnterprise("DcoinBroker1", AbstractEnterprise.Type.Broker); 
        Account db1 = deb1.getAccountDir().createAccount("DcoinBroker1", "123", new Admin());
        dmngReqOrg1= (MngRequest) deb1.getOrgDir().addOrg("MngRequest");
        Account dr1 = dmngReqOrg1.getAccountDir().createAccount("dr1", "123", new  MngRequestAdmin());
        deb = (BrokerEnterprise) ed1.addEnterprise("DcoinBroker2", AbstractEnterprise.Type.Broker);
        Account db = deb.getAccountDir().createAccount("DcoinBroker2", "123", new Admin());
        dmngReqOrg = (MngRequest) deb.getOrgDir().addOrg("MngRequest");
        Account dr = dmngReqOrg.getAccountDir().createAccount("dr2", "123", new  MngRequestAdmin());
        
        //create repository for each coin enterprise
        Repository r1 = new Repository("Repository");
        Repository r2 = new Repository("Repository");
        rb = r1;
        rd = r2;
        
        r1.setCoinType("Bcoin");
        r1.setAdminMiner();
        r1.createTheFirstBlock();
        
        r2.setCoinType("Dcoin");
        r2.setAdminMiner();
        r2.createTheFirstBlock();
        
        
        ce.getOrgDir().getOrgList().add(r1);
        ce1.getOrgDir().getOrgList().add(r2);
        
        
    }
    @Test
    public void testSellRequest(){
        NetworkDirectory nd = EcoSystem.getEcoSystem().getNetworkDirectory();
        System.out.println(nd.getNetworkDirectory().size());
        Account[] userAccount = new Account[13];
        userAccount[0] = EcoSystem.getEcoSystem().getAccountDir().createAccount("1", "123", new Business.Enterprise.Role.Customer());
        userAccount[0].getCus().addDollarBalance(100000000);
        userAccount[1] = EcoSystem.getEcoSystem().getAccountDir().createAccount("2", "123", new Business.Enterprise.Role.Customer());
        userAccount[1].getCus().addDollarBalance(100000000);
        userAccount[2] = EcoSystem.getEcoSystem().getAccountDir().createAccount("3", "123", new Business.Enterprise.Role.Customer());
        userAccount[2].getCus().addDollarBalance(100000000);
        userAccount[3] = EcoSystem.getEcoSystem().getAccountDir().createAccount("4", "123", new Business.Enterprise.Role.Customer());
        userAccount[3].getCus().addDollarBalance(100000000);
        userAccount[4] = EcoSystem.getEcoSystem().getAccountDir().createAccount("5", "123", new Business.Enterprise.Role.Customer());
        userAccount[4].getCus().addDollarBalance(100000000);
        userAccount[5] = EcoSystem.getEcoSystem().getAccountDir().createAccount("6", "123", new Business.Enterprise.Role.Customer());
        userAccount[5].getCus().addDollarBalance(100000000);
        userAccount[6] = EcoSystem.getEcoSystem().getAccountDir().createAccount("7", "123", new Business.Enterprise.Role.Customer());
        userAccount[6].getCus().addDollarBalance(100000000);
        userAccount[7] = EcoSystem.getEcoSystem().getAccountDir().createAccount("8", "123", new Business.Enterprise.Role.Customer());
        userAccount[7].getCus().addDollarBalance(100000000);
        userAccount[8] = EcoSystem.getEcoSystem().getAccountDir().createAccount("9", "123", new Business.Enterprise.Role.Customer());
        userAccount[8].getCus().addDollarBalance(100000000);
        userAccount[9] = EcoSystem.getEcoSystem().getAccountDir().createAccount("10", "123", new Business.Enterprise.Role.Customer());
        userAccount[9].getCus().addDollarBalance(100000000);
        userAccount[10] = EcoSystem.getEcoSystem().getAccountDir().createAccount("11", "123", new Business.Enterprise.Role.Customer());
        userAccount[10].getCus().addDollarBalance(100000000);
        userAccount[11] = EcoSystem.getEcoSystem().getAccountDir().createAccount("12", "123", new Business.Enterprise.Role.Customer());
        userAccount[11].getCus().addDollarBalance(100000000);
        userAccount[12] = EcoSystem.getEcoSystem().getAccountDir().createAccount("13", "123", new Business.Enterprise.Role.Customer());
        userAccount[12].getCus().addDollarBalance(100000000);
        
        Account minerAccount = EcoSystem.getEcoSystem().getAccountDir().createAccount("miner", "123", new Business.Enterprise.Role.Miner());
        Miner miner = (Miner)minerAccount.getCus();
        
//        Customer cus1 = new Customer("1");
//        cus1.sendReq("buy", 100, mngReqOrg1, "Bcoin", 10);
//        userAccount1.getCus().sendReq("buy", 100, mngReqOrg1, "Bcoin", 10);
//        System.out.println("First buy request from customer 2  broker1");
////        Customer cus2 = new Customer("2");
////        cus2.sendReq("buy", 200, mngReqOrg1, "Bcoin", 10);
//        userAccount2.getCus().sendReq("buy", 200, mngReqOrg1, "Bcoin", 10);
//        System.out.println("First buy request from customer 3  broker1");
////        Customer cus3 = new Customer("3");
////        cus3.sendReq("buy", 300, mngReqOrg1, "Bcoin", 10);
//        userAccount3.getCus().sendReq("buy", 300, mngReqOrg, "Bcoin", 10);
//        System.out.println("First buy request from customer 4  broker1");
////        Customer cus4 = new Customer("4");
////        cus4.sendReq("buy", 400, mngReqOrg1, "Bcoin", 10);
//        userAccount4.getCus().sendReq("buy", 400, mngReqOrg, "Bcoin", 10);
//        System.out.println("First buy request from customer 5  broker1");
////        Customer cus5 = new Customer("5");
////        cus5.sendReq("buy", 500, mngReqOrg1, "Bcoin", 10);
//        userAccount5.getCus().sendReq("buy", 500, mngReqOrg1, "Bcoin", 10);
//        System.out.println("First buy request from customer 6  broker");
////        Customer cus6 = new Customer("6");
////        cus6.sendReq("buy", 600, mngReqOrg, "Bcoin", 10);
//        userAccount6.getCus().sendReq("buy", 600, mngReqOrg1, "Bcoin", 10);
//        System.out.println("First buy request from customer 7  broker");
////        Customer cus7 = new Customer("7");
////        cus7.sendReq("buy", 700, mngReqOrg, "Bcoin", 10);
//        userAccount7.getCus().sendReq("buy", 700, mngReqOrg1, "Bcoin", 10);
//        System.out.println("First buy request from customer 8  broker");
////        Customer cus8 = new Customer("8");
////        cus8.sendReq("buy", 800, mngReqOrg, "Bcoin", 10);
//        userAccount8.getCus().sendReq("buy", 800, mngReqOrg1, "Bcoin", 10);
//        System.out.println("First buy request from customer 9  broker");
////        Customer cus9 = new Customer("9");
////        cus9.sendReq("buy", 900, mngReqOrg, "Bcoin", 10);
//        userAccount9.getCus().sendReq("buy", 900, mngReqOrg1, "Bcoin", 10);
//        System.out.println("First buy request from customer 10  broker");
////        Customer cus10 = new Customer("10");
////        cus10.sendReq("buy", 1000, mngReqOrg, "Bcoin", 10);
//        userAccount10.getCus().sendReq("buy", 1000, mngReqOrg1, "Bcoin", 10);
//        System.out.println("First Sell request from customer 11  broker1");
//        Customer cus11 = new Customer("11");
//        cus11.sendReq("sell", 1100, mngReqOrg1, "Bcoin", 10);
//        userAccount11.getCus().sendReq("sell", 1100, mngReqOrg1, "Bcoin", 10);
//        System.out.println("First sell request from customer 12  broker1");
//        userAccount12.getCus().sendReq("sell", 850, mngReqOrg1, "Bcoin", 10);
//        System.out.println("First sell request from customer 13  broker1");
//        userAccount13.getCus().sendReq("sell", 950, mngReqOrg1, "Bcoin", 10);
//        userAccount1.getCus().sendReq("buy", 400, dmngReqOrg, "Dcoin", 10);
//        System.out.println("buy request from customer 2  broker3");
//        userAccount3.getCus().sendReq("sell", 200, dmngReqOrg1, "Dcoin", 10);
//        userAccount2.getCus().sendReq("buy", 100, dmngReqOrg1, "Dcoin", 10);
//        userAccount4.getCus().sendReq("sell", 100, dmngReqOrg1, "Dcoin", 10);
//        userAccount5.getCus().sendReq("buy", 210, dmngReqOrg1, "Dcoin", 10);
//        userAccount6.getCus().sendReq("sell", 100, dmngReqOrg1, "Dcoin", 10);
//        userAccount7.getCus().sendReq("buy", 150, dmngReqOrg1, "Dcoin", 10);
        
        //assign wallet for each user\
        for(int i = 0;i<13;i++){
            EcoSystem.getEcoSystem().getWalletDirectory("Bcoin").addWallet(userAccount[i].getCus());
            EcoSystem.getEcoSystem().getWalletDirectory("Dcoin").addWallet(userAccount[i].getCus());
        }
        
        EcoSystem.getEcoSystem().getWalletDirectory("Dcoin").addWallet(minerAccount.getCus());
        EcoSystem.getEcoSystem().getWalletDirectory("Bcoin").addWallet(minerAccount.getCus());
        
        ArrayList<UTXO> BcoinUtxoList = new ArrayList<>();
        ArrayList<UTXO> DcoinUtxoList = new ArrayList<>();
        for(int i = 0;i<13;i++){
            BcoinUtxoList.add(initCoins(userAccount[i],1000,"Bcoin"));
            DcoinUtxoList.add(initCoins(userAccount[i],1000,"Dcoin"));
        }
        
        Transaction t1 = new Transaction(null,null,1300,0);
        Transaction t2 = new Transaction(null,null,1300,0);
        t1.setOutput(BcoinUtxoList);
        t2.setOutput(DcoinUtxoList);
        rb.getLastBlock().getTransactionHistory().add(t1);
        rd.getLastBlock().getTransactionHistory().add(t2);
        for(int j = 0;j<5;j++){
            for(int i = 0;i<userAccount.length;i++){
                int number = 2+(int)(Math.random()*2);
                RequestPopulator(number,userAccount[i]);
            }
            if(miner.enableAutoMiner("Bcoin"))
                miner.mineSuccess("Bcoin");
            if(miner.enableAutoMiner("Dcoin"))
                miner.mineSuccess("Dcoin");
        }
    }
    
    public UTXO initCoins(Account a,double amount,String coinType){
        Wallet w = EcoSystem.getEcoSystem().getWalletDirectory(coinType).searchWalletViaCustomer(a.getCus());
        UTXO u = new UTXO(w,amount);
        return u;
    }
    
    public void initRequest(Account a,String buysell,double amount,int price){
        double random1 = Math.random();
        int random2 = (int)(Math.random()*2);
        String[] coinType = {"Bcoin","Dcoin"};
        MngRequest BcoinmngR = null;
        MngRequest DcoinmngR = null;
        if(random1 < 0.5){
            BcoinmngR = mngReqOrg;
            DcoinmngR = dmngReqOrg;
        }
        else{
            BcoinmngR = mngReqOrg1;
            DcoinmngR = dmngReqOrg1;
        }
        
        
        a.getCus().sendReq(buysell, price, BcoinmngR, coinType[random2], amount);
        a.getCus().addDollarBalance(-price*amount*0.05);
    }
    
    public void RequestPopulator(int number,Account a){
        String[] bs = {"buy","sell"};
        
        for(int i = 0;i<number;i++){
            int Bprice = 80+(int)(Math.random()*40);
            double Bamount = Math.random()*10;
            BigDecimal b1 = new BigDecimal(Bamount);
            Double BamountScale = b1.setScale(4, RoundingMode.CEILING).doubleValue();
            int buyorsell = (int)(Math.random()*2);
            initRequest(a,bs[buyorsell],BamountScale,Bprice);
        }
    }
    
    @After
    public void storeData(){
        EcoSystem eco = EcoSystem.getEcoSystem();
        for(Network n:eco.getNetworkDirectory().getNetworkDirectory()){
            System.out.println(n.getName());
        }
        dB4OUtil.storeSystem(EcoSystem.getEcoSystem());
        
    }
}
