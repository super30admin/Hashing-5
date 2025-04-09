//TC: O(n*k)
//SC: O(1)
// where n is the length of the words & k is the average length of the each word

import java.util.*;

public class LC269 {
    List<Character>[] graph;
    private boolean addToGraph(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        for(int k=0;k<m;k++){
            char ch1 = s1.charAt(k);
            if(graph[ch1-'a']==null) graph[ch1-'a'] = new ArrayList<>();
        }
        for(int k=0;k<n;k++){
            char ch2 = s2.charAt(k);
            if(graph[ch2-'a']==null) graph[ch2-'a'] = new ArrayList<>();
        }
        for(int i=0;i<m && i<n;i++){
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            if(graph[ch1-'a']==null) graph[ch1-'a'] = new ArrayList<>();
            if(graph[ch2-'a']==null) graph[ch2-'a'] = new ArrayList<>();
            if(ch1!=ch2) {
                graph[ch1-'a'].add(ch2);
                return true;
            }
        }
        return m<=n;
    }
    public String alienOrder(String[] words) {
        int n = words.length;
        graph = new ArrayList[26];
        if(n==1) addToGraph("", words[0]);
        for(int i=0;i<n-1;i++){
            if(!addToGraph(words[i], words[i+1])) return "";
        }
        int indegree[] = new int[26];
        for(int i=0;i<26;i++){
            if(graph[i]!=null){
                for(int j=0;j<graph[i].size();j++){
                    indegree[graph[i].get(j)-'a']++;
                }
            } else {
                indegree[i] = -1;
            }
        }
        int total = 0;
        Queue<Integer> q = new LinkedList<>();
        StringBuffer ans = new StringBuffer();
        for(int i=0;i<26;i++){
            if(indegree[i]==0){
                q.add(i);
            }
            if(indegree[i]>=0) total++;
        }
        while(!q.isEmpty()){
            int polled = q.poll();
            ans.append((char)(polled+'a'));
            total--;
            for(int j=0;j<graph[polled].size();j++){
                indegree[graph[polled].get(j)-'a']--;
                if(indegree[graph[polled].get(j)-'a']==0) q.add(graph[polled].get(j)-'a');
            }
        }
        if(total>0) return "";
        return ans.toString();
    }
}
