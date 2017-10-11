import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class MovieList {
    /**
     * Class representing a list of movies.
     */

    private ArrayList <String> movies;

    /**
     * Class constructor that stores the movie titles contained a file in an {@link ArrayList}, until it scans all lines
     * in the file, if a valid path to the file exists or warns the user otherwise.
     *
     * @param pathname          Path to a file containing the movies' titles.
     */
    public MovieList(String pathname) {
        movies = new ArrayList();
        File file = new File(pathname);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                movies.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
        }
    }

    /**
     * Method that generates a random {@link Integer} from 0 to the number of movie titles in the list minus 1, and
     * returns the movie title in the movies {@link ArrayList} with that index.
     *
     * @return random movie title from the list.
     */
    public String getRandomMovie() {
        //Returns a string with a random movie title from the list.
        int movieIndex = (int) (Math.random() * movies.size());
        return movies.get(movieIndex);
    }
}
