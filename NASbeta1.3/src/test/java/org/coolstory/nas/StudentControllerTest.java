package org.coolstory.nas;

import org.coolstory.nas.bean.StudentBean;
import org.coolstory.nas.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

public class StudentControllerTest {
	
	@Mock
	private StudentService studentService;
	
	@InjectMocks
	StudentController studentController;
	
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
		
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        
    }

	/**
	 * This method will test testGetStudentsDataById method 
	 * @throws Exception
	 */
	@Test
	public void testGetStudentsDataById() throws Exception{
		
		StudentBean studentBean = new StudentBean();
		studentBean.setId(1L);
		studentBean.setName("Rahul");
		studentBean.setCategoryName("Athletic");
				    
		Mockito.when(studentService.getStudentsDataById(1L)).thenReturn(studentBean);
		
        mockMvc.perform(get("/student/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.categoryName", is("Athletic")))
        .andReturn();
			
	}
	
}
