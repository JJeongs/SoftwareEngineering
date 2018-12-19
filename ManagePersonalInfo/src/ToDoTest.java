import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Vector;

import org.junit.jupiter.api.*;

public class ToDoTest {
	@Test
	void indexBountTest() throws Exception {
		File file = new File("ToDoList");
		if (!file.exists()) {
			file.createNewFile();
			ManageToDoList.toDoList = new Vector<ToDo>();
		}
		else {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ToDoList"));
			ManageToDoList.toDoList = (Vector<ToDo>)ois.readObject();
			ois.close();
		}
		ManageToDoList manager = new ManageToDoList();
		assertFalse(manager.showSelectedToDo(ManageToDoList.toDoList.size() + 3));
	}
	
	@Test
	void descriptionLengthTest() {
		ManageToDoList manager = new ManageToDoList();
		String testString = new String();
		for (int i = 0; i < 100; i++)
			testString += "*";
		assertFalse(manager.isValidDescriptionLength(testString));
	}
}
