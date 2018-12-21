import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class AppointmentTest {

	@Test
	void testValidIndex() {
		ManageAppointments manager = new ManageAppointments();
		int listLength = 100;
		int index = 10;
		assertTrue(manager.checkValidIndex(listLength, index));
		listLength = 1;
		assertFalse(manager.checkValidIndex(listLength, index));
	}
	
	@Test
	void testValidAnswer() {
		ManageAppointments manager = new ManageAppointments();
		char ans = 'y';
		assertTrue(manager.checkValidAnswer(ans));
		ans = 'n';
		assertTrue(manager.checkValidAnswer(ans));
		ans = 't';
		assertFalse(manager.checkValidAnswer(ans));
	}
}

