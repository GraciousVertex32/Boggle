import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.TST;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver
{
    private final String[] Dictionary;
    private final TST tst;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary)
    {
        this.Dictionary = dictionary ;
        tst = new TST();
        for (String word : Dictionary)
        {
            if (word.contains("Qu"))
            {
                tst.put(word, word.length() + 1);
            }
            else
            {
                if (word.length() <= 2)
                {
                    tst.put(word, 0);
                }
                if (word.length() == 3 || word.length() == 4)
                {
                    tst.put(word, 1);
                }
                if (word.length() == 5)
                {
                    tst.put(word, 2);
                }
                if (word.length() == 6)
                {
                    tst.put(word, 3);
                }
                if (word.length() == 7)
                {
                    tst.put(word, 5);
                }
                if (word.length() == 8)
                {
                    tst.put(word, 11);
                }
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
        for (int a = 0; a < g.V(); a++)
        {
            dfs(g, a, marked, temp, board, allwords);
        }
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
        for (int i = 0; i < g.V(); i++)
        {
            int x = i % col;
            int y = i / col;
            if (i==15)
            {
                int a = 1;
            }
            if (x + 1 < col)
            {
                g.addEdge(i, i + 1);
            }
            if (y + 1 < row)
            {
                g.addEdge(i, i + col);
            }
            if (x + 1 < col && y  +  1 < row)
            {
                g.addEdge(i, i + col + 1);
            }
            if (x - 1 >= 0 && y  +  1 < row)
            {
                g.addEdge(i, i + col - 1);
            }
        }
        return g;
    }




    // check if current iteration can match
    //================
    private boolean IsPrefix(ArrayList<Character> c)
    {
        String s = chartostring(c);
        ArrayList<String> temp = new ArrayList<>();
        for (Object word : tst.keysWithPrefix(s))
        {
            temp.add(word.toString());
        }
        return !temp.isEmpty();
    }
    //performance problem
    private boolean IsPrefix(String s)
    {
        ArrayList<String> temp = new ArrayList<>();
        for (Object word : tst.keysWithPrefix(s))
        {
            temp.add(word.toString());
        }
        return !temp.isEmpty();
    }
    //===================






    //return char according to int in graph
    private char GraphToChar(int index, BoggleBoard board)
    {
        int row = index / board.cols();
        int col = index % board.cols();
        return board.getLetter(row,col);
    }
    private void dfs(Graph G, int first, boolean[] marked, ArrayList<Character> temp, BoggleBoard B, ArrayList<String> allwords)
    {
        boolean newmarked[] = new boolean[marked.length];
        newmarked = marked.clone();
        newmarked[first] = true;
        if (first == 5)
        {
            int v = 1;
        }
        ArrayList<Character> trys = new ArrayList<>();
        trys = (ArrayList<Character>) temp.clone();
        trys.add(GraphToChar(first, B));
        String neword = chartostring(trys);
        if (tst.contains(neword) && neword.length() >= 3)
        {
            allwords.add(chartostring(trys));
        }
        if (IsPrefix(trys))// if some word down this path
        {
            for (int w : G.adj(first))
            {
                if (!marked[w])
                {
                    dfs(G, w, newmarked, trys, B, allwords);
                }
            }
        }
    }
    private String chartostring(ArrayList<Character> chararray)
    {
        StringBuilder sb = new StringBuilder();
        for (char s : chararray)
        {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        In in = new In("dictionary-yawl.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard("board-points100.txt");
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}
