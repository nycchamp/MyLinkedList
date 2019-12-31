package extraCredit;
import java.util.*;
import java.io.*;
public class MyLinkedList<E> 
extends AbstractSequentialList<E>
implements List<E>, Deque<E>, Cloneable, Serializable {
	private static class Node<E> {
		public Node(){ 
			data = null; 
			next = prev = null;
		}
		
		public Node(E data) { 
			this.data = data; 
			next = prev = null;
		}
		
		public E getData() { return data; }
		
		public Node getNext() { return next; }
		
		public Node getPrevious() { return prev; }
				
		private Node<E> next, prev;
		private E data;
	}
	 private class MyListIterator<E> implements Iterator<E> {
	        private Node<E> pointer;
	        
	        public MyListIterator() {
	        	if(isEmpty())
				pointer = nil;
					
			pointer = nil.next;
	        }
	        
	        @Override
	        public boolean hasNext() {
	            return pointer != nil;
	        }

	        @Override
	        public E next() {
	        	 E old = pointer.data;
	             pointer = pointer.next;
	             return old;
	        } 
	        
	        public void remove() {
	            throw new UnsupportedOperationException();
	        }        
	 }
	 
	public MyLinkedList() {
		nil = new Node();
		nil.prev = nil;
		nil.next = nil; 
		size = 0;	
	}
	
	public MyLinkedList(Collection<? extends E> c) {
		MyLinkedList<E> list = new MyLinkedList<>();

		for(E e: c)
			list.add(e);
		
	}
	
	public boolean add(E elem) {
		Node newNode = new Node(elem);
		newNode.prev = nil.prev;
		newNode.next = nil;
		nil.prev.next = newNode;
		nil.prev = newNode;
		size++;
		return true;
	}
	
	public void add(int index, E elem) {
		if(isOutOfBounds(index))
			throw new IndexOutOfBoundsException();
		
		Node newNode = new Node(elem);
		
		Node old = nil.next;
		for(int i = 0; i < size(); i++) {
			old = old.next;
			if(i == index-1) {
				newNode.next = old;
				newNode.prev = old.prev;
				old.prev.next = newNode;
				old.prev = newNode;
				size++;
			}	
		}
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		if(c == null)
			throw new NullPointerException();
			
		for (E e: c)
			add(e);
		
		return true;
	}
	
	public boolean addAll(int index, Collection<? extends E> c) {
		if(c == null)
			throw new NullPointerException();
		
		if(isOutOfBounds(index))
			throw new IndexOutOfBoundsException();
		
		for(int i = 0; i < size(); i++) 
			if(i == index-1)
				for(int j = index; j < size(); j++)
					for(E e: c)
						add(j, e);
						
		return true;
	}
	
	public void addFirst(E elem) {
		Node newNode = new Node(elem);
		newNode.prev = nil;
		newNode.next = nil.next;
		nil.next.prev = newNode;
		nil.next = newNode;
		size++;
	}
	
	public void addLast(E elem) { add(e); }
	
	public void clear() {
		nil.next = null;
		nil.prev = null;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}
	
	public Iterator<E> descendingIterator(){
		if(isEmpty())
			return null;
		
		Iterator<E> iter = new MyLinkedList<E>.MyListIterator<E>();
		
	}
	
	public E element() { return getFirst(); }
	
	public E get(int index) {
		if(isOutOfBounds(index))
			throw new IndexOutOfBoundsException();
			
		Node result = nil.next;
		for(int i = 0; i < index; i++)
			result = result.next;
		
		return result.getData();
	}
	
	public E getFirst() { 
		 if (isEmpty())
			 return null;
		return nil.next.getData(); 
	}
	
	public E getLast() { 
		if (isEmpty())
			 return null;
		return nil.prev.getData(); 
	}
	
	public int indexOf(Object obj) {
        	Node nd = nil.next;
        
        	for(int i = 0; i < size(); i++) {
        		if(nd.data.equals(obj))
        			return i;
			
        		nd = nd.next;
        	}
        	return -1;
	}
	
	public int lastIndexOf(Object obj) {
		Node nd = nil.prev;

        	for(int i = size()-1; i >= 0; i--) {
        		if(nd.data.equals(obj))
        			return i;
        	
        		nd = nd.prev;
        	}
        	return -1;	
	}
	
	public ListIterator<E> listIterator(int index){
		if(isOutOfBounds(index))
			throw new IndexOutOfBoundsException();
			
		for(int i = index; i < size(); i++){
				
		}
	}
	
	public boolean offer(E e) {
		addLast(e);
		return true;
	}
	
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}
	
	public boolean offerLast(E e) { offer(e); }
	
	public E peek() { return getFirst(); }
	
	public E peekFirst() { return peek(); }
	
	public E peekLast() { return getLast(); }
	
	public E poll() { 
		if(isEmpty())
			return null;
		return removeFirst(); 
	}
	
	public E pollFirst() { return poll(); }
	
	public E pollLast() {
		if(isEmpty())
			return null;
		return removeLast();
	}
	
	public E pop() { return removeFirst(); }
	
	public void push(E e) { addFirst(e); }
	
	public E remove() {
		if (isEmpty()) 
			throw new NoSuchElementException();
				
		Node old = nil.next;
		nil.next.next.prev = nil;
		nil.next = nil.next.next;
		size--;
		return old.getData();
	}
	
	public E remove(int index) {
		if(isOutOfBounds(index))
			throw new IndexOutOfBoundsException();
		
		Node old = nil.next;
		
		for(int i = 0; i < index; i++)
			old = old.next;
		
		old.prev.next = old.next; 
		old.next.prev = old.prev;
		size--;
		
		return old.getData();
	}
	
	public boolean remove(Object obj) {
		if(!contains(obj))
			return false;
			
		remove(indexOf(obj));
		return true;
	}
	
	public E removeFirst() { return remove(); }
	
	public boolean removeFirstOccurrence(Object obj) { return remove(obj);	}
	
	public E removeLast() {
		 if (isEmpty()) 
			 throw new NoSuchElementException();
		 
		 Node old = nil.prev;
		 nil.prev.prev.next = nil;
		 nil.prev = nil.prev.prev;
		 size--;
		 return old.getData();
	}
	
	public boolean removeLastOccurrence(Object obj) {
		if(!contains(obj)) 
			return false;

		remove(lastIndexOf(obj));
		return true;
	}
	
	public E set(int index, E value) {
		if(isOutOfBounds(index))
			throw new IndexOutOfBoundsException();
		
		Node tempNode = nil.next;
		
		for(int i = 0; i < index; i++)
			tempNode = tempNode.next;
		
		tempNode.data = value;
		
		return tempNode.getData();
	}
	
	public int size() { return size; }
	
	public Object[] toArray() {
		Object[] arr = new Object[size()];
		int index = 0;
		for(E elem: this)
			if(index < size()){ 
				arr[index] = elem;
			}
		 return arr;
	}
	
	public <E> E[] toArray(E[] arr) {
		if(arr == null)
			throw new NullPointerException();
		
		//ArrayStoreException? 
		
		int index = 0;
		for(Object e: this) 
			if(index < size()) {
				arr[index] = (E) e;
			}
		return arr;
	}
	
	public boolean isEmpty(){ return size() == 0; }
	
	public boolean isOutOfBounds(int index) {
		return (index < 0 || index >= size());
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
        	
		sb.append("[ ");
        	for(E e: this)
            		sb.append(e + " ");
        	sb.append("]");
        	return sb.toString();
	 }

	@Override
	public Iterator<E> iterator() {
		return new MyLinkedList.MyListIterator();		
	}
	
	private Node nil; 
	private int size;
	
	public static void main(String[] args) {
		MyLinkedList test = new MyLinkedList();
		Collection coll = new ArrayList();
	
		for(int i = 1; i <= 10; i++)
			test.addLast(i); 
		
		System.out.println(test.toString());
		
		for(int i = 11; i <= 20; i++)
			coll.add(i);
				
		try {
			MyLinkedList testClone = (MyLinkedList) test.clone();
			System.out.println(testClone.toString());

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		System.out.println("removed first elem: " + test.removeFirst());		
		System.out.println("removed last elem: " + test.removeLast());
		
		System.out.println(test.getFirst());
		System.out.println(test.getLast());
		
		test.addAll(coll);
		System.out.println(test.toString());
		
		System.out.println("removed last elem: " + test.remove(test.size()-1));
		System.out.println(test.toString());

		test.add(2, "\"added this\"");
		System.out.println(test.toString());		
		
		
		System.out.println("contains 3?: " + test.contains(3));
		System.out.println("contains 1?: " + test.contains(1));
		
		System.out.println("value at index 0 is: " + test.get(0));
		System.out.println("Setting position 0 to 1: " + test.set(0, 1));
		System.out.println("value at index 0 is: " + test.get(0));
		System.out.println("contains 1?: " + test.contains(1));
		
		System.out.println("index of 1 is: " + test.indexOf(1));
		System.out.println("index of 7 is: " + test.indexOf(7));
		
		test.toArray();
		System.out.print("toArray: [");
		for(Object e: test) {
			System.out.print(e + ", ");
		}
		System.out.println("]");
		
		System.out.println("\n\n"+test.toString());
		test.set(16, 3);
		System.out.println(test.toString());
		System.out.println("index of 3 is: " + test.indexOf(3));
		System.out.println("Last index of 3 is: " + test.lastIndexOf(3));
		
		System.out.println("removed first occurrence of 3: " + test.removeFirstOccurrence(3));
		System.out.println(test.toString());
		
		System.out.println("removed last occurrence of 3: " + test.removeLastOccurrence(3));
		System.out.println(test.toString());
		
	}
}
