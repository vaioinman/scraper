package com.test.scraper.validator;

import com.test.scraper.bean.ItemBean;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidatorTest {
    @Test
    public void mustBeValid() {
        ItemBean item = ItemBean.builder()
                .title("Title")
                .unitPrice(new BigDecimal(10.1))
                .description("Description")
                .kcalPer100g(5)
                .build();

        boolean valid = Validator.validate(item);
        assertThat(valid, is(true));
    }

    @Test
    public void givenItemWithoutTitle_mustReturnFalse() {
        ItemBean item = ItemBean.builder()
                .unitPrice(new BigDecimal(10.1))
                .description("Description")
                .kcalPer100g(5)
                .build();

        boolean valid = Validator.validate(item);
        assertThat(valid, is(false));
    }

    @Test
    public void givenItemWithoutDescription_mustReturnFalse() {
        ItemBean item = ItemBean.builder()
                .title("Title")
                .unitPrice(new BigDecimal(10.1))
                .kcalPer100g(5)
                .build();

        boolean valid = Validator.validate(item);
        assertThat(valid, is(false));
    }

    @Test
    public void givenItemWithoutUnitPrice_mustReturnFalse() {
        ItemBean item = ItemBean.builder()
                .title("Title")
                .description("Description")
                .kcalPer100g(5)
                .build();

        boolean valid = Validator.validate(item);
        assertThat(valid, is(false));
    }

    @Test
    public void givenItemWithoutEnergy_mustReturnFalse() {
        ItemBean item = ItemBean.builder()
                .title("Title")
                .description("Description")
                .unitPrice(new BigDecimal(10.1))
                .build();

        boolean valid = Validator.validate(item);
        assertThat(valid, is(false));
    }
}
