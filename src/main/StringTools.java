package main;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class StringTools {
    /**
     * Determine whether a given input string is a palindrome.
     * Time complexity: O(n), where n is input.length().
     *
     * @implNote Implemented iteratively.
     * @param input The input string to test for whether it's a palindrome.
     * @return Whether the input string is a palindrome.
     */
    public static final String[] methods = new String[]{
            "palindromeIterative",
            "palindromeRecursive",
            "anagramChecker",
            "addSubstring"
    };

    public static boolean palindromeIterative(String input) {
        if (input.length() <= 1)
            return true;

        // Let i be an offset from the start and end of the string. We then compare the i-th index char from the left
        // to the i-th index char on the right. If they are not the same then we've determined that this string is not
        // a palindrome and do not need to proceed any further. If we make it up to the point where we've finished
        // checking the left half of the string (i = floor(input length / 2)), we can stop since we've tested every char
        // against its complementary right-side char.
        for (int i = 0; i < input.length() / 2; i++)
            if (input.charAt(i) != input.charAt(input.length() - 1 - i))
                return false;

        // All the chars have been tested against their complementary right-side chars. The string has been determined
        // to be a palindrome.
        return true;
    }

    /**
     * Determine whether a given input string is a palindrome.
     * Time complexity: O(n), where n is input.length().
     *
     * @implNote Implemented recursively.
     * @param input The input string to test for whether it's a palindrome.
     * @return Whether the input string is a palindrome.
     */
    public static boolean palindromeRecursive(String input) {
        // All strings of length 0 or 1 are palindromes.
        // This case will occur when the input string is very short, or when the recursion has whittled down the string
        // to length 0 or 1. It is our base case.
        if (input.length() <= 1)
            return true;
        // If the string has not yet reached the length at which it is guaranteed to be a palindrome, see if we can
        // whittle it down any further. If the leftmost char of the string does NOT match the rightmost char of the
        // string, the entire string is definitely not a palindrome, so we can stop and return false. Otherwise, whittle
        // away one char from the left and right of the string and recurse.
        else if (input.charAt(0) != input.charAt(input.length() - 1))
            return false;
        // Recurse with a string that has one char from the left and right side stripped away.
        return palindromeRecursive(input.substring(1, input.length() - 1));
    }

    /**
     * Determine whether a string is an anagram of another string.
     *
     * @implSpec Runs in O(s1 + s2) based on combined input length of strings. In other words, the method has
     * linear runtime.
     * @param str1 The first string to check.
     * @param str2 The second string to check.
     * @return Whether one string is the anagram of another string
     */
    public static boolean anagramChecker(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;

        // Create hashmaps to use as counters for character counts in both the strings.
        Map<Character, Integer> str1CharCounter = new HashMap<>();
        Map<Character, Integer> str2CharCounter = new HashMap<>();

        // Convert the strings to arrays of characters.
        char[] str1Chars = str1.toCharArray();
        char[] str2Chars = str2.toCharArray();

        // Tick up the proper counter for each character in string 1.
        for (Character str1Char : str1Chars)
            str1CharCounter.put(str1Char, str1CharCounter.getOrDefault(str1Char, 0) + 1);

        // Tick up the proper counter for each character in string 2.
        for (Character str2Char : str2Chars)
            str2CharCounter.put(str2Char, str2CharCounter.getOrDefault(str2Char, 0) + 1);

        // If there is a different number of unique characters in the first string than there are in the second, it's
        // not possible for the strings to be anagrams of each other.
        if (str1CharCounter.size() != str2CharCounter.size())
            return false;

        // Compare each element in the first hashmap to the corresponding value in the second hashmap.
        // If a given character appears a different number of times in the first and second string, that means that it
        // is not an anagram. Since we already ensured that there are the same number of unique characters in each
        // string, we only need to check against every corresponding character for one of the hashmaps, because if the
        // second hashmap has different characters than the first hashmap then the counts are guaranteed to be
        // different as well.
        for (Map.Entry<Character, Integer> count : str1CharCounter.entrySet()) {
            if (!count.getValue().equals(str2CharCounter.getOrDefault(count.getKey(), -1)))
                return false;
        }

        // Since all checks have been met, if the strings were not anagrams of each other false would already have been
        // returned. Reaching this step means that we're certain that the strings are anagrams of each other.
        return true;
    }

    /**
     * Insert a substring after a given index into a string.
     *
     * @implSpec Runs in O(i + s) where i is the length of the input string and s is the length of the substring that
     * we are appending. In other words, this method has linear runtime.
     * @return The length of a given input string.
     * @param input The input string.
     * @param substring The string to insert after the provided index.
     * @param index The index for which to insert a subsequent substring.
     */
    public static String addSubstring(String input, String substring, int index){
        // Create a StringBuilder which we'll use to concatenate the strings together without creating many redundant
        // new String objects.
        StringBuilder newStr = new StringBuilder();

        // Iterate through the input string. When we reach the index at which we are supposed to insert the substring,
        // insert it, and then carry on with appending the characters of the provided string.
        for (int i = 0; i <= input.length(); i++) {
            if (i != input.length())
                newStr.append(input.charAt(i));

            // We've reached the index at which we are supposed to append the requested substring. Append it, and then
            // carry on appending the original string's characters.
            if (i == index)
                newStr.append(substring);
        }

        // Convert the StringBuilder back into a String and return the result.
        return newStr.toString();
    }
}
