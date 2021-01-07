package com.customer.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    String customersJsonPath = "src/main/resources/customers.json";
    @GetMapping("/api/customers")
    public GetCustomersResponse getCustomers() throws IOException {
        GetCustomersResponse response = new GetCustomersResponse();
        response.setData(getCustomersList(customersJsonPath));
        return response;
    }
    private List<Customer> getCustomersList(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File customerFile = new File(fileName);
        List<Customer> customers = mapper.readValue(customerFile, new TypeReference<ArrayList<Customer>>() {
        });
        return customers;
    }

}
