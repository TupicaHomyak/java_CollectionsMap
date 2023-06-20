package practice;
import java.util.Scanner;
import java.util.Set;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook(); //хранениe контактов
        while (true) {
            //основная логика программы
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine().trim();
            //Если введена команда "LIST", вызывается метод getAllContacts
            // объекта PhoneBook для получения всех контактов, которые выводятся на экран
            if (input.equalsIgnoreCase("LIST")) {
                Set<String> allContacts = phoneBook.getAllContacts();
                if (allContacts.isEmpty()) {
                    System.out.println("Телефонная книга пуста.");
                } else {
                    for (String contact : allContacts) {
                        System.out.println(contact);
                    }
                }
            } else {//Если введено пустое значение, цикл продолжается снова.
                if (input.isEmpty()) {
                    continue;
                }
                if (input.matches("\\d+")) {//Если введен номер, вызывается getContactByPhone
                    String contact = phoneBook.getContactByPhone(input);
                    if (contact.isEmpty()) {//контакт не найден, запрашивается имя  и вызывается  addContact
                        System.out.println("Такого номера нет в телефонной книге.");
                        System.out.print("Введите имя абонента для номера \"" + input + "\": ");
                        String name = scanner.nextLine().trim();
                        phoneBook.addContact(input, name);
                        System.out.println("Контакт сохранен!");
                        //добавление контакта с  номером и именем
                    } else {
                        System.out.println(contact);//Если контакт найден, он выводится на экран.
                    }
                    //Если введено имя, вызывается getContactByName  для поиска контакта по имени.
                } else if (input.matches("[a-zA-Zа-яА-Я]+")) {
                    Set<String> contacts = phoneBook.getContactByName(input);
                    if (contacts.isEmpty()) {//контакт не найден, запраш. номер телефона абонента
                        System.out.println("Такого имени в телефонной книге нет.");
                        System.out.print("Введите номер телефона для абонента \"" + input + "\": ");
                        String phone = scanner.nextLine().trim();
                        //вызывается метод addContact объекта PhoneBook
                        // для добавления нового контакта с указанным именем и номером.
                        phoneBook.addContact(phone, input);
                        System.out.println("Контакт сохранен!");
                        //Если контакт найден, он выводится на экран.
                    } else {
                        for (String contact : contacts) {
                            System.out.println(contact);
                        }
                    }
                    //Если введенная строка не соответствует ни одному из вышеперечисленных условий,
                    // выводится сообщение о неверном формате ввода.
                } else {
                    System.out.println("Неверный формат ввода");
                }
            }
        }
    }
}