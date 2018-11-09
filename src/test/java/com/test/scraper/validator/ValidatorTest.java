package com.test.scraper.validator;

import com.test.scraper.bean.Item;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidatorTest {
    @Test
    public void mustBeValid() {
        Item item = Item.builder()
                .title("Title")
                .unitPrice(10.1)
                .description("Description")
                .kcalPer100g(5)
                .build();

        boolean valid = Validator.validate(item);
        assertThat(valid, is(true));
    }

    @Test
    public void givenItemWithoutTitle_mustReturnFalse() {
        Item item = Item.builder()
                .unitPrice(10.1)
                .description("Description")
                .kcalPer100g(5)
                .build();

        boolean valid = Validator.validate(item);
        assertThat(valid, is(false));
    }

    @Test
    public void givenItemWithoutDescription_mustReturnFalse() {
        Item item = Item.builder()
                .title("Title")
                .unitPrice(10.1)
                .kcalPer100g(5)
                .build();

        boolean valid = Validator.validate(item);
        assertThat(valid, is(false));
    }

    @Test
    public void givenItemWithoutUnitPrice_mustReturnFalse() {
        Item item = Item.builder()
                .title("Title")
                .description("Description")
                .kcalPer100g(5)
                .build();

        boolean valid = Validator.validate(item);
        assertThat(valid, is(false));
    }

    @Test
    public void givenItemWithoutEnergy_mustReturnFalse() {
        Item item = Item.builder()
                .title("Title")
                .description("Description")
                .unitPrice(10.1)
                .build();

        boolean valid = Validator.validate(item);
        assertThat(valid, is(false));
    }
}
