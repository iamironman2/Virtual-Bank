package com.Banking;

import java.io.Serial;
import java.io.Serializable;

public  class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private long accNo;
    private long mobNo;
    private String address;
    private String accType;
    private double accBal;
    private int accPin;

    public int getAccPin() {
        return accPin;
    }

    public void setAccPin(int accPin) {
        this.accPin = accPin;
    }

    public double getAccBal() {
        return accBal;
    }

    public void setAccBal(double accBal) {
        this.accBal = accBal;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccNo() {
        return accNo;
    }

    public void setAccNo(long accNo) {
        this.accNo = accNo;
    }

    public long getMobNo() {
        return mobNo;
    }

    public void setMobNo(long mobNo) {
        this.mobNo = mobNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
