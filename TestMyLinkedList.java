/* 
 * Mayolo Valencia
 * 7/17/2022
 * 
 * This program tests all the methods in MyLinkedList class
 * this replicates the LinkedList class. The driver program tests
 * the methods. The MyLinkedList class holds the implemented methods
 * and the interface MyList has the abstract classes that are inherited 
 * by the MyLinkedList Class.
 * 
 */
package AssignmentsDataStructures;
import java.util.Collection;

public class TestMyLinkedList {
    public static void main(String[] args) {
        // Create a list for strings
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add("Santa");
        list.add("Teller");
        list.add("Jane");
        list.add("Mayolo");
        list.add("Jack");
        list.add("Mario");
        list.add("Jose");
        list.add("Jake");
        list.add("Megan");
        list.add("Peter");

        System.out.println("Original List:");
        System.out.println(list + "\n");

        System.out.println("Removing head node: ");
        list.removeFirst();
        System.out.println(list + "\n");

        System.out.println("Removing tail node: ");
        list.removeLast();
        System.out.println(list + "\n");

        System.out.println("Get head of list: " + list.getFirst());

        System.out.println("Get Tail of list: " + list.getLast() + "\n");

        list.addFirst("Jacob");
        System.out.println("Add to head: " + list);

        list.addLast("Samantha");
        System.out.println("Add to tail: " + list + "\n");

        System.out.println("Does the list contain Mayolo? " + list.contains("Mayolo"));
        System.out.println("Where is Mayolo's name in the list? " + list.indexOf("Mayolo"));
        System.out.println("where is the last occurrence of Mayolo's name? " + list.lastIndexOf("Mayolo"));

        System.out.println("\nWho's name is in index 5? " + list.get(5));

        System.out.println("\nUsing the clear() method. ");
        list.clear();
        System.out.println(list.toString());
        System.out.println("List is now cleared.");
    }
}

class MyLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0; // Number of elements in the list

    /** Create an empty list */
    public MyLinkedList() {
    }

    /** Create a list from an array of objects */
    public MyLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
            tail = head;
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new for element e

        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            tail = newNode; // tail now points to the last node
        }

        size++; // Increase size
    }

    @Override /**
               * Add a new element at the specified index
               * in this list. The index of the head element is 0
               */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }

    /**
     * Remove the head node and
     * return the object that is contained in the removed node.
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            E temp = head.element;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp;
        }
    }

    /**
     * Remove the last node and
     * return the object that is contained in the removed node.
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            E temp = head.element;
            head = tail = null;
            size = 0;
            return temp;
        } else {
            Node<E> current = head;

            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }

            E temp = tail.element;
            tail = current;
            tail.next = null;
            size--;
            return temp;
        }
    }

    @Override /**
               * Remove the element at the specified position in this
               * list. Return the element that was removed from the list.
               */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    @Override /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    @Override /** Clear the list */
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override /** Return true if this list contains the element e */
    public boolean contains(Object e) {
        return indexOf(e) >= 0;

    }

    @Override /** Return the element at the specified index */
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).element;
    }

    @Override /**
               * Return the index of the head matching element in
               * this list. Return -1 if no match.
               */
    public int indexOf(Object e) {
        int index = 0;
        if (e == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (e.equals(x.element))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override /**
               * Return the index of the last matching element in
               * this list. Return -1 if no match.
               */
    public int lastIndexOf(E e) {
        Node<E> current = head;
        int index = 0;
        // if not found this var will remain -1 and be returned
        int found = -1;
        while (current != null) {
            if (current.element.equals(e)) {
                found = index;
            }
            current = current.next;
            index++;
        }
        return found;
    }

    @Override /**
               * Replace the element at the specified position
               * in this list with the specified element.
               */
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> target = getNode(index);
        E prevEl = target.element;
        target.element = e;
        return prevEl;
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override /** Return the number of elements in this list */
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /*
     * ~~~~~~Node Class~~~~~~
     */
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    /*
     * ~~~~Linked List Iterator Class~~~~
     */
    private class LinkedListIterator implements java.util.Iterator<E> {
    	// Current index
        private Node<E> current = head; 

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }
    }
}


interface MyList<E> extends Collection<E> {
    public void add(int index, E e);

    public E get(int index);

    public int indexOf(Object e);

    public int lastIndexOf(E e);

    public E remove(int index);

    public E set(int index, E e);

    @Override
    public default boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override
    public default boolean remove(Object e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        }
        return false;
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        return true;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return true;
    }

    public default Object[] toArray() {
        return null;
    }

    @Override
    public default <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        return null;
    }
}
