package com.gridu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactEditorTest {

    @Test
    void shouldAddPerson() {
        ArrayList<Contact> expectedContacts = new ArrayList<>();
        Person person = new Person();
        person.setName("Jack");
        person.setSurname("Sparrow");
        person.setBirthdate(LocalDate.parse("2000-12-12"));
        person.setGender("M");
        person.setPhone("555");
        person.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        person.setLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
        expectedContacts.add(person);
        String input = "Jack\nSparrow\n2000-12-12\nM\n555";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ContactEditor contactEditor = new ContactEditor();
        contactEditor.addContact("Contact");
        assertEquals(expectedContacts.get(0).toString(), App.contacts.get(0).toString());
        App.contacts.remove(0);
    }

    @Test
    void shouldAddOrganization() {
        ArrayList<Contact> expectedContacts = new ArrayList<>();
        Organization organization = new Organization();
        organization.setName("Org");
        organization.setAddress("Street");
        organization.setPhone("555");
        organization.setTimeCreated(LocalDateTime.now().withSecond(0).withNano(0));
        organization.setLastEdit(LocalDateTime.now().withSecond(0).withNano(0));
        expectedContacts.add(organization);
        String input = "Org\nStreet\n555";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ContactEditor contactEditor = new ContactEditor();
        contactEditor.addContact("Org");
        assertEquals(expectedContacts.get(0).toString(), App.contacts.get(0).toString());
    }

    @Test
    void shouldEditPerson() {
        Person person = new Person();
        person.setName("Jack");
        String input = "name\nEdited";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ContactEditor contactEditor = new ContactEditor();
        contactEditor.editPerson(person);
        assertEquals("Edited", person.getName());
    }

    @Test
    void shouldEditOrganization() {
        Organization organization = new Organization();
        organization.setName("Org");
        String input = "name\nEdited";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ContactEditor contactEditor = new ContactEditor();
        contactEditor.editOrganization(organization);
        assertEquals("Edited", organization.getName());
    }

    @Test
    void shouldRemoveContact() {
        ContactEditor contactEditor = new ContactEditor();
        Person person1 = new Person();
        person1.setName("Jack_del");
        person1.setSurname("Sparrow_del");
        person1.setBirthdate(LocalDate.parse("2000-12-12"));
        person1.setGender("M");
        person1.setPhone("555");
        person1.setTimeCreated(LocalDateTime.parse("2023-03-07T23:15"));
        person1.setLastEdit(LocalDateTime.parse("2023-03-07T23:15"));
        App.contacts.add(person1);
        Person person2 = new Person();
        person2.setName("Jack");
        person2.setSurname("Sparrow");
        person2.setBirthdate(LocalDate.parse("2000-12-12"));
        person2.setGender("M");
        person2.setPhone("555");
        person2.setTimeCreated(LocalDateTime.parse("2023-03-07T23:15"));
        person2.setLastEdit(LocalDateTime.parse("2023-03-07T23:15"));
        App.contacts.add(person2);

        contactEditor.removeContact(person1);
        assertEquals(1, App.contacts.size());
        contactEditor.removeContact(person2);
    }
}