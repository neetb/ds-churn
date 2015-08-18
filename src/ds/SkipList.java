package ds;

/**
 * Created by ramneet on 9/6/15.
 */
public class SkipList {

    private SkipListNode<Integer> head;
    private SkipListNode<Integer> tail;
    private int maxLevel;

    public SkipList(int maxLevel)   {
       this.head = new SkipListNode<Integer>(Integer.MIN_VALUE, maxLevel, true);
       this.tail = new SkipListNode<Integer>(Integer.MAX_VALUE, maxLevel, true);
       this.maxLevel = maxLevel;

        for(int i = 0; i <= maxLevel; i++) {
            this.head.forward[i] = this.tail;
        }
    }

    public static void main(String[] args) {
        SkipList list = new SkipList(3);
        list.insert(20);

        System.out.println(list.search(20).data);
        list.insert(22);
        list.insert(2);
        list.insert(50);

        System.out.println("before del : " +list.search(22));
        list.delete(22);
        System.out.println("after del" + list.search(22));


    }

    public void delete(int key) {
        SkipListNode<Integer> current = this.head;
        SkipListNode<Integer> prev = current;
        SkipListNode<Integer> next = null;
        for (int i = maxLevel; i >= 0; i--) {
            next = prev.forward[i];

            while (next.data < key) {
                prev = next;
                next = next.forward[i];
            }

            if (next.data == key) {
                prev.forward[i] = next.forward[i];
                next.forward[i] = null;
            }
        }

    }

    public void insert(int data)    {
        int level = getRandomLevel();
        SkipListNode<Integer> node = new SkipListNode<Integer>(data, level, false);
        insertUtil(data, maxLevel, node, this.head);
    }


    public void insertUtil(int data, int level, SkipListNode<Integer> node,  SkipListNode<Integer> head)    {

        if(level < 0) {
            return;
        }

        SkipListNode<Integer> current = head;
        SkipListNode<Integer> next = head.forward[level];
        while(next.data < data) {
            current = next;
            next = current.forward[level];
        }

        insertUtil(data, level - 1, node, current);

        if((level <= node.level) && (current.data != data)) {
            SkipListNode temp = current.forward[level];
            current.forward[level] = node;
            node.forward[level] = temp;
        }
    }

    public SkipListNode search(int key)  {
        SkipListNode<Integer> current = this.head;
        for(int i = maxLevel; i >=0; i--)   {
            SkipListNode<Integer> next = current.forward[i];
            //SkipListNode prev = null;

            while(next.data < key) {
                current = next;
                next = current.forward[i];
            }
        }

        current = current.forward[0];

        if(current.data == key) {
             return current;
        }else   {
            return null;
        }
    }

    private int getRandomLevel()    {
       int level = 0;
       while((level < maxLevel) && (Math.random() < 0.5))  {
            level++;
       }
       return level;
    }




    class SkipListNode<T>   {
        T data;
        int level;
        SkipListNode[] forward;
        boolean sentinel;

        public SkipListNode(T data, int level, boolean sentinel) {
            this.data = data;
            this.level = level;
            forward = new SkipListNode[level + 1];
            this.sentinel = sentinel;
        }
    }
}
