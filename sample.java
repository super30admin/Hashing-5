//Problem 1: Alien Dictionary
// Time Complexity : O(V+E)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
class Solution {
    //O(V+E) 
    HashMap<Character, HashSet<Character>> map;
    Queue<Character> q;
    int[] indeg;
    StringBuilder res;
    public String alienOrder(String[] words) {
        this.map=new HashMap<>();
        this.q=new LinkedList<>();
        this.res=new StringBuilder();
        this.indeg=new int[26];
        buildgraph(words);

        for(char c: map.keySet()){
            if(indeg[c-'a']==0){
                q.add(c);
                res.append(c);
            }
        }

        // if(q.isEmpty()) return "";

        while(!q.isEmpty()){
            char cur=q.poll();
            HashSet<Character> set=map.get(cur);
            for(char baby: set){
                indeg[baby-'a']--;
                if(indeg[baby-'a']==0){
                    q.add(baby);
                    res.append(baby);
                    if(res.length()==map.size())
                        return res.toString();
                }
            }
        }
        if(res.length()==map.size()) return res.toString();
        return "";
    }

    private void buildgraph(String[] words){
        for(String word:words){
            for(int i=0;i<word.length();i++){
                char c=word.charAt(i);
                if(!map.containsKey(c)){
                    map.put(c, new HashSet<>());
                }
            }
        }

        for(int k=0;k<words.length-1;k++){
            String fir=words[k];
            String sec=words[k+1];
            if(fir.length()!=sec.length() && fir.startsWith(sec)){
                map.clear();
                break;
            }
            for(int i=0;i<fir.length() && i<sec.length();i++){
                char fchar=fir.charAt(i);
                char schar=sec.charAt(i);
                if(fchar!=schar){
                    HashSet<Character> set=map.get(fchar);
                    if(!set.contains(schar)){
                        indeg[schar-'a']++;
                        set.add(schar);
                        // map.put(fchar, set);
                        
                    }
                    break;//dont check all next characters
                }//if
            }//inner for
        }//main for
    }
}

//Problem 2: Verify Alien Dictionary
// Time Complexity : O(nk)
// Space Complexity : O(26)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
class Solution {
    //O(nk) //O(26)
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        this.map= new HashMap<>();
        for(int i=0;i<order.length();i++){
            char c=order.charAt(i);
            map.put(c,i);
        }

        for(int i=0;i<words.length-1;i++){
            String first=words[i];
            String second=words[i+1];
            if(notSorted(first, second)) return false;
        }

        return true;
    }

    private boolean notSorted(String fir, String sec){
        for(int i=0;i<fir.length() && i<sec.length();i++){
            char fchar=fir.charAt(i);
            char schar=sec.charAt(i);
            if(fchar!=schar){
                return map.get(fchar)>map.get(schar); //Not sorted
            }
        }
        return fir.length()>sec.length();
    }
}