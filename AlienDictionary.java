// Time Complexity : O(n*l)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : NA
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// To find the order, we go through all the characters in the words to produce a graph with adjacency list
// We iterate each pair, in the differing char, we add a edge a -> b and increase indegree of b by 1
// app ,app is valid, but apple, app is not => cant find order - clear map for this edge case
// we do topological sort to find vertex independent of others and add it to sb
// if not all characters are clear => order cannot be found => return empty string

class Solution {
    public String alienOrder(String[] words) {

        int[] indegree = new int[26];
        HashMap<Character, HashSet<Character>> map = new HashMap();
        buildGraph(words, map, indegree);

        int count = 0;
        ArrayDeque<Character> q = new ArrayDeque<>();
        for (char c : map.keySet()) {
            if (indegree[c - 'a'] == 0) {
                q.add(c);
                count++;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char curr = q.poll();
            for (char child : map.get(curr)) {
                indegree[child - 'a']--;
                if (indegree[child - 'a'] == 0) {
                    q.add(child);
                    count++;
                }
            }
            sb.append(curr);
        }

        if (count != map.size())
            return "";
        return sb.toString();
    }

    private void buildGraph(String[] words, HashMap<Character, HashSet<Character>> map, int[] indegree) {

        for (String w : words) {
            for (char c : w.toCharArray()) {
                map.putIfAbsent(c, new HashSet());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i];
            String b = words[i + 1];

            if (a.startsWith(b) && a.length() != b.length()) {
                map.clear();
                return;
            }

            for (int j = 0; j < a.length() && j < b.length(); j++) {
                char aChar = a.charAt(j);
                char bChar = b.charAt(j);

                if (aChar != bChar) {
                    if (!map.get(aChar).contains(bChar)) {
                        map.get(aChar).add(bChar);
                        indegree[bChar - 'a']++;
                    }
                    break;
                }
            }
        }

    }

}