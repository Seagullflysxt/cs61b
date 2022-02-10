public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        //x和y只差一，返回true
        if (x == '\0' || y == '\0') {
            return false;
        }
        int diff = x - y;
        if (diff == 1 || diff == -1) {
            return true;
        }
        return false;
    }
}
