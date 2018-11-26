package com.test.scraper.bean;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TotalBean {
    private BigDecimal gross;
    private BigDecimal vat;
}
