// Time Complexity : O(n*l) where n is the no. of words and l is the average length of each word
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Create map of the charaters and their sequence in the sentence
// Now for each pair of words starting from begining chech subsequent charaters 
// whether the order is maintained as per values in map
// Return false if order doesn't satify else return true
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> hm = new HashMap<>();
        int i = 0;
        for(char c: order.toCharArray()){
            hm.put(c,i++);
        }
        for(i = 1; i < words.length; i++){
            String a = words[i-1];
            String b = words[i];
            int x = compare(a, b, hm);
            if(x >= 1)
                return false;
        }
        return true;
    }
    private int compare(String a, String b, Map<Character, Integer> hm){
        int i = 0;
        while(i < a.length() && i < b.length()){
            int diff = hm.get(a.charAt(i)) - hm.get(b.charAt(i));
            i++;
            if(diff != 0)
                return diff;
        }
        if(a.length() == b.length()){
            return 0;
        }
        if(a.length() < b.length())
            return -1;
        else 
            return 1;
    }
}