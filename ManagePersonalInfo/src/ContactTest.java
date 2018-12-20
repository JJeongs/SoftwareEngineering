import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ContactTest {
	@Test
	void testIsValidEmail() throws Exception {
		ManageContacts m = new ManageContacts();
		assertTrue(m.isValidEmail("wjdekdms99@naver.com")==true);
		assertTrue(m.isValidEmail("w")==false);
	}
	
	@Test
	void testIsValidPhone() throws Exception{
		ManageContacts contact = new ManageContacts();
		assertTrue(contact.isValidPhone("010-6666-6666")==true);
		assertTrue(contact.isValidPhone("jfdls")==false);
	}

}
