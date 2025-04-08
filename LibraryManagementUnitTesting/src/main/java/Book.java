import java.time.LocalDate;  
import java.util.Objects;
  
public class Book {  
    private String title;  
    private String author;  
    private boolean isCheckedOut;  
    private LocalDate dueDate;  
  
    public Book(String title, String author) {  
        this.title = title;  
        this.author = author;  
        this.isCheckedOut = false;  
    }  
  
    public String getTitle() {  
        return title;  
    }  
  
    public String getAuthor() {  
        return author;  
    }  
  
    public boolean isCheckedOut() {  
        return isCheckedOut;  
    }  
  
    public LocalDate getDueDate() {  
        return dueDate;  
    }  
  
    public void checkOut(int daysToDue) {  
        isCheckedOut = true;  
        dueDate = LocalDate.now().plusDays(daysToDue);  
    }  
  
    public void returnBook() {  
        isCheckedOut = false;  
        dueDate = null;  
    }  

    public void setDueDate(LocalDate dueDate) {  
        if (isCheckedOut) {  
            this.dueDate = dueDate; // Set the due date if the book is checked out  
        } else {  
            throw new IllegalStateException("Cannot set due date for a book that is not checked out.");  
        }  
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && 
               Objects.equals(author, book.author);
    }

    public int hashCode() {
        return Objects.hash(title, author);
    }

}  