/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Network;

import Business.EcoSystem;
import Business.Transaction;
import java.util.AbstractQueue;


/**
 *
 * @author summershoohaw
 */
public class TransactionQueue{
    private Node first;
    private Node last;
    private int number=0;
    private String coinType;

    public int getNumber() {
        return number;
    }
    
    public TransactionQueue(String coinType){
        this.coinType = coinType;
    }
    
    private class Node{
        Transaction t;
        Node next;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public boolean isFull(){
        return number == 10;
    }
    
    public void enqueue(Transaction t) throws Exception{
        Node oldLast = last;
        last = new Node();
        last.t = t;
        last.next = null;
        if(isEmpty())
            first = last;
        else oldLast.next = last;
        number ++;
        
        
    }
    
    public Transaction dequeue(){
        Transaction t = first.t;
        first = first.next;
        if(isEmpty())
            last = null;
        number--;
        return t;
    }
    
    
}
