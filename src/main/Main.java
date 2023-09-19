package main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * NOTE: Use -parameters flag when compiling to preserve parameter names for terminal UI.
 */

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                run(input);
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input! Only enter the number of the method you'd like to execute.");
                System.out.println("Print enter to continue.");
                input.nextLine();
                input.nextLine();
            }
            System.out.println("[y/n] Would you like to execute another method?");
            if (input.nextLine().equals("n"))
                System.exit(0);
        }
    }

    public static void run(Scanner input) {
        // Greet the user.
        System.out.println("Welcome to Wolf's string utility program!");
        System.out.println("To begin, select a mode from the following options:");

        // Load in the methods that the user should be able to call using reflection.
        Class<StringTools> stringToolsClass = StringTools.class;
        Method[] stringToolsMethods = stringToolsClass.getDeclaredMethods();

        // Print out all the available methods for the user to choose from using reflection.
        for (int i = 0; i < stringToolsMethods.length; i++)
            System.out.println("[" + (i + 1) + "]" + " - " + stringToolsMethods[i].getName());

        // Get the user's choice of method to use. User was presented options in index-1, so we must shift down the
        // index by 1 to return to index-0.
        int userInput = input.nextInt() - 1;
        try {
            Method chosenMethod = stringToolsMethods[userInput];
            Parameter[] chosenMethodParams = chosenMethod.getParameters();
            String[] chosenMethodParamStrings = new String[chosenMethodParams.length];
            for (int i = 0; i < chosenMethodParams.length; i++)
                chosenMethodParamStrings[i] = (
                        chosenMethodParams[i].getName() + " - " + chosenMethodParams[i].getType().getName()
                );

            System.out.println();
            System.out.println(
                    "Great choice! This method requires " +
                            chosenMethodParams.length +
                            " param" + (chosenMethodParams.length > 0 ? "" : "s") + ":"
            );
            for (String chosenMethodNameString : chosenMethodParamStrings)
                System.out.println(chosenMethodNameString);

            System.out.println();
            System.out.println("Please enter them one at a time as prompted.");

            // Consume the lingering \n that .nextInt() did not consume.
            input.nextLine();

            String[] arguments = new String[chosenMethodParams.length];
            for (int i = 0; i < chosenMethodParams.length; i++) {
                System.out.println("Enter parameter #" + (i + 1) + " and then press enter.");
                arguments[i] = input.nextLine();
            }

            System.out.println("Alright, executing method with provided params...");
            try {
                System.out.println(Arrays.toString(arguments));
                System.out.println();

                String output = chosenMethod.invoke((new StringTools()), arguments).toString();

                System.out.println("Success! The output is \"" + output + "\"");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Arguments passed were somehow wrong.");
            }
            catch (IllegalAccessException e) {
                System.out.println("Unable to access method. Weird.");
            }
            catch (InvocationTargetException e) {
                System.out.println("An unknown error occurred.");
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Whoops, you've chosen a method that doesn't exist. Please try again.");
        }
    }
}