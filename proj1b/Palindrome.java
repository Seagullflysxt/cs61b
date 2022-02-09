public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> d1 = new ArrayDeque<>();
        int length = word.length();
        d1.addFirst(word.charAt(0));
        for(int i = 1; i < length; i++) {
            Character tmp = word.charAt(i);
            d1.addLast(tmp);
        }
        return d1;
    }

    public boolean isPalindrome(String word){
        //boolean flag = true;
        if(word.length() <=1){
            return true;
        }
        Deque d1 = wordToDeque(word);
       /* for(int i = 0; i <= ((d1.size() / 2) -1); i++){
            flag = isPalindrome(d1,i,d1.size() - 1 - i);
            if(flag == false){
                break;
            }
        }*/
        return isPalindrome(d1);

    }
    private boolean isPalindrome(Deque d){
        /*if(d.get(p1).equals(d.get(p2))){
            return true;
        }
        return false;*/
        while(d.size() > 1){
            return d.removeFirst() == d.removeLast() && isPalindrome(d);
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word.length() <=1){
            return true;
        }
        Deque d1 = wordToDeque(word);

        return isPalindrome(d1,cc);
    }

    private boolean isPalindrome(Deque d,CharacterComparator cc){
        while(d.size() > 1){
            return cc.equalChars((char)d.removeFirst(),(char)d.removeLast())
                    && isPalindrome(d,cc);
        }
        return true;
    }
}
