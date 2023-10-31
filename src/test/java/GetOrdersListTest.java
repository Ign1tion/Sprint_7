import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static steps.OrderSteps.getOrdersList;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersListTest extends TestData {
    @Test
    @DisplayName("Get orders list")
    public void getOrdersListTest() {
        getOrdersList()
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body(notNullValue());
    }
}
