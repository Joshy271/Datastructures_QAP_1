import java.util.Scanner;

public class LibraryCatalog {
    private static final String[] titles = {
            "To Kill A Mockingbird", "The Hunger Games", "The Catcher In The Rye", "The Outsiders", "Varjak Paw"
    };
    private static final String[] authors = {
            "Harper Lee", "Suzanne Collins", "J.D. Salinger", "S.E. Hinton", "S.F. Said"
    };
    private static final String[] isbns = {
            "000001", "000002", "000003", "000004", "000005"
    };
    private static final boolean[] availability = {
            true, true, true, true, true
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("Welcome to the Library Catalog System");

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> searchForBook(scanner);
                case 2 -> checkoutBook(scanner);
                case 3 -> returnBook(scanner);
                case 4 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Search for a book");
        System.out.println("2. Checkout a book");
        System.out.println("3. Return a book");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void searchForBook(Scanner scanner) {
        System.out.print("Enter search term (Title, Author, or ISBN): ");
        String searchTerm = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < titles.length; i++) {
            // Check if the current book matches the search term (case-insensitive)
            if (titles[i].equalsIgnoreCase(searchTerm) || authors[i].equalsIgnoreCase(searchTerm) || isbns[i].equals(searchTerm)) {
                System.out.println("Book Found:");
                System.out.println("Title: " + titles[i]);
                System.out.println("Author: " + authors[i]);
                System.out.println("ISBN: " + isbns[i]);
                System.out.println("Availability: " + (availability[i] ? "Available" : "Checked out"));
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    private static void checkoutBook(Scanner scanner) {
        System.out.print("Enter the ISBN of the book you want to checkout: ");
        String isbn = scanner.nextLine();

        for (int i = 0; i < isbns.length; i++) {
            // Check if the entered ISBN matches a book's ISBN
            if (isbns[i].equals(isbn)) {
                if (availability[i]) {
                    availability[i] = false;
                    System.out.println("You have successfully checked out the book: " + titles[i]);
                } else {
                    System.out.println("Sorry, the book is already checked out.");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter the ISBN of the book you want to return: ");
        String isbn = scanner.nextLine();

        for (int i = 0; i < isbns.length; i++) {
            // Check if the entered ISBN matches a book's ISBN
            if (isbns[i].equals(isbn)) {
                if (!availability[i]) {
                    availability[i] = true;
                    System.out.println("You have successfully returned the book: " + titles[i]);
                } else {
                    System.out.println("This book is already in the library.");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }
}
