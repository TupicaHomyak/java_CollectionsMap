package practice;
import java.util.*;
public class PhoneBook {
    private Map<String, List<String>> contacts;
    //поле типа Map, которое хранит контакты в виде пар "имя - список номеров"
    public PhoneBook() {
        contacts = new HashMap<>(); //В конструкторе класса создается пустая хэш-карта
        //ключом является имя абонента, а значением - список номеров телефонов.
    }
    //метод addContact используется для добавления нового контакта в телефонную книгу.
    public void addContact(String phone, String name) {
        //программа проверяет корректность введенных данных
        if (!isValidPhone(phone) || !isValidName(name)) {
            System.out.println("Неверный формат ввода");
            return;
        }
        //
        for (Map.Entry<String, List<String>> entry : contacts.entrySet()) {
            String existingName = entry.getKey();
            List<String> phoneNumbers = entry.getValue();
            //При добавлении контакта с уже существующим номером, владелец номера перезаписывается.
            if (phoneNumbers.contains(phone)) {
                phoneNumbers.remove(phone);
                if (phoneNumbers.isEmpty()) {
                    contacts.remove(existingName);
                    //Если у предыдущего владельца не остается других номеров, его имя удаляется из книги.
                }
            }
        }
        //
        List<String> phoneNumbers = contacts.getOrDefault(name, new ArrayList<>());
        if (!phoneNumbers.contains(phone)) {
            phoneNumbers.add(phone);
        }
        //
        contacts.put(name, phoneNumbers);
    }
    public String getContactByPhone(String phone) {
        for (Map.Entry<String, List<String>> entry : contacts.entrySet()) {
            String name = entry.getKey();
            List<String> phoneNumbers = entry.getValue();
            if (phoneNumbers.contains(phone)) {
                return name + " - " + String.join(", ", phoneNumbers);
            }
        }
        //ищет контакт по номеру телефона и возвращает информацию о контакте в формате "имя - список номеров".
        return "";//Если контакт не найден, возвращается пустая строка.
    }
    public Set<String> getContactByName(String name) {
        List<String> phoneNumbers = contacts.getOrDefault(name, new ArrayList<>());
        Set<String> formattedContacts = new TreeSet<>(phoneNumbers);
        Set<String> formattedContactsOutput = new TreeSet<>();
        if (!formattedContacts.isEmpty()) {
            String contact = name + " - " + String.join(", ", formattedContacts);
            formattedContactsOutput.add(contact);
        }
        //ищет контакты по имени и возвращает информацию о контактах в формате "имя - список номеров".
        return formattedContactsOutput;//Если контакт не найден, возвращается пустая строка.
    }
    public Set<String> getAllContacts() {
        //возвращает все контакты из телефонной книги в виде множества строк
        Set<String> allContacts = new TreeSet<>();
        for (Map.Entry<String, List<String>> entry : contacts.entrySet()) {
            String name = entry.getKey();
            List<String> phoneNumbers = entry.getValue();
            //каждая из которых представляет контакт в формате "имя - список номеров"
            String formattedContact = name + " - " + String.join(", ", phoneNumbers);
            allContacts.add(formattedContact);
        }
        return allContacts;
    }
    //метод, который проверяет, что имя состоит только из букв.
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Zа-яА-Я]+");
    }
    //проверяет, что номер состоит только из цифр.
    private boolean isValidPhone(String phone) {
        return phone.matches("\\d+");
    }
    //
}