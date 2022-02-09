public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y){
        //x和y只差一，返回true
        int diff = x - y;
        if(diff == 1 || diff == -1){
            return true;
        }
        return false;
    }
}
