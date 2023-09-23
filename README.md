# Assignment 1, Part 2

## File structure
My submission's main code is located in `src/main/StringTools.java` and `src/main/Main.java`. The former is the class with all the static methods requested, providing different possible operations for input strings; the latter is a terminal based user interface to interact with the former. I've also included the JUnit tests that I wrote to test my code in `src/test/StringToolsTest.java`.

## Compiling Note
The program will work when compiled and run as-is, but ideally you want to compile using the `-parameters` flag. This is because usually Java strips the names off of methods' arguments when it compiles the code. My terminal GUI, however, relies on reflection to fetch the names of the available methods and their corresponding arguments. `javac` with `-parameters` tells Java to preserve the names of the arguments. If you don't do this, when prompted for the arguments to call a specific method through the terminal GUI it'll name the arguments "arg1", "arg2", "..." by default, which will work just fine (and I clarified would be sufficient for the purpose of this assignment), but is subideal from a UX perspective. 