package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A collection of methods that can be run on strings.
 */
public class StringTools {
    public static final Set<Character> punctuation = new PunctuationSet();

    /**
     * Determine whether a given input string is a palindrome.
     *
     * @implSpec Runs in O(n) where n is input.length().
     * @implNote Implemented iteratively.
     * @param input The input string to test for whether it's a palindrome.
     * @return Whether the input string is a palindrome.
     */
    public static boolean palindromeIterative(String input) {
        if (input.length() <= 1)
            return true;

        // Create an array of characters for the input to save on lookups for chars.
        char[] inputCharArray = input.toCharArray();

        // Let i be an offset from the start and end of the string. We then compare the i-th index char from the left
        // to the i-th index char on the right. If they are not the same then we've determined that this string is not
        // a palindrome and do not need to proceed any further. If we make it up to the point where we've finished
        // checking the left half of the string (i = floor(input length / 2)), we can stop since we've tested every char
        // against its complementary right-side char.
        for (int i = 0; i < inputCharArray.length / 2; i++)
            if (inputCharArray[i] != inputCharArray[input.length() - 1 - i])
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
     * @implSpec Runs in O(n) where n is the length of the input string.
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
     * @throws IndexOutOfBoundsException When the index to add the string at exceeds the length of the string, or is
     * negative.
     * @param input The input string.
     * @param substring The string to insert after the provided index.
     * @param index The index for which to insert a subsequent substring.
     */
    public static String addSubstring (String input, String substring, int index) throws IndexOutOfBoundsException {
        // Throw an error if the index that they want to add the substring at exceeds the length of the string.
        if (index < 0 || index > input.length())
            throw new IndexOutOfBoundsException();

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

    /**
     * Determine length of a string.
     *
     * @param string The string to determine the length of.
     * @return The length of the string.
     */
    public static int getLength(String string){
        return string.length();
    }

    /**
     * Count the number of occurrences of a substring in an input string.
     * @param input The string containing occurrences of a given substring.
     * @param substring The substring to count occurrences of.
     * @return The number of occurrences of the substring in the overall string.
     */
    public static int occurrenceCounter(String input, String substring){
        int occurences = 0;

        // A counter to determine our current position in the substring. When we begin to encounter a sequence of
        // characters that matches that of the beginning of the substring, this will tick upwards until it reaches the
        // length of the substring.
        int progression = 0;
        for (char character : input.toCharArray()) {
            if (character == substring.charAt(progression)) {
                if (progression == (substring.length() - 1)) {
                    // We've matched up the full length of the substring worth of letters, which means we've found an
                    // occurrence of the substring within the string. We can restart progressing from the start of the
                    // substring and tick the number of occurrences that we've found so far up by 1.
                    progression = 0;
                    occurences++;
                }
                else {
                    // We have matched up another letter between the string and substring, but we aren't quite at the
                    // end of the substring, so we can't update our occurrence counter yet.
                    progression++;
                }
            }
            else {
                // If we're no longer tracking along the substring, we must restart our progress.
                progression = 0;
            }
        }

        // Now that we've progressed all the way through the string and caught all the occurrences as we traversed it, we
        // can return our current occurrence counter's value
        return occurences;
    }

    /**
     * Reverse the words of a given sentence.
     *
     * @implNote If the last character of the input string is a punctuation sign, the output string's last character
     * will also be the punctuation mark. This is to assert consistency with the assignment instruction's example.
     * @throws InvalidSentenceException When there are multiple back to back separators in the string.
     * @param sentence The sentence to have its words reversed.
     * @return The sentence with its words in reverse order.
     */
    public static String sentenceReversal(String sentence) throws InvalidSentenceException{
        // If the string is empty we don't need to do any work.
        if (sentence.isEmpty())
            return sentence;

        // Create an array to store the separated list of words in the sentence, and a flag for whether the input string
        // originally ended with a period.
        char endingPunctuation = sentence.charAt(sentence.length() - 1);
        if (!punctuation.contains(sentence.charAt(sentence.length() - 1)))
            endingPunctuation = ' ';

        String[] words = new String[occurrenceCounter(sentence, String.valueOf(' ')) + 1];

        // Iterate through the string and store all the separate words in the words array.
        int occurrencesSoFar = 0;
        StringBuilder currentWord = new StringBuilder();
        for (char character : sentence.toCharArray()) {
            if (character == ' ') {
                if (currentWord.isEmpty())
                    throw new InvalidSentenceException();
                words[occurrencesSoFar++] = currentWord.toString();
                currentWord.setLength(0);
            }
            else if (!punctuation.contains(character))
                currentWord.append(character);
        }

        // If the last word ends in a period, strip off the period. We'll add a period to the end of the output string
        // later.
        if (currentWord.charAt(currentWord.length() - 1) == '.') {
            currentWord.setLength(currentWord.length() - 1);
        }
        words[words.length - 1] = currentWord.toString();

        // Now we reverse the order of the words array by swapping elements.
        for (int i = 0; i < words.length; i++) {
            if (i < words.length / 2) {
                String temp = words[i];
                words[i] = words[words.length - 1 - i];
                words[words.length - 1 - i] = temp;
            }
        }

        // Now we build back the output string by adding the separator between elements.
        StringBuilder output = new StringBuilder();
        for (String word : words) {
            output.append(word);
            output.append(' ');
        }

        // Cut off the trailing separator.
        output.setLength(output.length() - 1);

        // Append a period if the string originally ended with a period.
        if (endingPunctuation != ' ')
            output.append(endingPunctuation);

        return output.toString();
    }

    public static class InvalidSentenceException extends Exception {}

    public static class PunctuationSet extends HashSet<Character> {
        public PunctuationSet() {
            super();
            add('.');
            add('?');
            add('!');
        }
    }
}
