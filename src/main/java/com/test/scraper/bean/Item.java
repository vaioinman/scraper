package com.test.scraper.bean;


import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String title;
    private Double unitPrice;
    
}
