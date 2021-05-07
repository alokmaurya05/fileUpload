package com.awin.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.awin.application.AwinApplication;
import com.awin.entity.JobApplication;
import com.awin.resources.JobApplicationResource;

import com.awin.services.JobApplicationService;

import com.fasterxml.jackson.databind.ObjectMapper;


/*This class for resource test
 * We can add more test case also. This is just a basic.
 * */
@SpringBootTest(classes={AwinApplication.class})
@RunWith(SpringRunner.class)
public class JobApplicationResourceTest 
{
	private final String URL = "/api/v1/upload";
	
	MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	JobApplicationResource jobApplicationResource;
    
	@MockBean
	JobApplicationService jobService;
	
	@Before
	public void setup()
	{
		 this.mockMvc = standaloneSetup(this.jobApplicationResource).build();// Standalone context
	}
	
	@Test
	public void testSave() throws Exception 
	{      
		  JobApplication application = new JobApplication(new Long(101),"Harry","Jon","Harry@gmail.com","Java","StackOverflow");
		  MockMultipartFile file =
	                new MockMultipartFile(
	                        "resume",
	                        "test contract.pdf",
	                        MediaType.APPLICATION_PDF_VALUE,
	                        "<<pdf data>>".getBytes(StandardCharsets.UTF_8));

	        ObjectMapper objectMapper = new ObjectMapper();

	        MockMultipartFile metadata =
	                new MockMultipartFile(
	                        "resume",
	                        "resume",
	                        MediaType.APPLICATION_JSON_VALUE,
	                        objectMapper.writeValueAsString(application).getBytes(StandardCharsets.UTF_8));

	        mockMvc.perform(
	        		MockMvcRequestBuilders.multipart(URL)
	                        .file(file)
	                        .file(metadata)
	                        .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated())
	                ;
	} 
	
	
}
