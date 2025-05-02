// Time Complexity : O(V+E) v - no of distinct characters and E - number of rules deduced from the dictionary
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;
class Solution {
    private void getEdgeList(String[] words, Map<Character, Set<Character>> adj_lst, Map<Character, Integer> inDegree, Set<Character> unique)
    {
        for(int i=1; i<words.length; i++)
        {
            String x = words[i-1];
            String y = words[i];
            int index = 0;
            int len = Math.min(x.length(), y.length());
            while(index < len && (x.charAt(index) == y.charAt(index)))
            {
                unique.add(x.charAt(index));
                unique.add(y.charAt(index));
                index++;
            }
            if(index < len)
            {
                unique.add(x.charAt(index));
                unique.add(y.charAt(index));
                Set<Character> temp = adj_lst.getOrDefault(x.charAt(index), new HashSet<>());
                if(!temp.contains(y.charAt(index)))
                {
                    temp.add(y.charAt(index));
                    adj_lst.put(x.charAt(index), temp);
                    inDegree.put(x.charAt(index), inDegree.getOrDefault(x.charAt(index), 0));
                    inDegree.put(y.charAt(index), inDegree.getOrDefault(y.charAt(index), 0)+1);
                }
                
            }
            int iidx = index;
            while(iidx < x.length())
                unique.add(x.charAt(iidx++));
            int jidx = index;
            while(jidx < y.length())
                unique.add(y.charAt(jidx++));
        }
        return;
    }
    public String alienOrder(String[] words) {
        StringBuilder result = new StringBuilder();
        Set<Character> unique = new HashSet<>();
        if(words.length == 1)
        {
            for(char c : words[0].toCharArray())
                unique.add(c);
           for(char c : unique)
            result.append(c);
            return result.toString();
        }
        Map<Character, Set<Character>> adj_lst = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>(); 
        getEdgeList(words, adj_lst, inDegree, unique);
        
        for (int i = 0; i < words.length - 1; i++) 
        {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
        }
        Queue<Character> que = new LinkedList<>();
        for(Map.Entry<Character, Integer> inDegreeEntry : inDegree.entrySet())
        {
            if(inDegreeEntry.getValue() == 0)
                que.offer(inDegreeEntry.getKey());
        }

        
        while(!que.isEmpty())
        {
            char node = que.poll();
            result.append(node);
            unique.remove(node);
            Set<Character> temp = adj_lst.get(node);
            if(temp != null)
            {
                for(char child : temp)
                {
                    inDegree.put(child, inDegree.get(child)-1);
                    if(inDegree.get(child) == 0)
                        que.offer(child);
                }
            }
        }
        
        if(result.toString().length() != inDegree.size())
            return "";
        if(unique.size() > 0)
        {
            for(char x : unique)
                result.append(x);
        }
        return result.toString();
    }
}