package ds;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private int E;
    private int V;
    private List[] bag;
   // private List<Integer> bag;

    public Graph(int V, int E) {
        for(int i=0; i< V; i++) {
            bag[i] = new ArrayList<Integer>();
        }

        this.V = V;
        this.E = E;
    }

    public Graph(String inputFile) throws IOException, FileNotFoundException {
        BufferedReader is = new BufferedReader(new FileReader(inputFile));
        String firstLine = is.readLine();
        String[] firstLineParts = firstLine.split(",");

        for(int i=0; i< V; i++) {
            bag[i] = new ArrayList<Integer>();
        }

        this.V = Integer.parseInt(firstLineParts[0]);
        this.E = Integer.parseInt(firstLineParts[1]);

        String inputTxt =  is.readLine();
        while(inputTxt != null)   {
             addEdge(Integer.parseInt(inputTxt.split(",")[0]), Integer.parseInt(inputTxt.split(",")[1]));
             inputTxt = is.readLine();
        }

    }

     public int V()  {
        return this.V;
     }

     public List<Integer> adj(int v) {
        return bag[v];
     }

    public void addEdge(int v, int w)   {
        bag[v].add(w);
        bag[w].add(v);

    }


}
