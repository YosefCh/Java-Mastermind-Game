# Mastermind Game

This is a Java implementation of the classic game of Mastermind. The game is a code-breaking game where the player has to guess a code of four colors. The player has 12 guesses to break the code.

## How to Play

1. The game will display a list of acceptable colors at each turn.
2. Each color should be entered separated by a space.
3. Input with misspelled colors, colors not in the list, or extra spaces will have to be reentered.
4. The game will provide feedback in the form of symbols after each guess:
    - **Black Peg**: Correct color at the correct spot.
    - **White Peg**: Correct color but in the wrong spot.
    - **Blank**: Color not in the code.

## Code Structure

The code is structured into several methods:

- `makeCode()`: This method generates a random code of four colors.
- `guessScore()`: This method takes the code and the user's guess as input and returns feedback in the form of symbols.
- `isValidGuess()`: This method checks if the user's input is valid.

## Running the Game

To run the game, simply compile and run the `mastermind.java` file. The game will prompt you for your guesses and provide feedback after each guess.

## Note on IDE Compatibility

The symbols for the black and white feedback depend on the IDE used for the file. The current implementation works on Eclipse. However, for other IDEs like VSCode, some changes might need to be made to correctly display the symbols.

## Author

This game was created by Yosef Birnbaum on June 7, 2024.

Enjoy the game and good luck breaking the code!
