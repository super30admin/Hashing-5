public class Solution {
    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public String alienOrder(String[] words) {
        // Write your code here
        if(words == null || words.length == 0) return "";
        HashMap<Character, List<Character>> map = new HashMap<>();
        int [] indegrees = new int[26];
        buildgraph(words, map, indegrees);
        //bfs
        Queue<Character> q = new LinkedList<>(); 
        StringBuilder result = new StringBuilder();
        for(char key : map.keySet()){  //for  first character in string
            if(indegrees[key - 'a'] == 0){
                q.add(key);
                result.append(key);
            }
        }
        //bfs
        while(!q.isEmpty()){
            char c = q.poll();
            List<Character> li = map.get(c);
            for(char edge  : li){
                indegrees[edge - 'a']--;
                if(indegrees[edge - 'a'] == 0){
                    q.add(edge);
                    result.append(edge);
                    if(result.length() == map.size()) return result.toString();
                }
            }
        }
        return "";
    }
    private void buildgraph(String[] words, HashMap<Character, List<Character>> map,int [] indegrees){
        //
        for(String word : words){
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(!map.containsKey(c)){
                    map.put(c, new ArrayList<>());
                }
            }
        }
        //
        for(int  i = 0; i < words.length - 1; i++){
            String out = words[i];
            String in = words[i+1];
            //placeholder
            if(out.startsWith(in) && out.length() != in.length()){
                map.clear();
                break;
            }
            for(int k = 0; k < out.length() && k < in.length(); k++){
                char outChar  = out.charAt(k);
                char inChar = in.charAt(k);
                if(outChar != inChar){
                    map.get(outChar).add(inChar);
                    indegrees[inChar - 'a']++;
                    break;
                }
            }
        }
    }
}
