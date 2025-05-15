package part02;

import java.io.File;
import console.Console;
import part01.Library;
import part01.LibraryBook;
import part01.QUBLibrary;
import part01.BookType;
import part01.BookStatus;
import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class QUBLibraryUpdated  { //defining library, scanner and console
	private static Library library = new Library();
	private static Scanner scanner = new Scanner(System.in);
	private Console console;
	
	public QUBLibraryUpdated() {
		
		console = new Console(true);
		setUpConsole();
		inputLibrary();
	
	}

	
	
	public static void main(String[] args) {
		QUBLibraryUpdated app = new QUBLibraryUpdated();
		app.run();
	}
	
	private void run() {
		boolean running = true;
		
		while (running) {
			displayMenu(); // displays menu to user
			int input = getInput();
			
			switch(input) { //leads use to method that executes their desired choice
			case 1: 
				listBooks();
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
				showMostPopular();
				break;
			case 8:
				running = false; //menu displaying and allows user to leave
				
				console.println("Exiting the System...Goodbye!");
				return;
			default:
				console.setColour(Color.RED);
				console.println("Invalid choice. Please try again."); //to handle incorrect user input
				pauseAndContinue();
			}
		}
	}
	
	private void setUpConsole() { // what will be displayed for the user
		console.setSize(600,600);
		console.setVisible(true);
		console.setLocation(100,100);
		console.setBgColour(Color.BLACK);
		console.setColour(Color.WHITE);
		console.setFont(new Font("Courier", Font.BOLD, 18));
	}
	
	protected void inputLibrary() { //inputting the book options
		library.add(new LibraryBook ("The Bell Jar", "Sylvia Plath", "1234567890", BookType.FICTION, 1, "Plath’s seminal novel about a young female writer’s ambitions and experiences amidst the impermeable misogyny of 1950s society moves from razor sharp humour to stunning insights into depression and mental illness. ", 9.99, "1234567890.jpg"));
		library.add(new LibraryBook ("Orbital", "Samantha Harvey", "2345678901", BookType.FICTION, 2,"Winner of the Booker Prize 2024\n"
				+ "Combining an urgent message about the state of our planet - and us humans - with luminous hope and a refusal to sink into despair, the author of former Book of the Month The Western Wind delivers an affectionate, deeply human saga of six astronauts in space reflecting on life back down on Earth.", 9.99, "2345678901.jpg"));
		library.add(new LibraryBook ("Sapiens: A brief History of Humankind.", "Yuval Harari", "3456789012", BookType.NON_FICTION, 3, "This book explores the history of human civilization, from early hominins to modern society, combining history, anthropology, and science.", 12.99, "3456789012.jpg"));
		library.add(new LibraryBook ("The Oxford English Dictionary", "Sir James Murray", "4567890123", BookType.REFERENCE, 4, "It is a comprehensive dictionary that provides definitions, word origins, pronunciations, and historical usage examples. It is widely used for academic, professional, and personal reference.", 7.99, "4567890123.jpg"));
		library.add(new LibraryBook ("Mad Woman", "Bryony Gordon", "5678901234", BookType.NON_FICTION, 5, "Bryony Gordon reassesses everything she thought she knew about mental health in this insightful, fearless and brilliantly witty reflection on the eternal quest for a 'happy life.", 20.00, "5678901234.jpg"));
	}
	
	
	private void displayMenu() { //details of menu output
	console.clear();
	console.setColour(Color.RED);
	console.println("++~~~~~~~~~~~~~~~~~~~~~~~~~~++");
	console.setColour(Color.WHITE);
	console.println(" Welcome to the QUB Library! ");
	console.setColour(Color.RED);
	console.println("++~~~~~~~~~~~~~~~~~~~~~~~~~~++");
	console.setColour(Color.WHITE);
	console.println("1. List all books");
	console.println("2. List books by status");
	console.println("3. Add a book");
	console.println("4. Remove a books");
	console.println("5. Borrow a book");
	console.println("6. Return a book");
	console.println("7. Show Most Popular Books");
	console.println("8. Exit");


	
	
	
	}

	private int getInput() { //getting the user's choice
		console.setColour(Color.RED);
		console.println("Enter Your Choice: ");
		try {
			String input = console.readLn();
			return Integer.parseInt(input);
			
		} catch (NumberFormatException e) {
			console.setColour(Color.RED);
			console.println("Invalid input. Please enter a number."); //to handle inappropriate input
			return -1;
		}
	}
	
	private void displayBookCover(LibraryBook book) { //to show the book cover image
		if (book == null || book.getImage() == null || book.getImage().isEmpty()) {
			console.setColour(Color.RED);
			console.println("No book cover available for this book.");
			return;
		}
		
		//printing out the book cover on MAC
		String imgFilename = book.getImage();
		String imgPath = "Images/" + imgFilename;
		ImageIcon img = new ImageIcon(imgPath);
		
		
		console.print(img);	
	}
	
	private void listBooksByStatus() { //listing available books by their status
		try {
		console.println("Enter the status of the books you would like to view (AVAILABLE,ON_LOAN, WITHDRAWN etc)");
		String statusInput = console.readLn().toUpperCase();
		BookStatus status = BookStatus.valueOf(statusInput);
		
		LibraryBook[] books = library.list(status);
		if(books.length==0) {
			console.setColour(Color.RED);
			console.println("No books found with status: "+ status);
			
		} else {
			for (LibraryBook book : books) {
				displayBook(book);
			}
		}
	} catch (IllegalArgumentException e) {
		console.setColour(Color.RED);
		console.println("Invalid status entered.");
	}
		pauseAndContinue();
	}
	
	private void listBooks() { //listing all books, no constraints
		
	
		console.clear();
		LibraryBook[] books = library.list();
		
		if(books.length == 0) {
			console.setColour(Color.RED);
			console.println(" No books available in the library."); //for null library
			
			
		} else {
			console.setColour(Color.GREEN);
			console.println("\n========== Available Books ==========");
			for (LibraryBook book : books) {
				displayBook(book);
			}
		}
	
			pauseAndContinue();
	}

	
	private void displayBook(LibraryBook book) { //formatted output of books
	
	
		console.setColour(Color.WHITE);
		String line = "======================================";
		console.print("\n" + line);
		console.println("\nTitle: " + book.getTitle());
		console.println(line);
		console.print(String.format("\n| %-12s | %-20s |", "Author", book.getAuthor()));
		console.print(String.format("\n| %-12s | %-20s |", "Type", book.getType()));
		console.print(String.format("\n| %-12s | %-20s |", "Edition", book.getEdition()));
		console.print(String.format("\n| %-12s | %-20s |", "Price", book.getPrice()));
		console.print(String.format("\n| %-12s | %-20s |", "Loan Count", book.getLoanCount()));
	
		
		if( book.getStatus() == BookStatus.AVAILABLE) {
			console.setColour(Color.GREEN);
			
	} else {
		console.setColour(Color.RED);
	
	}
	console.print(String.format("\n| %-12s | %-20s |", "Status", book.getStatus()));
	console.print("\n"+ line + "\n");
	console.setColour(Color.WHITE);
	console.println("Cover Image:");
	displayBookCover(book);
	console.println("\n");
	
	}
		private void borrowBook() { //to enable a book to be taken out of the library
			
			console.println("Enter the book ID to borrow: ");
			int id = getInput();
			if(library.borrowBook(id)) {
				console.setColour(Color.GREEN);
				console.println("Book borrowed successfully!");
			} else {
				console.setColour(Color.RED);
				console.println("Book is not available or the ID is incorrect.");
			}
			pauseAndContinue();
		}
		
		private void returnBook() { //to enable a book to be re-entered into the library
			console.println("Enter the book ID to return: ");
			int id = getInput();
			if(library.returnBook(id)) {
				console.setColour(Color.GREEN);
				console.print("Book returned successfully!");
			} else {
				console.setColour(Color.RED);
				console.print("This book is not currently on loan.");
			}
			pauseAndContinue();
		}
		
		private void addBook() { //to add a new book to the library
		
		try {	
		console.clear();
		console.print("\nAdd a new Book");
		
		console.print("\nEnter title: ");
		String title = console.readLn();
		
		console.print("\nEnter author: ");
		String author = console.readLn();
		
		console.print("\nEnter ISBN (10 digit code): ");
		String isbn = console.readLn();
		
		console.print("\nEnter Book type in capitals (FICTION, REFERENCE, etc): ");
		BookType type = BookType.valueOf(console.readLn().toUpperCase()); //to handle user input by converting lower case to upper case
		
		console.print("\nEnter edition number: ");
		int edition = Integer.parseInt(console.readLn());
		
		console.print("\nEnter summary ( 20-150 characters): ");
		String summary = console.readLn();
		
		console.print("\nEnter price: £");
		String priceInput = console.readLn();
		if(priceInput.startsWith("£")) {
			priceInput = priceInput.substring(1);
		}
		double price = Double.parseDouble(priceInput);
		
		console.print("\nEnter cover image filename (Must be inside Images folder): ");
		String image = console.readLn();
		
		LibraryBook book = new LibraryBook(title, author, isbn, type, edition, summary, price, "Images/" + image);
		
		if (library.add(book)) {
			console.setColour(Color.GREEN);
			console.println("Book added successsfully!"); //correct input detected
			
		} else {
			console.setColour(Color.RED);
			console.print("Failed to add book. Check details and try again."); //for incorrect input
		}
		}  catch (Exception e) {
			console.setColour(Color.RED);
			console.println("Error adding book: "+ e.getMessage()); //allows the user to see the exact error
		}
		pauseAndContinue();
	}
		
		private void removeBook() { //to remove a book from the library
			
			console.print("Enter the book ID to remove: ");
			int id = getInput();
			if(library.remove(id)) {
				console.setColour(Color.GREEN);
				console.print("Book removed successfully!"); //correct id entered
				
			} else {
				console.setColour(Color.RED);
				console.println("Failed to remove book."); //for incorrect input meaning book is still in library
			}
			pauseAndContinue();
		}
		private void showMostPopular() { //shows books that have been borrowed the most
			console.clear();
			console.println("These are the most popular books: ");
			LibraryBook[] popularBooks = library.mostPopular();
			
			if (popularBooks.length == 0) {
				console.setColour(Color.RED);
				console.println("No books have been borrowed yet.");
			} else {
				for(LibraryBook book : popularBooks) {
					displayBook(book);
				}
			}
			pauseAndContinue();
		}
		
		private void pauseAndContinue() { //to facilitate ease of user input - allows menu to reappear after a selection has been made
			console.println("\nPress Enter to continue...");
			console.readLn();
			
		}
		


}
