// Time Complexity : O(Nk) where N is the total number of words and k is the average length of the words
// Space Complexity : O(1) space taken up by the order map
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/*
I create an order map by of all the characters present in the order string. Then I compare each every string with it's neighboring string and check one by one if they have a differing charcter. 
If a differing character is present, I check if the order of the character from the first string is less than that of the second string. If not, I return false straight away. In case both the
strings have similar characters and the first string has a length greater than the second string, I return false again as a word of greater length can't be present before a word of smaller length. 
After comparing all strings with it's subsequent neighboring string, I return true.
*/

public class Solution {
    public bool IsAlienSorted(string[] words, string order) {
        Dictionary<char, int> orderMap = new();
        
        for(int i = 0; i < order.Length; i++)
        {
            orderMap[order[i]] = i;
        }

        for(int i = 0; i < words.Length - 1; i++)
        {
            string firstWord = words[i];
            string secondWord = words[i+1];

            for(int j = 0; j < firstWord.Length && j < secondWord.Length; j++)
            {
                char firstWordChar = firstWord[j];
                char secondWordChar = secondWord[j];
                
                if(firstWordChar != secondWordChar)
                {
                    if(orderMap[firstWordChar] > orderMap[secondWordChar])
                    {
                        return false;
                    }

                    else
                    {
                        break;
                    }
                }

                else
                {
                    if(j==secondWord.Length - 1 && firstWord.Length > secondWord.Length)
                    {
                        return false;
                    }
                }

            }
        }

        return true;
    }
}