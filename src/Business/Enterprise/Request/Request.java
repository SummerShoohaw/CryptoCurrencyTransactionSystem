/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise.Request;

import Business.Investor.Customer;
import Business.Organization.Organization;

/**
 *
 * @author lm
 */
public class Request {
    public enum Type{
        Buy("buy"),Sell("sell");
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
        Type(String value){
            this.value = value;
        }
    }
    private Customer cus;
    private int price;
    private String coinType;
    private double amount;
    private String status = "pending";
    private int number;
    private String rType;

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }

    public int getNumber() {
        return number;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    

    
    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }
    private Organization org;

    public void setOrg(Organization org) {
        this.org = org;
    }

    public Organization getOrg() {
        return org;
    }
    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public Request(Customer cus, int price, Organization org, String coinType, double amount, int num, String type){
        this.cus = cus;
        this.price = price;
        this.org = org;
        this.coinType = coinType;
        this.amount = amount;
        this.number = num;
        this.rType = type;
    }
    
    
}
