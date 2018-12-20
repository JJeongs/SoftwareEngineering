import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ToDoTest {
	@Test
	void dateFormatTest() {
		ManageToDoList manager = new ManageToDoList();
		assertNotNull(manager.generateFormattedDueDate("2018/12/25"));
		assertNull(manager.generateFormattedDueDate("20181225"));
	}
	
	@Test
	void descriptionLengthTest() {
		ManageToDoList manager = new ManageToDoList();
		String invalidString = new String();
		for (int i = 0; i < 51; i++)
			invalidString += "*";
		assertFalse(manager.isValidDescriptionLength(invalidString));
		assertTrue(manager.isValidDescriptionLength("Valid Description"));
	}
}