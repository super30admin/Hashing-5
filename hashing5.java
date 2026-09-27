//problem1
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

        for(char c : map.keySet()){
            if(indegrees[c - 'a'] == 0){
                q.add(c);
                sb.append(c);
            }
        }

        while(!q.isEmpty()){
            char c = q.poll();
            for(char ne : map.get(c)){
                indegrees[ne - 'a']--;
                if(indegrees[ne - 'a'] == 0){
                    q.add(ne);
                    sb.append(ne);
                }
            }
        }

        if(sb.length() == map.size()) return sb.toString();  // cycle
        return "";
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
                        indegrees[sChar - 'a']++;
                    }
                   break;
                }
            }
        }
    }
}

//problem2
class Solution {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        this.map = new HashMap<>();
        for(int i=0; i<order.length(); i++){
            char c = order.charAt(i);
            map.put(c, i);
        }

        for(int i=0; i<words.length-1; i++){
            String first = words[i];
            String second = words[i+1];

            if(notSorted(first, second)) return false;
        }

        return true;
    }

    private boolean notSorted(String first, String second){

        for(int i=0; i<first.length() && i < second.length(); i++){
            char fChar = first.charAt(i);
            char sChar = second.charAt(i);

            if(fChar != sChar){
                return map.get(fChar) > map.get(sChar);  // apple, app
            }
        }

        return first.length() > second.length();
    }
}
