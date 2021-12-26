package ru.iteco.stock.service;

import org.springframework.stereotype.Service;
import ru.iteco.stock.model.Companies;
import ru.iteco.stock.model.CompanySymbols;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.iteco.stock.model.MultipleEndOfDay;
import ru.iteco.stock.model.StockQuotes;

import java.lang.management.MemoryUsage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class ExchangeApiImpl implements ExchangeApi{

    private final RestTemplate restTemplate;
    private final String token;
    private final String urlGetSymbols;
    private final String urlStockQuotes;
    private final String urlEOD;

    public ExchangeApiImpl(@Qualifier("restTemplateExchange") RestTemplate restTemplate,
                           @Value("${stock.token}") String token,
                           @Value("${stock.url.get-symbols}") String urlGetSymbols,
                           @Value("${stock.url.stock-quotes}") String urlStockQuotes,
                           @Value("${stock.url.eod-multiple}") String urlEOD){
        this.restTemplate = restTemplate;
        this.token = token;
        this.urlGetSymbols = urlGetSymbols;
        this.urlStockQuotes = urlStockQuotes;
        this.urlEOD = urlEOD;
    }

    @Override
    public CompanySymbols getSymbols(String symbols) {
        ResponseEntity<CompanySymbols> responseEntity = restTemplate.getForEntity(
                String.format(urlGetSymbols, symbols, token),
                CompanySymbols.class);
        return responseEntity.getBody();
    }

    @Override
    public StockQuotes getStockQuotes(Companies companies) {
        ResponseEntity<StockQuotes> responseEntity = restTemplate.getForEntity(
                String.format(urlStockQuotes, String.join(",", companies.getCompanies()), token),
                StockQuotes.class);
        return responseEntity.getBody();
    }

    @Override
    public MultipleEndOfDay getEODMultiple(Date date, Companies companies) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ResponseEntity<MultipleEndOfDay> responseEntity = restTemplate.getForEntity(
                String.format(urlEOD, String.join(",", companies.getCompanies()), token, simpleDateFormat.format(date)),
                MultipleEndOfDay.class);
        return responseEntity.getBody();
    }
}
