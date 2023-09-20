package main;

public class Main {
    public static void main(String[] args) {
        // Theoretically the recursive implementation has a better space complexity (and they both have the same time
        // complexity), but the overhead to begin a for loop seems to lead to the recursive implementation taking a lot
        // less time to execute for reasonably sized inputs. In fact, because of the stack limit, the recursive version
        // will always be faster for strings for which it works.
        System.out.println(StringTools.palindromeIterative("racecar"));
    }
}
