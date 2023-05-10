package com.gridu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactSearcher {
    Scanner scanner = new Scanner(System.in);
    Printer printer = new Printer();

    private final App app;
    public ContactSearcher(App app) {
        this.app = app;
    }

    public void searchContact() {
        ArrayList<Contact> foundContacts = new ArrayList<>();
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        for (Contact item : App.contacts) {
            if (item.getAllFields().contains(query.toLowerCase())) {
                foundContacts.add(item);
            }
        }
        System.out.println("Found " + foundContacts.size() + " results:");
        printer.printContacts(foundContacts);

        System.out.print("[search] Enter action ([number], back, again): ");
        String action = scanner.nextLine();
        Pattern digits = Pattern.compile("[0-9]");
        if (!action.equals("back")) {
            if (action.equals("again")) {
                searchContact();
            } else if (digits.matcher(action).find()) {
                app.recordMenu(App.contacts.get(Integer.parseInt(action) - 1));
            } else {
                System.out.println("Wrong action!");
            }
        }
    }
}
