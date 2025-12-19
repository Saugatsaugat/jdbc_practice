import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Books;
import model.Users;
import model.UsersBooks;
import service.BooksService;
import service.UsersBooksService;
import service.UsersService;

public class LibrarySystem {
    static Scanner sc = new Scanner(System.in);
    static BooksService booksService = new BooksService();
    static UsersService usersService = new UsersService();
    static UsersBooksService usersBooksService = new UsersBooksService();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        boolean flag = true;
        while (flag) {
            displayIntro();

            // Menu List
            System.out.println("Select:\n1. Users\n2. Books\n3. Records\n");

            try {
                int input = sc.nextInt();
                sc.nextLine();

                // switch case implementation
                switch (input) {
                    case 1:
                        aboutUsers();
                        break;
                    case 2:
                        aboutBooks();
                        break;
                    case 3:
                        aboutRecords();
                        break;
                    default:
                        System.out.println("Wrong input");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("\nDo you want to start again, type 'y' for yes?");
            String userInp = sc.nextLine();
            if (userInp.equalsIgnoreCase("y")) {
                flag = true;
            } else {
                flag = false;
            }

        }
        sc.close();
    }

    public static void displayIntro() {
        System.out.println(
                "********************************************************************************************\n" +
                        "********************************************************************************************\n"
                        +
                        "*********\t\t\tWelcome to Library Management System\t\t************" +
                        "\n*********\t\t\tby\t\t\t\t\t\t************\n" +
                        "*********\t\t\tSAUGAT\t\t\t\t\t\t************\n" +
                        "********************************************************************************************\n"
                        +
                        "********************************************************************************************\n\n");
    }

    /*
     * This method is about users.
     */
    public static void aboutUsers() {
        System.out.println("\n\t\t\t ABOUT USER\n");
        try {
            System.out.println("Choose\t 1. List All\t2. Find \t3. Create User\nSelect: ");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 1:
                    listAllUser();
                    break;
                case 2:
                    findUser();
                    break;
                case 3:
                    createUser();
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * This method is about books.
     */
    public static void aboutBooks() {
        System.out.println("\n\t\t\t ABOUT BOOK\n");
        try {
            System.out.println("Choose\t 1. List All\t2. Search \t3. Create Book\nSelect: ");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 1:
                    listAllBook();
                    break;
                case 2:
                    findBook();
                    break;
                case 3:
                    createBook();
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * This method is about Records.
     */
    public static void aboutRecords() {
        System.out.println("\n\t\t\t ABOUT RECORDS\n");
        try {
            System.out.println("Choose\t 1. List All\t2. Find \t3. Create Record\nSelect: ");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 1:
                    listAllRecord();
                    break;
                case 2:
                    findRecord();
                    break;
                case 3:
                    createRecord();
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * About User
     */
    public static void listAllUser() {
        List<Users> usersList = usersService.getAll();
        displayAllUser(usersList);
    }

    public static void findUser() {
        List<Users> usersList = new ArrayList<>();
        try {
            System.out.println("Enter user's email: ");
            String userEmail = sc.nextLine();
            Users user = usersService.findUserByEmail(userEmail);
            if (user != null) {
                usersList.add(user);
            } else {
                System.out.println("No Data");
            }
            displayAllUser(usersList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createUser() {
        try {
            System.out.println("Enter name: ");
            String name = sc.nextLine();

            System.out.println("Enter email: ");
            String email = sc.nextLine();

            System.out.println("Enter Joined Date(YYYY-MM-DD): ");
            String joinedDate = sc.nextLine();

            Users user = new Users();
            user.setName(name);
            user.setEmail(email);
            String[] values = joinedDate.split("-");
            user.setJoinedDate(LocalDate.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
                    Integer.parseInt(values[2])));

            usersService.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateUser() {

    }

    // Display all the list of users
    public static void displayAllUser(List<Users> usersList) {
        int count = 1;
        System.out.println("\nS.N.\tName\tEmail\tJoinedDate\n");
        for (Users user : usersList) {
            System.out.println(count + "\t" + user.getName() + "\t" + user.getEmail() + "\t" + user.getJoinedDate());
            count++;
        }
    }

    /*
     * About Book
     */
    public static void listAllBook() {
        List<Books> booksList = booksService.getAll();
        displayAllBook(booksList);
    }

    public static void findBook() {
        try {
            System.out.println("Enter book's title: ");
            String bookTitle = sc.nextLine();
            List<Books> bookList = booksService.findBookByTitle(bookTitle);
            if (!bookList.isEmpty()) {
                displayAllBook(bookList);
            } else {
                System.out.println("No Data");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createBook() {
        try {
            System.out.println("Enter title: ");
            String title = sc.nextLine();

            System.out.println("Enter author: ");
            String author = sc.nextLine();

            System.out.println("Enter category: ");
            String category = sc.nextLine();

            System.out.println("Enter quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter Added Date(YYYY-MM-DD): ");
            String addedDate = sc.nextLine();

            Books book = new Books();
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setQuantity(quantity);

            String[] values = addedDate.split("-");
            book.setAddedDate(LocalDate.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
                    Integer.parseInt(values[2])));

            booksService.create(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Display all the list of books
    public static void displayAllBook(List<Books> booksList) {
        int count = 1;
        System.out.println("\nS.N.\tId\tTitle\tAuthor\tCategory\tQuantity\tAdded Date\n");
        for (Books book : booksList) {
            System.out.println(count + "\t" +book.getId()+"\t"+ book.getTitle() + "\t" + book.getAuthor() + "\t" + book.getCategory()
                    + "\t" + book.getQuantity() + "\t" + book.getAddedDate());
            count++;
        }
    }

    /*
     * About Records
     */
    public static void listAllRecord() {
        List<UsersBooks> recordList = usersBooksService.getAll();
        displayAllRecord(recordList);
    }

    public static void findRecord() {
        try{
            System.out.println("\n1. By Due Date\t2. By Return Date\t3. By Issue Date\nSelect: ");
            int inp = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter date(YYYY-MM-DD): ");
            String filterDate = sc.nextLine();

            switch(inp){
                case 1:
                    findRecord("due_date", filterDate);
                    break;
                case 2:
                    findRecord("return_date", filterDate);
                    break;
                case 3:
                    findRecord("issue_date", filterDate);
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void findRecord(String by, String filterDate) {
        try {
            String[] values = filterDate.split("-");
            LocalDate filteredDate = LocalDate.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
            List<UsersBooks> recordList = usersBooksService.findRecord(by, filteredDate);
            if (!recordList.isEmpty()) {
                displayAllRecord(recordList);
            } else {
                System.out.println("No Data");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createRecord() {
        try {
            System.out.println("Enter user's email: ");
            String email = sc.nextLine();

            System.out.println("Enter BookId: ");
            int bookId = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter issue date(YYYY-MM-DD): ");
            String issueDate = sc.nextLine();

            System.out.println("Enter due date(YYYY-MM-DD): ");
            String dueDate = sc.nextLine();

            // System.out.println("Enter return date(YYYY-MM-DD): ");
            // String returnDate = sc.nextLine();

            UsersBooks record = new UsersBooks();
            Users user = usersService.findUserByEmail(email);

            record.setUserId(user.getId());
            record.setBookId(bookId);

            String issueDateValues[] = issueDate.split("-");
            record.setIssueDate(LocalDate.of(Integer.parseInt(issueDateValues[0]), Integer.parseInt(issueDateValues[1]), Integer.parseInt(issueDateValues[2])));

            String dueDateValues[] = dueDate.split("-");
            record.setDueDate(LocalDate.of(Integer.parseInt(dueDateValues[0]), Integer.parseInt(dueDateValues[1]), Integer.parseInt(dueDateValues[2])));

            // String returnDateValues[] = returnDate.split("-");
            // record.setReturnDate(LocalDate.of(Integer.parseInt(returnDateValues[0]), Integer.parseInt(returnDateValues[1]), Integer.parseInt(returnDateValues[2])));

            usersBooksService.create(record);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Display all the list of records
    public static void displayAllRecord(List<UsersBooks> recordList) {
        int count = 1;
        System.out.println("\nS.N.\tUser\tBook\tIssue Date\tDue Date\tReturn Date\n");
        for (UsersBooks record : recordList) {
            Users user = usersService.get(record.getUserId());
            Books book = booksService.get(record.getBookId());

            System.out.println(count + "\t" + user.getName() + "\t" + book.getTitle() +"\t" + record.getIssueDate() + "\t" + record.getDueDate()+"\t" +record.getReturnDate());
            count++;
        }
    }

}
