package part01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class QUBLibrary {
	private Library library;
	private Scanner scanner;
	private Menu mainMenu;
	
	
	public QUBLibrary( ) {
		library = new Library();
		scanner = new Scanner(System.in);
		
		String[] menuOptions = {
				"List all Books",
				"List Books by their status",
				"Add a Book",
				"Remove a Book",
				"Borrow a Book",
				"Return a Book",
				"Display Ranked List",
				"Exit"
		};
		
		mainMenu = new Menu("Qub Library - Main Menu", menuOptions);
		inputLibrary();
		
	}
	
	
	
	protected void inputLibrary() {
		library.add(new LibraryBook("The Bell Jar", "Sylvia Plath", "1234567890", BookType.FICTION, 1, "Plath’s seminal novel about a young female writer’s ambitions and experiences amidst the impermeable misogyny of 1950s society moves from razor sharp humour to stunning insights into depression and mental illness. ", 9.99, "1234567890.jpg"));
		library.add(new LibraryBook("Orbital", "Samantha Harvey", "2345678901", BookType.FICTION, 2,"Winner of the Booker Prize 2024\n"
				+ "Combining an urgent message about the state of our planet - and us humans - with luminous hope and a refusal to sink into despair, the author of former Book of the Month The Western Wind delivers an affectionate, deeply human saga of six astronauts in space reflecting on life back down on Earth.", 9.99, "2345678901.jpg"));
		library.add(new LibraryBook("Sapiens: A brief History of Humankind.", "Yuval Harari", "3456789012", BookType.NON_FICTION, 3, "This book explores the history of human civilization, from early hominins to modern society, combining history, anthropology, and science.", 12.99, "3456789012.jpg"));
		library.add(new LibraryBook("The Oxford English Dictionary", "Sir James Murray", "4567890123", BookType.REFERENCE, 4, "It is a comprehensive dictionary that provides definitions, word origins, pronunciations, and historical usage examples. It is widely used for academic, professional, and personal reference.", 7.99, "4567890123.jpg"));
		library.add(new LibraryBook("Mad Woman", "Bryony Gordon", "5678901234", BookType.NON_FICTION, 5, "Bryony Gordon reassesses everything she thought she knew about mental health in this insightful, fearless and brilliantly witty reflection on the eternal quest for a 'happy life.", 20.00, "5678901234.jpg"));
	}
	
	
	public void start() {
		boolean running = true;
		while (running) {
			mainMenu.display();
			int choice = mainMenu.getChoice();
	
			
			switch(choice) {
			case 1: 
				listAllBooks();
				break;
			case 2: 
				listBooksByStatus();
				break;
			case 3:
				addBook();
				break;
			case 4:
				removeBook();
				break;
			case 5:
				borrowBook();
				break;
			case 6:
				returnBook();
				break;
			case 7:
				displayRankedList();
				break;
			case 8:
				running = false;
				System.out.println("Exiting the System...Goodbye!");
				return;
			default:
				// default is already handled in Menu Class
				break;
		}
	}
}
	
	
	private void listAllBooks() {
		LibraryBook[] books = library.list();
		for (LibraryBook book : books) {
			System.out.println(book);
		}
	}
	private void listBooksByStatus() {
		try  {
			System.out.print("Enter status (AVAILABLE, ON_LOAN, WITHDRAWN): ");
			BookStatus status = BookStatus.valueOf(scanner.nextLine().toUpperCase());
			LibraryBook[] books = library.list(status);
			for (LibraryBook book : books) {
				System.out.println(book);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid status entered. Try again.");
		}
	}
	
	private void addBook() {	
		try {
			System.out.print("Enter title: ");	
			String title = scanner.nextLine();
			System.out.print("Enter Author: ");
			String author = scanner.nextLine();
			System.out.print("Enter ISBN: ");
			String isbn = scanner.nextLine();
			System.out.print("Enter book type (FICTION, NON_FICTION, REFERENCE");
			BookType type = BookType.valueOf(scanner.nextLine().toUpperCase());
			System.out.print("Enter Edition: ");
			int edition = scanner.nextInt();
			System.out.print("Enter Summary: ");
			String summary = scanner.nextLine();
			System.out.print("Enter Price: ");
			double price = scanner.nextDouble();
			System.out.print("Enter Cover Image (URL): ");
			String image = scanner.nextLine();
	
			LibraryBook book = new LibraryBook(title, author, isbn, type, edition, summary, price, image);
			library.add(book);
			System.out.println("Book added successfully!"); // for successful input of a new book
			
		} catch (IllegalArgumentException | InputMismatchException e) {
			System.out.println("Invalid input. Please try again."); // for unsuccessful input
			scanner.nextLine();
		}
}
	private void removeBook() {
		try {
		System.out.print("Enter the Book ID to remove: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		LibraryBook book = library.search(id);
		if(book != null && book.getStatus() != BookStatus.ON_LOAN) {
			book.setStatus(BookStatus.WITHDRAWN);
			System.out.println("Book marked as withdrawn."); //successful removal of a book
			
		} else {
			System.out.println("Book not found or currently on loan."); // error - book does not exist or not on loan
			
		}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid book ID."); //unsuccessful removal of a book 
		}
	}
	
	private void borrowBook() {
		try {
		System.out.print("Enter book ID to borrow: ");
		int id = scanner.nextInt(); //takes user input
		scanner.nextLine();
		if(library.borrowBook(id)) {
			System.out.println("Book borrowed successfully!"); // book borrowed successfully
			
		} else {
			System.out.println("Book not foud or not on loan."); // error
			
		}
	} catch (InputMismatchException e) {
		System.out.println("Invalid input. Please enter a valid book ID."); // unsuccessful borrowing attempt
		scanner.nextLine();
	}
}
	
	private void returnBook() {
		try {
		System.out.print("Enter book ID to return: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		if (library.returnBook(id)) {
			System.out.println("Book returned successfully!");
		} else {
			System.out.println("Book not found or not out on loan."); 
		}
	} catch (InputMismatchException e) {
		System.out.println("Invalid input. Please enter a valid book ID.");
		scanner.nextLine(); //clear scanner
	}
}
	
	private void displayRankedList() {
		LibraryBook[] books  = library.mostPopular(); //using most popular method and refining it for user friendly output
		System.out.println("Most Popular Books:");
		for(LibraryBook book : books) {
			System.out.println(book.getTitle() + " by " + book.getAuthor() + " - Borrowed " + book.getLoanCount() + " times");
			
		}
	}
	
	public static void main(String[] args) {
		QUBLibrary qubLibrary = new QUBLibrary();
		qubLibrary.start();
	}
}
