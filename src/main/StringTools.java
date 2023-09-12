package main;

public class StringTools {
    /**
     * Determine whether a given input string is a palindrome.
     *
     * @implNote Implemented iteratively.
     * @param input The input string to test for whether it's a palindrome.
     * @return Whether the input string is a palindrome.
     */
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
}
