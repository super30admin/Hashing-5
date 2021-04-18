/*
TC : O(V+E), 
SC: O(26), constant
building graph is very time consuming

*/

class Solution {
    
    HashMap<Character, HashSet<Character>> map;
    int[]indegrees;
    
    public String alienOrder(String[] words) {
        
        if(words.length == 0 || words == null){
            return "";
        }
        
        
        this.map = new HashMap<>();
        this.indegrees = new int[26];
        buildGraph(words);
        
        
        //add independent nodes
        Queue<Character> queue = new LinkedList<>();
        //since indegree array is of 26 letter, so add independent nodes which are only in keyset of hashmap 
        for(Character c : map.keySet()){
            if(this.indegrees[c - 'a'] == 0){
                queue.add(c);
            }
        }
        
        StringBuilder res = new StringBuilder();
        //bfs - topological sorting
        while(!queue.isEmpty()){
            char c = queue.poll();
            res.append(c);
            for(char next: map.get(c)){
                this.indegrees[next - 'a']--;
                if(this.indegrees[next-'a'] == 0){
                    queue.add(next);
                }
            }
        }
        
        if(res.length() < map.size()){
            return "";
        }
        return res.toString();
    }
    
    private void buildGraph(String[]words){
        for(String word : words){ // O(nk), n words of. k length
            for(int i = 0; i < word.length();i++){
                if(!map.containsKey(word.charAt(i))){
                    map.put(word.charAt(i),new HashSet<>());
                }
            }
        }
        for(int i = 0; i < words.length-1;i++){
            String word1 = words[i];
            String word2 = words[i+1];
            
            int m = word1.length();int n = word2.length();
            if(m > n && word1.startsWith(word2)){
                map.clear();
            }
            for(int j = 0; j < m && j < n;j++){
                char out = word1.charAt(j);
                char in = word2.charAt(j);
                if(out != in){ //wherever there is first mismatch we determine the order and break
                    if(!map.get(out).contains(in)){
                        map.get(out).add(in);
                        this.indegrees[in - 'a']++;
                    }
                    break;
                }
            }
                
        }
    }
}