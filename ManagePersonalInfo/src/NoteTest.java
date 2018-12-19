import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class NoteTest {

	@Test
	void testIsLongInput() {
		ManageNotes m = new ManageNotes();
		assertTrue(m.IsLongInput(25)==false);
		assertTrue(m.IsLongInput(24)==false);
		assertTrue(m.IsLongInput(30)==true);
	}
	
	@Test
	void testNotInRange() {
		ManageNotes m = new ManageNotes();
		assertTrue(m.notInRange(1, 3, 1) == false);
		assertTrue(m.notInRange(1, 3, 4) == true);
		assertTrue(m.notInRange(1, 3, 0) == true);
	}
}

