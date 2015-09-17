import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node end = null;
    private int len = 0;

    private class Node {
      private Item item;
      private Node next;
      private Node previous;
    }
    public Deque()                           // construct an empty deque
    {
        first = null;
        end = null;
        len = 0;
    }
    public boolean isEmpty()                 // is the deque empty?
    {
       return (len == 0);
    }
    public int size()                        // return the number of items on the deque
    {
        return len;  
    }
    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null) {
            throw new NullPointerException("Trying to add null element to the Deque");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (len == 0) {
            end = first;
        }
        len++;
        return;
    }
    public void addLast(Item item)           // add the item to the end
    {
        if (item == null) {
            throw new NullPointerException("Trying to add null element to the Deque");
        }
        Node newEnd = new Node();
        newEnd.item = item;
        newEnd.previous = end;
        newEnd.next = null;
        if (len == 0) {
            first = newEnd;
        }
        len++;
        return;
    }
    public Item removeFirst()                // remove and return the item from the front
    {
        if (len == 0) {
          throw new NoSuchElementException();
        }
        if (len == 1) {
           Node oldFirst = first;
           first = null;
           end = null;
           len = 0;
           return oldFirst.item;
        }
        Node oldFirst = first;
        first = first.next;
        len--;
        return oldFirst.item;
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if (len == 0) {
          throw new NoSuchElementException();
        }
        if (len == 1) {
           Node oldFirst = first;
           first = null;
           end = null;
           len = 0;
           return oldFirst.item;
        }
        end = end.previous;
        len--;
        return end.next.item;
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        private Node current;

        public ListIterator(Node first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    public static void main(String[] args)   // unit testing
    {
        
    }
}