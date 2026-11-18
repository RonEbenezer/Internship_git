package org.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class OrderResourceTest {
    @Test
    void getAllOrders(){
        Integer orderId =
                given()
                        .contentType("application/json")
                        .body("""
                    {
                        "customer": {
                            "id": 1,
                            "name": "John",
                            "email": "john@gmail.com"
                        },
                        "orderLines": []
                    }
                    """)
                        .when()
                        .post("/orders")
                        .then()
                        .statusCode(201)
                        .extract()
                        .path("orderId");

        given()
                .when()
                .get("/orders")
                .then()
                .statusCode(200)
                .body("orderId", hasItem(orderId));
    }

    @Test
    void testGetOrderNotFound() {
        given()
                .when()
                .get("/orders/999")
                .then()
                .statusCode(404);
    }

    @Test
    void testGetOrderById() {

        String orderId =
                given()
                        .contentType("application/json")
                        .body("""
                {
                    "customer": {
                        "id": 1,
                        "name": "Arun",
                        "email": "arun@gmail.com"
                    },
                    "orderLines": [
                        {
                            "productId": "P101",
                            "desc": "Laptop",
                            "quantity": 1,
                            "unitPrice": 50000
                        }
                    ]
                }
                """)
                        .when()
                        .post("/orders")
                        .then()
                        .statusCode(201)
                        .body("orderId",notNullValue())
                        .body("status",equalTo("PENDING"))
                        .extract()
                        .path("orderId").toString();

        given()
                .when()
                .get("/orders/" + orderId)
                .then()
                .statusCode(200)
                .body("customer.name", equalTo("Arun"));
    }
    @Test
    void testCreateOrder() {
        given()
                .contentType("application/json")
                .body("""
                {
                    "customer": {
                        "id": 1,
                        "name": "Arun",
                        "email": "arun@gmail.com"
                    },
                    "orderLines": []
                }
                """)
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .body("orderId",notNullValue())
                .body("status",equalTo("PENDING"));
    }
}
