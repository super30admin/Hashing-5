// Time Complexity : O(N*L) -> to build the graph , O(V+E) to traverse , N=number of words in the array, L=length of the string
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
The core idea is to identify which char comes before the other char, in a way, understand the dependency of
a char on another.So, we need to build dependency map for that and also maintain an indegree array to keep
track of the dependent chars count.After we build the graph, we need to traverse it using BFS by first visiting
independent chars by checking the count in indegree array and then iteratively visiting their neighbours if
their indegree count goes to zero.To identify if we have visited all the chars, we can check if the map's length
is equal to the resultant string length. If there is any underlying cycle, we would not have visited all the
chars.
 */
class Solution {
    Map<Character, Set<Character>> map;
    int[] indegree;
    public String alienOrder(String[] words) {
        this.map = new HashMap<>();
        this.indegree = new int[26];

        StringBuilder sb = new StringBuilder();
        buildGraph(words);

        if(map.size() == 0) {
            return "";
        }

        Queue<Character> q = new LinkedList<>();

        //add independent chars to the queue first
        for(Character ch : map.keySet()) {
            if(indegree[ch - 'a'] == 0) {
                sb.append(ch);
                q.add(ch);
            }
        }

        while (!q.isEmpty()) {
            char curr = q.poll();
            Set<Character> set = map.get(curr);
            for(char ch : set) {
                indegree[ch - 'a']--;
                if(indegree[ch - 'a'] == 0) {
                    sb.append(ch);
                    q.add(ch);
                }
            }
        }

        if(sb.length() == map.size())
            return sb.toString();
        return "";
    }

    private void buildGraph(String[] words) {
        for(String word : words) {
            for(char ch : word.toCharArray()) {
                map.put(ch, new HashSet<>());
            }
        }

        for(int i = 0 ; i < words.length - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i + 1];

            //apple, app example where we need not do further processing
            if(firstWord.length() > secondWord.length() && (firstWord.startsWith(secondWord))) {
                map.clear();
                return;
            }
            //independent->dependent char mapping and indegree increment for dependent chars
            for(int j = 0 ; j < firstWord.length() && j < secondWord.length() ; j++) {
                char fChar = firstWord.charAt(j);
                char sChar = secondWord.charAt(j);

                Set<Character> set = map.get(fChar);

                if(fChar != sChar) {
                    if(!set.contains(sChar)) {
                        indegree[sChar - 'a']++;
                        set.add(sChar);
                    }
                    break;
                }
            }
        }
    }
}