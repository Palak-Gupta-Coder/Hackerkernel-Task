import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book{
    private  String title;
    private boolean isBorrowed;

    public Book(String title){
        this.title = title;
        this.isBorrowed = false;
    }

    public String getTitle(){
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow(){
        isBorrowed = true;
    }

    public void returnBook(){
        isBorrowed = false;
    }
}

class Library{
    private List<Book> books;

    public Library(){
        books = new ArrayList<>();
    }

    public void addBooks(String title){
        books.add(new Book(title));
        System.out.println("Book added: " + title);
    }

    public void displayBooks(){
        if(books.isEmpty()){
            System.out.println("The library is empty.");
        }else{
            System.out.println("Books in the library: ");
            for(Book book : books){
                System.out.println(book.getTitle() + (book.isBorrowed() ? "(Borrowed)" : "(Available)"));
            }
        }
    }

    public Book findBook(String title){
        for( Book book : books){
            if(book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void checkOutBook(String title){
        Book book = findBook(title);
        if(book != null && !book.isBorrowed()){
            book.borrow();
            System.out.println("You have checked out the book:  "+ title);
        }else{
            System.out.println("book not found or book is not available");
        }
    }

    public void returnBook(String title){
        Book book = findBook(title);
        if(book != null && book.isBorrowed()){
            book.returnBook();
            System.out.println("You have returned the book: " + title);
        }else{
            System.out.println("book is not found");
        }
    }
}

public class LibraraySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Library System menu : ");
            System.out.println("press 1. for adding a new book");
            System.out.println("press 2. Display all books");
            System.out.println("press 3. search for a book");
            System.out.println("press 4. Check out a book");
            System.out.println("press 5. return a book");
            System.out.println("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();


            switch (choice){
                case 1:
                System.out.println("Enter the title of the book: ");
                String newBookTitle = sc.nextLine();
                library.addBooks(newBookTitle);
                break;
                case 2:
                library.displayBooks();
                break;
                case 3:
                System.out.println("Enter the title of the book to search for: ");
                String searchTitle = sc.nextLine();
                Book foundBook = library.findBook(searchTitle);
                if(foundBook != null){
                    System.out.println("Book found: " + foundBook.getTitle() + (foundBook.isBorrowed() ? "(Borrowed)" : "(Available)"));
                }else{
                    System.out.println("book not found.");
                }
                break;
                case 4:
                System.out.println("Enter the title of the book to check out: ");
                String checkOutTitle = sc.nextLine();
                library.checkOutBook(checkOutTitle);
                break;
                case 5:
                System.out.println("Enter the title of the book to return: ");
                String returnTitle = sc.nextLine();
                library.returnBook(returnTitle);
                break;
                case 6:
                System.out.println("thank you for visiting.");
                System.exit(0);
                default:
                    System.out.println("invalid choice. Please try again");
            }
        }
    }

}
