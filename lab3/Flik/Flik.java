/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        // Integer is a object which is different with int.Bug is at here.
        return a == b;
    }
}
