import java.util.NoSuchElementException;

public class LinkedList<E> {
    Node<E> first;
    private int sizeList = 0;

    LinkedList(){
    }

    LinkedList(Node<E> first){
        this.first = first;
    }

    void add(Node<E> node){
        Node<E> current = first;
        if(first == null) {
            first = node;
        }else if(first.next == null){
            first.next = node;
            sizeList++;
        }else{
            while(current.next != null){
                current = current.next;
            }
            current.next = node;
            sizeList++;
        }
    }

    void add(Node<E> node, int index) throws IndexOutOfBoundsException {
        Node<E> current = first;
        Node<E> previous;
        int counter = 0;

        if(index == 0){
            node.next = first;
            first = node;
            sizeList++;
        }else{
            while(current != null){
                previous = current;
                current = current.next;
                counter++;
                if(counter == index){
                    node.next = current;
                    previous.next = node;
                    sizeList++;
                }
            }
            if(index >= size() || index < 0){
                throw new IndexOutOfBoundsException();
            }
        }
    }

    void remove(Node<E> node){
        Node<E> current = first;
        Node<E> previous;

        if(current.equals(node)){
            first = current.next;
            sizeList--;
        }else{
            while(current.next != null){
                previous = current;
                current = current.next;
                if(current.equals(node)){
                    previous.next = current.next;
                    sizeList--;
                }
            }
        }
    }

    boolean exists(Node<E> node){
        Node<E> current = first;

        while(current != null){
            if(current.equals(node)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    boolean equals(LinkedList<E> list){
        Node<E> current = first;
        for (int i = 0; i < size(); i++) {
            if(list.get(i)!= current.data){
                return false;
            }
            current = current.next;
        }
        return true;
    }

    Node<E> getParent(Node<E> node)throws NoSuchElementException {
        Node<E> current = first;
        Node<E> previous = null;
        Node<E> parentNode = null;

        if(!exists(node)){
            throw new NoSuchElementException();
        }
        while(current != null) {
            if(node.equals(current)){
                parentNode = previous;
            }
            previous = current;
            current = current.next;
        }
        return parentNode;
    }

    void truncateList(E value){
        Node<E> converter = new Node<>(value);
        Node<E> current = first;
        Node<E> previous = null;
        while(current.next != null) {
            if(converter.equals(current)){
                previous.next = null;
                break;
            }
            previous = current;
            current = current.next;
        }
    }

    //Extra Methods
    E get(int index) throws IndexOutOfBoundsException{
        Node<E> current = first;
        for (int i = 0; i < size(); i++) {
            if(i == index){
                return current.data;
            }else if(index > size()){
                throw new IndexOutOfBoundsException();
            }
            current = current.next;
        }
        return null;
    }

    void removeFront(){
        first = first.next;
        sizeList--;
    }

    void removeLast(){
        Node<E> current = first;
        Node<E> previous = first;

        while(current.next != null){
            previous = current;
            current = current.next;
        }
        previous.next = null;
        sizeList--;
    }

    int size(){
        return sizeList + 1;
    }

    public String toString() {

        if (size() == 0 || first == null) {
            return "[ ]";
        }
        StringBuilder result = new StringBuilder("[" + first.data);
        Node current = first;
        while (current.next != null) {
            current = current.next;
            result.append(", ").append(current.data);
        }
        return result + "]";
    }

}