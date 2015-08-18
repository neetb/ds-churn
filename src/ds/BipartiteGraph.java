package ds;

/**
 * Created by ramneet on 29/4/15.
 */
public class BipartiteGraph {

   private Graph g;
   private int colorA = 1;
   private int colorB = 2;
   private boolean[] marked;
   private int[] color;
   private boolean bipartite = true;


   public BipartiteGraph(Graph g)   {
       this.g = g;
       this.color = new int[g.V()];
       this.marked = new boolean[g.V()];
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
                color[w] = (color[v] == colorA ? colorB : colorA);
                dfs(w, v);
            }else   {
                if(color[w] == color[v])  {
                    bipartite = false;
                }
            }
        }

    }

    public boolean isTwoColorable() {
        return bipartite;
    }

}
