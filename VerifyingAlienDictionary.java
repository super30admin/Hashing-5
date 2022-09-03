import java.util.HashMap;

//Time Complexity: O(n.k); where n is number of words and k is avg length of a word.
//Space Complexity: O(1) as map will at most have 26 chars
public class VerifyAlienDictionary { 
	/**Approach: HashMap**/
	HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        map = new HashMap<>();
        for(int i=0; i<order.length(); i++){//O(1)
            char c= order.charAt(i);
            map.put(c, i);
        }        
        for(int i=0; i<words.length-1; i++){//O(n)
            String word1 = words[i];
            String word2 = words[i+1];            
            if(isNotSorted(word1, word2)) return false;           
        }        
        return true;
    }
    private boolean isNotSorted(String s1, String s2){
        for(int i=0; i<s1.length() && i<s2.length(); i++){//O(k)
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);                
            if(c1 != c2){ 
                return map.get(c1) > map.get(c2);
            }
        }
        if(s1.length() > s2.length()) return true;
        return false;
    }

}
