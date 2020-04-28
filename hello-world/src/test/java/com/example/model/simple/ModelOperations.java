package com.example.model.simple;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ModelOperations {

    @Test
    public void test() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(new Date());

        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertEquals(1, violations.size());

        ConstraintViolation<Item> violation = violations.iterator().next();
        String failedPropertyName = violation.getPropertyPath().iterator().next().getName();
        assertEquals("auctionEnd", failedPropertyName);

        if (Locale.getDefault().getLanguage().equals("en")) {
            assertEquals("must be in the future", violation.getMessage());
        } else if (Locale.getDefault().getLanguage().equals("ru")) {
            assertEquals("должно быть в будущем", violation.getMessage());
        }
    }
}
