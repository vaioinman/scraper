package com.test.scraper.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.scraper.bean.ItemBean;
import com.test.scraper.bean.ResultBean;
import com.test.scraper.bean.TotalBean;

import java.math.BigDecimal;
import java.util.List;

public class ItemsToJsonStringConverter {
    private static final BigDecimal VAT = new BigDecimal(0.2);

    public static String convertToJsonString(List<ItemBean> items) throws JsonProcessingException {
        TotalBean total = TotalBean.builder()
                .gross(new BigDecimal(0.0))
                .vat(new BigDecimal(0.0))
                .build();

        items.stream()
                .forEach(item -> {
                    total.setGross(total.getGross().add(item.getUnitPrice()));
                });


        total.setVat(total.getGross().multiply(VAT));
        ResultBean resultBean = ResultBean.builder()
                .results(items)
                .total(total)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultBean);
    }
}
