package hw3.hash;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* TODO: Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */
    private ArrayList<Integer> getParams(){
        int params_num = StdRandom.uniform(1,10);
        ArrayList<Integer> params = new ArrayList<>(params_num);

        for(int i=0;i<params_num;i++){
            params.add(StdRandom.uniform(0,255));
        }

        return params;
    }
    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();


        // Your code here.
        int N = 100;
        ArrayList<Integer> residual = new ArrayList<>(4);
        residual.add(1);
        residual.add(2);
        residual.add(3);
        residual.add(4);

        // 由于int型只有4个字节（32位）由于地位都是相同的（1，2，3，4）因此所有的hashcode是相同的
        for(int i=0;i<N;i++){
            ArrayList<Integer> params = getParams();
            params.addAll(residual);
            deadlyList.add(new ComplexOomage(params));
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
