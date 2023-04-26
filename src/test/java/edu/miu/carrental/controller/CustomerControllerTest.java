//package edu.miu.carrental.controller;
//
//import edu.miu.carrental.domain.dto.CustomerDto;
//import edu.miu.carrental.respository.CustomerRepository;
//import edu.miu.carrental.respository.PaymentRepository;
//import edu.miu.carrental.respository.ReservationRepository;
//import edu.miu.carrental.service.CustomerService;
//import io.restassured.RestAssured;
//import org.junit.BeforeClass;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Collections;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.hasItems;
//
///**
// * @author bazz
// * Apr 26 2023
// * 16:49
// */
//@RunWith(SpringRunner.class)
//@WebMvcTest(CustomerController.class)
//class CustomerControllerTest {
//
//    @Autowired
//    MockMvc mock;
//
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
//
//
//    @BeforeClass
//    void setUp() {
//        RestAssured.port = Integer.valueOf(8200);
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.basePath = "";
//    }
//
//    @Test
//    void testGetAllCustomers() throws Exception {
//        CustomerDto faridaAshir = new CustomerDto(1L, "Farida Ashir", "fashir@gmail.com", Collections.emptyList());
//        CustomerDto faridaBenya = new CustomerDto(2L, "Farida Benya", "bashir@gmail.com", Collections.emptyList());
//        given().contentType("application/json")
//                .body(faridaAshir)
//                .when().post("/customer/create").then().statusCode(200);
//        given().contentType("application/json")
//                .body(faridaBenya)
//                .when().post("/customer/create").then().statusCode(200);
//
//        given().when()
//                .get("/customer")
//                .then()
//                .statusCode(200)
//                .and()
//                .body("customers.customerNumber",hasItems(1L,2L))
//                .body("customers.name",hasItems("Farida Ashir","Farida Benya"))
//                .body("customers.email",hasItems("fashir@gmail.com","bashir@gmail.com"));
//
//        given().when()
//                .delete("customers/1");
//        given().when()
//                .delete("customers/2");
//
//
//    }
//
//    @Test
//    void searchCustomer() {
//    }
//
//    @Test
//    void getCustomerData() {
//    }
//
//    @Test
//    void createCustomer() {
//    }
//
//    @Test
//    void updateCustomer() {
//    }
//
//    @Test
//    void deleteCustomer() {
//    }
//}
