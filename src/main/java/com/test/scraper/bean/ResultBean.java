package com.test.scraper.bean;

import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean {
    private List<ItemBean> results;

    private TotalBean total;
}
