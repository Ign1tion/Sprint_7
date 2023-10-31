import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static steps.CourierSteps.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;

public class LoginCourierTest extends TestData {
    @Before
    @Description("Creating courier before login")
    public void courierCreation() {
        createCourier(LOGIN, PASSWORD, FIRST_NAME);
    }

    @Test
    @DisplayName("valid courier authentication")
    @Description("Should return SC 200 OK and \"id\" in body")
    public void loginCourierTest() {
        loginCourier(LOGIN, PASSWORD)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("courier authentication with null in \"login\" field")
    @Description("Should response with SC 400 bad request and body message \"Недостаточно данных для входа\"")
    public void authenticationWithNullIntLoginField() {
        loginCourier(null, PASSWORD)
                .then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("courier authentication with null in \"password\" field")
    @Description("Should response with SC 400 bad request and body message \"Недостаточно данных для входа\"")
    public void authenticationWithNullInPasswordField() {
        loginCourier(LOGIN, null)
                .then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", is("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("courier authentication with incorrect password field")
    @Description("should return SC 404 not found and body message \"Учетная запись не найдена\"")
    public void authenticationWithWrongPassword(){
        loginCourier(LOGIN, PASSWORD + "s")
        .then()
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", is("Учетная запись не найдена"));

    }
    @Test
    @DisplayName("courier authentication with incorrect login field")
    @Description("should return SC 404 not found and body message \"Учетная запись не найдена\"")
    public void authenticationWithWrongLogin() {
        loginCourier(LOGIN + "s", PASSWORD)
                .then()
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", is("Учетная запись не найдена"));
    }
    @After
    public void clear() {
        deleteCourier(LOGIN, PASSWORD);
    }
}
