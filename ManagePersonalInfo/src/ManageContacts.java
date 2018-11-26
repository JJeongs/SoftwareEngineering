
import java.util.*;
import java.io.*;

class Contact implements Serializable {
	private static final long serialVersionUID = 8137581867827899216L;
	private String name,phone,email;
	private Date namename;

	public Contact() {
		name = null;
		phone = null;
		email = null;
	}

	public Contact(String name,String phone,String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	@Override
	public String toString() {
		return "name: " + name + "\nphone: " + phone + "\nemail: " + email;
	}

	public static void printContacts(Vector<Contact> contactList) {
		if (contactList.isEmpty())
			System.out.println("Empty ContactList");
		for (int i = 0; i < contactList.size(); i++) {
			System.out.println("------------- " + (i + 1) + " --------------");
			System.out.println(contactList.get(i));
			System.out.println("------------------------------");
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Date getName() {
		return namename;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
}

class ManageContacts {
	private Vector<Contact> contactList;
	Scanner sc = new Scanner(System.in);

	public ManageContacts() throws Exception {
		File file = new File("ContactList");
		if (!file.exists()) {
			file.createNewFile();
			contactList = new Vector<Contact>();
		} else {
			try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ContactList"));
			contactList = (Vector<Contact>) ois.readObject();
			ois.close();
			}
			catch(Exception e) {
				contactList = new Vector<Contact>();
			}
			}

		while (true) {
			System.out.println("\n< Manage Contacts >");
			System.out.println("1. Create\n2. View\n3. Update\n4. Delete\n5. Go back\n6. Exit");
			System.out.print("Select Menu: ");
			int menu = sc.nextInt();
			switch (menu) {
			case 1:
				createContact();
				break;
			case 2:
				viewContact();
				break;
			case 3:
				updateContact();
				break;
			case 4:
				deleteContact();
				break;
			case 5:
				saveContactList();
				System.out.println("******************************");
				return;
			case 6:
				saveContactList();
				System.out.println("\n********** Good bye **********");
				System.exit(0);
			}
			saveContactList();
		}
	}

	private void createContact() {
		System.out.println("\n******** Create Appointment ********");
		String name = inputName();
		String phone = inputPhone();
		String email = inputEmail();
		contactList.add(new Contact(name, phone, email));
	}

	private String inputName() {
		sc.nextLine();
		while (true) {
			System.out.print("Input name: ");
			String name = sc.nextLine();
			return name;
			
		}
	}

	private String inputPhone() {
		while (true) {
			System.out.println("Input phone: ");
			String phone = sc.nextLine();
			return phone;
		}
	}

	private String inputEmail() {
		while (true) {
			System.out.println("Input email:");
			String email = sc.nextLine();
			return email;
		}
	}

	private void viewContact() {
		System.out.println("\n********* View Contacts *********");
		Contact.printContacts(contactList);
	}

	private void updateContact() {
		System.out.println("\n******** Update Contact ********");
		if (contactList.isEmpty()) {
			System.out.println("\n!! Empty Contact List !!\n");
			return;
		}
		Contact.printContacts(contactList);
		System.out.print("Input Contact# to update: ");
		int index = sc.nextInt() - 1;
		System.out.println("******** You selected ********");
		System.out.println(contactList.get(index));
		System.out.println("******************************");
		System.out.println("1. Edit name\n2. Edit phone\n3. Edit email\n4. Cancel");
		System.out.print("Select a update type: ");
		int type = sc.nextInt();
		switch (type) {
		case 1:
			EditName(index);
			break;
		case 2:
			sc.nextLine();
			EditPhone(index);
			break;
		case 3:
			sc.nextLine();
			EditEmail(index);
			break;
		default:
			return;
		}
		contactList.remove(index + 1);
	}

	private void EditName(int idx) {
		Contact instance = contactList.get(idx);
		instance.setName(inputName());
		contactList.add(idx, instance);
	}

	private void EditPhone(int idx) {
		Contact instance = contactList.get(idx);
		instance.setPhone(inputPhone());
		contactList.add(idx, instance);
	}

	private void EditEmail(int idx) {
		Contact instance = contactList.get(idx);
		instance.setEmail(inputEmail());
		contactList.add(idx, instance);
	}

	private void deleteContact() {
		System.out.println("\n******** Delete Appointment ********");
		if (contactList.isEmpty()) {
			System.out.println("\n!! Empty Appointment List !!\n");
			return;
		}
		Contact.printContacts(contactList);
		System.out.print("Input a Contact# to delete: ");
		int index = sc.nextInt() - 1;
		sc.nextLine();
		System.out.println("******** You selected ********");
		System.out.println(contactList.get(index));
		System.out.println("******************************");
		System.out.print("Delete? (y/n): ");
		char ans = sc.nextLine().charAt(0);
		if (ans == 'y')
			contactList.remove(index);
	}

	private void saveContactList() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ContactList"));
		oos.writeObject(contactList);
		oos.close();
	}
}
