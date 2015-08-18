package ds;

import java.util.Arrays;

/**
 * Created by abhinav on 11/6/15.
 */
public class HashTable<K,V> {

    private Entry[] arr;
    private int size;
    private int count;

    public HashTable(int initialSize)   {
        this.arr = (Entry[])new Object[initialSize];
        this.size = initialSize;
        this.count = 0;
    }

    public HashTable()   {
        //default initial size
        this.arr = (Entry[])new Object[10];
        this.size = 10;
        this.count = 0;
    }

    public void insert(K key, V value)  {
        if(key == null)
            return;

        /*if(reHashNeeded())  {
            rehash();
        }*/
        int hash = hash(key);
        int index = hash % size;
        Entry entry = arr[index];
        if(entry != null)   {
            arr[index] = new Entry(key, value, entry);
        }else   {
            arr[index] = new Entry(key, value, null);
        }
        count++;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = hash % size;
        Entry entry = arr[index];
        while(entry!=null)  {
            if(entry.key.equals(key))   {
                return (V)entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    private void rehash()   {
        Entry[] newArr = Arrays.copyOf(arr, 2 * arr.length);
        for(int i = 0; i < arr.length; i++) {
            Entry entry = arr[i];
            while(entry != null)    {
                int hash = hash((K)entry.key);
                int index = hash % size;
                Entry existing = arr[index];
                if(existing != null)   {
                    newArr[index] = new Entry(entry.key, entry.value, entry);
                }else   {
                    newArr[index] = new Entry(entry.key, entry.value, null);
                }
            }
        }
    }

    private int hash(K key) {
        return key.hashCode();
    }

    private boolean rehashNeeded()  {
        return count / size > 0.7;
    }

    class Entry<K, V>   {
        private K key;
        private V value;
        private Entry next;

        public Entry(K key, V value, Entry next)    {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
