import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

class ToDo implements Serializable {
	private static final long serialVersionUID = 8137581867827899216L;
	private Date createDate, dueDate;
	private String description;

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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return "Create Date: " + dateFormat.format(createDate) + "\nDue Date: " + dateFormat.format(dueDate) + "\n" + description;
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
	
	public void printToDoMenu() throws Exception {	
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
			System.out.print("Input due date(yyyy/MM/dd): ");
			String strDueDate = sc.nextLine();
			Date dueDate = generateFormattedDueDate(strDueDate);
			if (dueDate == null)
				System.out.println("\n!! Invaild date format !!\n");
			else return dueDate;
		}
	}
	
	public Date generateFormattedDueDate(String dueDate) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date returnDueDate = dateFormat.parse(dueDate);
			return returnDueDate;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	private String inputDescription() {
		while (true) {
			System.out.println("Input description(up to 50 characters):");
			String description = sc.nextLine();
			if (isValidDescriptionLength(description) == false)
				System.out.println("\n!! You entered more then 50 characters !!\n");
			else
				return description;
		}
	}
	
	public boolean isValidDescriptionLength(String description) {
		if (description.length() > 50)
			return false;
		else
			return true;
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
		if (showSelectedToDo(index) == false) {
			System.out.println("\n!!! Index Out of Bound !!\n");
			return;
		}
		System.out.println("1. Edit due date\n2. Edit description\n3. Cancel");
		System.out.print("Select a update type: ");
		int type = sc.nextInt();
		switch(type) {
		case 1: editDue(index); break;
		case 2: sc.nextLine(); editDescription(index); break;
		default: return;
		}
		toDoList.remove(index + 1);
	}
	
	private boolean showSelectedToDo(int index) {
		if (toDoList.size() <= index)
			return false;
		System.out.println("******** You selected ********");
		System.out.println(toDoList.get(index));
		System.out.println("******************************");
		return true;
	}
	
	private void editDue(int idx) {
		ToDo instance = toDoList.get(idx);
		instance.setDueDate(inputDueDate());
		toDoList.add(idx, instance);
	}
	
	private void editDescription(int idx) {
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
		if (showSelectedToDo(index) == false) {
			System.out.println("\n!!! Index Out of Bound !!\n");
			return;
		}
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