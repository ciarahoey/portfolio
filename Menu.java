package part01;

import java.util.Scanner;

public class Menu {
	private String[] options;
	private Scanner scanner;
	private String title;
	
	public Menu(String title, String[] options) { //menu for easier application of design elements to output
		this.title = title;
		this.options = options;
		this.scanner = new Scanner(System.in);
	}
	
	public void display() {
		System.out.println("\n╔══════════════════════════════════════╗");
		System.out.printf("║ %-36s ║\n", title);
		System.out.println("╠══════════════════════════════════════╣");
		
		for (int i = 0; i < options.length; i++) {
			System.out.printf("║ %d. %-32s  ║\n", (i + 1), options[i]);
		}
		System.out.println("╠══════════════════════════════════════╣");
		System.out.println("╚══════════════════════════════════════╝");
		System.out.println("➡ Please enter your choice:");
	}
	
	public int getChoice() {
		int choice = -1;
		
		while (true) {
		try {
			choice = Integer.parseInt(scanner.nextLine().trim());
			
			if (choice >= 0 && choice <= options.length) {
				return choice;
				
			} else {
				System.out.println("⚠ Invalid choice. Please enter a number between 1 and " + options.length + ": ");
				
			}
			
			
		} catch (NumberFormatException e) {
			System.out.println("⚠ Invalid input. Please enter a number.");
		}
		}
	}
}
