import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
   // Uncomment this class once you've created your CharacterComparator interface and OffByOne class.
    @Test
    public void TestequalChars(){
        OffByOne testStr = new OffByOne();
        assertTrue(testStr.equalChars('a','b'));
        assertTrue(testStr.equalChars('b','a'));
        assertFalse(testStr.equalChars('a','c'));
        assertFalse(testStr.equalChars('a','z'));



    }
}
