// TC: O(n)
// SC: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {

    int[] indegrees;
    HashMap<Character, HashSet<Character>> map;

    public String alienOrder(String[] words) {
        map = new HashMap<>();
        indegrees = new int[26];
        buildGraph(words);

        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for (char c : map.keySet()) {
            if (indegrees[c - 'a'] == 0) {
                sb.append(c);
                q.add(c);
            }
        }

        while (!q.isEmpty()) {
            char curr = q.poll();
            if (map.get(curr) == null || map.get(curr).size() == 0)
                continue;
            for (char in : map.get(curr)) {
                indegrees[in - 'a']--;
                if (indegrees[in - 'a'] == 0) {
                    q.add(in);
                    sb.append(in);
                }
            }
        }

        if (sb.length() < map.size()) {
            return "";
        }
        return sb.toString();
    }

    private void buildGraph(String[] words) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!map.containsKey(c)) {
                    map.put(c, new HashSet<>());
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            int l1 = first.length(), l2 = second.length();
            if (l1 > l2 && first.startsWith(second)) {
                map.clear();
            }
            for (int j = 0; j < l1 && j < l2; j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    char out = first.charAt(j);
                    char in = second.charAt(j);
                    if (!map.get(out).contains(in)) {
                        map.get(out).add(in);
                        indegrees[in - 'a']++;
                    }
                    break;
                }
            }
        }
    }
}

