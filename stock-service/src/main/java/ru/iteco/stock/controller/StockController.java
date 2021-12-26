package ru.iteco.stock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.iteco.stock.model.Companies;
import ru.iteco.stock.model.CompanySymbols;
import ru.iteco.stock.model.MultipleEndOfDay;
import ru.iteco.stock.model.StockQuotes;
import ru.iteco.stock.service.ExchangeApi;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final ExchangeApi exchangeApi;

    @GetMapping("/find")
    public CompanySymbols getCompanies(@RequestParam("symbols") String symbols) {
        return exchangeApi.getSymbols(symbols);
    }

    @PostMapping("/stock-quotes")
    public StockQuotes getStockQuotes(@RequestBody Companies companies) {
        return exchangeApi.getStockQuotes(companies);
    }

    @PostMapping("/multiple")
    public MultipleEndOfDay getMultiple(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date date, @RequestBody Companies companies) {
        return exchangeApi.getEODMultiple(date, companies);
    }

}
