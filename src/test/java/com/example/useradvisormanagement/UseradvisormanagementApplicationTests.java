package com.example.useradvisormanagement;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.useradvisormanagement.domain.Advisor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class UseradvisormanagementApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ObjectMapper mapper;
	
	@BeforeEach
	public void setup() throws Exception{
		System.out.println("Inside setup of AdvisorControllerTest");
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	
	@Test 
	public void testAdvisorController() throws Exception {
	    System.out.println("Inside setup of testAdvisorController"); 
	    mockMvc.perform(
	        MockMvcRequestBuilders.get("/admin/advisors")
	          ).andExpect(MockMvcResultMatchers.status().isOk())
	          .andExpect(MockMvcResultMatchers.content().json(
				  "[{'advisorId':1,'advisorName':'AdvisorName1'," +
				  "'advisorProfileURL':'files/profilepic/advisorName1','bookings':[]}," +
				  "{'advisorId':2,'advisorName':'AdvisorName2','advisorProfileURL':'files/profilepic/advisorName2',"
				  + "'bookings':[]},{'advisorId':3,'advisorName':'AdvisorName3'," +
				  "'advisorProfileURL':'files/profilepic/advisorName3','bookings':[]}," +
				  "{'advisorId':4,'advisorName':'xyz','advisorProfileURL':'files//profilepic//xyz',"
				  +
				  "'bookings':[]},{'advisorId':5,'advisorName':'lmn','advisorProfileURL':'files//profilepic//lmn',"
				  + "'bookings':[]},{'advisorId':6,'advisorName':'zaiba','advisorProfileURL':'files/profilepic/zaiba123',"
				  + "'bookings':[]}]")); 
	}
	 
	
	@Test
    public void testCreateAdvisor() {
    	System.out.println("Inside testCreateAdvisor");
        Advisor advisorObj = new Advisor(6,"zaiba", "files/profilepic/zaiba123");
        System.out.println("Advisor created successfully"+ advisorObj.toString());
        try {
			String jsonRequest = mapper.writeValueAsString(advisorObj);
			
			MvcResult result= mockMvc.perform(post("/admin/advisors").content(jsonRequest).contentType(
					MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
			
			assertEquals(200,result.getResponse().getStatus());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}                
    }
   
	@Test
    public void testGetAllAdvisors() {
    	System.out.println("Inside testGetAllAdvisors");        
        try {
        	List<Advisor> advisors = new ArrayList<>();
            advisors.add(new Advisor(1, "AdvisorName1","files/profilepic/advisorName1"));
            advisors.add(new Advisor(2, "AdvisorName2","files/profilepic/advisorName2"));
            advisors.add(new Advisor(3, "AdvisorName3","files/profilepic/advisorName3"));
            advisors.add(new Advisor(4, "xyz","files/profilepic/xyz"));
            advisors.add(new Advisor(5, "lmn","files/profilepic/lmn"));
            advisors.add(new Advisor(6, "zaiba","files/profilepic/zaiba123"));
			MvcResult result= mockMvc.perform(get("/admin/advisors").contentType(
					MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
			
			assertEquals(200,result.getResponse().getStatus());
			
			 String jsonResponse = result.getResponse().getContentAsString();
		     List<Advisor> actualAdvisors = new ObjectMapper().readValue(jsonResponse, new TypeReference<List<Advisor>>() {});
		     System.out.println("Mock advisor result="+advisors.size()+" , actual="+actualAdvisors.size());
		     assertEquals(advisors.size(), actualAdvisors.size());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}                
    }
}
