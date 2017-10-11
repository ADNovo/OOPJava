public class Main {

    public static void main(String[] args) {
        Game game = new Game("movies.txt");

        System.out.println("Welcome to Guess the Movie!");
        System.out.println("The rules are simple, the computer randomly picks a movie title, and shows you how " +
                "many " + "letters it's made up of.");
        System.out.println("Your goal is to try to figure out the movie by guessing one letter at a time.");
        System.out.println("If a letter is indeed in the title the computer will reveal its correct position in" +
                " the " + "word, if not, you lose a point.");
        System.out.println("If you lose 10 points, game over!");
        System.out.println("Let's start!");
        System.out.println("The movie title has " + game.getMovieTitle().length() + " characters (including spaces "
                + "and punctuation).");

        while(!game.gameEnded()){
            System.out.println("You are guessing:" + game.getHiddenMovieTitle());
            System.out.println("You have guessed (" + game.getWrongLetters().length()/2 + ") wrong letters:"
                    + game.getWrongLetters());
            game.guessLetter();
        }
        if(game.WonGame()){
            System.out.println("You win!");
            System.out.println("You have guessed " + game.getMovieTitle() + " correctly.");
        }
        else{
            System.out.println("You have guessed (" + game.getWrongLetters().length()/2 + ") wrong letters:" +
                    game.getWrongLetters());
            System.out.println("You lost!");
            System.out.println("The movie title was " + game.getMovieTitle());
            System.out.println("Better luck next time.");
        }
    }
}
