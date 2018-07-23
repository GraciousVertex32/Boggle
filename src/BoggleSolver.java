import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.TST;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

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
            if (word.contains("Qu"))
            {
                tst.put(word, word.length() + 1);
            }
            else
            {
                tst.put(word, word.length());
            }
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board)
    {
        ArrayList<String> allwords = new ArrayList<>();
        boolean marked[] = new boolean[board.cols()*board.rows()];
        ArrayList<Character> temp = new ArrayList<>(); // compare to dictionary
        Graph g = ConnectBoard(board);
        dfs(g,1, marked, temp, board, allwords);
        return allwords;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word)
    {
        if (IsPrefix(word))
        {
            return (int) tst.get(word);
        }
        return 0;
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
    // check if current iteration can match
    private boolean IsPrefix(ArrayList<Character> c)
    {
        String s = c.toString();
        return tst.keysWithPrefix(s) != null;
    }
    private boolean IsPrefix(String s)
    {
        return tst.keysWithPrefix(s) != null;
    }
    //return char according to int in graph
    private char GraphToChar(int index, BoggleBoard board)
    {
        int row = index / board.cols();
        int col = index % board.cols();
        return board.getLetter(row,col);
    }
    private void dfs(Graph G, int first, boolean[] marked, ArrayList<Character> temp, BoggleBoard B, ArrayList<String> allwords)
    {
        marked[first] = true;
        temp.add(GraphToChar(first, B));
        if (IsPrefix(temp))// if some word down this path
        {
            for (int w : G.adj(first))
            {
                if (!marked[w])
                {
                    dfs(G, w, marked, temp, B, allwords);
                }
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}
