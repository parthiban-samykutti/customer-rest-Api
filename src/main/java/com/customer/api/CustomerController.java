package com.customer.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.responses.GetCustomerResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class CustomerController {

	String customersJsonPath = "src/main/resources/customers.json";

	@GetMapping("/customers")
	public GetCustomersResponse getCustomers() throws IOException {
		GetCustomersResponse response = new GetCustomersResponse();
		response.setData(getCustomersList());
		return response;
	}

	@GetMapping("/customers/{id}")
	public GetCustomerResponse getCustomerById(@PathVariable String id) throws IOException {
		return getCustomersList().stream().filter(customer -> customer.getId().equals(id)).findFirst()
				.map(GetCustomerResponse::new).get();

	}

	/**
	 * Helper method to load customer list
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private List<Customer> getCustomersList() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		File customerFile = new File(customersJsonPath);
		List<Customer> customers = mapper.readValue(customerFile, new TypeReference<ArrayList<Customer>>() {
		});
		return customers;
	}

}
