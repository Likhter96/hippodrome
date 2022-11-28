import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HippodromeTest {
    @Mock
    Horse horse;

    @Test
    void nullParameterInConstructorTest() {
        List<Horse> list = null;
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(list));
    }

    @Test
    void messageTest_WhenNullParameterInConstructor() {
        List<Horse> list = null;
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(list));
        String expected = "Horses cannot be null.";
        String actual = thrown.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void sendEmptyListInConstructorTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void messageTest_sendEmptyListInConstructor() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        String expected = "Horses cannot be empty.";
        String actual = thrown.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void getHorsesReturnTheSameListTest() {
        List<Horse> actual = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            actual.add(new Horse("Horse " + i, 1, 1));
        }
        Hippodrome hippodrome = new Hippodrome(actual);
        List<Horse> expected = hippodrome.getHorses();
        assertEquals(expected, actual);
    }

    @Test
    void checkMoveMethodOnAllHorses() {
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
           // list.add(new Horse("Horse #"+i,1,1));
            list.add(horse);
            horse.move();
        }
        Hippodrome hippodrome = new Hippodrome(list);
        Mockito.verify(horse, Mockito.times(50)).move();
    }

    @Test
    void checkGetWinnerReturnHorseWithBiggestDistance() {
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Horse("Horse" + i, 3, i));
        }
        Hippodrome hippodrome = new Hippodrome(list);
        Horse expected = list.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();

        Horse actual = hippodrome.getWinner();
        assertEquals(expected, actual);
    }

    @AfterEach
    void deleteHorseAfterEachTest() {
        horse = null;
    }
}
