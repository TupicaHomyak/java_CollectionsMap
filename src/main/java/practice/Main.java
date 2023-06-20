package practice;
import java.util.Scanner;
import java.util.Set;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        //объект PhoneBook в качестве параметра.
        while (true) {
            //основной цикл будет продолжаться бесконечно,
            // пока пользователь не введет команду для выхода из программы.
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("LIST")) {
                //Если введена команда "LIST", вызывается метод handleListCommand(phoneBook)
                handleListCommand(phoneBook);//обрабатывает и выводит список контактов.
            } else {
                //Если введенная строка не является  "LIST", вызывается метод handleInput
                handleInput(phoneBook, input, scanner);
            }
        }
    }
    private static void handleInput(PhoneBook phoneBook, String input, Scanner scanner) {
        //Получает объект PhoneBook, введенную пользователем строку input
        // и объект Scanner в качестве параметров.
        if (input.isEmpty()) {
            return;//Проверяет, если введена пустая строка, то возвращается из метода.
        }
        //Если введенная строка соответствует числу (\\d+),
        // вызывает метод handlePhoneNumberInput()
        if (input.matches("\\d+")) {
            handlePhoneNumberInput(phoneBook, input, scanner);
            //Если введенная строка соответствует имени ([a-zA-Zа-яА-Я]+),
            // вызывает метод handleNameInput()
        } else if (input.matches("[a-zA-Zа-яА-Я]+")) {
            handleNameInput(phoneBook, input, scanner);
        } else {
            //Если ни одно условие не выполняется, выводит сообщение "Неверный формат ввода".
            System.out.println("Неверный формат ввода");
        }
    }

    private static void handleListCommand(PhoneBook phoneBook) {
        Set<String> allContacts = phoneBook.getAllContacts();
        //Вызывает метод getAllContacts()  чтобы получить все контакты.
        if (allContacts.isEmpty()) {
            //Если список контактов пуст, выводит сообщение "Телефонная книга пуста.".
            System.out.println("Телефонная книга пуста.");
        } else {
            //Если список  не пуст, идет по нему в цикле и выводит каждый контакт на экран.
            for (String contact : allContacts) {
                System.out.println(contact);
            }
        }
    }

    private static void handlePhoneNumberInput(PhoneBook phoneBook, String phone, Scanner scanner) {
        String contact = phoneBook.getContactByPhone(phone);
        //Вызывает метод getContactByPhone(phone) у объекта phoneBook,
        // чтобы проверить наличие контакта с введенным номером.
        if (contact.isEmpty()) {//Если контакт не найден
            System.out.println("Такого номера нет в телефонной книге.");
            System.out.print("Введите имя абонента для номера \"" + phone + "\": ");
            String name = scanner.nextLine().trim();
            phoneBook.addContact(phone, name);//Вызывает метод addContact для добавления
            System.out.println("Контакт сохранен!");
        } else {
            //если такой номер уже есть, выдает контакт с этим номером
            System.out.println(contact);
        }
    }
    private static void handleNameInput(PhoneBook phoneBook, String name, Scanner scanner) {
        Set<String> contacts = phoneBook.getContactByName(name);
        //Вызывает метод getContactByName(name) у объекта phoneBook,
        // чтобы получить контакты с введенным именем.
        if (contacts.isEmpty()) {//Если контакт не найден
            System.out.println("Такого имени в телефонной книге нет.");
            System.out.print("Введите номер телефона для абонента \"" + name + "\": ");
            String phone = scanner.nextLine().trim();
            phoneBook.addContact(phone, name);//Вызывает метод addContact для добавления
            System.out.println("Контакт сохранен!");
        } else {
            //если такое имя уже есть, выдает контакт с этим именем
            for (String contact : contacts) {
                System.out.println(contact);
            }
        }
    }
}