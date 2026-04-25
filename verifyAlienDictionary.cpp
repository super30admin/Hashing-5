class Solution {
public:
    bool isNotSorted(string &a, string &b, unordered_map<char, int>& um) {
        int minLen = min(a.length(), b.length());
        for (int i=0;i<minLen;i++) {
            if (a[i] != b[i]) {
                return um[a[i]] > um[b[i]];
            }
        }
        return a.length() > b.length();
    }
    bool isAlienSorted(vector<string>& words, string order) {
        unordered_map<char, int> um;
        for (int i=0;i<order.length();i++) {
            um[order[i]]=i;
        }

        for (int i=0;i<words.size()-1;i++) {
            if (isNotSorted(words[i], words[i+1], um)) {
                return false;
            }
        }
        return true;
    }
};
