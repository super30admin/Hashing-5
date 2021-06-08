class AlienDictionary {
    
    // TC: O(n * maxLen(word))  (where n -> no. of words,)
    // SC: O(N*(N-1)/2)     --> total number of edges in the graph  
    
    private HashMap<Character, Set<Character>> edges;
    private HashMap<Character, Integer> indegrees;
    
    public String alienOrder(String[] words){
        edges = new HashMap<>();
        indegrees = new HashMap<>();
        
        // Construct the graph
        buildGraph(words);
        
        // Processing -- Topological Sort (BFS)
        Queue<Character> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        
        // Get the starting points --> the nodes where indegrees is zero
        for(char c : indegrees.keySet()){
            if(indegrees.get(c) == 0){
                q.offer(c);
                sb.append(c);
            }
        }
        
        while(!q.isEmpty()){
            char front = q.poll();
            for(char edge : edges.get(front)){
                indegrees.put(edge, indegrees.get(edge)-1);
                
                // If the indegree count of the character (edge) becomes zero - we add it to the queue and final output sequence string
                if(indegrees.get(edge) == 0){
                    q.offer(edge);
                    sb.append(edge);
                }
            }
        }
        
        // Check Ordering
        if(sb.length() < indegrees.size())
            return "";
        
        return sb.toString();
    }
    
    private void buildGraph(String[] words){
        // For every unique character we create a key in both edges and indegrees hashmaps
        for(String word : words){
            for(char c : word.toCharArray()){
                edges.put(c, new HashSet<>());
                indegrees.put(c, 0);
            }
        }
        
        // We calculate the dependencies for each word to its next word in the way they are sorted
        for(int i = 0; i < words.length - 1; i++){
            String word1 = words[i];
            String word2 = words[i+1];
            
            int n = word1.length();
            int m = word2.length();
            
            for(int j = 0; j < n && j < m; j++){
                char out = word1.charAt(j);
                char in = word2.charAt(j);
                
                // When we can't make distinction between two words --> "abc" , "ab"
                if(n > m && word1.startsWith(word2)){
                    indegrees.clear();
                    return;
                }
                
                if(out != in){
                    if(!edges.get(out).contains(in)){
                        edges.get(out).add(in);
                        indegrees.put(in, indegrees.get(in) + 1);
                    }
                    break;
                }
            }
        }
    }
}