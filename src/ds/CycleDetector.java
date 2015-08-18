package ds;

/**
 * Created by ramneet on 29/4/15.
 */
public class CycleDetector {

    private Graph g;
    private boolean cycle;
    private boolean[] marked;

    public CycleDetector(Graph g)   {
        this.g = g;
        marked = new boolean[g.V()];

        for(int i=0; i<g.V(); i++)  {
            if(!marked[i])
                dfs(i, i);
        }
    }

    private void dfs(int v, int s) {
        marked[v] = true;
        for(int w : g.adj(v))   {
            if(!marked[w])  {
                marked[w] = true;
                dfs(w, v);
            }else   {
                if(w != s)  {
                    cycle = true;
                }
            }
        }

    }

    public boolean hasCycle()   {
        return cycle;
    }

}
