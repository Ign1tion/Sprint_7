package steps;

import io.qameta.allure.Step;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import model.Courier;

import static config.Endpoints.*;
import static config.URL.BASE_URL;
import static io.restassured.RestAssured.given;

public class CourierSteps {
    @Step("Create courier")
    public static Response createCourier(String login, String password, String firstName) {
        Courier courier = new Courier(login, password, firstName);
        return given()
                .header("content-type", "application/json")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(courier)
                .when()
                .post(BASE_URL + COURIER);



    }

    @Step("Login courier")
    public static Response loginCourier(String login, String password) {
        Courier loginCourier = new Courier(login, password);
        return given()
                .header("content-type", "application/json")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(loginCourier)
                .when()
                .post(BASE_URL + LOGIN_COURIER);


    }

    @Step("Delete courier")
    public static void deleteCourier(String login, String password) {
        Courier loginCourier = new Courier(login, password);
        Integer courierId = given()
                .header("content-type", "application/json")
                .body(loginCourier)
                .when()
                .post(BASE_URL + LOGIN_COURIER)
                .then()
                .extract().body().path("id");
        given()
                .header("content-type", "application/json")
                .delete(String.format(BASE_URL + COURIER_ID, courierId));


    }

}
