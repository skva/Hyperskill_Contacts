package com.gridu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    Validator validator = new Validator();

    @Test
    void shouldBeValidPhone() {
        assertTrue(validator.isValidPhone("+7-999-356-77-77"));
    }

    @Test
    void shouldNotBeValidPhone() {
        assertFalse(validator.isValidPhone("+(7)(111)-22-22"));
    }

    @Test
    void shouldBeValidBirthDate() {
        assertTrue(validator.isValidBirthDate("2000-12-12"));
    }

    @Test
    void shouldNotBeValidBirthDate() {
        assertFalse(validator.isValidBirthDate("2000.12.12"));
    }

    @Test
    void shouldBeValidGender() {
        assertTrue(validator.isValidGender("M"));
        assertTrue(validator.isValidGender("F"));
    }

    @Test
    void shouldNotBeValidGender() {
        assertFalse(validator.isValidGender("male"));
        assertFalse(validator.isValidGender("female"));
        assertFalse(validator.isValidGender("m"));
        assertFalse(validator.isValidGender("f"));
        assertFalse(validator.isValidGender(""));
    }
}