import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactManagement {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static final String PHONE_REGEX = "^\\([0-9]{2,4}\\)\\-\\(0[0-9]{9,10}\\)$";
    private final Scanner sc = new Scanner(System.in);
    public static final String PATH_CONTACT = "C:\\TRUNGTV\\HOC TAP\\CODEGYM\\MODULE2\\FILE CODE\\EXAM MODULE 2\\data\\saveFileContact.csv";
    private final ArrayList<Contact> contactArrayList;

    public ContactManagement() {
        this.contactArrayList = readContactFromFile(PATH_CONTACT);
    }

    public void writeContactToFile(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                for (Contact contact : contactArrayList) {
                    bw.write(contact.getPhoneNumber() + "," + contact.getGroup()
                            + "," + contact.getName() + "," + contact.getGender() + ","
                            + contact.getAddress() + "," + contact.getBirth() + "," + contact.getEmail()
                            + "\n");
                }
                bw.flush();
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Contact> readContactFromFile(String path) {
        createFolder();
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        File file = new File(PATH_CONTACT);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] strings = line.split(",");
                contactArrayList.add(new Contact(strings[0], strings[1], strings[2], strings[3], strings[4], Integer.parseInt(strings[5]), strings[6]));
            }
        } catch (Exception e) {
            System.err.println();
        }
        return contactArrayList;
    }

    public void creatContact() {
        System.out.println("Input information to create contact ");
        String phoneNumber = choosePhoneNumber();
        while (!checkDoublePhoneNumber(phoneNumber)) {
            System.out.println("Please try again");
            phoneNumber = choosePhoneNumber();
        }
        System.out.println("Input Group of Contact: ");
        String group = sc.nextLine();
        System.out.println("Input name: ");
        String name = sc.nextLine();
        String gender = chooseGender();
        while (gender.equals("")) {
            System.out.println("Invalid Gender, choose again");
            gender = chooseGender();
        }
        sc.nextLine();
        System.out.println("Input address");
        String address = sc.nextLine();

        System.out.println("Input birth :");
        int birth = sc.nextInt();
        sc.nextLine();
        String email = entryEmail();
        Contact contact = new Contact(phoneNumber, group, name, gender, address, birth, email);
        contactArrayList.add(contact);
        writeContactToFile(PATH_CONTACT);
    }


    public void editContact() {
        System.out.println("Input phoneNumber");
        String phone = sc.nextLine();
        Contact contactEdit;
        boolean flag = false;
        for (Contact contact : contactArrayList) {
            if (contact.getPhoneNumber().trim().equals(phone)) {
                flag = true;
                contactEdit = contact;
                System.out.println("Input new Group of Contact: ");
                contactEdit.setGroup(sc.nextLine());
                System.out.println("Input new name: ");
                contactEdit.setName(sc.nextLine());
                String gender = chooseGender();
                contactEdit.setGender(gender);
                sc.nextLine();
                System.out.println("Input address");
                contactEdit.setAddress(sc.nextLine());
                System.out.println("Input birth :");
                contactEdit.setBirth(sc.nextInt());
                contactEdit.setEmail(entryEmail());
                if (chooseGender().trim().equals("")) {
                    System.out.println("please input gender again");
                    return;
                }
                writeContactToFile(PATH_CONTACT);
            } else {
                flag = false;
            }
        }
        if (flag) {
            System.out.println("Edit successfully");
        } else {
            System.out.println("not Found");
        }
        sc.nextLine();

    }


    public void display() {
        System.out.printf("%-20S%-20S%-20s%-20S%-20S%-20S%-20S\n", "Phone Number", "Group", "Full Name", "Gender", "Address", "Birth", "Email");
        for (Contact contact : contactArrayList) {
            System.out.println(contact.toString());
        }
    }


    public String chooseGender() {
        System.out.println("Choose Gender: ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        int choice = sc.nextInt();
        if (choice == 1) {
            return "Male";
        }
        return "Female";

    }

    public String choosePhoneNumber() {
        String phoneNumber;
        while (true) {
            System.out.println("Input phoneNumber: ");
            String phoneEntry = sc.nextLine();
            if (!validatePhone(phoneEntry)) {
                System.out.println("Invalid PhoneNumber, please try again");
            } else {
                phoneNumber = phoneEntry;
                break;
            }
        }
        return phoneNumber;
    }

    public boolean checkDoublePhoneNumber(String phone) {
        try {
            for (Contact contact : contactArrayList) {
                if (contact.getPhoneNumber().equals(phone)) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.err.println();
        }
        return true;
    }

    public boolean validatePhone(String regex) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    public boolean validateEmail(String regex) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    public String entryEmail() {
        String email;
        while (true) {
            System.out.println("Input Email");
            String mail = sc.nextLine();
            if (!validateEmail(mail)) {
                System.out.println("Invalid Email, please try again");
            } else {
                email = mail;
                break;
            }
        }
        return email;
    }

    public void deleteContact() {
        System.out.println("Input Phone Number you want to delete");
        String phoneNumber = sc.nextLine();
        Contact deleteContact = null;
        for (Contact contact : contactArrayList) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                deleteContact = contact;
            }
        }
        if (deleteContact != null) {
            System.out.println("Do you really want to delete:");
            System.out.println("Y: Delete");
            System.out.println("Other character: Exit");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                contactArrayList.remove(deleteContact);
                writeContactToFile(PATH_CONTACT);
                System.out.println("Delete successfully");
            }
        } else {
            System.out.println("Not Found");
        }
    }


    public void searchContactByNameOrPhone(String keyword) {
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : contactArrayList) {
            if (validatePhoneOrName(keyword, contact.getPhoneNumber()) || validatePhoneOrName(keyword, contact.getName())) {
                contacts.add(contact);
            }
        }
        if (contacts.isEmpty()) {
            System.out.println(" Not Found !");
        } else {
            System.out.println("Result Search:");
            System.out.printf("%-20S%-20S%-20s%-20S%-20S%-20S%-20S\n", "Phone Number", "Group", "Full Name", "Gender", "Address", "Birth", "Email");
            contacts.forEach(System.out::println);
        }
    }

    public boolean validatePhoneOrName(String keyword, String regex) {
        keyword = keyword.toLowerCase();
        String PHONE_NAME_REGEX = ".*" + keyword + ".*";
        Pattern pattern = Pattern.compile(PHONE_NAME_REGEX);
        Matcher matcher = pattern.matcher(regex.toLowerCase());
        return matcher.matches();
    }

    public void createFolder() {
        File file = new File("C:\\TRUNGTV\\HOC TAP\\CODEGYM\\MODULE2\\FILE CODE\\EXAM MODULE 2\\data");
        boolean create = file.mkdirs();

    }


}
