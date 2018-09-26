package hw5;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>>{
	private class Node<E>{
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException();
			} else {
				this.data = data;
				this.priority = priority;
				this.left = null;
				this.right = null;
			}
		}
		
		public Node<E> rotateRight() {
			Node<E> head = this.left;
			Node<E> left = head.right;
			head.right = this;
			this.left = left;
			return head;
		}

		public Node<E> rotateLeft() {
			Node<E> head = this.right;
			Node<E> right = head.left;
			head.left = this;
			this.right = right;
			return head;
		}
	}
	
	private Random priorityGenerator;
	private Node<E> root;
	
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	}
	
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	}
	
	public void reheap(Node<E> curr, Stack<Node<E>> path) {
		while (!path.isEmpty()) {
			Node<E> parent = path.pop();
			if (parent.priority < curr.priority){
				if (parent.data.compareTo(curr.data) > 0) {
					curr = parent.rotateRight();
				} else {
					curr = parent.rotateLeft();
				}
				if (!path.isEmpty()) {
					if (path.peek().left == parent) {
						path.peek().left = curr;
					} else {
						path.peek().right = curr;
					}
				} else { 
					this.root = curr;
				}
			} else {
				break;
			}
		}
	}
	
	boolean add(E key, int priority) {
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		} else {
			Node<E> temp = new Node<E>(key, priority);
			Stack<Node<E>> stackTemp = new Stack<Node<E>>();
			Node<E> current = root;
			while (current != null) {
				int comparison = current.data.compareTo(key);
				if (comparison == 0) {
					return false;
				} else {
					if (comparison < 0) {
						stackTemp.push(current);
						if (current.right == null) {
							current.right = temp;
							reheap(temp, stackTemp);
							return true;
						} else {
							current = current.right;
						}
					} else {
						stackTemp.push(current);
						if (current.left == null) {
							current.left = temp;
							reheap(temp, stackTemp);
							return true;
						} else {
							current = current.left;
						}
					}
				}
			}
			return false;
		}
	}
	
	boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}
	
	private Node<E> delete(E key, Node<E> n){
		if (n == null) {
			return n;
		} else {
			if (n.data.compareTo(key) < 0) {
				n.right = delete(key, n.right);
			} else {
				if (n.data.compareTo(key) > 0) {
					n.left = delete(key, n.left);
				} else {
					if (n.right == null) {
						n = n.left;
					} else if (n.left == null) {
						n = n.right;
					} else {
						if (n.right.priority < n.left.priority) {
							n = n.rotateRight();
							n.right = delete(key, n.right);
						} else {
							n = n.rotateLeft();
							n.left = delete(key, n.left);
						}
					}
				}
			}
		}
		return n;
	}

	public boolean delete(E key) {
		if (root == null || find(key) == false) {
			return false;
		} else {
			root = delete(key, root);
			return true;
		}
	}
	private boolean find(Node<E> n, E key) {
		if (n == null) {	
			return false;
		} else {
			int comparison = n.data.compareTo(key);
			if (comparison == 0) {
				return true;
			} else {
				return find(n.right, key) || find(n.left, key);
			}
		}
	}

	public boolean find(E key) {
		return find(root, key);
	}

	private String toString(Node<E> n, int depth) {
		StringBuilder r = new StringBuilder();
		// add indentation
		for (int i=0;i<depth;i++) {
			r.append("--");
		}
		if (n==null) {
			r.append("null");
		} else {
			r.append("(key = " + n.data +", priority = " + n.priority + ")" );
			r.append("\n");
			r.append(toString(n.left, depth+1));
			r.append("\n");
			r.append(toString(n.right, depth+1));
		}
		return r.toString();
		
	}
	
	public String toString() {
		return toString(root,0);
	}
	
	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19); 
		testTree.add(2,31);
		testTree.add(6,70); 
		testTree.add(1,84);
		testTree.add(3,12); 
		testTree.add(5,83);
		testTree.add(7,26);
		System.out.println(testTree.delete(55));
		System.out.println(testTree.toString());
	}
}
