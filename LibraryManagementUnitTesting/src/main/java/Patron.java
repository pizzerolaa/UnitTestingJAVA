import java.util.ArrayList;  
import java.util.List;
import java.util.Random;  
  
public class Patron implements Runnable {  
    private String name;  
    private List<Book> checkedOutBooks;
    private Library library;
    private Random random;

    public Patron(String name) {  
        this.name = name;  
        this.checkedOutBooks = new ArrayList<>();
    }
  
    public Patron(String name, Library library) {  
        this.name = name;
        this.library = library;
        this.checkedOutBooks = new ArrayList<>();
        this.random = new Random();
    }  
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(3000));

                List<Book> availableBooks = library.listAvailableBooks();
                if (!availableBooks.isEmpty()) {
                    Book bookToBorrow = availableBooks.get(random.nextInt(availableBooks.size()));
                    library.checkOutBook(this, bookToBorrow, 7);
                }

                Thread.sleep(random.nextInt(2000));

                if (!checkedOutBooks.isEmpty()) {
                    library.returnBook(this);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public String getName() {  
        return name;  
    }  
  
    public List<Book> getCheckedOutBooks() {  
        return checkedOutBooks;  
    }  
  
    // public void checkOutBook(Book book) {  
    //     checkedOutBooks.add(book);  
    // }  
  
    // public void returnBook(Book book) {  
    //     checkedOutBooks.remove(book);  
    // }  
  
    // public boolean hasCheckedOutBook(Book book) {  
    //     return checkedOutBooks.contains(book);  
    // }

    public synchronized void checkOutBook(Book book) {  
        checkedOutBooks.add(book);  
    }

    public synchronized void returnBook(Book book) {  
        checkedOutBooks.remove(book);  
    }

    public synchronized boolean hasCheckedOutBook(Book book) {  
        return checkedOutBooks.contains(book);  
    }
}  