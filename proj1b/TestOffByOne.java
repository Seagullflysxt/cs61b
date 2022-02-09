import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        boolean actual1 = offByOne.equalChars('a', 'b');
        assertTrue(actual1);

        boolean actual2 = offByOne.equalChars('e', 'c');
        assertFalse(actual2);

        boolean actual3 = offByOne.equalChars('e', 'd');
        assertTrue(actual3);

        boolean actual4 = offByOne.equalChars('e', 'E');
        assertFalse(actual4);

        boolean actual5 = offByOne.equalChars('\0', 'd');
        assertFalse(actual5);

        boolean actual6 = offByOne.equalChars('A', 'B');
        assertTrue(actual6);

        boolean actual7 = offByOne.equalChars('\0', '\0');
        assertTrue(actual7);


    }

}
