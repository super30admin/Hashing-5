class Solution {
    HashMap<Character, HashSet<Character>> map;
    int[] indegrees;
    public String alienOrder(String[] words) {
        this.map = new HashMap<>();
        this.indegrees = new int[26];

        buildGraph(words);
        if(map.size() == 0) return "";

        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for(char key : map.keySet()){
            if(indegrees[key - 'a'] == 0){
                q.add(key);
                sb.append(key);
            }
        }


        while(!q.isEmpty()){
            char curr = q.poll();
            HashSet<Character> neighbours = map.get(curr);
            for(char ne: neighbours){
                indegrees[ne- 'a']--;
                if(indegrees[ne - 'a']==0){
                    q.add(ne);
                    sb.append(ne);                
                }
            }
        }

        if(sb.length() == map.size()) return sb.toString();
        return "";
    }

    private void buildGraph(String[] words){
        for(String word: words){
            for(char c: word.toCharArray()){
                if(!map.containsKey(c)){
                    map.put(c,new HashSet<>());
                }
            }
        }


        for(int i=0;i<words.length-1;i++){
            String first = words[i];
            String second = words[i+1];

            if(first.length() > second.length() && first.startsWith(second)){
                map.clear();
                break;
            }

            for(int k=0;k<first.length() && k<second.length();k++){
                char fChar = first.charAt(k);
                char sChar = second.charAt(k);

                if(fChar!=sChar){
                    HashSet<Character> set = map.get(fChar);
                    if(!set.contains(sChar)){
                        set.add(sChar);
                        indegrees[sChar-'a']++;

                    }
                    break;
                }
            }
        }
    }

}