package hw3;

import java.util.ArrayList;

/**
 * Class that builds an Indexed Double Linked List.
 * @author Mathew Seedhom
 * I pledge my honor that I have abided by the Stevens Honor System.
 * @param <E> 
 */
public class IDLList<E> {
	/**
	 * A private class that builds a single Node object for an Indexed Double Linked List.
	 * @author Mathew Seedhom
	 *
	 * @param <E>
	 */
	private class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		/**
		 * Constructor for the Node that sets the Data.
		 * @param elem
		 */
		Node(E elem) {
			this.data = elem;
			this.next = null;
			this.prev = null;
			
		}
		
		/**
		 * Constructor for the Node that sets the Data, Previous Node, and Next Node.
		 * @param elem
		 * @param prev
		 * @param next
		 */
		Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	ArrayList<Node<E>> indices;
	
	/**
	 * Constructor for an empty Indexed Linked List.
	 */
	 public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = null;
	}
	 
	 /**
	  * Adds a Node at a specified index of the IDLL.
	  * @param index
	  * @param elem
	  * @return true
	  */
	 public boolean add(Integer index, E elem) {
		 if (index < 0 || index > size - 1 || !(index instanceof Integer)) {
			 throw new IllegalArgumentException();
		 } else {
			 if (head == null || index == 0) {
				 head = new Node<E>(elem, null, head);
				 if (tail == null) {
					 tail = head;
				 }
			 } else if (index == size - 1) {
				 Node<E> a = new Node<E>(elem, tail.prev, tail);
				 tail.prev.next = a;
				 tail.prev = a;
			 } else {
				 Node<E> front = head;
				 for (int i = 0; i != index; i++) {
					 front = front.next;
				 }
				 Node<E> a = new Node<E>(elem, front.prev, front);
				 front.prev.next = a;
			 }
			 size++;
			 return true;
		}
	}
	 
	 /**
	  * Adds a Node at the beginning of the IDLL.
	  * @param elem
	  * @return true
	  */
	 public boolean add(E elem) {
		 if (head == null) {
			 head = new Node<E>(elem);
			 tail = head;
		 } else {
			 Node<E> a = head;
			 head = new Node<E>(elem, null, a);
			 a.prev = head;
		 }
		 size++;
		 return true;
	 }
	 
	 /**
	  * Adds a Node to the end of the IDLL.
	  * @param elem
	  * @return true
	  */
	 public boolean append(E elem) {
		 Node<E> a = new Node<E>(elem, tail, null);
		 size++;
		 if (head != null) {
			 tail.next = a;
		 } else {
			 head = a;
		 }
		 tail = a;
		 return true;
	 }
	 
	 /**
	  * Gets the data of a Node at a specified index in the IDLL.
	  * @param index
	  * @return data of the Node
	  */
	 public E get(Integer index) {
		 if (index < 0 || index > size - 1 || head == null || !(index instanceof Integer)) {
			 throw new IllegalArgumentException();
		 } else {
			 if (index == 0) {
				 return head.data;
			 } else {
			 Node<E> front = head;
			 for (int i = 0; i < index; i++) {
				 front = front.next;
			 }
			 return front.data;
			 }
		 }
	 }
	 
	 /**
	  * Gets the data of the first Node in the IDLL.
	  * @return data of the first Node
	  */
	public E getHead() {
		if (size == 0) {
			throw new IllegalArgumentException();
		}
		 return head.data;
	}
	
	/**
	 * Gets the data of the last Node in the IDLL.
	 * @return data of the last Node.
	 */
	public E getLast() {
		if (size == 0) {
			throw new IllegalArgumentException();
		}
		return tail.data;
	}
	
	/**
	 * Gets the size of the IDLL.
	 * @return size of IDLL
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes the first Node in the IDLL.
	 * @return data of the first Node
	 */
	public E remove() {
		if (head == null) {
			throw new IllegalArgumentException();
		} else {
			if (size == 1) {
				head = null;
				tail = head;
				return null;
			} else {
				E a = head.data;
				head = head.next;
				head.prev = null;
				size--;
				return a;
			}
		}
	}
	
	/**
	 * Removes the last Node in the IDLL.
	 * @return data of the last Node
	 */
	public E removeLast() {
		if (head == null) {
			throw new IllegalArgumentException();
		} else {
			if (size == 1) {
				head = null;
				tail = head;
				return null;
			} else {
				E a = tail.data;
				tail = tail.prev;
				tail.next = null;
				size--;
				return a;
			}
		}
	}
	
	/**
	 * Removes the Node at a specified index in the IDLL.
	 * @param index
	 * @return data of the removed Node
	 */
	public E removeAt(Integer index) {
		if (head == null || !(index instanceof Integer)) {
			throw new IllegalArgumentException();
		} else {
			if (index == 0) {
				return this.remove();
			} else if (index == size - 1) {
				return this.removeLast();
			} else {
				Node<E> front = head;
				for (int i = index; i != -1; i--) {
					front = front.next;
				}
				E a = front.data;
				front.prev.next = front.next;
				size--;
				return a;
			}
		}
	}
	
	/**
	 * Removes a Node with specified data in the IDLL.
	 * @param elem
	 * @return true if Node with specified data is found, false otherwise
	 */
	public boolean remove(E elem){
		if (size == 0) {
			return false;
		} else {
			Node<E> check = head;
			for (int i = 0; i < size; i++) {
				if (check.data == elem) {
					this.removeAt(i);
					return true;
				} else {
					check = check.next;
				}
			}
			return false;
		}
	}
	
	/**
	 * Returns a string representation of the IDLL.
	 * @return string representation of the IDLL
	 */
	public String toString() {
		 String r = "( ";
		 Node<E> current=head;
		 while (current != null) {
			 if (current.next != null){
				 r = r + current.data + ", ";
			 } else {
				 r = r + current.data + " ";
			 }
			 current = current.next;
		 }
		 return r + ")";
	}
}