class Solution {
    public String alienOrder(String[] words) {
        int n = words.length;


        HashMap<Character,List<Character>> adj = new HashMap<>();
        HashMap<Character, Integer>  inDegree = new HashMap<>();

        for (String word : words) {
                for (char c : word.toCharArray()) {
                    inDegree.put(c, 0);
                    adj.put(c, new ArrayList<>());
                }
            }


        for(int i =0; i < n-1; i++){
            int len = Math.min(words[i].length(), words[i+1].length());

            if(words[i].length() > words[i+1].length() && words[i].startsWith(words[i+1])) return "";

            for(int j = 0; j < len; j++){
                char ch1 = words[i].charAt(j), ch2= words[i+1].charAt(j);
                if(ch1 != ch2){
                    adj.get(ch1).add(ch2);
                    inDegree.put(ch2, inDegree.get(ch2)+1);
                    break;
                }
            }
        }

        
        return topoSort(adj, inDegree);
    }


    private String topoSort(Map<Character, List<Character>> adj, Map<Character, Integer> inDegree){
        Queue<Character> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();

        for(Map.Entry<Character, Integer> val : inDegree.entrySet()){
            if(val.getValue() == 0)
            q.add(val.getKey());
        }


        while(!q.isEmpty()){
            char ch = q.poll();
            res.append(ch);
            
            if(adj.get(ch) != null){
            for(char child : adj.get(ch)){
                inDegree.put(child, inDegree.get(child)-1);
                if(inDegree.get(child) == 0)
                    q.offer(child);
            }}
        }

        if(res.length() < adj.size()) return "";

        return res.toString();
    }
}