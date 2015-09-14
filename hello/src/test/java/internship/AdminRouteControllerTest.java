package internship;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acc.internship.controller.AdminRouteController;
import com.acc.internship.model.Route;
import com.acc.internship.model.Station;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.StationDAO;

public class AdminRouteControllerTest {

	@InjectMocks
	AdminRouteController controller;

	@Mock
	private StationDAO stationDaoMock;
	
	@Mock
	private RouteDAO routeDaoMock;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.build();

	}

	@After
	public void tearDown() throws Exception {

	}


	@Test
	public void viewRoutesTest() throws Exception {
		
		List<Route> route =Mockito.mock(List.class);
		when(routeDaoMock.list()).thenReturn(route);

		this.mockMvc.perform(get("/admin/routes"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("routes", route))
				.andExpect(model().attributeExists("page"))
				.andExpect(view().name("admin"));
	 	    

	}
	
	@Test
	public void newRouteGetTest() throws Exception {
   
		
		List<Station> stations =Mockito.mock(List.class);
		when(stationDaoMock.list()).thenReturn(stations);
		
		this.mockMvc.perform(get("/admin/newroute"))
				.andExpect(status().isOk())
				.andExpect(model().hasNoErrors())
				.andExpect(model().attributeExists("route"))
				.andExpect(model().attributeExists("page"))
				.andExpect(model().attribute("stations", stations))
				.andExpect(view().name("admin"));
				

	}
	
	
	@Test
	public void newRoutePostTest() throws Exception {
    
	
		this.mockMvc.perform(post("/admin/newroute"))
				.andExpect(status().is3xxRedirection())
				.andExpect(model().hasNoErrors())
				.andExpect(model().attributeExists("route"))
				.andExpect(model().attributeExists("success"))
				.andExpect(redirectedUrl("/admin/routes?success=Route+added"))
				.andExpect(view().name("redirect:/admin/routes"));
	
				
	 	    

	}
	
	@Test
	public void deleteRouteTest() throws Exception {
		
		
		this.mockMvc.perform(get("/admin/deleteroute")	
									.param("id","4"))
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrl("/admin/routes"))
					.andExpect(view().name("redirect:/admin/routes"));
	 	    

	}
	
	@Test
	public void viewRouteTest() throws Exception {

		Route route=Mockito.mock(Route.class);
		
		when(routeDaoMock.get(2)).thenReturn(route);
	
		this.mockMvc.perform(get("/admin/view-edit-route")
										.param("id", "2")
										.param("edit", "false"))
					.andExpect(status().isOk())
					.andExpect(model().attributeExists("route"))
					.andExpect(model().attribute("route", route))
					.andExpect(view().name("admin"));
	 	    

	}
	
	@Test
	public void updateRouteTest() throws Exception {
		Route r=new Route();
		Station start=Mockito.mock(Station.class);
		Station end=Mockito.mock(Station.class);
		
		r.setStart(start);
		r.setEnd(end);
		

 
		this.mockMvc.perform(post("/admin/view-edit-route")			
							.flashAttr("route", r))	
					.andExpect(status().is3xxRedirection())
					.andExpect(model().attributeExists("success"))
					.andExpect(view().name("redirect:/admin/routes"))
					.andExpect(redirectedUrl("/admin/routes?success=Route+updated"));

	}

}