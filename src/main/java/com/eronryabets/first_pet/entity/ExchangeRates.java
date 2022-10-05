package com.eronryabets.first_pet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
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

}
