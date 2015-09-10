package internship;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import org.springframework.ui.Model;

import com.acc.internship.controller.HomeController;

public class HomeTest {

	@Test
	public void testIndex() {
		HomeController test = Mockito.mock(HomeController.class);
		Model mockModel = Mockito.mock(Model.class);
		when(test.index(mockModel)).thenReturn("index");
		
		String s = "index";
		assertEquals(test.index(mockModel), s);
	}
}
