
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import org.junit.After;
import org.junit.Test;

import static steps.CourierSteps.createCourier;
import static steps.CourierSteps.deleteCourier;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.*;

public class CourierTest extends TestData {


    @Test
    @DisplayName("Creating courier")
    @Description("Should response with SC 201 Created and body \"ok\": true")
    public void createCourierTest() {
        createCourier(LOGIN, PASSWORD, FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", is(true));


    }

    @Test
    @DisplayName("Creating two identical couriers")
    @Description("Creating two identical couriers should result in SC 409 conflict and body message: \"Этот логин уже используется\"")
    public void createIdenticalCouriersTest() {
        createCourier(LOGIN, PASSWORD, FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", is(true));
        createCourier(LOGIN, PASSWORD, FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(SC_CONFLICT)
                .and()
                .body("message", is("Этот логин уже используется"));

    }

    @Test
    @DisplayName("Creating courier with null in \"firstName\" field")
    @Description("\"firstName\" is not mandatory field so it should result in SC 201 created and body message: \"ok\"")
    public void createCourierWithNullInFirstNameFieldTest() {
        createCourier(LOGIN, PASSWORD, null)
                .then()
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Creating courier with null in \"password\" field")
    @Description("\"password\" is a mandatory field so it should result in SC 400 bad request and body message \"Недостаточно данных для создания учетной записи\"")
    public void createCourierWithNullInPasswordFieldTest() {
        createCourier(LOGIN, null, FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Creating courier with null in \"login\" field")
    @Description("\"login\" is a mandatory field so it should result in SC 400 bad request and body message \"Недостаточно данных для создания учетной записи\"")
    public void createCourierWithNullInLoginFieldTest() {
        createCourier(null, PASSWORD, FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Creating courier with existing \"login\" field")
    @Description("Should response with SC 409 conflict and body message \"Этот логин уже используется\"")
    public void createCourierWithExistingLoginFieldTest() {
        createCourier(LOGIN, PASSWORD, FIRST_NAME)
                .then()
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", is(true));
        createCourier(LOGIN, PASSWORD + 123, FIRST_NAME + "s")
                .then()
                .assertThat()
                .statusCode(SC_CONFLICT)
                .and()
                .body("message", is("Этот логин уже используется"));
    }

    @After
    public void clear() {
        deleteCourier(LOGIN, PASSWORD);
    }

}
