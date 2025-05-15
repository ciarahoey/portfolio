package part01;
import java.util.ArrayList;
public class Library {
	private ArrayList<LibraryBook> books;
	
	
	public Library() {
		this.books = new ArrayList<>();
	}
	
	public boolean borrowBook(int id) {
		LibraryBook book = search(id);
		if(book == null) {
			// in the case of a book not existing in the system
			return false;
		}
		
		
			return book.checkout(); // use checkout method from Lendable interface
		}
		
	
	public boolean returnBook(int id) {
		LibraryBook book = search(id);
		if(book == null) {
			// for null book
			return false;
		}
		// successful returning of book
			return book.checkIn();
	
	}
	
	public boolean add(LibraryBook book) {
		if (book == null) {
			throw new IllegalArgumentException("Error: Cannot add a null book."); // for null book
		}
			if(book.getPrice() <=0) {
			throw new IllegalArgumentException("Error: Book price must be greater than 0"); // Inappropriate pricing of book
		
	}
		return books.add(book); // successfully added book
	
	
}
	public LibraryBook search(int id) {
		for(LibraryBook book : books) {
			if(book.getId() == id) {
				return book; // if correct id is entered, book is returned
			}
		}
		return null; // if incorrect id is entered
	}
	
	public boolean remove(int id) {
		LibraryBook book = search(id);
		if(book == null) {
			System.out.println("Error: Book not found."); // in case of book not existing/ has already been removed
			return false;
		}
		if(book.getStatus() == BookStatus.ON_LOAN) {
			System.out.println("Cannot remove. This book is currently being borrowed"); // in case of book being used
			return false;
		}
		return books.remove(book); //successful removal of book
	}
	
	public LibraryBook[] list() {
		if(books.isEmpty()) {
			System.out.println("Warning: No books are availible in the library."); // null library
		}
		
		return books.toArray(new LibraryBook[0]);
	}
	
	public LibraryBook[] list(BookStatus stat) { //listing books grouped by their status
		ArrayList<LibraryBook> filteredBooks = new ArrayList<>();
		for(LibraryBook book : books) {
			if (book.getStatus() == stat) {
				filteredBooks.add(book);
			}
		}
		if (filteredBooks.isEmpty()) {
			System.out.println("Warning: No books found with the status: "+ stat); // in case of no books with the entered status
	}
		return filteredBooks.toArray(new LibraryBook[0]);
		
	}

	public LibraryBook[] mostPopular() {
	int n = books.size();
	if( n == 0) {
		System.out.println("Warning: No books available to rank."); // for empty library
		return new LibraryBook[0];
		
	}
	
	LibraryBook[] bookArray = books.toArray(new LibraryBook[0]); //bubble sort most popular books using loan count
	for(int i = 0; i<n-1; i++) {
		for (int j = 0; j < n -i - 1; j++) {
			if (bookArray[j].getLoanCount() < bookArray[j+1].getLoanCount()) {
			LibraryBook temp = bookArray[j]	;
			bookArray[j] = bookArray[j+1];
			bookArray[j+1] = temp;
			
			}
			}
	}
	return bookArray;
	}
}
	
	

