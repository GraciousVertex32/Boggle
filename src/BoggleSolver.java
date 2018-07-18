import edu.princeton.cs.algs4.TST
import java.util.Arrays;
import java.util.HashSet;

public class BoggleSolver
{
    private final HashSet<String> Dictionary;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary)
    {
        Dictionary = new HashSet<String>(Arrays.asList(dictionary)) ;
        TST tst = new TST();
        for (String word : Dictionary)
        {
            tst.put(word, word.length());
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {

    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word)
    {

    }
}
