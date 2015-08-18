package ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by ramneet on 29/4/15.
 */
public class DepthFirstSearch {

    private Graph g;
    private boolean[] marked;
    private int count;
    private int[] edgeTo;
    private int s;

    public DepthFirstSearch(Graph g, int s) {
        this.g = g;
        this.s = s;
        this.marked = new boolean[g.V()];
        this.edgeTo = new int[g.V()];
        dfs(s);
    }

    private void dfs(int v)  {
        for (int w : this.g.adj(v)) {
            if(!marked[w])  {
                marked[w] = true;
                edgeTo[w] = v;
                count ++;
                dfs(w);
            }
        }

    }

    public boolean marked(int v)    {
        return marked[v];
    }

    public int count()  {
        return count;
    }

    public boolean hasPathTo(int v)  {
        return marked[v];
    }

    public List<Integer> path(int v)    {
        Stack<Integer> stack = new Stack<Integer>();
        while(edgeTo[v] != s)   {
            stack.push(edgeTo[v]);
        }
        stack.push(s);

        List<Integer> list = new ArrayList<Integer>();
        while(!stack.isEmpty())  {
          list.add(stack.pop());
        }

       for(int s : list) {
            System.out.print(s + " " );
        }

        return list;
    }

}
