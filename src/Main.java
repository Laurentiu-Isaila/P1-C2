import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	static Scanner sc = new Scanner(System.in);
	public static GuestList startEvent() {
		System.out.println("Bun venit, introduceti numarul de locuri disponibile: ");
		int locuri = sc.nextInt();
		return new GuestList(locuri);
	}
	
	public static void help() {
		System.out.println("help         - Afiseaza aceasta lista de comenzi\r\n" + 
				"add          - Adauga o noua persoana (inscriere)\r\n" + 
				"check        - Verifica daca o persoana este inscrisa la eveniment\r\n" + 
				"remove       - Sterge o persoana existenta din lista\r\n" + 
				"update       - Actualizeaza detaliile unei persoane\r\n" + 
				"guests       - Lista de persoane care participa la eveniment\r\n" + 
				"waitlist     - Persoanele din lista de asteptare\r\n" + 
				"available    - Numarul de locuri libere\r\n" + 
				"guests_no    - Numarul de persoane care participa la eveniment\r\n" + 
				"waitlist_no  - Numarul de persoane din lista de asteptare\r\n" + 
				"subscribe_no - Numarul total de persoane inscrise\r\n" + 
				"search       - Cauta toti invitatii conform sirului de caractere introdus\r\n" + 
				"quit         - Inchide aplicatia");
	}
	
	
	
	public static Guest add() {
		System.out.println("Se adauga o noua persoana...");
		String lastName;
		String firstName;
		String email;
		String phone;
		
		
		System.out.print("Introduceti numele de familie: ");
		lastName = sc.next();
		System.out.println("Introduceti preumele: ");
		firstName = sc.next();
		System.out.println("Introduceti email");
		email = sc.next();
		System.out.println("Introduceti numarul de telefon sub forma +07xxxxxxxx: ");
		phone = sc.next();
		
		return new Guest(lastName, firstName, email, phone);
		
	}

	public static void main(String[] args) {
		GuestList event = startEvent();
		boolean quit = false;
		while(!quit) {
			System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
			String command = sc.next();
			
			switch (command) {
			
				case "available":
					System.out.println("Sunt " + event.available() + " locuri libere.");
					break;
					
				case "subscribe_no":
					System.out.println("Sunt " + (event.getWaiting() + event.getNoGuest()) + " persoane inscrise la eveniment. ");
					
				case "waitlist_no":
					System.out.println("Sunt " + event.getWaiting() + " persoane pe lista de asteptare. ");
					
				case "guests_no":
					System.out.println("Sunt " + event.getNoGuest() + " persoane care participa la eveniment." );
			
				case "guests":
					event.getParticipants();
					break;
				
				case "waitlist":
					event.getWaitingList();
					break;
			
				case "add":
					System.out.println("Adauga criteriul de verificare: \n1 - Nume \n2 - Email \n3 - Telefon ");
					String checkOption = sc.next();
					
					switch (checkOption) {
						case "1":
							System.out.println("Introduceti nume pentru verificare:  \nNume:  ");
							String lastName = sc.next();
							System.out.println("\nPrenume: ");
							String firstName = sc.next();
							if(event.checkName(lastName, firstName)) {
								System.out.println("Introduceti email pentru verificare: ");
								String email = sc.next();
								if(event.checkEmail(email)) {
									System.out.println("Sunteti deja inscris! ");
									
								}else {
									event.addGuest(add());							
								}								 
							}else {
								event.addGuest(add());								
							}
							break;
						case "2":
							System.out.println("Introduceti email pentru verificare: ");
							if(event.checkEmail(sc.next())) {
								System.out.println("Sunteti deja inscris! ");
							}else {
								event.addGuest(add());
							}
							break;
						case "3":
							System.out.println("Introduceti numarul de telefon pentru verificare: ");
							if(event.checkPhone(sc.next())) {
								System.out.println("Sunteti deja inscris!");
							}else {
								event.addGuest(add());
							}
							
							break;
							
							default: 
								System.out.println("Optiune invalida!");								
								continue;
					}	
					break;
					
				case "remove":
					System.out.println("Adauga criteriul de verificare: \n1 - Nume \n2 - Email \n3 - Telefon ");
					String checkOp = sc.next();
					switch(checkOp) {
						case "1":
						case "2":
						case "3":
						event.removeGuest(checkOp);
						break;
						
						default:
							System.out.println("Optiune invalida! ");
							continue;
					}
					break;
					
				case "check":
					System.out.println("Adauga criteriul de verificare: \n1 - Nume \n2 - Email \n3 - Telefon ");
					String checkO = sc.next();
					switch (checkO) {
					case "1":
						System.out.println("Introduceti nume pentru verificare:  \nNume:  ");
						String lastName = sc.next();
						System.out.println("\nPrenume: ");
						String firstName = sc.next();
						if(event.checkName(lastName, firstName)) {
							System.out.println("Introduceti email pentru verificare: ");
							String email = sc.next();
							if(event.checkEmail(email)) {
								System.out.println("Sunteti deja inscris! ");
								
							}
						}else {
							System.out.println("Persoana nu este inscrisa");
						}
						break;
						
					case "2":
						System.out.println("Introduceti email pentru verificare: ");
						if(event.checkEmail(sc.next())) {
							System.out.println("Sunteti deja inscris! ");
						}else {
							System.out.println("Persoana nu este inscrisa");
						}
						break;
						
					case "3":
						System.out.println("Introduceti numarul de telefon pentru verificare: ");
						if(event.checkPhone(sc.next())) {
							System.out.println("Sunteti deja inscris!");
						}else {
							System.out.println("Persoana nu este inscrisa");
						}	
					default: 
						System.out.println("Optiune invalida!");								
						continue;
						
					}	
					break;
					
				
					
				case "quit":
					quit = true;
					break;
					
				case "help":
					help();
					break;
					
				default:
					System.out.println("Nu este o comanda valida! ");
					break;
			
			
			
			}
		}
		System.out.println(event.getParticipants());
		System.out.println(event.getWaitingList());
			
		
		
		
		



	}

}
