import java.util.*;

/*
Alien Dictionary
approach: similar to course schedule, we can check for independent characters and also, when a mismatch happens we'll know which character comes first
time: O(nxk)
space: O(1)
 */
public class Problem1 {
    HashMap<Character, Set<Character>> adjList;
    int[] indegrees;
    private String generateOrder(String[] words) {
        indegrees = new int[26];
        adjList = new HashMap<>();
        StringBuilder res = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        buildGraph(words);
        for (char c: adjList.keySet()) {
            if (indegrees[c-'a']==0) {
                q.add(c);
            }
        }
        if (q.isEmpty()) return res.toString();
        while (!q.isEmpty()) {
            char popped = q.poll();
            Set<Character> set = adjList.get(popped);
            res.append(popped);
            if (set!=null)
            for (char c: set) {
                indegrees[c-'a']--;
                if (indegrees[c-'a']==0) {
                    q.add(c);
                }
            }
        }

        if (res.length()!=adjList.size()) return "";
        return res.toString();
    }

    private void buildGraph(String[] words) {
        for (String word:words) {
            for (int i=0;i<word.length();i++) {
                char c = word.charAt(i);
                if (!adjList.containsKey(c)) {
                    adjList.put(c, new HashSet<>());
                }
            }
        }

        for (int i=0;i< words.length-1;i++) {
            String first = words[i];
            String second = words[i+1];
            int fl = first.length();
            int j;
            for (j=0;j<first.length() && j<second.length();j++) {
                char fchar = first.charAt(j);
                char schar = second.charAt(j);

                if (fchar!=schar) {
                    if (!adjList.get(fchar).contains(schar)) {
                        indegrees[schar-'a']++;
                        adjList.get(fchar).add(schar);
                    }
                    break;
                }
            }
            if ( fl-j > 0 ) {
                adjList = new HashMap<>();
                break;
            }
        }
    }

    public static void main(String []args) {
        Problem1 problem1 = new Problem1();
        problem1.generateOrder(new String[]{"abc", "ab"});
    }
}
