package part01;
import java.io.File;



public class LibraryBook extends Book implements Lendable { //use of inheritance for this system
	//declaring variables
	private int id;
	private static int nextId =1;
	private BookStatus status;
	private String image;
	private int loanCount;
	// library book constructor
	public LibraryBook(String title, String author, String isbn, 
			BookType type, int edition, String summary, double price,
			String coverImage) {
		super(title, author, isbn, type, edition, summary, price);
		this.id = nextId++;
		this.status = BookStatus.AVAILABLE;
		this.image = coverImage;
		this.loanCount = 0;
	}
	
	
	public BookStatus getStatus() {
		return status;
	}
	
	public void setStatus(BookStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("Error: Status cannot be null.");
		}
		this.status  = status;
	}
	
	@Override
	public boolean checkout() {
		if(status == BookStatus.AVAILABLE) {
			status = BookStatus.ON_LOAN;
			loanCount++;
			return true; //book is available
		}
		System.out.println("Error: Book is not available for checkout."); // in case book is not available
		return false;
	}
	
	@Override
	public boolean checkIn() {
		if(status == BookStatus.ON_LOAN) {
			status = BookStatus.AVAILABLE;
			return true; //book is available
		}
		System.out.println("Error: Book is not currently on loan."); // in case book is not available
		return false;
	}
	
	public String getImage() {
		return image;
	}
	

	public void setImage(String image) {
		if(image  == null || image.trim().isEmpty()) {
			throw new IllegalArgumentException("Error: The Cover Image link cannot be empty.");
		}

		this.image = image;
	}
	
	public int getLoanCount() {
		return loanCount;
	}
	
	public void setLoanCount() {
		this.loanCount++; // increment loan count for every time book is borrowed
	}
	
	public int getId() {
		return id;
	}
	// toString for neater output
	@Override
	public String toString() {
	return super.toString()+"\nID: "+id+" \nStatus: "+status+"\nLoan Count: "+loanCount+
			"\nCover Image: "+image;
	}
	
	

}
