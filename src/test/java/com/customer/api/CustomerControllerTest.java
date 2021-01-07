package com.customer.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest
public class CustomerControllerTest {
    
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
}
