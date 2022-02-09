import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        boolean actual1 = palindrome.isPalindrome("Abby");
        assertFalse(actual1);

        boolean actual2 = palindrome.isPalindrome("noon");
        assertTrue(actual2);

        boolean actual3 = palindrome.isPalindrome("a");
        assertTrue(actual3);

        boolean actual4 = palindrome.isPalindrome("");
        assertTrue(actual4);
    }

    @Test
    public void testIsPalindromeCC(){

        CharacterComparator ofo = new OffByOne();

        boolean actual1 = palindrome.isPalindrome("flake",ofo);
        assertTrue(actual1);

        boolean actual2 = palindrome.isPalindrome("flakee",ofo);
        assertFalse(actual2);

        boolean actual3 = palindrome.isPalindrome("bflabkec",ofo);
        assertTrue(actual3);

        boolean actual4 = palindrome.isPalindrome("f",ofo);
        assertTrue(actual4);

        boolean actual5 = palindrome.isPalindrome("",ofo);
        assertTrue(actual5);
    }
}
