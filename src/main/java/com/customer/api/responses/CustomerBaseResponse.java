package com.customer.api.responses;

import com.customer.api.model.Customer;

public class CustomerBaseResponse {

	private Customer data;

	public CustomerBaseResponse(Customer customer) {
		this.setData(customer);
	}	
	
	public CustomerBaseResponse () {
		
	}

	public Customer getData() {
		return data;
	}

	public void setData(Customer data) {
		this.data = data;
	}

}
