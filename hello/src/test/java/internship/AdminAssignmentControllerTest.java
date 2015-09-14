package internship;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

import com.acc.internship.controller.AdminAssignmentController;
import com.acc.internship.model.Assignment;
import com.acc.internship.model.Route;
import com.acc.internship.model.User;
import com.acc.internship.repo.AssignmentDAO;
import com.acc.internship.repo.RouteDAO;
import com.acc.internship.repo.UserDAO;

public class AdminAssignmentControllerTest {

	
	@InjectMocks
	private AdminAssignmentController controller;
	

	@Mock
	private UserDAO userDaoMock ;
	
	@Mock
	private AssignmentDAO assignmentDaoMock ;
	
	@Mock
	private RouteDAO routeDaoMock ;
	
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
	public void assignRouteSelectUserTest() throws Exception {
		
		List<User> user =Mockito.mock(List.class);
		
		when(userDaoMock.list()).thenReturn(user);
	
		this.mockMvc.perform(get("/admin/assign-route"))
					.andExpect(status().isOk())
					.andExpect(model().attribute("users", user))
					.andExpect(model().attribute("page", "assign-route"))
					.andExpect(view().name("admin"));
	 	    

	}
	
	@Test
	public void assignRouteSelectUser() throws Exception {
		List<Route> routes = Mockito.mock(List.class);
		List<Assignment> assignments = Mockito.mock(List.class);
		User user= Mockito.mock(User.class);
		Route route =Mockito.mock(Route.class);
		
		when(userDaoMock.get(1)).thenReturn(user);
		
		
		this.mockMvc.perform(get("/admin/assign-route/driver")
									.param("id", "1")
									.flashAttr("routes", routes)
									.flashAttr("user", user)
									.flashAttr("assignments",  assignments))	
					.andExpect(status().isOk())
					.andExpect(model().hasNoErrors())
					.andExpect(model().attribute("user", user))
					.andExpect(view().name("admin"));

	}
	
	@Test
	public void unasignRouteTest() throws Exception {
			
		this.mockMvc.perform(get("/admin/assign-route/driver")
									.param("id", "2"))	
					.andExpect(status().isOk())
					.andExpect(model().hasNoErrors())				
					.andExpect(view().name("admin"));

	}
	
	
	@Test
	public void assignRouteTest() throws Exception {
		
		User user=Mockito.mock(User.class);
		when(userDaoMock.get(2)).thenReturn(user);
		
		this.mockMvc.perform(get("/admin/assign-route/driver")
								.param("idDriver","2")
								.param("id","2"))	
					.andExpect(status().isOk())
					.andExpect(model().hasNoErrors())				
					.andExpect(view().name("admin"));

	}

}
