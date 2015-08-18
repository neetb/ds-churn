package ds;

/**
 * Created by ramneet on 29/4/15.
 */
public class CC {

    private Graph g;
    private int count;
    private int[] id;
    private boolean[] marked;

    public CC(Graph g)  {
        this.g = g;
        id = new int[g.V()];
        marked = new boolean[g.V()];
        for (int v=0; v<g.V(); v++) {
            if(!marked[v]) {
                id[v] = count;
                dfs(v);
                count++;
            }
        }


    }


    private void dfs(int v) {

        for (int w : g.adj(v))  {
            if(!marked[w])  {
               marked[w] = true;
               id[w] = count;
               dfs(w);
            }
        }

    }


    public int count()  {
        return count;
    }

    public boolean connected(int w, int v)  {
        return id[v] == id[w];
    }

}
