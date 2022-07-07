import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    static CharacterComparator offByx = new OffByN(5);

    @Test
    public void TestequalChars(){
        OffByN offByx = new OffByN(5);
        assertTrue(offByx.equalChars('f','a'));
        assertTrue(offByx.equalChars('a','f'));
        assertFalse(offByx.equalChars('a','a'));
    }
}
