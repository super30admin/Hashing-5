//Time Complexity : O(V+E)
//Space Complexity : O(V+E)
//Did this code run successfully on leetcode : Yes
//Did you face any problem while coding this problem: Had to refer video once
class AlienDictionary {
    HashMap<Character, HashSet<Character>> map;
    int[] indegrees;
    public String alienOrder(String[] words) {
        this.map = new HashMap<>();
        this.indegrees = new int[26];

        buildGraph(words);
        if(map.size() == 0) return "";

        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for(Character c : map.keySet()) {
            if(indegrees[c-'a'] == 0)  {
                queue.add(c);
                sb.append(c);
            }
        }

        if(map.size() == sb.length()) return sb.toString();
        while(!queue.isEmpty()) {
            Character curr = queue.poll();
            HashSet<Character> s = map.get(curr);
            for(char c : s) {
                indegrees[c-'a']--;
                if(indegrees[c-'a'] == 0) {
                    queue.add(c);
                    sb.append(c);
                    if(sb.length() == map.size()) return sb.toString();
                }
            }
            
        }
        return "";
    }

    private void buildGraph(String[] words) {
        for(String word : words) {
            for(int i = 0; i < word.length(); i++) {
                map.putIfAbsent(word.charAt(i), new HashSet<>());
            }
        }

        for(int i = 0; i < words.length-1; i++) {
            String first = words[i];
            String second = words[i+1];
            
            if(first.length() != second.length() && first.startsWith(second)) {
                map.clear();
                break;
            }

            for(int j = 0; j < first.length() && j < second.length(); j++) {
                if(first.charAt(j) != second.charAt(j)) {
                    HashSet<Character> set = map.get(first.charAt(j));
                    if(!set.contains(second.charAt(j))) {
                        set.add(second.charAt(j));
                        indegrees[second.charAt(j)-'a']++;
                    }
                    break;
                }
            }
        }
    }
}