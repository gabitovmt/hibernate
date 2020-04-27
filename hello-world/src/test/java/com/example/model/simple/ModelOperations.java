package com.example.model.simple;

import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ModelOperations {

    @Test
    public void test() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(new Date());

        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertEquals(violations.size(), 1);

        ConstraintViolation<Item> violation = violations.iterator().next();
        String failedPropertyName = violation.getPropertyPath().iterator().next().getName();
        assertEquals(failedPropertyName, "auctionEnd");

        if (Locale.getDefault().getLanguage().equals("en")) {
            assertEquals(violation.getMessage(), "must be in the future");
        } else if (Locale.getDefault().getLanguage().equals("ru")) {
            assertEquals(violation.getMessage(), "должно быть в будущем");
        }
    }
}
