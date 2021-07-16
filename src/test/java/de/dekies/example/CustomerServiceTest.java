package de.dekies.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class CustomerServiceTest
{

    @Test
    public void testServlet() {
        RestAssured.when().get("/services/customerService?wsdl").then()
                .contentType("text/xml; charset=UTF-8")
                .body(containsString("getCustomersByName"));
    }

}
