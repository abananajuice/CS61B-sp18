package synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(10);
        assertEquals(10, arb.capacity);
        assertEquals(true, arb.isEmpty());
        arb.enqueue(5);
        arb.enqueue(4);
        assertEquals(5, (int) arb.dequeue());
        assertEquals(4, (int) arb.dequeue());
        arb.enqueue(3);
        arb.enqueue(2);
        assertEquals(3, (int) arb.peek());
        assertEquals(3, (int) arb.dequeue());
        assertEquals(1, arb.fillCount);
        arb.enqueue(3);
        arb.enqueue(4);
        for (int i : arb) {
            System.out.println(i);
        }
    }

    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);

    }
} 
