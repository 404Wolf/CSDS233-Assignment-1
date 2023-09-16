package test;

import main.StringTools;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringToolsTest {
    private final String[] palindromes = new String[] {
        "racecar", "hannah", "", "- -", " ", "anna", "baaaaaaaaab  baaaaaaaaab"
    };

    private final String[] notPalindromes = new String[] {
        "racecars", "banana", "w ", "- --", " .", "this is not a palindrome", "aaaaaaaaaa baaaaaaaaa"
    };

    @Test
    public void testIterativePalindrome() {
        for (String palindrome: palindromes)
            assertTrue(StringTools.palindromeIterative(palindrome));
        for (String notPalindrome: notPalindromes)
            assertFalse(StringTools.palindromeIterative(notPalindrome));
    }

    @Test
    public void testRecursivePalindrome() {
        for (String palindrome: palindromes)
            assertTrue(StringTools.palindromeRecursive(palindrome));
        for (String notPalindrome: notPalindromes)
            assertFalse(StringTools.palindromeRecursive(notPalindrome));
    }

    @Test
    public void testSubstringInsertion() {
        assertEquals("hello world", StringTools.addSubstring("hello", " world", 4));
        assertEquals("Confusing", StringTools.addSubstring("Coning", "fus", 2));
        assertEquals("", StringTools.addSubstring("", "", 2));
        assertEquals("3", StringTools.addSubstring("", "3", 0));
    }

    @Test
    public void testAnagramChecker() {
        assertTrue(StringTools.anagramChecker("wonder", "rednow"));
        assertTrue(StringTools.anagramChecker("angel", "glean"));
        assertTrue(StringTools.anagramChecker("players", "parsley"));
        assertTrue(StringTools.anagramChecker("state", "taste"));

        assertFalse(StringTools.anagramChecker("this is", "not an anagram"));
        assertFalse(StringTools.anagramChecker("!wonder;", ";wonder."));
        assertFalse(StringTools.anagramChecker(",,,", "  "));
        assertFalse(StringTools.anagramChecker("12", "123"));
        assertFalse(StringTools.anagramChecker("light", "right"));
    }
}