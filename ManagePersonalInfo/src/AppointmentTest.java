import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class AppointmentTest {
	@Test
	void validIndexTest() throws Exception {
		ManageAppointments manager = new ManageAppointments();
		int listLength = 10;
		int index=10;
		assertFalse(manager.checkListIndex(listLength, index));
		listLength = 100;
		assertTrue(manager.checkListIndex(listLength, index));
	}
	
	@Test
	void validAnswerTest() throws Exception {
		ManageAppointments manager = new ManageAppointments();
		char ans = 'y';
		assertTrue(manager.checkAnswer(ans));
		ans = 't';
		assertFalse(manager.checkAnswer(ans));
	}

}