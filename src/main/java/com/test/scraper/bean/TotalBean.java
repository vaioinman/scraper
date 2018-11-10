package com.test.scraper.bean;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TotalBean {
    private Double gross;
    private Double vat;
}
