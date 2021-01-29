// Time Complexity : O(n^2)
// Space Complexity : O(n) n: number of characters in the order
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
// Using HashMap
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if(words == null || order==null || order.equals("")) {
			return false;
		}
		
        Map<Character, Integer> mapOr = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
			mapOr.put(order.charAt(i), i);
		}
        
        for (int i = 0; i < words.length-1; i++) {
			String w1 = words[i];
			String w2 = words[i+1];
			
			if(!isSorted(w1, w2, mapOr)) {
				return false;
			}
		}
        return true;
    }
    
    public static boolean isSorted(String w1, String w2, Map<Character, Integer> map) {
		int i=0;
		int j=0;
		
		while(i< w1.length() && j < w2.length()) {
            
			if( map.get(w1.charAt(i)) <  map.get(w2.charAt(j)) ){
				return true;
			}else if ( map.get(w1.charAt(i)) >  map.get(w2.charAt(j))){
				return false;
			}
			i++;
			j++;
		}
		return w1.length() < w2.length();
	}
}