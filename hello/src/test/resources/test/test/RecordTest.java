package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.acc.internship.model.Route;
import com.acc.internship.repo.RecordDAO;

public class RecordTest {
	@Test
	public void test() {
		RecordDAO test = Mockito.mock(RecordDAO.class);
		Route routeTest = Mockito.mock(Route.class);
		List<Integer> list = Mockito.mock(List.class);
		when(list.size()).thenReturn(20);
		when(test.listTour(routeTest, 1)).thenReturn(list);

		assertEquals(test.listTour(routeTest, 1).size(), 20);
	}

}
