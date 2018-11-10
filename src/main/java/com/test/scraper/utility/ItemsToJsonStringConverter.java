package com.test.scraper.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.scraper.bean.ItemBean;
import com.test.scraper.bean.ResultBean;
import com.test.scraper.bean.TotalBean;

import java.util.List;

public class ItemsToJsonStringConverter {
    private static final Double VAT = 0.2;

    public static String convertToJsonString(List<ItemBean> items) throws JsonProcessingException {
        TotalBean total = TotalBean.builder()
                .gross(0.0)
                .vat(0.0)
                .build();

        items.stream()
                .forEach(item -> {
                    total.setGross(total.getGross() + item.getUnitPrice());
                    total.setVat(total.getVat() + item.getUnitPrice()*VAT);
                });


        ResultBean resultBean = ResultBean.builder()
                .results(items)
                .total(total)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultBean);
    }
}
