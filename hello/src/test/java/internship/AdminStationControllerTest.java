package internship;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.acc.internship.controller.AdminStationController;
import com.acc.internship.model.Station;
import com.acc.internship.repo.StationDAO;

public class AdminStationControllerTest {

	@InjectMocks
	AdminStationController controller;

	@Mock
	StationDAO stationDaoMock;

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
	public void testnewStationGet() throws Exception {
		
		List<Station> expectedStation =Mockito.mock(List.class);
		when(stationDaoMock.list()).thenReturn(expectedStation);

		this.mockMvc.perform(get("/admin/newstation"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("stations", expectedStation))
				.andExpect(model().attributeExists("station"))
				.andExpect(model().attribute("page","newstation"))
				.andExpect(view().name("admin"));
	 	    

	}
	
	@Test
	public void testnewStationPostGreen() throws Exception {
   
		this.mockMvc.perform(post("/admin/newstation").param("name", "green"))
				.andExpect(status().isOk())
				.andExpect(model().hasNoErrors())
				.andExpect(view().name("admin"));
				

	}
	
	
	@Test
	public void testnewStationPostRed() throws Exception {
    
	
		this.mockMvc.perform(post("/admin/newstation").param("name", "green"))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors())
				.andExpect(model().attribute("name","green"))
				.andExpect(view().name("admin"));
				
	 	    

	}
	
	@Test
	public void testdeleteStation() throws Exception {

	
		this.mockMvc.perform(get("/admin/newstation/delete-station")	
									.param("id","4"))
					.andExpect(status().is3xxRedirection())
					.andExpect(model().hasNoErrors());
	 	    

	}

}
