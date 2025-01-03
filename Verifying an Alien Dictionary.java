// 953. Verifying an Alien Dictionary

// In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
// Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
// Example 1:
// Input: words = ["hello", "leetcode"], order =
// "hlabcdefgijkmnoparstuvwxyz"
// Output: true
// Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
// Example 2:
// Input: words = ["word", "world", "row"], order =
// "worldabcefghijkmnpqstuvxyz"
// Output: false
// Explanation: As 'd' comes after 'I' in this language, then words [®]
// > words [1], hence the sequence is unsorted.
// Example 3:
// Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
// Output: false
// Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules
// "apple" > "app", because '!' > 'e', where 'e' is defined as the blank character which is less than any other character .

// "static void main" must be defined in a public class.
//tc-O(n) sc-(1)
public class Main {
    HashMap<Character, Integer> map;
    public void main(String[] args) {
        //System.out.println("Hello World!");
        String[] words = new String[]{"word", "world", "row"};
        String order = "worldabcefghijkmnpqstuvxyz";
        
        System.out.println(isAlienSorted(words, order));
        
        
        
    }
    
   
    public boolean isAlienSorted(String[] words, String order){
        this.map = new HashMap<>();
        
        for(int i = 0; i < order.length(); i++){//putting order of alphabet in hashmap
            char c = order.charAt(i);
            map.put(c, i);
        }
        
        for(int i = 0; i< words.length - 1; i++){//taking 2 words at a time thats why len-1
            String first = words[i];
            String second = words[i+1];
            
            if(notSorted(first, second)) return false;
            
        }
        
        return true;
        
    }
    
    private boolean notSorted(String first, String second){
        for(int i = 0; i < first.length() && i < second.length(); i++){
            char fChar = first.charAt(i);
            char sChar = second.charAt(i);
            
            if(fChar != sChar){
                return map.get(fChar) > map.get(sChar);
           } 
        }
        
        return first.length() > second.length();
    }
    
}