package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.ExchangeRates;
import com.eronryabets.first_pet.utility.GetPOJOExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MainService {

    @Autowired
    private GetPOJOExchangeRates exchangeRates;

    public ArrayList<ExchangeRates> getExchangeRatesList(){
        ArrayList<ExchangeRates> list = new ArrayList<>();
        ExchangeRates[] array = exchangeRates.getArrayRates();
        for (ExchangeRates e : array){
            if(e.getCurrencyCodeL().equals("USD")
                    | e.getCurrencyCodeL().equals("EUR")
                    | e.getCurrencyCodeL().equals("PLN")){
                list.add(e);
            }
        }return list;
    }

}
