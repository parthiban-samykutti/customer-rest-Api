package com.customer.api.responses;

import com.customer.api.Customer;

public class GetCustomerResponse {

	private Customer data;

	public GetCustomerResponse(Customer customer) {
		this.setData(customer);
	}	
	
	public GetCustomerResponse () {
		
	}

	public Customer getData() {
		return data;
	}

	public void setData(Customer data) {
		this.data = data;
	}

}
