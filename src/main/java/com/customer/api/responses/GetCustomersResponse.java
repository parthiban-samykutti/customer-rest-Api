package com.customer.api.responses;

import java.util.List;

import com.customer.api.model.Customer;

public class GetCustomersResponse {

    private List<Customer> data;

    public List<Customer> getData() {
        return data;
    }

    public void setData(List<Customer> data) {
        this.data = data;
    }

}
