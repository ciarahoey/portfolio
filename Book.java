package part01;

public class Book {
	//classify the variables needed for a book
	private String title;
	private String author;
	private String isbn;
	private BookType type;
	private int edition;
	private String summary;
	private double price;
	
	//book constructor class
	public Book(String title, String author, String isbn, BookType type, 
			int edition, String summary, double price) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.type = type;
		this.edition = edition;
		this.summary = summary;
		this.price = price;
	}
		// create the setters which include validation to ensure books can be initialised correctly
		public void setTitle(String title) {
		if(title == null || title.length() < 5 || title.length() > 40) {
			//error handling in the case of inappropriate input
			throw new IllegalArgumentException("The title must be between 5 and 40 characters.");
		}
			this.title = title;
		
	}
		
		public void setAuthor(String author) {
		if(author == null || author.length() < 5 || author.length() > 40) {
			throw new IllegalArgumentException("The author's name must be between 5 and 40 characters.");
		}
			this.author = author;
		}
		
		
		public void setIsbn(String isbn) {
		if(isbn == null || !isbn.matches("\\d{10}")) { //ensuring ISBN matches the correct format
			throw new IllegalArgumentException("ISBN must be 10 digits(0...9).");
			
		}
			this.isbn = isbn;
		}
		
		public void setType(BookType type) {
		if (type == null) {
			throw new IllegalArgumentException("The book type cannot be null.");
		}
			this.type = type;
		
		}
		
		public void setEdition(int edition) {
		if(edition < 1) {
			throw new IllegalArgumentException("The edition must be at least 1.");
		}
			this.edition = edition;
	
		}
		
		public void setSummary(String summary) {
		if(summary == null || summary.length() < 20 || summary.length() > 150) {
			throw new IllegalArgumentException("Summary must be between 20 and 150 characters.");
		}
			this.summary = summary;
		}
		
		
		public void setPrice(double price) {
		if (price <0) {
			throw new IllegalArgumentException("Price cannot be negative.");
		}
			
			this.price = price;	
	}
	// getters to ensure data can be called when necessary	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public BookType getType() {
		return type;
	}

	public int getEdition() {
		return edition;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public double getPrice() {
		return price;
	}
	// toString method for neater output as a string
	@Override
	public String toString() {
		return "\nBook Title: "+title+" \nAuthor: "+author+" \nIsbn: "+isbn+" \nType: "+type+" \nEdition: "+edition+" \nSummary: "+summary+" \nPrice: £"+price;
	}
	}
	
	
	

