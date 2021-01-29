// Time Complexity : O(nxm)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
// Topological Sort
class Solution {
    
    //Step 0: Create DS, find all unique letters
    Map<Character, List<Character>> graph = new HashMap<>();
    Map<Character, Integer> indegree = new HashMap<>();
    
    // BFS
    public String alienOrder(String[] words) {
        
        buildGraph(words);
        
        if(graph.size() == 0)
            return "";

        //Step 2: Breadth-first search
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue =  new LinkedList<>();
        for(char c: indegree.keySet()){
            if(indegree.get(c).equals(0)){
                queue.add(c);
            }   
        }
        
        while(!queue.isEmpty()){
            char c = queue.poll();
            sb.append(c);
        
            for(Character next: graph.get(c)){
                indegree.put(next, indegree.get(next) -1);
                if(indegree.get(next).equals(0)){
                    queue.add(next);
                }
            }
        }
        
        if(sb.length() < indegree.size())
            return "";
        
        return sb.toString();
    }
    
    private void buildGraph(String[] words){
        
        for(String s: words){
            for(char c : s.toCharArray()){
                indegree.put(c, 0);
                graph.put(c, new ArrayList<>());
            }
        }
        
        //Step 1: Find all edges
        for(int i=0; i<words.length-1; i++){
            String word1 = words[i];
            String word2 = words[i+1];
            
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                graph.clear();
                return;
            }
            
            // Find the first non match and insert the corresponding relation.
            int minLen = Math.min(word1.length(), word2.length());
            for (int j = 0; j < minLen ; j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    graph.get(word1.charAt(j)).add(word2.charAt(j));
                    indegree.put(word2.charAt(j), indegree.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }
    }
}