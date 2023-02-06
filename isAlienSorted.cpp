// Time complexity: O(nl) where n is the number of words and l is the average length of each word
// Space complexity: O(1) - map of size 26 


/*
create a map of char->index and comapre the letter to make sure they are in order
*/


class Solution {
public:
    bool isAlienSorted(vector<string>& words, string order) {
        if(words.size() == 0)
            return true;
        
        // creating the dict
        unordered_map<char, int> dict;
        for(int i = 0; i<order.length(); i++) {
            dict[order[i]] = i;
        }

        // comparing every 2 words
        for(int i = 0; i<words.size()-1; i++) {
            string word1 = words[i];
            string word2 = words[i+1];
            // true if they are the same word
            if(word1 == word2)
                continue;

            // if the first few letter are same, move to the first different letter 
            // app, apple
            int k = 0;
            while(word1[k] == word2[k])
                k++;

            // if the second word is longer than the first,, its false
            if(k >= word2.length())
                return false;

            // if the first different letter are not in lex order, false
            else if(k < word1.length() && dict[word1[k]] > dict[word2[k]])
                return false;
        }
        return true;
    }
};