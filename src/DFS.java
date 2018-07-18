import edu.princeton.cs.algs4.Graph;

import java.util.HashSet;

public class DFS
{
    Graph graph;
    boolean marked[];
    HashSet<String> Dictionary;
    public DFS(Graph g, HashSet<String> dictionary)
    {
        graph = g;
        marked = new boolean[g.V()];
        Dictionary = dictionary;
    }
    private void dfs(Graph G, int v, boolean[] marked, char[] temp)
    {
        marked[v] = true;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, marked, temp);
            }
        }
    }

}
