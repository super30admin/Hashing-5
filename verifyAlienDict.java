class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] idx = new int[26];

        for(int i =0; i< order.length(); i++){
            char ch = order.charAt(i);
            idx[ch-'a'] = i;
        }


        for(int i =0; i< words.length-1; i++){
            String word1 = words[i];
            String word2 = words[i+1];
            int len1 = word1.length();
            int len2 = word2.length();

            for(int j =0; j< Math.min(len1, len2); j++){
                char ch1 = word1.charAt(j);
                char ch2 = word2.charAt(j);

                if(ch1 != ch2){
                    if(idx[ch1-'a'] > idx[ch2-'a'])
                        return false;
                    else
                        break;
                }else{
                    if(j == Math.min(len1,len2)-1 && len1 > len2)
                        return false;
                }
                
            }
        }
    return true;
    }
}