package edu.miu.carrental.controller;

import edu.miu.carrental.CarFleetClient;
import edu.miu.carrental.domain.dto.CustomerDto;
import edu.miu.carrental.domain.dto.ReservationDto;
import edu.miu.carrental.domain.dto.ReservationRequestDto;
import edu.miu.carrental.domain.entity.Customer;
import edu.miu.carrental.respository.CustomerRepository;
import edu.miu.carrental.service.ReservationService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bazz
 * Apr 26 2023
 * 20:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ReservationControllerTest {

    @Autowired
    MockMvc mock;

    @Autowired
    CarFleetClient client;

    @LocalServerPort
    private int port;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReservationService reservationService;

    static RequestSpecification when = null;


    @BeforeEach
    void setUp() {
        ReservationRequestDto reservIs250 = new ReservationRequestDto("IS250",1L, LocalDate.of(2023,4,26), LocalDate.of(2023,12,26));

        Mockito.when(reservationService.reserveCar(reservIs250)).thenReturn(new ReservationDto("AB", LocalDate.of(2023,4,26), LocalDate.of(2023,12,26)));
        RestAssured.defaultParser = Parser.JSON;
        when = given()
                .port(port)
                .basePath("/reservation")
                .when();
    }

    @Test
    void testReserveCar() {

        ReservationRequestDto reservIs250 = new ReservationRequestDto("IS250",1L, LocalDate.of(2023,4,26), LocalDate.of(2023,12,26));
        ReservationRequestDto reservIs251 = new ReservationRequestDto("IS250",1L, LocalDate.of(2023,4,26), LocalDate.of(2023,12,26));
        CustomerDto faridaBenya = new CustomerDto(2L, "Farida Benya", "bashir@gmail.com", Collections.emptyList());
        when.contentType("application/json")
                .body(reservIs250)
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
    void searchCar() {
    }

    @Test
    void getCarData() {
    }

    @Test
    void searchCars() {
    }

    @Test
    void pickupCar() {
    }

    @Test
    void returnCar() {
    }
}
