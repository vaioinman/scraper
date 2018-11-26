package com.test.scraper.bean;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ItemBean {
    private String title;

    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    private String description;

    @JsonProperty("kcal_per_100g")
    private Integer kcalPer100g;
}
