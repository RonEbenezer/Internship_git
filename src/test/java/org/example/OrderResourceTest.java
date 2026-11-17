package org.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class OrderResourceTest {
    @Test
    void getGetAllOrders(){
        given().when().get("/orders")
                .then().statusCode(200);
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
                        .extract()
                        .path("orderId").toString();

        given()
                .when()
                .get("/orders/" + orderId)
                .then()
                .statusCode(200);
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
                .statusCode(201);
    }
}
