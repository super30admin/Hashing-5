import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//Time Complexity:O(N*L), O(V+E)
//Space Complexity:O(V+E)
//First, we create a graph by comparing adjacent words and noting down character precedence.
//Then we count the incoming edges (indegree) and start BFS with nodes having zero indegree.
//As we process, we append characters to the result and reduce indegrees of neighbors.
class Solution{
    HashMap<Character,HashSet<Character>> map;
    int[] indegrees;

    public String alienOrder(String[] words)
    {
        this.map = new HashMap<>();
        this.indegrees = new int[26];

        buildGraph(words);
        if(map.size() == 0) return "";

        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();

        for(char c:map.keySet())
        {
            if(indegrees[c-'a']==0)
            {
                q.add(c);
                sb.append(c);
            }
        }

        while(!q.isEmpty())
        {
            char c = q.poll();
            for(char ne: map.get(c))
            {
                indegrees[ne-'a']--;
                if(indegrees[ne-'a']==0){
                    q.add(ne);
                    sb.append(ne);
                }
            }
        }

        if(sb.length()==map.size()) return sb.toString();
        return "";
    }

    private void buildGraph(String[] words)
    {
        for(String word:words)
        {
            for(char c: word.toCharArray())
            {
                map.put(c,new HashSet<>());
            }
        }

        for(int i=0;i<words.length-1;i++)
        {
            String first = words[i];
            String second = words[i+1];

            if(first.startsWith(second)&&first.length()>second.length())
            {
                map.clear();
                return;
            }
            for(int j=0;j<first.length()&&j<second.length();j++)
            {
                char fChar = first.charAt(j);
                char sChar = second.charAt(j);

                if(fChar!=sChar)
                {
                    HashSet<Character> set = map.get(fChar);
                    if(!set.contains(sChar))
                    {
                        set.add(sChar);
                        indegrees[sChar-'a']++;
                    }
                    break;
                }
            }
        }
    }
}