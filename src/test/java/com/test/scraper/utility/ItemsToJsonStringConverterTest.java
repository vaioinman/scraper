package com.test.scraper.utility;


import com.test.scraper.bean.ItemBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ItemsToJsonStringConverterTest {

    @Test
    public void shouldConvertToJsonString() throws Exception {
        List<ItemBean> items = new ArrayList<>();

        ItemBean item1 = ItemBean.builder()
                .title("Item 1")
                .unitPrice(1.5)
                .description("Desc 1")
                .kcalPer100g(22)
                .build();
        items.add(item1);

        ItemBean item2 = ItemBean.builder()
                .title("Item 2")
                .unitPrice(2.5)
                .description("Desc 2")
                .kcalPer100g(33)
                .build();
        items.add(item2);

        ItemBean item3 = ItemBean.builder()
                .title("Item 3")
                .unitPrice(15.5)
                .description("Desc 3")
                .kcalPer100g(1000)
                .build();
        items.add(item3);

        String result = ItemsToJsonStringConverter.convertToJsonString(items);

        System.out.println(result);
        assertThat(result, not(isEmptyOrNullString()));
    }
}
