package com.customer.api;

import java.util.List;

public class GetCustomersResponse {

    private List<Customer> data;

    public List<Customer> getData() {
        return data;
    }

    public void setData(List<Customer> data) {
        this.data = data;
    }

}
