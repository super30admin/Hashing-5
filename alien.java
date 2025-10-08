//Time Complexity: O(N * L) to build graph and O(V + E ) to traverse the graph. (N is number of words, L is average word length, V Vertices and E Edges)
//Space Complexity: O(V + E)

//Build graph: add every unique char as a node; for each adjacent word pair, add edge (first mismatching char f -> s); if a longer word prefixes a shorter one, return "".
//Topological sort via DFS: use `path` (on-stack) to detect cycles and `visited` to avoid rework; append chars to `sb` post-order.
//If any cycle/invalid prefix, return ""; otherwise reverse `sb` to produce a valid alien character order.

class Solution {
    HashMap<Character, HashSet<Character>> map;

    boolean[] path;  // are we visiting the smae node in the path
    boolean[] visited;  // we visited that node and there is no cycle
    StringBuilder sb;
    
    public String alienOrder(String[] words) {
        this.map = new HashMap<>();
        this.path = new boolean[26];
        this.visited = new boolean[26];
        this.sb = new StringBuilder();
      
        buildGraph(words);
        if(map.size() == 0) return "";

        for(char c: map.keySet()){
            if(hasCycle(c)) return "";
        }

        return sb.reverse().toString();
    }

    private boolean hasCycle(char c){

        if(path[c - 'a']) return true;
        if(visited[c - 'a']) return false;

        path[c - 'a'] = true;

        for(char ne : map.get(c)){
            if(hasCycle(ne)) return false;
        }

        path[c - 'a'] = false;
        visited[c - 'a'] = true;

        sb.append(c);

        return false;
    }

    private void buildGraph(String[] words){ 

        for(String word: words){
            for(char c: word.toCharArray()){
                map.put(c, new HashSet<>());
            }
        }

        for(int i=0; i<words.length-1; i++){
            String first = words[i];
            String second = words[i+1];

            if(first.startsWith(second) && first.length() > second.length()){
                map.clear();
                return;
            }

            for(int j=0; j<first.length() && j < second.length(); j++){
                char fChar = first.charAt(j);
                char sChar = second.charAt(j);

                if(fChar != sChar){
                    HashSet<Character> set = map.get(fChar);
                    if(!set.contains(sChar)){
                        set.add(sChar);
                    }
                   break;
                }
            }
        }
    }
}
