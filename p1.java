// Time Complexity :O(n*l + v+e)
// Space Complexity :O(v+e)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    //hashmap to keep track of edges 
    HashMap<Character, HashSet<Character>> map;
    //indegrees array
    int[] inde;
    public String alienOrder(String[] words) {
        inde = new int[26];
        map = new HashMap<>();
        helper(words);
        StringBuilder result = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        //put the characters in queue with indegrees 0
        for(int i=0; i<26; i++){
            
            if(inde[i] == 0){
                char c = (char)('a' + i);
                if(map.containsKey(c)){
                    q.add(c);
                    result.append(c);
                }
            }
        }

        //recurse
        while(!q.isEmpty()){
            char ca = q.remove();
            HashSet<Character> set = map.get(ca);
            for(char c: set){
                inde[c-'a']--;
                if(inde[c-'a'] == 0){
                    q.add(c);
                    result.append(c);
                }
            }
        }
        // System.out.println(map);
        if(map.size() == result.length()) return result.toString();
        else return "";
    }

    //helper function to fill the edges hashmap and indegrees array
    private void helper(String[] words){
        for(String word: words){
            for(int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                if(!map.containsKey(c)){
                    map.put(c, new HashSet<>());
                }
            }
        }

        for(int i=0; i<words.length-1; i++){
            String word1 = words[i];
            String word2 = words[i+1];
            // System.out.println(word1);
            // System.out.println(word2);
            for(int j = 0; j<word1.length() && j<word2.length(); j++){
                char w1 = word1.charAt(j);
                char w2 = word2.charAt(j);
                // System.out.println(w1);
                // System.out.println(w2);
                if(w1 != w2){
                    if(!map.get(w1).contains(w2)){
                        map.get(w1).add(w2);
                        inde[w2 - 'a']++;
                        
                    }
                    break;
                }
            }
            if(word1.length() > word2.length() && word1.startsWith(word2)){
                map.clear();
                return;
            }
        }
    }
}