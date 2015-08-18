package ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav on 5/6/15.
 */
public class SuffixTree {

    private Node root = new Node();

    public static void main(String[] args)  {
        SuffixTree tree = new SuffixTree();
        /*Node root = tree.buildSuffixTree("banana$");
        root = tree.buildSuffixTree("ban#");
*/

        Node root = tree.buildSuffixTree("ATCGATCGA$");
        root = tree.buildSuffixTree("ATCGA#");
        Result result = new Result();
        result.len = 0;
        System.out.println(tree.lcs(root, new Edge(""), result));

    }

    public String lcs(Node root, Edge connectingEdge, Result result) {
        if (root == null) {
            return null;
        }

        if ((hasBothLeafNodes(root))) {
            if(connectingEdge.label.length() > result.len)  {
                result.lcs = connectingEdge.label;
                result.len = connectingEdge.label.length();
            }
        }

        List<Edge> edges = root.edges;
        for (Edge edge : edges) {
            lcs(edge.toNode, edge, result);
            /*if((res != null) && (res.length() > result.len)) {
               result.lcs = res;
               result.len = res.length();
            }*/

        }

        return result.lcs;
    }

    private boolean hasBothLeafNodes(Node node) {
        List<Edge> edges = node.edges;
        boolean firstEOLExists = false;
        boolean secEOLExists = false;

        for (Edge edge : edges) {
            if(edge.label.contains("$"))    {
               firstEOLExists = true;
            }

            if(edge.label.contains("#"))    {
                secEOLExists = true;
            }

            if(firstEOLExists && secEOLExists)  {
                return true;
            }

        }

        return false;
    }

    public Node buildSuffixTree(String input)   {
        if(input == null)   {
            return null;
        }

        input = input.concat("$");
        String[] suffixArray = new String[input.length()];
        int len = input.length();

        for(int i = 0; i < len; i++) {
            suffixArray[i] = input.substring(i);
        }

        for(int i = len - 1; i >= 0; i--) {
            add(root, suffixArray[i]);
        }

        return root;
    }

    private Node add(Node root, String input)   {
        if(root == null)    {
            return new Node();
        }

        List<Edge> edges = root.edges;
        for(Edge edge : edges) {

            String commonStr = findCommonPrefix(edge.label, input);

            if((commonStr != null) && (commonStr.length() > 0))   {
                edge.label = commonStr;
                String remainingEdgeLabel = edge.label.substring(commonStr.length());
                if((remainingEdgeLabel != null) && (remainingEdgeLabel.length() != 0))
                    edge.toNode.edges.add(new Edge(remainingEdgeLabel));

                String remainingInput = input.substring(commonStr.length());
                add(edge.toNode, remainingInput);
                //edge.toNode.edges.add(new Edge(remainingInput));
                return root;
            }

        }

        Edge newEdge = new Edge(input);
        newEdge.toNode = new Node();
        root.edges.add(newEdge);
        return root;
    }

    private String findCommonPrefix(String input, String pattern)   {
        if((input == null) || (pattern == null))    {
            return null;
        }

        int len = input.length() > pattern.length() ? pattern.length() : input.length();
        int i = 0;
        for(i = 0; i < len; i++)    {
            if(input.charAt(i) != pattern.charAt(i))    {
                break;
            }
        }

        return input.substring(0, i);
    }

   static class Node  {
        List<Edge> edges;
        public Node()   {
            edges = new ArrayList<Edge>();
        }
    }

   static class Edge  {
        String label;
        Node toNode;
        public Edge(String label)   {
            this.label = label;
            this.toNode = new Node();
        }
    }

   static class Result    {
        String lcs;
        int len;
    }
}
