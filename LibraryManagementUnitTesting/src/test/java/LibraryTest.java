import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
  
public class LibraryTest {  
  
    @Test  
    public void testAddBook() {  
        Library library = new Library();  
        Book book = new Book("1984", "George Orwell");  
        library.addBook(book);  
        assertTrue(library.listAvailableBooks().contains(book));  
    }  

    @Test
    public void testAddDuplicateBook() {
        Library library = new Library();
        Book book = new Book("Moby Dick", "Herman Melville");

        library.addBook(book);
        library.addBook(book);

        assertEquals(1, library.listAvailableBooks().size());
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
    public void testReturnBookNotCheckedOut() {
        Library library = new Library();
        Patron patron = new Patron("John Smith");
        Book book = new Book("Clean Code", "Robert C. Martin");

        library.addBook(book);
        library.addPatron(patron);

        boolean returned = library.returnBook(patron);

        assertFalse(returned, "Should not return a book that was never checked out.");
    }

    @Test
    public void testRegisterPatron() {
        Library library = new Library();
        Patron patron = new Patron("Carlos Ramirez");

        library.addPatron(patron);

        assertTrue(library.listPatrons().contains(patron));
    }

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
  
    @Test  
    public void testCalculateFineAfterReturn() {  
        // Setup  
        Library library = new Library();  
        Patron patron = new Patron("Alice Smith");  
        Book book = new Book("Design Patterns", "Erich Gamma");  
  
        library.addBook(book);  
        library.addPatron(patron);  
          
        // Check out for 2 days  
        library.checkOutBook(patron, book, 2);   
          
        // Simulate that 2 days have passed, and set the due date  
        book.setDueDate(LocalDate.now().minusDays(2));  
  
        // Return the book  
        library.returnBook(patron);   
  
        // Act: Calculate fine after returning  
        double fineAfterReturn = library.calculateFine(patron);   
  
        // Assert: No fine should be calculated after return  
        assertEquals(0, fineAfterReturn, "The fine should be zero after returning the book.");  
    }  
}  
 