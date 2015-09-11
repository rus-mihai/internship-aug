package internship;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.acc.internship.controller.AdminRouteController;
import com.acc.internship.model.Route;
import com.acc.internship.model.Station;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.StationDAO;

public class RouteControllerTest {

	@InjectMocks
	AdminRouteController test;
	
	@Mock
	RouteDAO routeDao;
	
	@Mock
	StationDAO stationDao;
	
	@Before
	public void initMocks(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void newRouteSuccess(){
		Route route = Mockito.mock(Route.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Model model = Mockito.mock(Model.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		@SuppressWarnings("unchecked")
		List<Station> stations = Mockito.mock(List.class);
		
		when(stationDao.list()).thenReturn(stations);
		
		assertEquals("redirect:/admin/routes", test.newRoutePost(route,bindingResult,request,model));
	}
	
	@Test
	public void newRouteFail(){
		Route route = Mockito.mock(Route.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Model model = Mockito.mock(Model.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		@SuppressWarnings("unchecked")
		List<Station> stations = Mockito.mock(List.class);
		
		when(bindingResult.hasErrors()).thenReturn(true);
		when(stationDao.list()).thenReturn(stations);
		
		assertEquals("admin", test.newRoutePost(route,bindingResult,request,model));
	}

}
