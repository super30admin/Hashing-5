# Hashing-5

## Problem 1 Alien Dictionary https://leetcode.com/problems/alien-dictionary/

// Time Complexity : O(M\*l), M : Number of words, l -> Avg length of each word
// Space Complexity : O(Size of trie)

class Solution {
Map<Character, Set<Character>> map;
int[] indegrees;
public String alienOrder(String[] words) {
if(words == null || words.length == 0) {
return "";
}
map = new HashMap<>();
indegrees = new int[26];

        buildGraph(words);

        Queue<Character> queue = new LinkedList<>();

        StringBuilder sb = new StringBuilder();

        for(char c : map.keySet()) {
            if(indegrees[c-'a'] == 0) {
                queue.add(c);
                sb.append(c);
            }
        }

        if(queue.size() == 0) {
            return "";
        }

        while(!queue.isEmpty()) {
            char c = queue.poll();
            Set<Character> set = map.get(c);

            for(char nc : set) {
                indegrees[nc - 'a']--;
                if(indegrees[nc - 'a'] == 0) {
                    queue.add(nc);
                    sb.append(nc);
                }
            }
        }
        if(map.size() != sb.length()) {
            return "";
        }
        return sb.toString();
     }

    private void buildGraph(String[] words) {
        for(String word : words) {
            for(int i = 0 ; i < word.length(); i++) {
                char c = word.charAt(i);
                if(!map.containsKey(c)) {
                    map.put(c, new HashSet<>());
                }
            }
        }

        for(int i = 0 ;i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i+1];
            int m = first.length();
            int n = second.length();
            boolean flag = false;

            // if(m > n && first.startsWith(second)) {
            //     map.clear();
            //     return;
            // }

            for(int j = 0 ; j < m && j < n; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);
                if(out != in) {
                    Set<Character> set = map.get(out);
                    if(!set.contains(in)) {
                        set.add(in);
                        indegrees[in - 'a']++;
                    }
                    flag = true;
                    break;
                }
            }
            if(m > n && !flag) {
                map.clear();
                return;
            }


        }
    }

}

## Problem 2 Verify Alien Dictionary https://leetcode.com/problems/verifying-an-alien-dictionary/

// Time Complexity : O(M\*l), M : Number of words, l : Average length of each word
// Space Complexity : O(1)

class Solution {
public boolean isAlienSorted(String[] words, String order) {

        for(int i = 1; i < words.length; i++) {
            for(int j = 0; j < words[i-1].length();j++) {

                if(j == words[i].length()) {
                    return false;
                }

                int a = order.indexOf(words[i-1].charAt(j));
                int b = order.indexOf(words[i].charAt(j));

                if(a < b) {
                    break;
                } else if(a > b) {
                    return false;
                }
            }
        }
        return true;
    }

}
