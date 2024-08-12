/*
Time: O(n)
Space: O(n)
*/
class Solution {
public:
    vector<int> ord;

    //checks if s1 is lexicographically smaller than s2
    bool is_smaller(string& s1, string& s2){
        int i;

        //if any characters mismatch, the strings order can be defined by their precedance
        for(i=0;i<min(s1.length(),s2.length());i++){
            int o1 = ord[s1[i] - 'a'], o2 = ord[s2[i]-'a'];
            if(o1 < o2)  return true;
            if(o1 > o2)  return false;
        }

        //if all characters are same, then s1 must be smaller or equal in length
        return i == s1.length();
    }

    
    bool isAlienSorted(vector<string>& words, string order) {
        ord = vector(26,0);
        for(int i=0;i<order.length();i++)  ord[order[i]-'a'] = i;

        for(int i=1;i<words.size();i++){
            //checks if every word is smaller than the following word
            if(is_smaller(words[i-1],words[i]) == false )  return false;
        }

        return true;
    }
};
