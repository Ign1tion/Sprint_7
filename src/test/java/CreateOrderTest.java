import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import model.Order;

import java.util.List;

import static steps.OrderSteps.cancelOrder;
import static steps.OrderSteps.createOrder;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest extends TestData {
    private static final List<Order> ORDERS = List.of(
            new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, COLOR_GREY),
            new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, COLOR_BLACK),
            new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, BOTH_COLORS),
            new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT));
    private final Order order;

    public CreateOrderTest(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static List<Order> getOrderData() {
        return ORDERS;
    }

    @Test
    @DisplayName("Create order")
    @Description("Creating some orders with different color field")
    public void createOrderTest() {
        var response = createOrder(order);
        response.then()
                .assertThat()
                .body("track", notNullValue())
                .and()
                .statusCode(SC_CREATED);
        setTrack(response.asString());
    }

    @After
    public void clear() {
        cancelOrder(getTrack());
    }
}
