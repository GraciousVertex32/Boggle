import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.TST
import java.util.Arrays;
import java.util.HashSet;

public class BoggleSolver
{
    private final HashSet<String> Dictionary;
    private final TST tst;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary)
    {
        Dictionary = new HashSet<String>(Arrays.asList(dictionary)) ;
        tst = new TST();
        for (String word : Dictionary)
        {
            tst.put(word, word.length());
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
        Graph g = ConnectBoard(board);

    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word)
    {

    }

    private Graph ConnectBoard(BoggleBoard board)
    {
        int row = board.rows();
        int col = board.cols();
        Graph g = new Graph(row*col);
        for (int i = 1; i <= g.V(); i++)
        {
            // all horizontal connection
            if (col % i > 2)
            {
                g.addEdge(i - 1, i);
                g.addEdge(i - 1, i -2);
            }
            if (col % i == 1)
            {
                g.addEdge(i - 1, i);
            }
            if (col % i == 0)
            {
                g.addEdge(i - 1, i - 2);
            }
            //all vertical connection
            if (i > col || i <= col * (row - 1))
            {
                g.addEdge(i - 1, i - col -1);
                g.addEdge(i - 1, i + col -1);
            }
            if (i <= col)
            {
                g.addEdge(i - 1, i + col -1);
            }
            if (i > col * (row - 1))
            {
                g.addEdge(i - 1, i - col -1);
            }
        }
        return g;
    }
    private boolean IsPrefix(String s)
    {
        return tst.keysWithPrefix(s) != null;
    }
}
