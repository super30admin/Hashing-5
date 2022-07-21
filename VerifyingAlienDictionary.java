class Solution {

    //Time Complexity: 0 (m*n) where m is the length if string array and n is the average length of each word
    //Space Complexity: 0 (l) where l is the length of order string

    HashMap<Character, Integer> map;    //a hashmap to store each character and their index in the order array
    public boolean isAlienSorted(String[] words, String order) {
        if(words == null || words.length == 0){
            return true;
        }

        map = new HashMap<>();

        for(int i = 0; i < order.length(); i++){    //going over order array and storing the character and their occurences
            char c = order.charAt(i);
            map.put(c, i);
        }

        for(int i = 0; i < words.length - 1; i++){  //going over words array to check if they are in order or not
            String first = words[i];    //getting 1st word
            String second = words[i+1]; //getting 2nd word
            if(notsorted(first, second)){   //checking if they are sorted or not. If not, then returning false
                return false;
            }
        }

        return true;    //else returning true
    }

    public boolean notsorted(String first, String second){//checking if 2 words are in order or not
        int m = first.length();
        int n = second.length();

        for(int i = 0; i < m && i < n; i++){    //going over the length. here i is less than both m and n because one string's length can be greater
            char firstchar = first.charAt(i);//getting the character of both the strings
            char secondchar = second.charAt(i);
            if(firstchar != secondchar){    //if they are not equal, then I check the order in the order string which I have stored in the hashmap
                return map.get(firstchar) > map.get(secondchar);    //if the character at 1st word has occured in the order string later, then this function will return true. Which inturn is false, so i return false for the program. If not, then they are in correct order and this function will return false and in the above function, it won't return false
            }
        }

        return m > n;   //if all the characters are same, then i check the length. If length of 1st word is greater, then it's not in order. So this will return true. But in turn the program will return false
    }
}