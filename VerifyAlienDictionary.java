import java.util.HashMap;

// Time Complexity : O(m*n) -> m is the number of words , n is the length of each word
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes

public class VerifyAlienDictionary {

    HashMap<Character,Integer> map;
    public boolean isAlienSorted(String[] words, String order) {

        map = new HashMap<>();

        for(int i=0; i<order.length(); i++){
            char c = order.charAt(i);
            map.put(c,i);
        }
        for(int i=0; i<words.length-1; i++)
        {
            if(notSorted(words[i],words[i+1]))
            {
                return false;
            }
        }

        return true;
    }

    private boolean notSorted(String first, String second){

        for(int i=0; i<first.length() && i<second.length(); i++){
            char fChar = first.charAt(i);
            char sChar = second.charAt(i);

            if(fChar != sChar){
                return map.get(fChar) > map.get(sChar);
            }
        }
        return first.length() > second.length();
    }
}
