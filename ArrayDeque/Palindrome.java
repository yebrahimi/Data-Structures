public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() == 0) {
            return true;
        }
        String backWord = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            backWord += word.charAt(i);
        }
        return backWord.equals(word);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        int len = word.length();
        if (len == 0) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        for (int i = 0; i < d.size(); i++) {
            if (len % 2 != 0 && i == len / 2) {
                break;
            }
            if (!cc.equalChars(d.get(i), d.get(d.size() - i - 1))) {
                return false;
            }
        }
        return true;
    }

}
