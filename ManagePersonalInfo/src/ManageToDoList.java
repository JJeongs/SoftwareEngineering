import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

class ToDo implements Serializable {
	private static final long serialVersionUID = 8137581867827899216L;
	private Date createDate, dueDate;
	private String description;
	private static SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");

	public ToDo() {
		createDate = null;
		dueDate = null;
		description = null;
	}
	
	public ToDo(Date dueDate, String description) {
		this.createDate = new Date();
		this.dueDate = dueDate;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Create Date: " + date.format(createDate) + "\nDue Date: " + date.format(dueDate) + "\n" + description;
	}
	
	public static void printToDoList(Vector<ToDo> toDoList) {
		if (toDoList.isEmpty())
			System.out.println("Empty To-Do List");
		for (int i = 0; i < toDoList.size(); i++) {
			System.out.println("------------- " + (i + 1) + " --------------");
			System.out.println(toDoList.get(i));
			System.out.println("------------------------------");
		}
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

class ManageToDoList {
	private Vector<ToDo> toDoList;
	Scanner sc = new Scanner(System.in);
	
	public ManageToDoList() throws Exception {	
		File file = new File("ToDoList");
		if (!file.exists()) {
			file.createNewFile();
			toDoList = new Vector<ToDo>();
		}
		else {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ToDoList"));
			toDoList = (Vector<ToDo>)ois.readObject();
			ois.close();
		}
		
		while (true) {
			System.out.println("\n< Manage To-Do List >");
			System.out.println("1. Create\n2. View\n3. Update\n4. Delete\n5. Go back\n6. Exit");
			System.out.print("Select Menu: ");
			int menu = sc.nextInt();
			switch(menu) {
			case 1:	createToDo(); break;
			case 2: viewToDo(); break;
			case 3: updateToDo(); break;
			case 4: deleteToDo(); break;
			case 5: saveToDoList(); System.out.println("******************************");return;
			case 6: saveToDoList(); System.out.println("\n********** Good bye **********"); System.exit(0);
			}
			saveToDoList();
		}
	}
	
	private void createToDo() {
		System.out.println("\n******** Create To-Do ********");
		Date date = inputDueDate();
		String description = inputDescription();
		toDoList.add(new ToDo(date, description));
	}
	
	private Date inputDueDate() {
		sc.nextLine();
		while (true) {
			try {
				System.out.print("Input due date(yyyy/MM/dd): ");
				SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
				String dueDate = sc.nextLine();
				return date.parse(dueDate);
			}
			catch(Exception e) {
				System.out.println("\n!! Invaild date format !!\n");
			}
		}
	}
	
	private String inputDescription() {
		while (true) {
			System.out.println("Input description(up to 50 characters):");
			String description = sc.nextLine();
			if (description.length() > 50)
				System.out.println("\n!! You entered more then 50 characters !!\n");
			else
				return description;
		}
	}
	
	private void viewToDo() {
		System.out.println("\n********* View To-Do *********");
		ToDo.printToDoList(toDoList);
	}
	
	private void updateToDo() {
		System.out.println("\n******** Update To-Do ********");
		if (toDoList.isEmpty()) {
			System.out.println("\n!! Empty To Do List !!\n");
			return;
		}
		ToDo.printToDoList(toDoList);
		System.out.print("Input a ToDo# to update: ");
		int index = sc.nextInt() - 1;
		System.out.println("******** You selected ********");
		System.out.println(toDoList.get(index));
		System.out.println("******************************");
		System.out.println("1. Edit due date\n2. Edit description\n3. Cancel");
		System.out.print("Select a update type: ");
		int type = sc.nextInt();
		switch(type) {
		case 1: EditDue(index); break;
		case 2: sc.nextLine(); EditDescription(index); break;
		default: return;
		}
		toDoList.remove(index + 1);
	}
	
	private void EditDue(int idx) {
		ToDo instance = toDoList.get(idx);
		instance.setDueDate(inputDueDate());
		toDoList.add(idx, instance);
	}
	
	private void EditDescription(int idx) {
		ToDo instance = toDoList.get(idx);
		instance.setDescription(inputDescription());
		toDoList.add(idx, instance);
	}
	
	private void deleteToDo() {
		System.out.println("\n******** Delete To-Do ********");
		if (toDoList.isEmpty()) {
			System.out.println("\n!! Empty To Do List !!\n");
			return;
		}
		ToDo.printToDoList(toDoList);
		System.out.print("Input a ToDo# to delete: ");
		int index = sc.nextInt() - 1;	sc.nextLine();
		System.out.println("******** You selected ********");
		System.out.println(toDoList.get(index));
		System.out.println("******************************");
		System.out.print("Delete? (y/n): ");
		char ans = sc.nextLine().charAt(0);
		if (ans == 'y')
			toDoList.remove(index);
	}
	
	private void saveToDoList() throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ToDoList"));
		oos.writeObject(toDoList);
		oos.close();
	}
}