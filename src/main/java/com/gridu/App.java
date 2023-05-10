package com.gridu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    Scanner scanner = new Scanner(System.in);
    public static ArrayList<Contact> contacts = new ArrayList<>();
    Printer printer = new Printer();
    ContactEditor contactEditor = new ContactEditor();

    private final ContactSearcher contactSearcher;
    public App() {
        this.contactSearcher = new ContactSearcher(this);
    }

    public void mainMenu() {
        while (true) {
            switch (mainMenuAction()) {
                case "add" -> contactEditor.addContact(enterType());
                case "list" -> {
                    printer.printContacts(contacts);
                    listMenu();
                }
                case "search" -> contactSearcher.searchContact();
                case "count" -> printer.countContacts(contacts);
                case "exit" -> System.exit(0);
                default -> System.out.println("Wrong action!");
            }
        }
    }

    public String mainMenuAction() {
        System.out.print("[menu] Enter action (add, list, search, count, exit): ");
        return scanner.nextLine();
    }

    public void recordMenu(Contact contact) {
        System.out.print("[record] Enter action (edit, delete, menu): ");
        switch (scanner.nextLine()) {
            case "edit" -> contactEditor.editContact(contact);
            case "delete" -> {
                contactEditor.removeContact(contact);
                recordMenu(contact);
            }
            case "menu" -> mainMenu();
            default -> System.out.println("Wrong action!");
        }
    }

    public void listMenu() {
        System.out.print("Enter action ([number], back): ");
        String action = scanner.nextLine();
        Pattern digits = Pattern.compile("[0-9]");
        if (action.equals("back")) {
            mainMenu();
        } else if (digits.matcher(action).find()) {
            printer.printInfo(contacts.get(Integer.parseInt(action) - 1));
            recordMenu(contacts.get(Integer.parseInt(action) - 1));
        } else {
            System.out.println("Wrong action!");
        }
    }

    public String enterType() {
        System.out.print("Enter the type (person, organization): ");
        return scanner.nextLine();
    }
}
