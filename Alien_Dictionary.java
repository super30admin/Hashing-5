class Solution{
    int [] indegrees;
    HashMap<Character, HashSet<Character>> map;
    
    public String alienOrder(String [] words){
        map = new HashMap<>(); // TC : O(1)
        indegrees = new int[26];
        
      
        //bfs
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>(); // TC: O(1)
        
        for(char c:map.keySet()){
            if(indegrees[c - 'a'] == 0){
                //add the independent char in sb;
                sb.append(c);
                q.add(c);
            }
        }
        
      
        //processing the q
        while(!q.isEmpty(){
            char curr = q.poll();
            if(map.get(curr) == null || map.get(curr).size() == 0) continue;
          
            //iterating on the dependent char has become independent row
            for(char in:map.get(curr)){
                indegrees[in - 'a'] == 0){
                    q.add(in);
                  
                    sb.append(in);
                }
            }
        }
       if(sb.length() < map.size()){  
          return " ";
       }
       return sb.toString();
              
    }
     
              
    private void buildGraph(String[] words){ // (TC: nk)
        //Iterating over all words and add corresponding character entries in map
        
        for(String word: words){
            for(char c:word.toCharArray()){
                if(!map.containsKey(c)){
                    map.put(c, new HashSet<>());
                }
            }
        }
        for(int i =0; i < words.length - 1; i++){
            String first = words[i];
            String second = words[i+1];
            
            int l1 = first.length();
            int l2 = second.length();
            
            if(l1 >l2 && first.startsWith(second)){
                map.clear();
            }
            for(int j = 0; j < li && j < l2; j++){
                if(first.charAt(j)!= second.charAt(j)){
                    char out = first.charAt(j);
                    char in  = second.charAt(j);
                    
                    if(!map.get(out).contains(in)){
                        map.get(out).add(in);
                        indegrees[in - 'a']++;
                    }
                    break;
                }
            }
        }
    }
              }
              
//TC: O(n*k)
//SC: O(1)
          
              
              
              
