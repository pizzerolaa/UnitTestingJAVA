import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    // TC-LIB-001 - Add Book - Duplicate Book
    @Test
    public void testAddBookWithError() {
        Library library = new Library();
        Book book = new Book("Moby Dick", "Herman Melville");

        library.addBook(book);
        library.addBook(book);

        assertEquals(1, library.listAvailableBooks().size());
    }

    // TC-LIB-002 - Checkout - Already Checked Out Book
    @Test
    public void testCheckoutUnavailableBook() {
        Library library = new Library();
        Patron patron1 = new Patron("User A");
        Patron patron2 = new Patron("User B");
        Book book = new Book("1984", "George Orwell");

        library.addBook(book);
        library.addPatron(patron1);
        library.addPatron(patron2);

        library.checkOutBook(patron1, book, 3);
        boolean result = library.checkOutBook(patron2, book, 3);

        assertFalse(result, "Book is already checked out, second checkout should fail.");
    }

    // TC-LIB-003 - Return - Book never checked out
    @Test
    public void testReturnNonCheckedOutBook() {
        Library library = new Library();
        Patron patron = new Patron("John Smith");
        Book book = new Book("Hamlet", "William Shakespeare");

        library.addBook(book);
        library.addPatron(patron);

        boolean returned = library.returnBook(patron);

        assertFalse(returned, "Should not return a book that was never checked out.");
    }

    // TC-LIB-004 - Fine Calculation - Overdue 5 days
    @Test
    public void testCalculateFine() {
        Library library = new Library();
        Patron patron = new Patron("Late Patron");
        Book book = new Book("Overdue Book", "Author");

        library.addBook(book);
        library.addPatron(patron);
        library.checkOutBook(patron, book, 2);

        book.setDueDate(LocalDate.now().minusDays(5));

        double fine = library.calculateFine(patron);

        assertEquals(5.0, fine, "Expected fine for 5 days overdue is 5.0");
    }

    // TC-LIB-005 - List Books & Patrons
    @Test
    public void testListBooksAndPatrons() {
        Library library = new Library();

        Book book = new Book("Dune", "Frank Herbert");
        Patron patron = new Patron("Ana Torres");

        library.addBook(book);
        library.addPatron(patron);

        assertEquals(1, library.listAvailableBooks().size());
        assertEquals(1, library.listPatrons().size());
    }

    // TC-BOOK-001 - Create Book - Valid Book
    @Test
    public void testBookCreated() {
        Book book = new Book("Brave New World", "Aldous Huxley");

        assertEquals("Brave New World", book.getTitle());
        assertEquals("Aldous Huxley", book.getAuthor());
    }

    // TC-PATRON-001 - Register Patron - Valid Patron
    @Test
    public void testRegisterPatron() {
        Library library = new Library();
        Patron patron = new Patron("John Doe");

        library.addPatron(patron);

        assertTrue(library.listPatrons().contains(patron));
    }

    // test ya existentes en este archivo, creados por el profesor
    @Test
    public void testAddBook() {
        Library library = new Library();
        Book book = new Book("1984", "George Orwell");

        library.addBook(book);

        assertTrue(library.listAvailableBooks().contains(book));
    }

    @Test
    public void testCheckOutNonexistentBook() {
        Library library = new Library();
        Patron patron = new Patron("Jane Doe");

        library.addPatron(patron);
        Book nonexistentBook = new Book("Nonexistent", "Ghost");

        boolean result = library.checkOutBook(patron, nonexistentBook, 3);

        assertFalse(result, "Should not check out a book that does not exist in the library.");
    }

    @Test
    public void testCalculateFineAfterReturn() {
        Library library = new Library();
        Patron patron = new Patron("Alice Smith");
        Book book = new Book("Design Patterns", "Erich Gamma");

        library.addBook(book);
        library.addPatron(patron);

        library.checkOutBook(patron, book, 2);
        book.setDueDate(LocalDate.now().minusDays(2));
        library.returnBook(patron);

        double fineAfterReturn = library.calculateFine(patron);
        assertEquals(0, fineAfterReturn, "The fine should be zero after returning the book.");
    }
}
