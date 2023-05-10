import java.util.HashMap;
/*
Verifying an Alien Dictionary
approach: at a character mismatch we need to check which character comes first
time: O(nxk)
space: O(1)
 */
public class Problem2 {
    HashMap<Character, Integer> orderMap;
    private boolean isSorted(String[] words, String order) {
        orderMap = new HashMap<>();

        for (int i=0;i<order.length();i++) {
            char c = order.charAt(i);
            orderMap.put(c, i);
        }

        for (int i=0;i<words.length-1;i++) {
            String first = words[i];
            String second = words[i+1];

            if (notSorted(first, second)) return false;
        }

        return true;
    }

    private boolean notSorted(String first, String second) {
        int fl = first.length(), sl = second.length();

        for (int j=0;j<first.length() && j<second.length();j++) {
            char fchar = first.charAt(j);
            char schar = second.charAt(j);

            if (fchar!=schar && orderMap.get(schar) < orderMap.get(fchar)) return false;
        }
        if (sl < fl) return false;
        return true;
    }

    public static void main(String []args) {
        Problem2 problem2 = new Problem2();
        System.out.println("isSorted "+ problem2.isSorted(new String[]{"abc", "abcd"}, "abc"));
    }
}
