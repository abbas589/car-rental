package edu.miu.carrental.controller;

import edu.miu.carrental.domain.dto.CustomerDto;
import edu.miu.carrental.respository.CustomerRepository;
import edu.miu.carrental.respository.PaymentRepository;
import edu.miu.carrental.respository.ReservationRepository;
import edu.miu.carrental.service.CustomerService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

/**
 * @author bazz
 * Apr 26 2023
 * 16:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    MockMvc mock;

//    @MockBean
//    CustomerService customerService;
//
//    @MockBean
//    CustomerRepository customerRepository;
//
//    @MockBean
//    ReservationRepository reservationRepository;
//    @MockBean
//    PaymentRepository paymentRepository;

    @LocalServerPort
    private int port;

    static RequestSpecification when = null;
    @BeforeEach
    void setUp() {
        RestAssured.defaultParser = Parser.JSON;
        when = given()
                .port(port)
                .basePath("/customer")
                .when();
    }

    @Test
    void testGetAllCustomers() throws Exception {
        CustomerDto faridaAshir = new CustomerDto(1L, "Farida Ashir", "fashir@gmail.com", Collections.emptyList());
        CustomerDto faridaBenya = new CustomerDto(2L, "Farida Benya", "bashir@gmail.com", Collections.emptyList());
        when.contentType("application/json")
                .body(faridaAshir)
                .when().post("/create").then().statusCode(201);
        when.contentType("application/json")
                .body(faridaBenya)
                .when().post("/create").then().statusCode(201);

        when
                .get()
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .and()
                .body("customers.customerNumber",hasItems(1,2))
                .body("customers.name",hasItems("Farida Ashir","Farida Benya"))
                .body("customers.email",hasItems("fashir@gmail.com","bashir@gmail.com"));

        when
                .delete("1");
        when
                .delete("2");


    }

    @Test
    void testSearchCustomerByEmail() {
        CustomerDto faridaAshir = new CustomerDto(1L, "Farida Ashir", "fashir@gmail.com", Collections.emptyList());
        CustomerDto faridaBenya = new CustomerDto(2L, "Farida Benya", "bashir@gmail.com", Collections.emptyList());
        when.contentType("application/json")
                .body(faridaAshir)
                .when().post("/create").then().statusCode(201);
        when.contentType("application/json")
                .body(faridaBenya)
                .when().post("/create").then().statusCode(201);

        when
                .queryParam("searchBy","email")
                .queryParam("value","fashir@gmail.com")
                .get("/search")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .and()
                .body("customers.customerNumber",hasItems(1))
                .body("customers.name",hasItems("Farida Ashir"))
                .body("customers.email",hasItems("fashir@gmail.com"));

        when
                .delete("1");
        when
                .delete("2");

    }
    @Test
    void testSearchCustomerByName() {
        CustomerDto faridaAshir = new CustomerDto(1L, "Farida Ashir", "fashir@gmail.com", Collections.emptyList());
        CustomerDto faridaBenya = new CustomerDto(2L, "Farida Benya", "bashir@gmail.com", Collections.emptyList());
        when.contentType("application/json")
                .body(faridaAshir)
                .when().post("/create").then().statusCode(201);
        when.contentType("application/json")
                .body(faridaBenya)
                .when().post("/create").then().statusCode(201);

        when
                .queryParam("searchBy","name")
                .queryParam("value","Farida Ashir")
                .get("/search")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .and()
                .body("customers.customerNumber",hasItems(1))
                .body("customers.name",hasItems("Farida Ashir"))
                .body("customers.email",hasItems("fashir@gmail.com"));

        when
                .delete("1");
        when
                .delete("2");

    }

    @Test
    void getOneCustomerData() {
        CustomerDto faridaAshir = new CustomerDto(1L, "Farida Ashir", "fashir@gmail.com", Collections.emptyList());
        when.contentType("application/json")
                .body(faridaAshir)
                .when().post("/create").then().statusCode(201);
        when

                .get("1/get")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .and()
                .body("customer.customerNumber",equalTo(1))
                .body("customer.name",equalTo("Farida Ashir"))
                .body("customer.email",equalTo("fashir@gmail.com"));


        when
                .delete("1");
    }

    @Test
    void createCustomer() {
        CustomerDto faridaAshir = new CustomerDto(1L, "Zainab Benya", "fashir@gmail.com", Collections.emptyList());
        when.contentType("application/json")
                .body(faridaAshir)
                .when().post("/create").then()
                .statusCode(201)
                .body("name",equalTo("Zainab Benya"));

        when
                .delete("1");
    }

    @Test
    void updateOneCustomer() {
        CustomerDto zainabBenya = new CustomerDto(1L, "Zainab Benya", "fashir2@gmail.com", Collections.emptyList());
        when.contentType("application/json")
                .body(zainabBenya)
                .when().post("/create").then()
                .statusCode(201)
                .body("name",equalTo("Zainab Benya"));
        CustomerDto faridaAshir = new CustomerDto(1L, "Farida Benya", "fashir@gmail.com", Collections.emptyList());

        when.contentType("application/json")
                .body(faridaAshir)
                .when().put("/update/1").then()
                .statusCode(200)
                .body("name",equalTo("Farida Benya"));

        when
                .delete("1");


    }

    @Test
    void deleteCustomer() {
        CustomerDto zainabBenya = new CustomerDto(1L, "Zainab Benya", "fashir2@gmail.com", Collections.emptyList());
        when.contentType("application/json")
                .body(zainabBenya)
                .when().post("/create").then()
                .statusCode(201)
                .body("name",equalTo("Zainab Benya"));

        when
                .delete("1").then()
                .statusCode(204);
    }
}
