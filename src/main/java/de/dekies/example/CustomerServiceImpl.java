/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package de.dekies.example;

import com.example.customerservice.*;

import jakarta.jws.WebService;
import java.math.BigDecimal;
import java.util.*;

// copied from https://github.com/apache/cxf/blob/master/distribution/src/main/release/samples/wsdl_first/src/main/java/com/example/customerservice/server/CustomerServiceImpl.java
@WebService(endpointInterface = "com.example.customerservice.CustomerService", serviceName = "CustomerService")
public class CustomerServiceImpl implements CustomerService {

    @Override
    public List<Customer> getCustomersByName(String name) throws NoSuchCustomerException {
        if ("None".equals(name)) {
            NoSuchCustomer noSuchCustomer = new NoSuchCustomer();
            noSuchCustomer.setCustomerName(name);
            throw new NoSuchCustomerException("Did not find any matching customer for name=" + name,
                    noSuchCustomer);
        }

        List<Customer> customers = new ArrayList<>();
        for (int c = 0; c < 2; c++) {
            Customer cust = new Customer();
            cust.setCustomerId(c);
            cust.setName(name + " " + c);
            cust.getAddress().add("Pine Street 200");
            Date bDate = new GregorianCalendar(2009, Calendar.JANUARY, 1).getTime();
            cust.setBirthDate(bDate);
            cust.setNumOrders(1);
            cust.setRevenue(c*10000);
            cust.setTest(new BigDecimal("1.5"));
            cust.setType(CustomerType.BUSINESS);
            customers.add(cust);
        }

        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) {

    }
}
