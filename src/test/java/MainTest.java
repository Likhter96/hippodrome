import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {

    @Test
    @Timeout(22)
    @Disabled
    void mainMethodWorkLess22SecondTest() throws Exception {
        Main.main(null);
    }
}
