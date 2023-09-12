package test;

import main.StringTools;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringToolsTest {
    private final String[] palindromes = new String[] {
            "racecar",
            "hannah",
            "",
            "- -",
            " ",
            "anna"
    };

    private final String[] notPalindromes = new String[] {
            "racecars",
            "banana",
            "w ",
            "- --",
            " .",
            "this is not a palindrome"
    };

    @Test
    void testIterativePalindrome() {
        for (String palindrome: palindromes)
            assertTrue(StringTools.palindromeIterative(palindrome));
        for (String notPalindrome: notPalindromes)
            assertFalse(StringTools.palindromeIterative(notPalindrome));
    }

    @Test
    void testRecursivePalindrome() {
        for (String palindrome: palindromes)
            assertTrue(StringTools.palindromeRecursive(palindrome));
        for (String notPalindrome: notPalindromes)
            assertFalse(StringTools.palindromeRecursive(notPalindrome));
    }
}