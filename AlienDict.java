//Time: O(NK + (V+E)) | Space: O(1)

class Solution {
    Map<Character, Set<Character>> map;
    int[] indegrees;
    public String alienOrder(String[] words) {
        if(words.length == 0) return "";
        map = new HashMap<>();
        indegrees = new int[26];
        buildGraph(words);
        Queue<Character> q = new LinkedList<>();
        for(char key: map.keySet()) {
            if(indegrees[key-'a'] == 0)
                q.add(key);
        }
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            char currChar = q.poll();
            sb.append(currChar);
            Set<Character> set = map.get(currChar);
            for(char c: set) {
                indegrees[c-'a']--;
                if(indegrees[c-'a'] == 0) q.add(c);
            }
        }
        if(sb.length() < map.size()) return "";
        return sb.toString();
    }
    private void buildGraph(String[] words) {
        for(String word: words) {
            for(int i=0;i<word.length();i++) {
                char c = word.charAt(i);
                if(!map.containsKey(c))
                    map.put(c, new HashSet<>());
            }
        }
        for(int i=1;i<words.length;i++){
            String word1 = words[i-1];
            String word2 = words[i];
            int j=0;
            for(;j<word1.length() && j< word2.length();){
                char fChar = word1.charAt(j);
                char sChar = word2.charAt(j);
                if(fChar != sChar) {
                    Set<Character> set = map.get(fChar);
                    if(!set.contains(sChar)) {
                        set.add(sChar);
                        indegrees[sChar-'a']++;
                    }
                    break;
                }
                j++;
            }
            if((j == word1.length() || j == word2.length()) && word1.length() > word2.length()) {
                map = new HashMap<>();
                break;
            }
        }
    }
}