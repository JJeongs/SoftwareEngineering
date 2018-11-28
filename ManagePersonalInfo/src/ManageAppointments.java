import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

class Appointment implements Serializable {
	private static final long serialVersionUID = 8137581867827899216L;
	private Date ddate;
	private String persons, location;
	private static SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");

	public Appointment() {
		ddate = null;
		persons = null;
		location = null;
	}
	
	public Appointment(Date ddate, String persons, String location) {
		this.ddate = ddate;
		this.persons = persons;
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Appointment's Date: " + date.format(ddate) + "\nPersons: " + persons + "\nLocation: " + location;
	}
	
	public static void printAppointments(Vector<Appointment> appointmentList) {
		if (appointmentList.isEmpty())
			System.out.println("Empty AppointmentList");
		for (int i = 0; i < appointmentList.size(); i++) {
			System.out.println("------------- " + (i + 1) + " --------------");
			System.out.println(appointmentList.get(i));
			System.out.println("------------------------------");
		}
	}
	
	public void setDate(Date ddate) {
		this.ddate = ddate;
	}
	public void setPersons(String persons) {
		this.persons = persons;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}

class ManageAppointments {
	private Vector<Appointment> appointmentList;
	Scanner sc = new Scanner(System.in);
	
	public ManageAppointments() throws Exception {	
		File file = new File("appointmentList");
		if (!file.exists()) {
			file.createNewFile();
			appointmentList = new Vector<Appointment>();
		}
		else {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("appointmentList"));
			appointmentList = (Vector<Appointment>)ois.readObject();
			ois.close();
		}
		
		while (true) {
			System.out.println("\n< Manage Appointments >");
			System.out.println("1. Create\n2. View\n3. Update\n4. Delete\n5. Go back\n6. Exit");
			System.out.print("Select Menu: ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:	createAppointment(); break;
			case 2: viewAppointment(); break;
			case 3: updateAppointment(); break;
			case 4: deleteAppointment(); break;
			case 5: saveAppointmentList(); System.out.println("******************************");return;
			case 6: saveAppointmentList(); System.out.println("\n********** Good bye **********"); System.exit(0);
			}
			saveAppointmentList();
		}
	}
	
	private void createAppointment() {
		System.out.println("\n******** Create Appointment ********");
		Date ddate = inputDate();
		String persons = inputPersons();
		String location = inputLocation();
		appointmentList.add(new Appointment(ddate, persons, location));
	}
	
	private Date inputDate() {
		sc.nextLine();
		while (true) {
			try {
				System.out.print("Input date(yyyy/MM/dd): ");
				SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
				String ddate = sc.nextLine();
				return date.parse(ddate);
			}
			catch(Exception e) {
				System.out.println("\n!! Invaild date format !!\n");
			}
		}
	}
	
	private String inputPersons() {
		while (true) {
			System.out.println("Input Persons(you can input several people with commas):");
			String persons = sc.nextLine();
			return persons;
		}
	}
	
	private String inputLocation() {
		while (true) {
			System.out.println("Input Location:");
			String location = sc.nextLine();
			return location;
		}
	}
	
	private void viewAppointment() {
		System.out.println("\n********* View Appointments *********");
		Appointment.printAppointments(appointmentList);
	}
	
	private void updateAppointment() {
		System.out.println("\n******** Update Appointment ********");
		if (appointmentList.isEmpty()) {
			System.out.println("\n!! Empty Appointment List !!\n");
			return;
		}
		Appointment.printAppointments(appointmentList);
		int index;
		while(true) {
			System.out.print("Input Appointment# to update: ");
			index = sc.nextInt() - 1;
			if(index<appointmentList.size())
				break;
			else {
				System.out.println("error : input is invalid");
			}
		}
		
		System.out.println("******** You selected ********");
		System.out.println(appointmentList.get(index));
		System.out.println("******************************");
		System.out.println("1. Edit date\n2. Edit persons\n3. Edit location\n4. Cancel");
		System.out.print("Select a update type: ");
		int type = sc.nextInt();
		switch(type) {
		case 1: EditDate(index); break;
		case 2: sc.nextLine(); EditPersons(index); break;
		case 3: sc.nextLine(); EditLocation(index); break;
		default: return;
		}
		appointmentList.remove(index + 1);
	}
	
	private void EditDate(int idx) {
		Appointment instance = appointmentList.get(idx);
		instance.setDate(inputDate());
		appointmentList.add(idx, instance);
	}
	
	private void EditPersons(int idx) {
		Appointment instance = appointmentList.get(idx);
		instance.setPersons(inputPersons());
		appointmentList.add(idx, instance);
	}
	private void EditLocation(int idx) {
		Appointment instance = appointmentList.get(idx);
		instance.setLocation(inputLocation());
		appointmentList.add(idx, instance);
	}
	
	private void deleteAppointment() {
		System.out.println("\n******** Delete Appointment ********");
		if (appointmentList.isEmpty()) {
			System.out.println("\n!! Empty Appointment List !!\n");
			return;
		}
		Appointment.printAppointments(appointmentList);
		int index;
		while(true) {
			System.out.print("Input a Appointment# to delete: ");
			index = sc.nextInt() - 1; sc.nextLine();
			if(index<appointmentList.size())
				break;
			else {
				System.out.println("error : index is invalid");
			}
		}
		System.out.println("******** You selected ********");
		System.out.println(appointmentList.get(index));
		System.out.println("******************************");
		while(true) {
			System.out.print("Delete? (y/n): ");
			char ans = sc.nextLine().charAt(0);
			if (ans == 'y' || ans == 'n') {
				if (ans == 'y') {
					appointmentList.remove(index);
				}
				break;
			}
			else {
				System.out.println("Input is invalid");
			}
		}
	}
	
	private void saveAppointmentList() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("appointmentList"));
		oos.writeObject(appointmentList);
		oos.close();
	}
}