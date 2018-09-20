/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization.Exchange;
import Business.Enterprise.Request.BuyingRequest;
import Business.Enterprise.Request.Request;
import Business.Enterprise.Role.AbstractRole;
import Business.Investor.Customer;
import Business.Organization.Broker.MngRequest;
import Business.Organization.Organization;
import Business.Transaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import Business.EcoSystem;
import Business.Enterprise.Request.RequestDirectory;
import Business.Enterprise.Role.MatchAdmin;
import Business.Network.Network;
import Business.Network.TransactionQueue;
import Business.Wallet.Wallet;

/**
 *
 * @author lm
 */
public class MatcherOrganization extends Organization{
//    private static MatcherOrganization m;
    private PriorityQueue srQ ;
    private PriorityQueue brQ ;
    public ArrayList<Integer> dealPriceList;
    public PriorityQueue getSrQ() {
        return srQ;
    }

    public PriorityQueue getBrQ() {
        return brQ;
    }
    public MatcherOrganization(String name){
        super(name);
        dealPriceList = new ArrayList<Integer>();
    }
    
//    public static MatcherOrganization getMatcherInstance(String name){
//        if(m == null){
//            m = new MatcherOrganization(name);
//        }
//        return m;
//    }
    
    public void enqueueSellRequest(Request req){
        this.srQ = getSellRD().getRequestQueue();
//        this.brQ = getBuyRD().getRequestQueue();
      
        srQ.add(req);
        checkEmpty();
    }
    /*
        Broker.enqueueRequest() 
    */
    public void enqueueBuyRequest(Request req){
        /*
            if either selling request queue or buying request queue is empty after enqueue, DO NOT MATCH()
        */
//        this.getBuyRD()
//        srQ = this.getSellRD().getRequestQueue();
        brQ = this.getBuyRD().getRequestQueue();
        
        this.brQ.add(req);
        checkEmpty();
    }
    
    public Request checkBQTimeout(){
        PriorityQueue temp;
        Request r = null;
        if(brQ!= null && brQ.size()>=10){
            temp = new RequestDirectory("buy").getRequestQueue();
            for(int i=0; i<9; i++){
                temp.add((Request)brQ.poll());
            }
            r = (Request) brQ.poll();
            brQ = temp;
            
        }
        return r;
    }
    public Request checkSQTimeout(){
        PriorityQueue temp;
        Request r = null;
        if(srQ != null && srQ.size()>=10){
            temp = new RequestDirectory("sell").getRequestQueue();
            for(int i=0; i<9; i++){
                temp.add((Request)srQ.poll());
            }
            r = (Request) srQ.poll();
            this.getSellRD().setRequestQueue(temp);
        }
        return r;
    }
    public void checkEmpty(){
        Request br = null;
        Request sr = null;
        double amount = 0;
        int dealPrice = -1;
        if(srQ != null && brQ != null && !srQ.isEmpty() && !brQ.isEmpty()){
            String result = match(brQ,srQ);
            if(result == "unMatch"){
                // check if the queue is full.
                // if full, remove the last element, and send failed message to customer
                br = checkBQTimeout();
                sr = checkSQTimeout();
            }else if(result == "Cancel"){
                br = (Request) brQ.poll();
            }
            else{
                br = (Request) brQ.poll();
                sr = (Request) srQ.poll();
                dealPrice = sr.getPrice();
                amount = Math.min(br.getAmount(), sr.getAmount());
                dealPriceList.add(dealPrice); 
            }
            sendFeedBack(br, sr, dealPrice, amount);
        }else{
//            System.out.println("Queue is Empty!!");
            dealPrice = 0;
            br = checkBQTimeout();
            sr = checkSQTimeout();
            sendFeedBack(br, sr, dealPrice, amount);
            
        }
    }
    
    public void sendFeedBack(Request br, Request sr, int dealPrice, double amount){
         if(br != null){
            MngRequest bmr = (MngRequest) br.getOrg();

   // choice 1. push the message in queue and check the queue when other customer send meesage to MngRequest and then can feedback the transaction 
            //status to customer
            HashMap<Request, String> feedInfo = new HashMap<Request,String>();
            if(dealPrice<0){
                // write down transaction failed
                feedInfo.put(br, "Balance not enough");
            }else if(dealPrice == 0){
                feedInfo.put(br, "Your request is expired");
            }else{
                feedInfo.put(br, "Buying price:" + dealPrice + " Amount: " + amount);
            }
            Queue q = bmr.getMessageQueue();
            q.add(feedInfo);
   // choice 2: do sendCustomerInfo directly, no message queue.
//                bmr.sendCustomerInfo(br.getCus(),dealPrice);
//                smr.sendCustomerInfo(sr.getCus(), dealPrice);
            }
        if(sr != null){
            MngRequest smr = (MngRequest) sr.getOrg();
            HashMap<Request, String> sfeedInfo = new HashMap<Request, String>();
            if(dealPrice == 0){
                sfeedInfo.put(sr, "Your request is expired");
            }else{
                sfeedInfo.put(sr, "Selling Price:" + dealPrice + " Amount: " + amount);
            }
            
            smr.getMessageQueue().add(sfeedInfo);
        }
    }
    
    public String match(PriorityQueue brQ, PriorityQueue srQ){
        System.out.println("-----------------------------------");
        System.out.println("start match");
        Request br = (Request)brQ.peek();
        Request sr = (Request)srQ.peek();
        System.out.println(br.getCus().getName()+" wants to buy "+br.getAmount()+" "+br.getCoinType()+"@ price"+br.getPrice());
        System.out.println(sr.getCus().getName()+" wants to sell "+sr.getAmount()+" "+sr.getCoinType()+"@ price"+sr.getPrice());
        if(br.getPrice() >= sr.getPrice()){
           
             // if match successful
        // 1.create a transaction
        // 2.push transaction into transactionList( transactionList in network level as an attribute)
        // 3.update state to success
        
            //            
            /*
                write transaction
            */
            System.out.println("match success, start create transaction");
            EcoSystem sys = EcoSystem.getEcoSystem();
            Network network = sys.getNetwork(sr.getCoinType());
            Wallet srW = sys.getInvestorDirectory().searchWallet(sr.getCus(), sr.getCoinType());
            Wallet brW = sys.getInvestorDirectory().searchWallet(br.getCus(), br.getCoinType());
            int price1 = br.getPrice();
            int price2 = sr.getPrice();
            int price = Math.min(price1, price2);
            //amount problem --- get the minimum matched amount
            double min = Math.min(br.getAmount(), sr.getAmount());
            Transaction transaction = new Transaction(brW, srW, min,price);
            TransactionQueue tempTransactionQueue = network.getTempTransactionQueue();
            System.out.println("transaction create successfully！");
              try{
                    tempTransactionQueue.enqueue(transaction);
                    System.out.println("transaction added to temp queue. Receipt number is: "+transaction.getReceipt()+"tempQueue capacity: "+tempTransactionQueue.getNumber());
                    try{
                        EcoSystem.getEcoSystem().getNetwork(sr.getCoinType()).StartWriteTrans();
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Trying to write into blockchain but failed, reason:");
                        System.out.println("                    old block is full, new block hasnt been created");
                    }
              }catch (Exception e){
                  System.out.println("transaction invalid");
                  return "Cancel";
              }
            return "Match";
        }
        System.out.println("match failed");
        return "unMatch";
    }
    @Override
    public String toString(){
        return this.getName();
    }
    @Override
    public ArrayList<AbstractRole> getSupportedRole() {
         ArrayList<AbstractRole> roles = new ArrayList();
        roles.add(new MatchAdmin());
        return roles;
    }
}
