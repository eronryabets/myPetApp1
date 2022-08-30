package com.eronryabets.first_pet.utility;

import java.util.Collections;

import com.eronryabets.first_pet.entity.ExchangeRates;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GetPOJOExchangeRates {

    static final String URL_ExchangeRates = "https://bank.gov.ua/NBU_Exchange/exchange?json";

    public ExchangeRates[] getArrayRates(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        HttpEntity<ExchangeRates[]> entity = new HttpEntity<ExchangeRates[]>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ExchangeRates[]> response = restTemplate.exchange(URL_ExchangeRates, //
                HttpMethod.GET, entity, ExchangeRates[].class);

        HttpStatus statusCode = response.getStatusCode();

        if (statusCode == HttpStatus.OK) {
            ExchangeRates[] list = response.getBody();
            return list;

        }else return null;
    }
}