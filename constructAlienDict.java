//Time - O(n*k)
//Space - O(n*k)
class Solution {
    HashMap<Character, Set<Character>> edges;
    HashMap<Character, Integer> indegree;
    public String alienOrder(String[] words) {
        edges = new HashMap<>();
        indegree = new HashMap<>();
        Queue<Character> q = new LinkedList<>();
        for(int i=0; i<words.length; i++){
            for(int j=0; j<words[i].length(); j++){
                edges.put(words[i].charAt(j), new HashSet<>());
                indegree.put(words[i].charAt(j),0);
            }
        }
        buildGraph(words);
        System.out.print(edges);

        for(char key: indegree.keySet()){
            if(indegree.get(key) == 0){
                q.add(key);
            }
        }
        StringBuilder result = new StringBuilder();
        while(!q.isEmpty()){ //O(V+E) constant here 
            char c = q.poll();
            result.append(c);
            for(char child: edges.get(c)){
                int indeg = indegree.get(child);
                indeg = indeg-1;
                indegree.put(child, indeg);
                if(indegree.get(child) == 0){
                    q.add(child);
                }
            }
        }
        if(result.length()!=edges.size()) return "";
        return result.toString();
    }
    void buildGraph(String[] words){
        for(int i=0; i<words.length-1; i++){
            String firstWord = words[i];
            String secondWord = words[i+1];
            if(!firstWord.equals(secondWord) && firstWord.startsWith(secondWord)) {
                edges = new HashMap<>();
                indegree = new HashMap<>();
                return;
            }
            for(int j=0; j<firstWord.length()&&j<secondWord.length(); j++){
                char firstChar  =  firstWord.charAt(j);
                char secondChar  = secondWord.charAt(j);
                if(firstChar!=secondChar){
                    if(!edges.get(firstChar).contains(secondChar)){
                        edges.get(firstChar).add(secondChar);
                        int degree = indegree.get(secondChar);
                        indegree.put(secondChar, degree+1);
                    }
                    break;
                }
            }
        }
    }
}