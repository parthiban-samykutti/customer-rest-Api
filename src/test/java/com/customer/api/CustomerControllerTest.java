package com.customer.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.customer.api.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class CustomerControllerTest {
	
	String newCustomerJsonPath = "src/main/resources/newCustomer.json";
    
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCustomers() throws Exception {
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.data").exists())
        .andExpect(jsonPath("$.data.length()").value(4));
    }
    
    
    @Test
    public void testGetCustomerById() throws Exception {
    	String validId = "b8a504e8-7cbd-4a54-9a24-dc1832558162";
    	mockMvc.perform(
    			get("/api/customers/"+validId))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.data").exists())
    	.andExpect(jsonPath("$.data.id").value(validId))
    	.andExpect(jsonPath("$.data.firstName").value("Qin"))
    	.andExpect(jsonPath("$.data.lastName").value("Zhang"))
    	.andExpect(jsonPath("$.data.address").value("1 Main Street, Topeka, KS 37891"))
    	.andExpect(jsonPath("$.data.phoneNumber").value("510-555-2367"));    	
    }
    
    @Test
    public void testCreateCustomer() throws Exception {
    	mockMvc.perform(
    			post("/api/customers").content(getNewCustomerContent()).contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.data").exists())
    	.andExpect(jsonPath("$.data.id").exists())
    	.andExpect(jsonPath("$.data.firstName").value("Araminta"))
    	.andExpect(jsonPath("$.data.lastName").value("Ross"))
    	.andExpect(jsonPath("$.data.address").value("1849 Harriet Ave, Auburn, NY 63102"))
    	.andExpect(jsonPath("$.data.phoneNumber").value("309-555-1370"));     	
    }
    
    /**
     * Helper method to create new customer
     * @return
     * @throws IOException
     */
    private String getNewCustomerContent() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		File customerFile = new File(newCustomerJsonPath);
		Customer customer = mapper.readValue(customerFile, Customer.class);
		return mapper.writeValueAsString(customer);		
	}
}
