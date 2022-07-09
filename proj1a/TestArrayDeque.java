import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDeque {
    static ArrayDeque list = new ArrayDeque();

    @Test
    public void testadd(){
        ArrayDeque d = new ArrayDeque();
        for (int i = 0; i < 40; i++) {
            d.addFirst(i);
            d.addLast(i);
        }
        for (int i = 0; i < 70; i++) {
            d.removeLast();
        }
        d.printDeque();
    }


}
