package ru.iteco.stock.service;

import ru.iteco.stock.model.Companies;
import ru.iteco.stock.model.CompanySymbols;
import ru.iteco.stock.model.MultipleEndOfDay;
import ru.iteco.stock.model.StockQuotes;

import java.util.Date;

public interface ExchangeApi {

    CompanySymbols getSymbols(String symbols);

    StockQuotes getStockQuotes(Companies companies);

    MultipleEndOfDay getEODMultiple(Date date, Companies companies);

}
