// TC: O(n*k)
// SC: O(1)
// where n is the length of the words & k is the average length of the each word

public class LC953 {
    int mapping[];
    private boolean compareTo(String s1, String s2){
        int m = s1.length(), n = s2.length();
        int i=0, j=0;
        while(i<m && j<n){
            if(s1.charAt(i) != s2.charAt(j)){
                return mapping[s1.charAt(i)-'a'] < mapping[s2.charAt(j)-'a'] ;
            }
            i++;
            j++;
        }
        return m<=n;
    }
    public boolean isAlienSorted(String[] words, String order) {
        int m = order.length(), n = words.length;
        mapping = new int[26];
        for(int i=0;i<m;i++){
            mapping[order.charAt(i)-'a'] = i;
        }
        for(int i=1;i<n;i++){
            boolean ans = compareTo(words[i-1], words[i]);
            if(!ans) return false;
        }
        return true;
    }
}