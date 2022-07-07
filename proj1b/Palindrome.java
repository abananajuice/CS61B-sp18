public class Palindrome {
    public Deque wordToDeque(String word){
        Deque<Character> res = new ArrayDeque<>();
        for(int i =0;i<word.length();i++){
            // get ith char from string.From CSDN;
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word){
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);

    }
    private boolean isPalindromeHelper(Deque word){
        // use <=1 to make function proper to odd length string.
        if(word.size()<=1){
            return true;
        }
        else if(!word.removeFirst().equals(word.removeLast())) {
            return false;
        }
        else {
            return isPalindromeHelper(word);
        }

    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque,cc);

    }


    private boolean isPalindromeHelper(Deque<Character> word,CharacterComparator cc){
        //change Deque to specific type to make "cc.equalChars()" could run.
        // use <=1 to make function proper to odd length string.
        if(word.size()<=1){
            return true;
        }
        else if(!cc.equalChars(word.removeFirst(),word.removeLast())) {
            return false;
        }
        else {
            return isPalindromeHelper(word,cc);
        }

    }
}
