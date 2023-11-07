import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestData {
    protected static final String LOGIN = "JohnCourier";
    protected static final String PASSWORD = "1234";
    protected static final String FIRST_NAME = "John";
    protected static final String LAST_NAME = "Johnson";
    protected static final String ADDRESS = "John's.st";
    protected static final String METRO_STATION = "John's";
    protected static final String PHONE = "+123456789";
    protected static final int RENT_TIME = 1;
    protected static final String DELIVERY_DATE = LocalDate.now().toString();
    protected static final String COMMENT = "Be careful, angry John's dog";
    protected static final List<String> COLOR_BLACK = List.of("BLACK");
    protected static final List<String> COLOR_GREY = List.of("GREY");
    protected static final List<String> BOTH_COLORS = Arrays.asList("BLACK", "GREY");
    protected static String track;

    public static String getTrack() {
        return track;
    }

    public static void setTrack(String track) {
        TestData.track = track;
    }

}
