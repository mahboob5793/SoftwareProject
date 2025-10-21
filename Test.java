public class Main {
    public static void main(String[] args) {
        linkedList list = new linkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert("Hallo");
        list.insertAt(2, 10);
        list.show();

    }
}
public class Node<T> {
    T data;
    Node<T> next;




}
public class linkedList<T> {
    Node head;
    Node tail;
    public void insert(T data) {
        Node node = new Node();
        node.data = data;
        node.next = null;
        if (head == null) {
            head = node;
        }
        else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;

        }
    }
    public void insertAt(int index, T data) {

        int count = 0;
        Node temp = head;
            while ( count != index) {
                count = count + 1;
                temp = temp.next;

        }
        temp.data=data;
    }
    public void show() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }

    }


}
