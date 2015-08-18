package ds;

import java.util.*;

/**
 * Created by abhinav on 29/4/15.
 */
public class BreadthFirstSearch {

    private Graph g;
    private boolean[] marked;
    private int count;
    private int[] edgeTo;
    private int s;

    public BreadthFirstSearch(Graph g, int s) {
        this.g = g;
        this.s = s;
        this.marked = new boolean[g.V()];
        this.edgeTo = new int[g.V()];
        bfs(s);
    }

    private void bfs(int v)  {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(v);
        marked[v] = true;

        while(!queue.isEmpty()) {
            int w = queue.remove();
            for(int x : g.adj(w))   {
                if(!marked[x])  {
                    marked[x] = true;
                    edgeTo[x] = w;
                    count ++;
                    queue.add(x);
                }
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
