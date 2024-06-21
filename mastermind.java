/*
 * Program: Mastermind Game
 * Author:  Yosef Birnbaum
 * Date:    06/07/2024
 * Description: A game of Mastermind where the user has to guess a code of four colors. The user has 12 guesses to break the code.
 */


// use * to import the whole util module
import java.util.*;

public class mastermind {
    // create an array of available colors which will be displayed to the user at each turn
    static String[] colors = {"red", "green", "blue", "black", "cyan", "yellow", "white", "magenta"};
   
    // constants for ANSI code for colors, and the code to reset to default colors after each color is applied
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_MAGENTA = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";

     // constant for amount of guesses
     static final int GUESSES = 12;

    // constants for Unicode for black circle which will be used for feedback
    static String WHITE = "\u25CF"; 
    // constant for Unicode for white circle which will be used for feedback
    static String BLACK = "\u25CB"; 
    
    
    public static void main(String[] args) {
        // call the makeCode method to create a code

    

        List<String> code = makeCode();

        // Display instructions and code guess entry details
        
        System.out.println("");
        System.out.println("WELCOME TO THE GAME OF MASTERMIND!!\n");
        System.out.println("You will have " + GUESSES + " attempts to guess the code.\n");
        // the guess will be later turned into an array, split at each space, and then displayed.
        System.out.println("Each color should be entered separated by a space.\n");
        System.out.println("A list of the acceptable colors will be provided at each turn.\n");
        System.out.println("Input with misspelled colors, colors not in the list, or extra spaces will have to be reentered.\n");


        // Create a separate scanner object for console mode input
        try (Scanner modeScanner = new Scanner(System.in)) {
            // Ask the user about their console mode
            System.out.println("If you're using light mode, enter 'L'. Enter any other key for dark mode.");
            String consoleMode = modeScanner.nextLine();
    
            // Set the WHITE and BLACK constants based on the console mode
            
            if ("L".equalsIgnoreCase(consoleMode)) {
                WHITE = "\u25CB"; // Unicode for white circle
                BLACK = "\u25CF"; // Unicode for black circle
            } else {
                WHITE = "\u25CF"; // Unicode for black circle
                BLACK = "\u25CB"; // Unicode for white circle
            }
        // The modeScanner will be automatically closed here
        System.out.println(" ");
        System.out.println("Feedback will be in the form of the following symbols:");
        System.out.println(" " + BLACK + " For black peg.");
        System.out.println(" " + WHITE + " For white peg.");
        System.out.println(" " + "-" + " For a blank.");
        System.out.println("Note: Dark mode is the assumed background color of the console and is the preffered mode for this game.");
        System.out.println("When console is in light mode the symbol for a white peg will appear as a black circle,");
        System.out.println("and the symbol for a black peg will be a non shaded circle with the color of the console.");

        System.out.println("\nGOOD LUCK!!");

            
        

        try (Scanner scanner = new Scanner(System.in)) {
            // loop for amount of guesses allowed
            for (int i = 0; i < GUESSES; i++) {
            	// display the colors using arrays.toString at every turn
                System.out.println("\nColors: " + Arrays.toString(colors));
                System.out.print("Guess the code: ");
                // assign a variable to the users guess
                String guess = scanner.nextLine();
                
                // call isValidGuess method to test if the guess is valid
                while (!isValidGuess(guess)) {
                    /* 
                	 while guess isn't valid, continuously show error message, and message to reenter code.
                	 User cannot enter colors with leading spaces as the split method will count empty spaces as individual elements
                    */
                    System.out.println("ERROR! Exactly four valid colors must be entered whithout leading spaces.");
                    System.out.print("Please reenter the code: ");
                    // correct entry will now be saved to the guess variable
                    guess = scanner.nextLine();
                }
                
                System.out.println(" ");

                /* 
                 output the users guess with the guess number (adding one since the count starts from zero)
                 use print not println so that the guess number and the actual guess can be on the same line
                */
                System.out.print("This is guess number " + (i + 1) + ": ");

                // split the guess into an array, getting each word separated by a space
                String[] guessArray = guess.split(" ");

                // iterate over the guess array to match the colors to the correct ANSII code for the color display
                for (String color : guessArray) {
                    /* 
                     switch statement to test each value in the list for all possible colors.
                     have the users guess made into lowercase so there shouldn't be any confilcts between same colors with
                     different  cases
                    */
                    switch (color.toLowerCase()) {
                        case "red":
                            // use the ANSI code and the users color to print out the guess and then reset to default color.
                            System.out.print(ANSI_RED + color + ANSI_RESET + " ");
                            // break out of code once a mathc has been found (otherwise we'll always be looking for the last color)
                            break;
                        case "green":
                            System.out.print(ANSI_GREEN + color + ANSI_RESET + " ");
                            break;
                        case "blue":
                            System.out.print(ANSI_BLUE + color + ANSI_RESET + " ");
                            break;
                        case "black":
                            System.out.print(ANSI_BLACK + color + ANSI_RESET + " ");
                            break;
                        case "cyan":
                            System.out.print(ANSI_CYAN + color + ANSI_RESET + " ");
                            break;
                        case "yellow":
                            System.out.print(ANSI_YELLOW + color + ANSI_RESET + " ");
                            break;
                        case "white":
                            System.out.print(ANSI_WHITE + color + ANSI_RESET + " ");
                            break;
                        case "magenta":
                            System.out.print(ANSI_MAGENTA + color + ANSI_RESET + " ");
                            break;
                    }
                }

                // create some space in between the guess and the feedback
                System.out.println();


                // variable that holds the score of each guess by calling the guessScore method
                List<String> feedback = guessScore(new ArrayList<>(code), guess);
                /* 
                 since the guessScore method returns a list it will be displayed in brackets and comma separated,
                 therfore we will iterate through the list to display the symbols without the brackets and commas
                */
                for (String e : feedback) {
                	// use print and not println so each symbol will be on the same line
                    System.out.print(e);
                }
                
                // output a space in between each round
                System.out.println('\n');
                
                // test if the user correctly guessed the code by testing for all black feedback
                if (feedback.get(0).equals(BLACK) && feedback.get(1).equals(BLACK) && feedback.get(2).equals(BLACK) && feedback.get(3).equals(BLACK)) {
                    System.out.println("\nCongratulations!! You broke the code!");
                    // breaking out of the main for loop once a user guesses the code
                    break;
                // If user had 12 chances ouput a gameover message.
                } else if (i == GUESSES - 1) {
                    System.out.println("\nGAME OVER! You have had " + GUESSES + " times to guess the code.\nBetter luck next time.");
                }
            }
        }
        }
    }


    // makeCode method which outputs an Arraylist of a random selection of four colors
    public static ArrayList<String> makeCode() {
        // create an instance of ArrayList
        ArrayList<String> code = new ArrayList<>();
        // create instance of Random
        Random rand = new Random();
        // iterate four times for length of code
        for (int i = 0; i < 4; i++) {
        /* 
         add a randomly selected color to "code" by using the random number as an index to choose a value in the colors Arraylist.
         using the length of "colors" as the range for the rand number (starts from zero up to but not including length number)
        */
            code.add(colors[rand.nextInt(colors.length)]);
        }
        // returns the code
        return code;
    }
    /* 
     guessScore method which outputs the score of each guess
     this method generates a list of string values. It takes a list of strings (the code) and a sting (users guess)
    */
    public static List<String> guessScore(List<String> codeColors, String guessedColors) {
        // create a an array of the users guess which is a string by splitting by each space
        String[] guessList = guessedColors.split(" ");
        // change the colors to lower case to compare with those in the code that are in lowercase
        for (int i = 0; i < guessList.length; i++) {
             guessList[i] = guessList[i].toLowerCase();
}

        // create a list for the feedback symbols
        List<String> symbols = new ArrayList<>();
        /* 
         first check for exact matches in the code to see if any black circles are to be in the feedback
         by iterating four times and testing for it
        */
        for (int i = 0; i < guessList.length; i++) {
            // test if the color at the same index in both the code and the guess are the same
            if (codeColors.get(i).equals(guessList[i])) {
                // add a black circle to the feedback symbols for a correct color at the correct spot
                symbols.add(BLACK);
                // once  a color has been scored a black circle, remove it by assigning it a blank space
                guessList[i] = " ";
                /*
                   similarly set the corresponding color in the code to another symbol. This is so that when looking for 
                   colors in the next block of code, (which only needs a color to be contained in the code) we shouldn't count 
                   a color that has already been scored.
                   We also use different symbols so that a white symbol shouldn't be added for used guesses. 
                */
                codeColors.set(i, "/");
            }
        }
        
        // check for correctly guessed colors in the code but in the wrong spot, by iterating four times and testing for it
        for (int i = 0; i < guessList.length; i++) {
            // use contains function to see if each remaining color is in the code.  (the blank space will not be in the code) 
            if (codeColors.contains(guessList[i])) {
                // add white symbol if found
                symbols.add(WHITE);
                /* 
                 resetting the code colors to a "/" to show it was used. Here, we must first get the index of the color found
                 in the code and feed that number to the set method since unlike in the previous block of code, we don't know
                 where in the code the color is found. Therfore, we fist use indexOf to get the index which is fed to the set
                 and that index in the code is changed.
                */
                codeColors.set(codeColors.indexOf(guessList[i]), "/");
                // once a color has been scored a white circle, remove it by assigning it a blank space
                guessList[i] = " ";
                // if the color is not a space (it is not a color that has been awardeed a black or white circle)
            } else if (!guessList[i].equals(" ")) {
                // add a dash to the feedback symbols
                symbols.add("-");
            }
        }
        // return the feedback symbols precede by the word "Score: "
        System.out.print("Score: ");
        
         return symbols;
    }
    
    // method to test if user input is valid
    public static boolean isValidGuess(String guess) {
    	// split the guess at each space
        String[] guessList = guess.split(" ");
        // test if length is less than or greater than 4 (if user entered wrong amount of colors or if there were too many spaces).
        if (guessList.length != 4) {
            return false;
        }
        // use a for each loop to loop through the colors guessed by user and test if they are in the colors array
        for (String color : guessList) {
            /* 
        	 convert the colors array to a list (to be able to use the "contains" method and test if it contains the color. 
        	 also convert to lower case since user might enter upper case
        	 use the negation symbol to see if it is NOT in the colors array
            */
            if (!Arrays.asList(colors).contains(color.toLowerCase())) {
                return false;
            }
        }
        // if the length is 4 and the colors are valid
        return true;
    }
}


