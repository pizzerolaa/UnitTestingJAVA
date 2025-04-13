import java.time.LocalDate;  
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;  
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue; 
  
public class Library {  
    private List<Book> books;  
    private List<Patron> patrons;  
    private HashMap<Patron, Book> checkedOutBooks;  
  
    public Library() {  
        books = new ArrayList<>();  
        patrons = new ArrayList<>();  
        checkedOutBooks = new HashMap<>();  
    }  
  
    // public void addBook(Book book) {  
    //     if (!books.contains(book)) {  
    //         books.add(book);  
    //     } else {  
    //         System.out.println("Book already exists in the library."); 
    //     }
    // }  
  
    public void removeBook(Book book) {  
        books.remove(book);  
    }  
  
    // public void addPatron(Patron patron) {  
    //     patrons.add(patron);  
    // }  
  
    // public boolean checkOutBook(Patron patron, Book book, int daysToDue) {  
    //     if (books.contains(book) && !book.isCheckedOut()) {  
    //         book.checkOut(daysToDue);  
    //         checkedOutBooks.put(patron, book);  
    //         return true;  
    //     }  
    //     return false;  
    // }  
  
    // public boolean returnBook(Patron patron) {  
    //     Book book = checkedOutBooks.get(patron);  
    //     if (book != null) {  
    //         book.returnBook();  
    //         checkedOutBooks.remove(patron);  
    //         return true;  
    //     }  
    //     return false;  
    // }  
  
    public double calculateFine(Patron patron) {  
        Book book = checkedOutBooks.get(patron);  
        if (book != null && book.isCheckedOut()) {  
            LocalDate today = LocalDate.now();  
            long daysOverdue = ChronoUnit.DAYS.between(book.getDueDate(), today);  
            if (daysOverdue > 0) {  
                return daysOverdue * 1.00; // $0.50 fine per day overdue  
            }  
        }  
        return 0.0;  
    }  
  
    public List<Book> listAvailableBooks() {  
        List<Book> availableBooks = new ArrayList<>();  
        for (Book book : books) {  
            if (!book.isCheckedOut()) {  
                availableBooks.add(book);  
            }  
        }  
        return availableBooks;  
    }  
  
    public List<Patron> listPatrons() {  
        return patrons;  
    }

    public synchronized boolean checkOutBook(Patron patron, Book book, int daysToDue) {
        if (!books.contains(book)) {
            System.out.println("Book not available in the library: " + book.getTitle());
            return false;
        }
        if (book.isCheckedOut()) {
            System.out.println("Book already checked out: " + book.getTitle());
            return false;
        }
        book.checkOut(daysToDue);
        checkedOutBooks.put(patron, book);
        System.out.println("Book checked out: " + book.getTitle() + " to " + patron.getName());
        return true;
    }

    public synchronized boolean returnBook(Patron patron) {
        Book book = checkedOutBooks.get(patron);
        if (book != null) {
            book.returnBook();
            checkedOutBooks.remove(patron);
            return true;
        }
        return false;
    }

    public synchronized void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
        } else {
            System.out.println("Book already exists in the library.");
        }
    }

    public synchronized void addPatron(Patron patron) {
        if (!patrons.contains(patron)) {
            patrons.add(patron);
        } else {
            System.out.println("Patron already exists in the library.");
        }
    }

    public synchronized Map<Patron, Book> getCheckedOutBooks() {
        return new HashMap<>(checkedOutBooks);
    }

    public synchronized void printLibraryStatus() {
        System.out.println("\n=== Library Status ===");
        System.out.println("Available Books: " + listAvailableBooks().size());
        System.out.println("Checked Out Books: " + checkedOutBooks.size());
        System.out.println("===================\n");
    }

    private Map<Book, Queue<Patron>> waitlist = new HashMap<>();

    public synchronized void addToWaitingList(Patron patron, Book book) {
        waitlist.computeIfAbsent(book, k -> new LinkedList<>()).add(patron);
        System.out.println(patron.getName() + " added to waiting list for " + book.getTitle());
    }

    public synchronized void notifyNextInWaitingList(Book book) {
        Queue<Patron> waitingList = waitlist.get(book);
        if (waitingList != null && !waitingList.isEmpty()) {
            Patron nextPatron = waitingList.poll();
            System.out.println("Notifying " + nextPatron.getName() + " that " + book.getTitle() + " is available");
        }
    }
}  
