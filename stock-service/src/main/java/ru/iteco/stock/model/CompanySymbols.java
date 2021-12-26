package ru.iteco.stock.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CompanySymbols {

    private Map<String, Integer> meta;
    private List<Map<String, String>> data;

}
