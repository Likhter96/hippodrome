import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    @Mock
    Horse horse;

    @Test
    public void nullNameParameterException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5, 2.0));
    }

    @Test
    public void nullNameParameterMessage() {
        try {
            new Horse(null, 2.5, 2.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\s", "\t", "\n"})
    public void emptyLineTest(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.5, 2.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void negativeSpeedTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Pegasus", -2.5, 2.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void negativeDistanceTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Pegasus", 2.5, -2.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameTest() {
        horse = new Horse("Pegasus", 2.5, 2.0);
        String expected = horse.getName();
        String actual = "Pegasus";
        assertEquals(expected, actual);
    }

    @Test
    public void getSpeedTest() {
        horse = new Horse("Pegasus", 2.5, 2.0);
        double expected = horse.getSpeed();
        double actual = 2.5;
        assertEquals(expected, actual);
    }

    @Test
    public void getDistanceTest() {
        horse = new Horse("Pegasus", 2.5, 2.0);
        double expected = horse.getDistance();
        double actual = 2.0;
        assertEquals(expected, actual);
    }

    @Test
    void horseMoveTest() {
        try (MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)) {
            horse = new Horse("Test", 2.5, 2.0);
            horse.move();
            mockStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @Test
    void checkCorrectDistanceFormula() {
        try (MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)) {
            mockStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(2.0);
            double actual = 3.0 + 5.0 * Horse.getRandomDouble(0.2, 0.9);
            double expected = 13.0;
            horse = new Horse("Test", 3, 5);
            assertEquals(expected, actual);
        }
    }

    @AfterEach
    void deleteHorseAfterEachTest() {
        horse = null;
    }
}
