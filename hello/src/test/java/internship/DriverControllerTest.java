package internship;

import static org.junit.Assert.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acc.internship.controller.AdminAssignmentController;
import com.acc.internship.controller.DriverController;
import com.acc.internship.model.PasswordVerify;
import com.acc.internship.model.User;
import com.acc.internship.model.UserRole;
import com.acc.internship.repo.AssignmentDAO;
import com.acc.internship.repo.UserDAO;

public class DriverControllerTest {

	@InjectMocks
	private DriverController controller;
	
	@Mock
	private UserDAO userDaoMock;

	@Mock
	private AssignmentDAO assignmentDaoMock;
	
	@Mock
	private SimpMessagingTemplate template1;
	
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
	public void updateDriverPostTest() throws Exception {
		
		User user =Mockito.mock(User.class);
	
		this.mockMvc.perform(post("/driver")
								.flashAttr("userupdate", user))
					.andExpect(status().is3xxRedirection())
					.andExpect(model().hasErrors())
					.andExpect(view().name("redirect:/driver"));
	 	    		
	}
	
	@Test
	public void updatePassPostTest() throws Exception {
		
		PasswordVerify passupdateoptions=Mockito.mock(PasswordVerify.class);
		User user =Mockito.mock(User.class);
		
		when(userDaoMock.get(passupdateoptions.getId())).thenReturn(user);
				
		this.mockMvc.perform(post( "/driver/updatepass" )
								.flashAttr("passupdateoptions", passupdateoptions))
					.andExpect(status().is3xxRedirection())
					.andExpect(model().hasErrors())
					.andExpect(view().name("redirect:/driver"));
	 	    		

	}
}
