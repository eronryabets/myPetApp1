package com.eronryabets.first_pet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRates {

    @JsonProperty("StartDate")
    private String startDate;
    @JsonProperty("TimeSign")
    private String timeSign;
    @JsonProperty("CurrencyCode")
    private String currencyCode;
    @JsonProperty("CurrencyCodeL")
    private String currencyCodeL;
    @JsonProperty("Units")
    private int units;
    @JsonProperty("Amount")
    private double amount;

    public ExchangeRates() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTimeSign() {
        return timeSign;
    }

    public void setTimeSign(String timeSign) {
        this.timeSign = timeSign;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCodeL() {
        return currencyCodeL;
    }

    public void setCurrencyCodeL(String currencyCodeL) {
        this.currencyCodeL = currencyCodeL;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "startDate='" + startDate + '\'' +
                ", timeSign='" + timeSign + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", currencyCodeL='" + currencyCodeL + '\'' +
                ", units=" + units +
                ", amount=" + amount +
                '}';
    }
}
