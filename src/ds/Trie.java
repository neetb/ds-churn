package ds;

import java.util.List;

/**
 * Created by abhinav on 5/6/15.
 */
public class Trie {

    private TrieNode root = new TrieNode();

    public static void main(String[] args) {
        Trie t = new Trie();
        t.add("a", 1);
        t.add("an", 2);
        t.add("and", 3);
        t.add("ape", 4);
        t.add("are", 5);
        t.add("ass", 6);
        t.add("ate", 7);
        t.add("age", 8);
        t.add("ago", 9);
        t.add("bat", 10);
        t.add("ban", 11);
        t.add("ramneet", 12);
        t.add("ramneek", 13);
        t.add("please", 14);
        //t.add("pleased", 15);
        t.add("pleaseds", 16);
        System.out.println(t.get("andxyz"));

/*
        TrieNode node = t.lookupNodeWithPrefix(t.root, "please", 0);
        t.printAllChildNodes(node, "please", "");
        System.out.println(node.next['d' - 'a']);

        t.delete(t.root, "pleaseds", 0);

        System.out.println("After deleting middle node pleased");
        System.out.println(node.next['d' - 'a']);
        t.printAllChildNodes(node, "please", "");

        t.delete(t.root, "pleaseds", 0);

        System.out.println("After deleting  node pleaseds");
        t.lookupAllKeysWithPrefix(t.root, "ple");*/
    }

    public void lookupAllKeysWithPrefix(TrieNode root, String prefix) {
        TrieNode node = lookupNodeWithPrefix(root, prefix, 0);
        if (node == null) {
            return;
        }

        printAllChildNodes(node, new String(prefix), null);

    }

    /*
    A node that is to be deleted can be of the following types -
    1. Internal node with value != null - Since its an internal node and will have child nodes, only set the value to null.
    2. Leaf node with value != null - Set the value as null and also delete the node. Recursively go to the parent, if parent has no child
    nodes delete that too.
     */
    public TrieNode delete(TrieNode root, String key, int index) {
        if (root == null) {
            return null;
        }

        if (index < key.length()) {
            char ch = key.charAt(index);
            root.next[ch - 'a'] = delete(root.next[ch - 'a'], key, index + 1);
            if ((!hasChildNodes(root)) && (root.val == null)) {
                root = null;
                return root;
            }
        }

        if (index == key.length()) {
            root.val = null;
            if (!hasChildNodes(root)) {
                root = null;
                return root;
            }
        }

        return root;
    }

    private boolean hasChildNodes(TrieNode node) {
        if (node == null)
            return false;

        if ((node.next == null) || (node.next.length == 0)) {
            return false;
        }

        for (int i = 0; i < node.next.length; i++) {
            if (node.next[i] != null) {
                return true;
            }
        }

        return false;
    }

    private void printAllChildNodes(TrieNode node, String prefix, String suffix) {
        if (node == null) {
            return;
        }

        if (suffix != null) {
            prefix = prefix.concat(suffix);
        }

        if (node.val != null) {
            System.out.println(prefix.toString());
        }

        for (int i = 0; i < node.next.length; i++) {
            if (node.next[i] != null) {
                //System.out.println(prefix.hashCode());
                printAllChildNodes(node.next[i], prefix, String.valueOf((char) (i + 'a')));
            }
        }

    }

    private TrieNode lookupNodeWithPrefix(TrieNode root, String pattern, int index) {
        if (root == null) {
            return null;
        }

        if (index == pattern.length()) {
            return root;
        }

        char ch = pattern.charAt(index);
        return lookupNodeWithPrefix(root.next[ch - 'a'], pattern, index + 1);

    }


    //Add to trie
    public void add(String input, Object value) {
        root = add(root, input, value, 0);
    }

    private TrieNode add(TrieNode root, String key, Object value, int index) {
        if (root == null) {
            root = new TrieNode();
        }

        if (index == key.length()) {
            root.val = value;
            return root;
        }

        char ch = key.charAt(index);
        root.next[ch - 'a'] = add(root.next[ch - 'a'], key, value, index + 1);
        return root;
    }


    public Object get(String pattern) {
        return get(root, pattern, 0);
    }

    private Object get(TrieNode root, String key, int index) {
        if (root == null) {
            return null;
        }

        if (index == key.length()) {
            return root.val;
        }

        char ch = key.charAt(index);
        return get(root.next[ch - 'a'], key, index + 1);
    }

    class TrieNode {
        Object val;
        TrieNode[] next;

        public TrieNode() {
            this.next = new TrieNode[26];
            this.val = null;
        }
    }
}
