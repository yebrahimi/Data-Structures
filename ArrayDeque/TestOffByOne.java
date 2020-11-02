import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    static Palindrome p = new Palindrome();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        // Tests that should be true
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertTrue(offByOne.equalChars('e', 'f'));
        assertTrue(offByOne.equalChars('f', 'e'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertTrue(offByOne.equalChars('1', '2'));

        // Tests that should be false
        assertFalse(offByOne.equalChars('a', 'c'));
        assertFalse(offByOne.equalChars('c', 'c'));
        assertFalse(offByOne.equalChars('c', 'a'));
        assertFalse(offByOne.equalChars('g', 't'));
        assertFalse(offByOne.equalChars('&', '@'));
        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars('A', 'b'));
        assertFalse(offByOne.equalChars('c', 'D'));
        assertFalse(offByOne.equalChars('1', '4'));
    }

    @Test
    public void testIsPalindrome() {
        // Tests that should be true
        assertTrue(p.isPalindrome("flake", offByOne));
        assertTrue(p.isPalindrome("aabb", offByOne));
        assertTrue(p.isPalindrome("", offByOne));

        // Tests that should be false
        assertFalse(p.isPalindrome("racecar", offByOne));
        assertFalse(p.isPalindrome("aa", offByOne));
        // assertFalse(p.isPalindrome("", offByOne));
        assertFalse(p.isPalindrome(null, offByOne));
    }
}
