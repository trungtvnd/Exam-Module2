import java.util.Scanner;

public class ContactMenu {
    private static final Scanner sc = new Scanner(System.in);

    public void runContact() {
        ContactManagement contactManagement = new ContactManagement();
        int choice = 0;
        do {
            System.out.println("-------------------\n");
            System.out.println("============MENU============");
            System.out.println("1. Display data");
            System.out.println("2. Add Contact");
            System.out.println("3. Update data");
            System.out.println("4. Delete Data");
            System.out.println("5. Search Data");
            System.out.println("6. Read from File");
            System.out.println("7. Write to File");
            System.out.println("8. Exit");
            System.out.println("In put your choice (by number integer): ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                while (choice < 0 || choice > 8) {
                    System.out.println("you can choose from 0 to 7, please choose again");
                    choice = sc.nextInt();
                }
            } catch (Exception e) {
                System.err.println("Please choose a Number");
                runContact();
            }

            switch (choice) {
                case 1:
                    contactManagement.display();
                    break;
                case 2:
                    contactManagement.creatContact();
                    break;
                case 3:
                    contactManagement.editContact();
                    break;
                case 4:
                    contactManagement.deleteContact();
                    break;
                case 5:
                    System.out.println("Input Name");
                    String name = sc.nextLine();
                    contactManagement.searchContactByNameOrPhone(name);
                    break;
                case 6:
                    contactManagement.writeContactToFile(ContactManagement.PATH_CONTACT);
                    break;
                case 7:
                    contactManagement.readContactFromFile(ContactManagement.PATH_CONTACT);
                    break;

            }

        } while (choice != 8);
    }

}
