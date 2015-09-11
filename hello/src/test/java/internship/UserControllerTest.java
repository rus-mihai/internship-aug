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

import com.acc.internship.controller.AdminUserController;
import com.acc.internship.model.User;
import com.acc.internship.model.UserRole;
import com.acc.internship.repo.UserDAO;
import com.acc.internship.repo.UserRoleDAO;

public class UserControllerTest {

	
	
	@InjectMocks 
	AdminUserController test;
	
	
	@Mock
	UserDAO dao;
	
	@Mock
	UserRoleDAO roleDao;
	
	@Before
	public void initMocks(){
		MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void addUserSuccess(){
		User user = Mockito.mock(User.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		List<UserRole> list =  Mockito.mock(List.class);
		
		when(roleDao.list()).thenReturn(Mockito.mock(List.class));
		when(list.get(1)).thenReturn(Mockito.mock(UserRole.class));
		when(user.getPassword()).thenReturn("pass");
		when(user.getConfirmPassword()).thenReturn("pass");
		assertEquals("redirect:/admin/userview",test.newDriverPost(user, bindingResult, Mockito.mock(HttpServletRequest.class), Mockito.mock(Model.class)));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void addUserFail(){
		User user = Mockito.mock(User.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		List<UserRole> list =  Mockito.mock(List.class);
		
		when(roleDao.list()).thenReturn(Mockito.mock(List.class));
		when(list.get(1)).thenReturn(Mockito.mock(UserRole.class));
		when(user.getPassword()).thenReturn("pass");
		when(user.getConfirmPassword()).thenReturn("passx");
		assertEquals("admin",test.newDriverPost(user, bindingResult, Mockito.mock(HttpServletRequest.class), Mockito.mock(Model.class)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void editUserSuccess(){
		User user = Mockito.mock(User.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		List<UserRole> list =  Mockito.mock(List.class);
		
		when(roleDao.list()).thenReturn(Mockito.mock(List.class));
		when(list.get(1)).thenReturn(Mockito.mock(UserRole.class));
		when(user.getPassword()).thenReturn("pass");
		when(user.getConfirmPassword()).thenReturn("pass");
		assertEquals("redirect:/admin/userview",test.newDriverPost(user, bindingResult, Mockito.mock(HttpServletRequest.class), Mockito.mock(Model.class)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void editUserFail(){
		User user = Mockito.mock(User.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		List<UserRole> list =  Mockito.mock(List.class);
		
		when(roleDao.list()).thenReturn(Mockito.mock(List.class));
		when(list.get(1)).thenReturn(Mockito.mock(UserRole.class));
		when(user.getPassword()).thenReturn("pass");
		when(user.getConfirmPassword()).thenReturn("passx");
		assertEquals("admin",test.newDriverPost(user, bindingResult, Mockito.mock(HttpServletRequest.class), Mockito.mock(Model.class)));
	}

}
