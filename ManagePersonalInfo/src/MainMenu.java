import java.util.*;

public class MainMenu {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("************ Hello ***********");
		while (true) {
			System.out.println("\n< Main Menu >");
			System.out.println("1. Manage contacts\n2. Manage To-Do list\n3. Manage appointments\n4. Manage notes\n5. Exit");
			System.out.print("Select Menu: ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1: new ManageContacts(); break;
			case 2: new ManageToDoList().printToDoMenu(); break;
			case 3: new ManageAppointments(); break;
			case 4: new ManageNotes().printNotesMenu(); break;
			case 5: System.out.println("\n********** Good bye **********"); System.exit(0);
			}
		}
	}

}