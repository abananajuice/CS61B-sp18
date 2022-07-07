public class OffByN implements CharacterComparator{
    private int distance=1;
    @Override
    public boolean equalChars(char x, char y){
        int diff = x - y;
        if(java.lang.Math.abs(diff)==distance){
            return true;

        }
        return false;
    }

    public OffByN(int N){
        distance = N;
    }
}