//TC :- O(V+E)
//SC :- O(V+E)
class AlienDictionary {
    public String alienOrder(String[] words) {
        StringBuilder result = new StringBuilder();
        HashMap<Character, HashSet<Character>> map = new HashMap<>(); // adjacency map
        int[] indegrees = new int[26];
        buildGraph(words, map, indegrees);
        int count = 0;
        Queue<Character> q = new LinkedList<>();
        for (char key : map.keySet()) {
            if (indegrees[key - 'a'] == 0) { // add independent node in the queue
                q.add(key);
                count++;
            }
        }
        if (q.isEmpty())
            return "";
        while (!q.isEmpty()) {
            char parent = q.poll();
            result.append(parent);
            HashSet<Character> children = map.get(parent);
            for (char child : children) {
                indegrees[child - 'a']--;
                if (indegrees[child - 'a'] == 0) {
                    q.add(child);
                    count++;
                }
            }
        }
        if (count == map.size())
            return result.toString();
        return "";

    }

    private void buildGraph(String[] words, HashMap<Character, HashSet<Character>> map, int[] indegrees) {
        // add all the characters of all the words in the map
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!map.containsKey(c)) {
                    map.put(c, new HashSet<>());
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String firsrtWord = words[i];
            String secondWord = words[i + 1];

            if (firsrtWord.length() != secondWord.length() && firsrtWord.startsWith(secondWord)) {
                map.clear();
                break;
            }

            for (int j = 0; j < firsrtWord.length() && j < secondWord.length(); j++) {
                char fchar = firsrtWord.charAt(j);
                char schar = secondWord.charAt(j);

                if (fchar != schar) {
                    HashSet<Character> fset = map.get(fchar);
                    if (!fset.contains(schar)) {
                        fset.add(schar);
                        indegrees[schar - 'a']++;
                    }
                    break; // break when we get fist differing character in both the words
                }
            }
        }

    }
}