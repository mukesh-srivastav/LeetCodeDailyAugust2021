/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 

Follow up: Could you find an algorithm that runs in O(m + n) time?
Hint #1 = Use two pointers to create a window of letters in S, which would have all the characters from T.
Hint #2 = Since you have to find the minimum window in S which has all the characters from T, you need to expand and contract the window using the two pointers and keep checking the window for all the characters. This approach is also called Sliding Window Approach.

L ------------------------ R , Suppose this is the window that contains all characters of T 
                          
        L----------------- R , this is the contracted window. We found a smaller window that still contains all the characters in T

When the window is no longer valid, start expanding again using the right pointer.  
*/
class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int hash_pat[] = new int[256];
        int hash_str[] = new int[256];
        
        int l1 = s.length();
        int l2 = t.length();
        
        if (l1 < l2) {
            return "";
        }
        
        for (int i=0; i<l2; i++) {
            hash_pat[t.charAt(i)]++;
        }
        
        int start = 0, start_index = -1, min_length = Integer.MAX_VALUE;
        
        int count =0; 
        
        for (int i=0; i<l1; i++) {
            hash_str[s.charAt(i)]++;
            
            if (hash_str[s.charAt(i)] <= hash_pat[s.charAt(i)]) {
                count++;
            }
            
            if (count == l2) {
                while (hash_str[s.charAt(start)] > hash_pat[s.charAt(start)] || hash_pat[s.charAt(start)] == 0) {
                    if (hash_str[s.charAt(start)] > hash_pat[s.charAt(start)]) {
                        hash_str[s.charAt(start)]--;
                    }
                    
                    start++;
                }
                
                int current_window_length = i - start + 1;
                
                if (current_window_length < min_length) {
                    min_length = current_window_length;
                    start_index = start;
                }
            }
        }
        
        if (start_index == -1)
            return "";
        
        return s.substring(start_index, start_index + min_length);
    }
}
