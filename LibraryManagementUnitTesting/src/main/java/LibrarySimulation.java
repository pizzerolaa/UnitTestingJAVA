import java.util.ArrayList;
import java.util.List;

public class LibrarySimulation {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("1984", "George Orwell"));

        int numOfPatrons = 5;
        List<Thread> patronThreads = new ArrayList<>();

        for (int i = 0; i < numOfPatrons; i++) {
            Patron patron = new Patron("Patron " + i, library);
            library.addPatron(patron);
            Thread thread = new Thread(patron);
            patronThreads.add(thread);
            thread.start();
        }

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Thread thread : patronThreads) {
            thread.interrupt();
        }
    }
}
