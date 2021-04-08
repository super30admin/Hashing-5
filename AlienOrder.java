// Time Complexity : The time complexity is O(mn) where m is the length of the array and n is the lenght of each word
// Space Complexity : Te space complexity is O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    Map<Character,Set<Character>> map;
    int[] indegree;
    public String alienOrder(String[] words) {

        map = new HashMap<>();
        indegree = new int[26];
        StringBuilder res = new StringBuilder();

        //build a graph from the given words
        buildGraph(words);

        Queue<Character> q = new LinkedList<>();

        //find the independent nodes
        for(char c:map.keySet()){
            if(indegree[c-'a'] == 0){
                q.offer(c);
            }
        }

        //the independent letters should have better precedence
        while(!q.isEmpty()){
            char ch = q.poll();
            res.append(ch);

            for(char c:map.get(ch)){
                indegree[c-'a']--;
                if(indegree[c-'a'] == 0){
                    q.offer(c);
                }
            }
        }
        if(res.length() != map.size()) return "";
        return res.toString();
    }

    public void buildGraph(String[] words){

        //letters which may not be compared should also be in the graph
        for(String word:words){
            for(int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                if(!map.containsKey(ch)){
                    map.put(ch,new HashSet<>());
                }
            }
        }

        // compare every consecutive words and form a graph
        for(int i=0;i<words.length-1;i++){
            String s1 = words[i];
            String s2 = words[i+1];

            if(s1.startsWith(s2) && s1.length() > s2.length()){
                map.clear();
                return;
            }
            int j=0;
            int k=0;
            while(j < s1.length() && k < s2.length()){
                char c1 = s1.charAt(j);
                char c2 = s2.charAt(k);
                if(c1 != c2){
                    if(!map.get(c1).contains(c2)){
                        map.get(c1).add(c2);
                        indegree[c2 - 'a']++;
                    }
                    break;
                }
                j++;
                k++;
            }
        }
    }
}