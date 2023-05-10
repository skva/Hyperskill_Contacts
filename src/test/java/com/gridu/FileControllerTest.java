package com.gridu;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileControllerTest {
    FileController fileController = new FileController();

    @Test
    void shouldSave() {
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
        FileController.file = new File("saveMethodTest");
        try {
            if (FileController.file.createNewFile()) {
                fileController.save(testContacts);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileController.load("saveMethodTest");
        assertEquals(testContacts.get(0).toString(), App.contacts.get(0).toString());
    }

    @Test
    void shouldLoad() {
        ArrayList<Contact> expectedContacts = new ArrayList<>();
        Person person = new Person();
        person.setName("Jack");
        person.setSurname("Sparrow");
        person.setBirthdate(LocalDate.parse("2000-12-12"));
        person.setGender("M");
        person.setPhone("555");
        person.setTimeCreated(LocalDateTime.parse("2023-03-07T23:15"));
        person.setLastEdit(LocalDateTime.parse("2023-03-07T23:15"));
        expectedContacts.add(person);
        fileController.load("test");
        assertEquals(expectedContacts.get(0).toString(), App.contacts.get(0).toString());
    }
}