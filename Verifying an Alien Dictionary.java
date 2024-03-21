class Solution {

    HashMap<Character, Integer> map;

    public boolean isAlienSorted(String[] words, String order) {
        
        if(words == null || words.length == 0) return true;

        map = new HashMap();

        for(int i = 0; i < order.length(); i++) map.put(order.charAt(i), i);

        for(int i = 0; i < words.length - 1; i++)
        {
            String first = words[i];
            String second = words[i + 1];

            // We return false because it is not sorted in alien order
            if(isNotSorted(first, second) == true) return false;
        }

        return true;
    }

    private boolean isNotSorted(String first, String second)
    {
        int n = first.length(), m = second.length();

        for(int i = 0; i < n && i < m; i++)
        {
            char firstChar = first.charAt(i);
            char secondChar = second.charAt(i);

            // This returns trur if first string is bigger than second
            if(firstChar != secondChar) return map.get(firstChar) > map.get(secondChar);
        }

        // Assume we had 2 string "apple" and "app". In this case both strings are same but when we are at character l the other
        // string is exhausted. In this case app should come before apple so we return true if n > m
        return n > m;
    }
}