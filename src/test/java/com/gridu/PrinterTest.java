package com.gridu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void shouldPrintCountContacts() {
        Printer printer = new Printer();
        ArrayList<Contact> testContacts = new ArrayList<>();
        Person person = new Person();
        person.setName("Jack");
        testContacts.add(person);
        printer.countContacts(testContacts);
        assertEquals("The Phone Book has 1 records.", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void shouldPrintContacts() {
        Printer printer = new Printer();
        ArrayList<Contact> testContacts = new ArrayList<>();
        Person person = new Person();
        person.setName("Jack");
        person.setSurname("Sparrow");
        testContacts.add(person);
        printer.printContacts(testContacts);
        assertEquals("1. Jack Sparrow", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void printInfo() {
        Printer printer = new Printer();
        ArrayList<Contact> testContacts = new ArrayList<>();
        Person person = new Person();
        person.setName("Jack");
        person.setSurname("Sparrow");
        person.setBirthdate(LocalDate.parse("2000-12-12"));
        person.setGender("M");
        person.setPhone("555");
        person.setTimeCreated(LocalDateTime.parse("2023-03-07T23:15"));
        person.setLastEdit(LocalDateTime.parse("2023-03-07T23:15"));
        testContacts.add(person);
        printer.printInfo(testContacts.get(0));
        assertEquals("""
                Name: Jack
                Surname: Sparrow
                Birth date: 2000-12-12
                Gender: M
                Number: 555
                Time created: 2023-03-07T23:15
                Time last edit: 2023-03-07T23:15""", outputStreamCaptor.toString()
                .trim());
    }
}