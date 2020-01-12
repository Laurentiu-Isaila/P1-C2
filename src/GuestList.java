import java.util.ArrayList;
import java.util.Scanner;

public class GuestList {
	
	private int eventSpots;
	private int noGuest;
	private int waiting;
	ArrayList<Guest> waitingList;
	ArrayList<Guest> participants;
	public static Scanner sc = new Scanner(System.in);
	
	public ArrayList<Guest> getWaitingList() {
		return waitingList;
	}

	public ArrayList<Guest> getParticipants() {
		return participants;
	}

	public GuestList(int eventSpots) {
		this.eventSpots = eventSpots;
		this.waiting = 0;
		this.noGuest = 0;
		this.waitingList = new ArrayList<Guest>();
		this.participants = new ArrayList<Guest>();		
	}
	
	
	
	public int addGuest(Guest guest) {
		if( noGuest < eventSpots ) {
			this.participants.add(guest);
			noGuest ++;
			System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!.");
			return 0;
		} else if( noGuest >= eventSpots){
			this.waitingList.add(guest);
			System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " 
			+ (this.waitingList.indexOf(guest) + 1) + " . Te vom notifica daca un loc devine disponibil.");
			this.waiting++;
			return this.waitingList.indexOf(guest);
		} 			
		
		return -1;
	}
	
	
	
	public int getNoGuest() {
		return this.noGuest;
	}

	public int available() {
		return this.eventSpots - this.noGuest; 
	}	
		
	public int getWaiting() {
		return this.waiting;
	}

	public boolean checkName( String lastName, String firstName) {
		String fullName = lastName + " " + firstName;
		if(this.waiting > 0) {
			for(int i = 0; i < this.waitingList.size();i++) {
				if(fullName.equals(waitingList.get(i).getFullName())) {
					System.out.println("O persoana cu acest nume a fost gasita in lista de asteptare. "	);
					return true;
				}
			}
		}
		for(int i = 0; i < this.participants.size();i++) {
			if(fullName.equals(participants.get(i).getFullName())) {
				System.out.println("O persoana cu acest nume a fost gasita in lista evenimentului. ");
				return true;
			
			}
		}
		return false;
			
	}
	
	public boolean checkEmail(String email) {
		if(waiting > 0) {
			for(int i = 0; i < this.waitingList.size();i++) {
				if(email.equals(waitingList.get(i).getEmail())) {
					return true;			
				}
			}
		}
		for(int i = 0; i < this.participants.size();i++) {
			if(email.equals(participants.get(i).getEmail())) {
			return true;
			}
		}
		return false;
	}
	public boolean checkPhone(String phone) {
		if(waiting > 0) {
			for(int i = 0; i < this.waitingList.size();i++) {
				if(phone.equals(waitingList.get(i).getPhoneNumber())) {
					return true;			
				}
			}
		}
		for(int i = 0; i < this.participants.size();i++) {
			if(phone.equals(participants.get(i).getPhoneNumber())) {
			return true;
			}
		
		}
		return false;
	}
	
	
		
	
	
	private int removeByEmail(String email) {
		if(waiting > 0) {
			for(int i = 0; i < this.waitingList.size();i++) {
				if(email.equals(waitingList.get(i).getEmail())) {
					waitingList.remove(i);
					return 2;						
				}
			}
		}
		for(int i = 0; i < this.participants.size();i++) {
			if(email.equals(participants.get(i).getEmail())) {
				participants.remove(i);
				
				if(waiting > 0) {
				participants.add(waitingList.get(0));
				this.noGuest++;								
				waitingList.remove(0);
				return 1;
				}
			}
		}
			return 0;
	}
	
	private int removeByPhone(String phone) {
		if(waiting > 0) {
			for(int i = 0; i < this.waitingList.size();i++) {
				if(phone.equals(waitingList.get(i).getPhoneNumber())) {
					waitingList.remove(i);
					return 2;						
				}
			}
		}
		for(int i = 0; i < this.participants.size();i++) {
			if(phone.equals(participants.get(i).getPhoneNumber())) {
				participants.remove(i);				
				this.noGuest--;
				if(waiting > 0) {
				participants.add(waitingList.get(0));
				this.noGuest++;
				}				
				waitingList.remove(0);
				return 1;
			}
		}
			return 0;
	}
	
	private int removeByName(String lastNameFirstName) {
		int wlFound = 0;
		int partFound = 0;
		if(waiting > 0) {
			for(int i = 0; i < this.waitingList.size();i++) {			
				if(lastNameFirstName.equals(waitingList.get(i).getFullName())) {				
					wlFound++;						
				}
			}
		}
		for(int i = 0; i < this.participants.size();i++) {
			if(lastNameFirstName.equals(participants.get(i).getFullName())) {
				partFound++;
				
			}
		}
		if(wlFound == 1) {
			for(int i = 0; i < this.waitingList.size();i++) {			
				if(lastNameFirstName.equals(waitingList.get(i).getFullName())) {				
				waitingList.remove(i);
				return 2;						
				}
			}
		}else if(wlFound > 1) {
		
			System.out.println("Au fost gasiti mai multi participanti cu acelasi nume in lista de asteptare, introduceti email pentru o verificare suplimentara: ");
			String email = sc.nextLine();
			this.removeByEmail(email);
			return 2;
		}
		if(partFound == 1) {
			for(int i = 0; i < this.participants.size();i++) {
				if(lastNameFirstName.equals(participants.get(i).getFullName())) {
					System.out.println(i);
					participants.remove(i);
				
					this.noGuest--;
					if(waiting > 0) {
						participants.add(waitingList.get(0));
						this.noGuest++;									
						waitingList.remove(0);
						return 1;
					}
				}
			}
		}else if(partFound > 1) {
			System.out.println("Au fost gasiti mai multi participanti cu acelasi nume, introduceti email pentru o verificare suplimentara: ");
			String email = sc.nextLine();
			this.removeByEmail(email);
			return 1;
		}
			return 0;
		
	}
	
	
	public boolean removeGuest(String nameEmailOrPhone) {
		boolean result = false;
		switch (nameEmailOrPhone.toLowerCase()) {
			case "2":
				System.out.println("Introduceti email: ");
				String email = sc.next();
				this.removeByEmail(email);
				int resultMail = this.removeByEmail(email);
				if(resultMail == 1 || resultMail == 2) {
					System.out.println("Participantul a fost scos din lista.");
					result = true;
				}
				break;
			case "3":
				System.out.println("Introduceti numarul de telefon: ");
				String phone = sc.next();
				this.removeByPhone(phone);
				int resultPhone = this.removeByEmail(phone);
				if(resultPhone == 1 || resultPhone == 2) {
					System.out.println("Participantul a fost scos din lista.");
					result = true;
										
				}
				break;
			case "1":
				System.out.println("Introduceti numele: ");
				String lastName = sc.next().toLowerCase();
				System.out.println("introduceti prenumele: ");
				String firstName = sc.next().toLowerCase();
				this.removeByName(lastName + " " + firstName);
				int resultName = this.removeByName(lastName + " " + firstName);
				if(resultName == 1 || resultName == 2) {
					System.out.println("Participantul a fost scos din lista.");
					result = true;
				}
				break;
				
				default:
					break;
		}
		return result;
		
		
	}
	
	

}
