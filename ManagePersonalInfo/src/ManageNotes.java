import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

class Note implements Serializable {
	private static final long serialVersionUID = 8137581867827899216L;
	private Date createDate;
	private String title, memo;
	private static SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
 	public Note() {
		createDate = null;
		title = null;
		memo = null;
	}
	
	public Note(String title, String memo) {
		this.title = title;
		this.createDate=new Date();
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "Title: "+ title + "\nCreate Date: " + date.format(createDate) + "\n" + memo;
	}
	
	
	public static void printNotes(Vector<Note> Notes) {
		if (Notes.isEmpty())
			System.out.println("Empty Note");
		for (int i = 0; i < Notes.size(); i++) {
			System.out.println("------------- " + (i + 1) + " --------------");
			System.out.println(Notes.get(i));
			System.out.println("------------------------------");
		}
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
 class ManageNotes {
	private Vector<Note> Notes;
	Scanner sc = new Scanner(System.in);
	
	public ManageNotes() throws Exception {	
		File file = new File("Notes");
		if (!file.exists()) {
			file.createNewFile();
			Notes = new Vector<Note>();
		}
		else {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Notes"));
			Notes = (Vector<Note>)ois.readObject();
			ois.close();
		}
		
		while (true) {
			System.out.println("\n< Manage Notes >");
			System.out.println("1. Create\n2. View\n3. Update\n4. Delete\n5. Go back\n6. Exit");
			System.out.print("Select Menu: ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:	create(); break;
			case 2: view(); break;
			case 3: update(); break;
			case 4: delete(); break;
			case 5: saveNotes(); System.out.println("******************************");return;
			case 6: saveNotes(); System.out.println("\n********** Good bye **********"); System.exit(0);
			}
			saveNotes();
		}
	}
	
	private void create() {
		System.out.println("\n******** Create Note ********");
		String title = inputTitle();
		String memo = inputMemo();
		Notes.add(new Note(title, memo));
	}
	
	private String inputTitle() {
		sc.nextLine();
		while (true) {
			System.out.println("Input title(up to 25 characters):");
			String title = sc.nextLine();
			if (title.length() > 25)
				System.out.println("\n!! You entered more then 25 characters !!\n");
			else
				return title;
		}
	}
	
	private String inputMemo() {
		System.out.println("Input memo:");
		String memo = sc.nextLine();
		return memo;
	}
	
	private void view() {
		System.out.println("\n********* View Note *********");
		Note.printNotes(Notes);
	}
	
	private void update() {
		System.out.println("\n******** Update Note ********");
		if (Notes.isEmpty()) {
			System.out.println("\n!! Empty Note !!\n");
			return;
		}
		Note.printNotes(Notes);
		System.out.print("Input a Note# to update: ");
		int index = sc.nextInt() - 1;
		System.out.println("******** You selected ********");
		System.out.println(Notes.get(index));
		System.out.println("******************************");
		System.out.println("1. Edit title\n2. Edit memo\n3. Cancel");
		System.out.print("Select a update type: ");
		int type = sc.nextInt();
		switch(type) {
		case 1: EditTitle(index); break;
		case 2: sc.nextLine(); EditMemo(index); break;
		default: return;
		}
		Notes.remove(index + 1);
	}
	
	private void EditTitle(int idx) {
		Note instance = Notes.get(idx);
		instance.setTitle(inputTitle());
		Notes.add(idx, instance);
	}
	
	private void EditMemo(int idx) {
		Note instance = Notes.get(idx);
		instance.setMemo(inputMemo());
		Notes.add(idx, instance);
	}
	
	private void delete() {
		System.out.println("\n******** Delete To-Do ********");
		if (Notes.isEmpty()) {
			System.out.println("\n!! Empty Note !!\n");
			return;
		}
		Note.printNotes(Notes);
		System.out.print("Input a Note# to delete: ");
		int index = sc.nextInt() - 1;	sc.nextLine();
		System.out.println("******** You selected ********");
		System.out.println(Notes.get(index));
		System.out.println("******************************");
		System.out.print("Delete? (y/n): ");
		char ans = sc.nextLine().charAt(0);
		if (ans == 'y')
			Notes.remove(index);
	}
	
	private void saveNotes() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Notes"));
		oos.writeObject(Notes);
		oos.close();
	}
} 