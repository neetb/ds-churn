package hackerrank.warmup.hackerrank.difficult;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ramneet on 20/5/15.
 */
public class DynamicSummation {

    public static void main(String[] args)  {
        DynamicSummation solution = new DynamicSummation();

        //create an array of nodes.
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        Node[] nodes = new Node[N];
        for(int i = 0; i < N; i++)   {
            nodes[i] = new Node(i+1);
        }

        for(int i = 0; i < N-1; i++) {
            String[] pairInput = sc.nextLine().trim().split(" ");
            int v = Integer.parseInt(pairInput[0]);
            int w = Integer.parseInt(pairInput[1]);

            nodes[v - 1].adj.add(w);
            nodes[w - 1].adj.add(v);
        }

        int operationCount = sc.nextInt();
        sc.nextLine();
        int i = 0;

        List<String[]> inputReqs = new ArrayList<String[]>();
        for(int j = 0; j < operationCount; j++) {
            i++;
            String opString = sc.nextLine();
            String[] operands = opString.split(" ");
            inputReqs.add(operands);
            if(i == 10)
                break;

        }

         i = 0;
        for(String[] input : inputReqs) {
            if(i == 10)
                break;
            if(input[0].equals("U"))    {
                //update
                solution.update(nodes, Integer.parseInt(input[1].trim()), Integer.parseInt(input[2].trim()), new BigInteger(input[3].trim()), new BigInteger(input[4].trim()));
            }else   {
                //read
                BigInteger val =  solution.report(nodes, Integer.parseInt(input[1].trim()), Integer.parseInt(input[2].trim()), Integer.parseInt(input[3].trim()));
                System.out.println(val);
            }
            i++;
        }
    }

    //U 3 2 2 2
    private void update(Node[] nodes, int rootIndex, int subRootIndex, BigInteger a, BigInteger b)    {
        boolean[] marked = new boolean[nodes.length];
        BigInteger addTerm =   pow(a,b).add(pow(a.add(BigInteger.ONE),b)).add(pow(b.add(BigInteger.ONE),a));
        doUpdate(rootIndex, subRootIndex, marked, nodes, false, addTerm);
    }

    private BigInteger pow(BigInteger x, BigInteger n)  {
        if(n.intValue() == 0)
            return BigInteger.ONE;
        else if (n.intValue() == 1)
            return x;
        else if(n.mod(BigInteger.valueOf(2)) == BigInteger.ZERO)  {
            return pow(x.pow(2), n.divide(BigInteger.valueOf(2)));
        }else    {
            return x.multiply(pow(x.pow(2), (n.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2))));
        }
}

    private void doUpdate(int currentIndex, int subRootIndex, boolean[] marked, Node[] nodes, boolean subRootCrossed, BigInteger addTerm) {
        marked[currentIndex - 1] = true;

        if(currentIndex == subRootIndex)    {
          subRootCrossed = true;
        }

        Node current = nodes[currentIndex - 1];

        if(subRootCrossed)  {
           BigInteger newValue = addTerm.add(current.value);
            current.setValue(newValue);
        }

        for(int adjNodeIndex : current.getAdj())    {
            if(!marked[adjNodeIndex - 1]){
                doUpdate(adjNodeIndex, subRootIndex, marked, nodes, subRootCrossed, addTerm);
            }
        }
    }

    //R 1 2 8
    private BigInteger report(Node[] nodes, int rootIndex, int subRootIndex, int m) {
        boolean[] marked = new boolean[nodes.length];
        BigInteger value =   BigInteger.ZERO;
        value =  doReport(rootIndex, subRootIndex, marked, nodes, false, value);
        return value.mod(BigInteger.valueOf(m));
    }

    private BigInteger doReport(int currentIndex, int subRootIndex,  boolean[] marked, Node[] nodes, boolean subRootCrossed, BigInteger reportedValue) {
        marked[currentIndex - 1] = true;

        if(currentIndex == subRootIndex)    {
            subRootCrossed = true;
        }

        if(subRootCrossed)  {
            reportedValue = reportedValue.add(nodes[currentIndex - 1].value);
        }

        for(int adjNodeIndex : nodes[currentIndex - 1].getAdj())    {
            if(!marked[adjNodeIndex - 1]){
                reportedValue = doReport(adjNodeIndex, subRootIndex, marked, nodes, subRootCrossed, reportedValue);
            }
        }
        return reportedValue;
    }


    static class Node  {
        BigInteger value;
        int index;
        List<Integer> adj;

        public Node(int index)   {
            this.value = BigInteger.ZERO;
            this.index = index;
            adj = new ArrayList<Integer>();
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public BigInteger getValue() {
            return value;
        }

        public void setValue(BigInteger value) {
            this.value = value;
        }

        public List<Integer> getAdj() {
            return adj;
        }

        public void setAdj(List<Integer> adj) {
            this.adj = adj;
        }
    }
}
