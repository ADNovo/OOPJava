import java.util.Scanner;

public class Game {
    /**
     * Class that represents a "Guess The Movie" Game:
     * The rules are simple, the computer randomly picks a movie title, and shows the player how many letters it's made
     * up of. The goal is to try to figure out the movie by guessing one letter at a time. If a letter is indeed in the
     * title the computer will reveal its correct position in the word, if not, the player loses a point. If the player
     * loses 10 points, the game is over.
     */

    private String movieToGuess;
    private int pointsLost;
    private String wrongLetters;
    private String rightLetters;
    private boolean gameWon;

    /**
     * Class constructor that initializes a {@link MovieList} object from a file containing the movies' titles, and
     * all other attributes nof the game:
     * pointsLost = points lost so far;
     * rightLetters = letters guessed that are actually in the movie title (in upper and lower case);
     * wrongLetters = letters guessed that are not in the movie title;
     * gameWon = whether the player has already won the game.
     *
     * @param pathname          Path to a file containing the movies' titles.
     */
    public Game(String pathname) {
        MovieList movieList = new MovieList(pathname);
        movieToGuess = movieList.getRandomMovie().trim();
        pointsLost = 0;
        rightLetters = "";
        wrongLetters = "";
        gameWon = false;
    }

    /**
     * Method that returns the title of the movie to be guessed.
     *
     * @return title of the movie to be guessed.
     */
    public String getMovieTitle() {
        return movieToGuess;
    }

    /**
     * Method that replaces all the letters in the movie title with underscores, if no letters have been correctly
     * guessed yet, and all the letters except the ones guessed, if any letter was already correctly guessed.
     *
     * @return {@link String} with the movie title with the letters not guessed hidden.
     */
    public String getHiddenMovieTitle() {
        if(rightLetters.equals("")){
            return movieToGuess.replaceAll("[a-zA-Z]", "_");
        }
        else{
            return movieToGuess.replaceAll("[a-zA-Z&&[^" + rightLetters +"]]", "_");
        }
    }

    /**
     * Method that returns letters guessed that are not in the movie title.
     *
     * @return {@link String} with letters guessed that are not in the movie title separated by blank spaces.
     */
    public String getWrongLetters() {
        return wrongLetters;
    }

    /**
     * Method that returns true if the game was won and false otherwise.
     *
     * @return true if the game was won and false otherwise.
     */
    public boolean WonGame() {
        return gameWon;
    }

    /**
     * Method that returns that the game has ended and the player did not win if number of points lost is at least 10,
     * and returns that the game has ended and the player won if the previous is not true and there are no underscores
     * left in the hidden version of the movie title.
     *
     * @return true if the game has ended and false otherwise.
     */
    public boolean gameEnded() {
        if (pointsLost >= 10) {
            return true;
        }

        if(!getHiddenMovieTitle().contains("_")) {
            gameWon = true;
            return true;
        }
        return false;
    }

    /**
     * Method that (1) asks the player to input a letter; (2) converts it to lower case; (3) asks him to input another
     * letter (implemented recursively) if (a) the {@link String} inputted is not a letter or (b) if the letter was
     * already guessed and so is included in the {@link String} objects containing the letters guessed wrongly and
     * correctly, respectively; (4) if the {@link String} inputted is a letter not guessed yet, the method returns the
     * letter.
     *
     * @return Letter not guessed yet.
     */
    private String inputLetter(){

        System.out.println("Guess a letter:");
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine().toLowerCase();

        if(!letter.matches("[a-z]")){
            System.out.println("That is not a letter.");
            return inputLetter();
        }
        else if(wrongLetters.contains(letter) || rightLetters.contains(letter)){
            System.out.println("You already guessed that letter.");
            return inputLetter();
        }
        else{
            return letter;
        }
    }

    /**
     * Method that (1) asks the player for a letter not guessed and (a) if the guess is correct, adds it to the
     * {@link String} that contains the letters guessed correctly in upper and lower case, (b) otherwise increases
     * the number of points lost by 1 and adds the letter to the {@link String} that contains the letters guessed
     * wrongly.
     */
    public void guessLetter() {

        String guessedLetter = inputLetter();

        if (movieToGuess.toLowerCase().contains(guessedLetter)) {
            rightLetters += guessedLetter + guessedLetter.toUpperCase();
        }
        else {
            pointsLost++;
            wrongLetters += " " + guessedLetter;
        }
    }
}
