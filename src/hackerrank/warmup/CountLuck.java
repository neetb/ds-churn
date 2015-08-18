package hackerrank.warmup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by ramneet on 15/5/15.
 */
public class CountLuck {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCasesCount = Integer.parseInt(sc.nextLine());
        int[] Ks = new int[testCasesCount];
        List<String[][]> inputs = new ArrayList<String[][]>();

        for(int i = 0; i < testCasesCount; i++) {
            String dimensionLine = sc.nextLine();
            String[] dimension = dimensionLine.split(" ");
            int N =  Integer.parseInt(dimension[0]);
            int M =  Integer.parseInt(dimension[1]);

            String[][] input = new String[N][M];

            for(int j = 0; j < N; j++)  {
                String splits[] = sc.nextLine().split("");
                for (int k = 0; k < M; k++) {
                    //System.out.println(splits.length);
                    input[j][k] = splits[k];
                }
                //sc.nextLine();
            }

            inputs.add(input);
            Ks[i] = Integer.parseInt(sc.nextLine());

        }

        CountLuck sol = new CountLuck();
        sol.initDFS(inputs,Ks);
    }

    private void initDFS(List<String[][]> inputs, int[] Ks)  {

        int i = 0;
        for(String[][] input : inputs) {

            Graph g = initGraph(input);
            DFS dfs = new DFS(g, g.source);

            int dest = g.dest;
            int myK = 0;

            if(dfs.hasPathTo(dest)) {
                for(int p : dfs.path(dest)) {
                    if( p == g.source)  {
                        if(g.adj(p).size() > 1) {
                            myK++;
                        }
                        continue;
                    }

                    if(g.adj(p).size() > 2) {
                        myK++;
                    }
                }
            }

            if(myK == Ks[i]) {
                System.out.println("Impressed!");

            }else   {
                System.out.println("Oops!");
            }

            i++;
        }
    }

    private Graph initGraph(String[][] input){
        int N = input.length;
        int M = input[0].length;
        Graph graph = new Graph(M * N);

        for(int i = 0; i < N; i++)   {
            for(int j = 0; j < M; j++)   {
               String current = input[i][j];
               if(current.equals("X"))   {
                  //blocked
               }else {

                   if(isValid(i-1, j, N, M) && !(input[i-1][j].equals("X")))  {
                       graph.addEdge(getPos(i, j, N, M), getPos(i-1, j, N, M));
                   }

//                   if((isValid(i+1, j, N, M)) && !(input[i+1][j].equals("X")))  {
//                       graph.addEdge(getPos(i, j, N, M), getPos(i+1, j, N, M));
//                   }

                   if((isValid(i, j-1, N, M)) && !(input[i][j-1].equals("X")))  {
                       graph.addEdge(getPos(i, j, N, M), getPos(i, j-1, N, M));
                   }

                  /* if((isValid(i, j+1,N, M)) && !(input[i][j+1].equals("X")))   {
                       graph.addEdge(getPos(i, j, N, M), getPos(i, j+1, N, M));
                   }*/

                   if(current.equals("*"))   {
                       graph.setDest(getPos(i,j,N,M));
                   }

                   if (current.equals("M"))   {
                       graph.setSource(getPos(i,j,N,M));
                   }
               }
            }
        }
        return graph;
    }

    private boolean isValid(int i, int j, int N, int M)   {
        return ((i >= 0 )  && (i < N) && (j >= 0) && (j < M));
    }

    private int getPos(int i, int j, int N, int M)   {
         return (i * M) + j;
    }

    class Graph {

        private int E;
        private int V;
        private int source;
        private int dest;

        private List<Integer>[] adj;

        public Graph(int V)  {
           this.V = V;
           this.E = 0;
            adj = (List<Integer>[])new List[V];
            for(int i = 0; i < V; i++)  {
                adj[i] = new ArrayList<Integer>();
            }
        }

        public void setSource(int s)    {
            this.source = s;
        }

        public void setDest(int d)  {
            this.dest = d;
        }

        public void addEdge(int v, int w)   {
            adj[v].add(w);
            adj[w].add(v);
        }

        public List<Integer> adj(int s) {
            return adj[s];
        }

    }

    class DFS {

        boolean[] marked;
        int[] edgeTo;
        int source;

        public DFS(Graph g, int s)   {
            this.source = s;
            edgeTo = new int[g.V];
            marked = new boolean[g.V];
            edgeTo[s] = s;
            marked[s] = true;
            //initialize edgeTo ?

            for(Integer v : g.adj(s)) {
                if(!marked[v])  {
                    marked[v] = true;
                    edgeTo[v] = s;
                    dfs(g, v);
                }
            }

        }

        private void dfs(Graph g, int v)    {
            for(Integer w : g.adj(v))   {
                if(!marked[w])  {
                    marked[w] = true;
                    edgeTo[w] = v;
                    dfs(g, w);
                }
            }
        }

        public boolean hasPathTo(int v) {
            return marked[v];
        }

        public Iterable<Integer> path(int d)    {
            if(!hasPathTo(d))   {
                return null;
            }

            Stack<Integer> stack = new Stack<Integer>();

            for( int i = d; i!=source; i = edgeTo[i])   {
                stack.push(i);
            }

            stack.push(source);

            return stack;
        }

    }

}
