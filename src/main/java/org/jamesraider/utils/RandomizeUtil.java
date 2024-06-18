package org.jamesraider.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomizeUtil {

    public RandomizeUtil() {}

    public String generateRandomAlphabeticWord(int length) {
        return RandomStringUtils.random(length, true, false);
    }

    public String generateRandomAlphanumericWord(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    public String generateRandomNumericWord(int length) {
        return RandomStringUtils.random(length, false, true);
    }
}
