import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne(){
        boolean actual1 = offByOne.equalChars('a','b');
        assertTrue(actual1);

        boolean actual2 = offByOne.equalChars('e','c');
        assertFalse(actual2);

        boolean actual3 = offByOne.equalChars('e','d');
        assertTrue(actual3);


    }

}
