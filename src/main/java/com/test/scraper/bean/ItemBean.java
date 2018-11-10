package com.test.scraper.bean;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ItemBean {
    private String title;

    @JsonProperty("unit_price")
    private Double unitPrice;

    private String description;

    @JsonProperty("kcal_per_100g")
    private Integer kcalPer100g;
}
