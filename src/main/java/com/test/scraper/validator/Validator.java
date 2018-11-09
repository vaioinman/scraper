package com.test.scraper.validator;

import com.test.scraper.bean.Item;

public class Validator {
    public static boolean validate(Item item) {
        return item.getTitle() != null
                && item.getUnitPrice() != null
                && item.getKcalPer100g() != null
                && item.getDescription() != null;
    }
}
