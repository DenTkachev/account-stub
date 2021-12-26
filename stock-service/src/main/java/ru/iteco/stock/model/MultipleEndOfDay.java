package ru.iteco.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class MultipleEndOfDay {

    @JsonProperty("data")
    private List<DataEndOfDay> data;

}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
class DataEndOfDay {

    private String symbol;
    private Date date;
    private BigDecimal price;
    private String currency = "USD";

    @JsonProperty("price")
    public BigDecimal getPrice() {
        return price;
    }

    @JsonProperty("close")
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
