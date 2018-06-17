package temp_test_example;

public class LinkedList<E> {
    Node<E> head;
    int size;
    
    private class Node<E> {
        private E data;
        private Node<E> next;
        
        private Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    public LinkedList(E data) {
        this.head = new Node<E>(data);
        this.size = 1;
    }
    
    /** Add node to the end of the list. **/
    public void append(E data) {
        Node<E> tmp = head;
        
        while(tmp.next != null) {
            tmp = tmp.next;
        }
        
        tmp.next = new Node<E>(data);
        size++;
    }
    
    /** Determine if insertion index is valid.
        @param index The insertion index to check.
        @return true if index exists within current list; otherwise, false.
        @throws IndexOutOfBoundsException if index is invalid and prints an error message. 
    **/
    public boolean checkIndex(int index) {
        if(index < 0 || size <= index) {
            try {
                throw new IndexOutOfBoundsException(
                    "You attempted to perform an operation at index " + index 
                    + ", but the largest index in the list is " + (size - 1) + "."
                );

            } catch (Exception e) {
                System.err.println(e);
                return false;
            }
        }
        
        return true;
    }
    
    /** Add node at a specific index; if index is invalid, inserts at the end of the list. 
        @param data The item to be inserted.
        @param index The index to insert the new node at.
    **/
    public void insert(E data, int index) {
        if(checkIndex(index)) { // Otherwise insert at correct index
            Node<E> newNode = new Node<E>(data);
            
            // Insert at front
            if (index == 0) {
                newNode.next = head;
                head = newNode;
   
            }
            else { // Traverse the list to find insertion point
                int count = 1;
                
                // Create a reference to the node just before the insertion point
                Node<E> tmp = head;
                while(tmp.next != null && count < index) {
                    tmp = tmp.next;
                    count++;
                }
                
                // Insert the node
                newNode.next = tmp.next;
                tmp.next = newNode;
            }
            // Increment list size
            size++;
        }
    }
    
    /** Delete the node at a given index.
        @param index The index of the node to be deleted.
    **/
    public void delete(int index) {
        if(checkIndex(index)) {
            // Delete from front
            if (index == 0) {
                head = head.next;
            }
            else { // Traverse the list to find deletion point
                int count = 1;
                
                // Create a reference to the node just before the deletion point
                Node<E> tmp = head;
                while(tmp.next != null && count < index) {
                    tmp = tmp.next;
                    count++;
                }
                
                // Change references to skip over node being deleted
                tmp.next = tmp.next.next;
            }
            // Decrement list size
            size--;
        }
    }
    
    /** Print the current list **/
    public void print() {
        Node<E> tmp = head;
        
        System.out.print("Current List: ");
        
        while(tmp != null) {
            System.out.print(tmp.data + " ");
            tmp = tmp.next;
        }
        
        System.out.println();
    }
}  