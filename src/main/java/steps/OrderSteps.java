package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;


import static config.Endpoints.CANCEL_ORDER;
import static config.Endpoints.ORDERS;
import static config.URL.BASE_URL;
import static io.restassured.RestAssured.given;


public class OrderSteps {

    @Step("Create order")
    public static Response createOrder(Order order) {
        return given()
                .header("content-type", "application/json")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(order)
                .when()
                .post(BASE_URL + ORDERS)
                .then()
                .extract().response();

    }

    @Step("Get orders list")
    public static Response getOrdersList() {
        return given()
                .header("content-type", "application/json")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .get(BASE_URL + ORDERS)
                .then()
                .extract()
                .response();

    }

    @Step("Cancel order")
    public static void cancelOrder(String track) {
        given()
                .header("content-type", "application/json")
                .body(track)
                .when()
                .put(BASE_URL + CANCEL_ORDER);
         /*      .then()
               .assertThat()
               .statusCode(SC_OK)
              .and()
              .body("ok", is(true));  */
    }


}

