// Time Complexity : O(C) total length of all the words in the array
// Space Complexity : O(U + min(U^2, N)), where U is no. of unique letters, n is no. of strings
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Create count map and adjacency list map, initialize them
// Fill the map by comparing adjacent words and their characters
// We will all put the count of indegree in counts map
// Now perform BFS starting with nodes with 0 indegree
// Append the character to the answer string
// Decrease the indegree count of all the nodes which are adjacent to this one
// Repeat till the queue is empty
// Check the length of answer string, it should be equal to no. of charaters we have
// If not this means there is some cycle and indegree didn't went to 0 for those chars
// Return empty string in this case, otherwise return the answer string
class Solution {
    public String alienOrder(String[] words) {
        //find unique letters
        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer> counts = new HashMap<>(); //in-degree count
        for(String word: words){
            for(char c: word.toCharArray()){
                counts.put(c, 0);
                adjList.put(c, new ArrayList<>());
            }
        }
        //Find edges
        for(int i = 0; i < words.length - 1; i++){
            String word1 = words[i];
            String word2 = words[i+1];
            //Check prefix, if this is the case its invalid and return empty string
            if(word1.length() > word2.length() && word1.startsWith(word2))
                return "";
            //Find non matching charactor and add to adjList
            for(int j = 0; j < Math.min(word1.length(), word2.length()); j++){
                if(word1.charAt(j) != word2.charAt(j)){
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }
        //Perform BFS
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for(Character c: counts.keySet()){
            if(counts.get(c).equals(0))
                q.add(c);
        }
        while(!q.isEmpty()){
            char c = q.remove();
            sb.append(c);
            for(char next: adjList.get(c)){
                counts.put(next, counts.get(next) - 1);
                if(counts.get(next).equals(0))
                    q.add(next);
            }
        }
        if(sb.length() < counts.size())
            return "";
        return sb.toString();
    }
}
