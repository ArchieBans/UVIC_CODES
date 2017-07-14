/*
 * Name:			Michael Kitzan
 */

public class IntegerLinkedList implements IntegerList
{
	private int count;
	private IntegerNode head;
	private IntegerNode tail;
	
	public IntegerLinkedList()
	{
		count = 0;
		head = null;
		tail = null;
	}

	/*
	 * PURPOSE:
	 *   Add the element x to the front of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addFront(9) returns, then l is {9,1,2,3}.
	 * If l is {} and l.addFront(3) returns, then l is {3}.
	 */
	public void addFront (int x)
	{
		IntegerNode temp = new IntegerNode(x);
		
		if(head == null) {
			head = temp;
			tail = temp;
		} else {
			temp.next = head;
			head.prev = temp;
			head = temp;
		}
		
		count++;
	}


	/*
	 * PURPOSE:
	 *   Add the element x to the back of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addBack(9) returns, then l is {1,2,3,9}.
	 * If l is {} and l.addBack(9) returns, then l is {9}.
	 */
	public void addBack (int x)
	{
		IntegerNode temp = new IntegerNode(x);
		
		if(tail == null) {
			head = temp;
			tail = temp;
		} else {
			tail.next = temp;
			temp.prev = tail;
			tail = temp;
		}
		
		count++;
	}

	/*
	 * PURPOSE:
	 *   Add the element x at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for addAt are
	 *   0, 1, 2, 3.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos <= l.size()
	 *
	 * Examples:
	 *
	 * If l is {} and l.addAt(9,0) returns, then l is {9}.
	 * If l is {1} and l.addAt(9,0) returns, then l is {9,1}.
	 * If l is {1,2} and l.addAt(9,1) returns, then l is {1,9,2}
	 * If l is {1,2} and l.addAt(9,2) returns, then l is {1,2,9}
	 */
	public void addAt (int x, int pos)
	{
		if(pos < 0 || pos > size()) {
			return;
		} else if(pos == 0) {
			addFront(x);
		} else if(pos == size()) {
			addBack(x);
		} else {
			IntegerNode curr = head;
			IntegerNode prev = null;
			IntegerNode insert = new IntegerNode(x);
			for(int i = 0; i < pos; i++) {
				prev = curr;
				curr = curr.next;
			}
			insert.prev = prev;
			prev.next = insert;
			curr.prev = insert;
			insert.next = curr;
			count++;
		}
	}

	/*
	 * PURPOSE:
	 *	Return the number of elements in the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {7,13,22} l.size() returns 3
	 *	If l is {} l.size() returns 0
	 */
	public int size()
	{
		return count;
	}

	/*
	 * PURPOSE:
	 *   Return the element at position pos in the list.
	 *
	 * PRECONDITIONS:
	 *	pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *	If l is {67,12,13} then l.get(0) returns 67
	 *	If l is	{67,12,13} then l.get(2) returns 13
	 *	If l is {92} then the result of l.get(2) is undefined.
	 *
	 */
	public int  get (int pos)
	{
		if(pos < 0 || pos >= size()) {
			return -1;
		} else {
			IntegerNode curr = head;
			for(int i = 0; i < pos; i++) {
				curr = curr.next;
			}
			
			return curr.value;
		}
	}

	/*
	 * PURPOSE:
	 *   Remove all elements from the list.  After calling this
	 *   method on a list l, l.size() will return 0
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13} then after l.clear(), l is {}
	 *	If l is {} then after l.clear(), l is {}
	 *
	 */
	public void clear()
	{
		head = null;
		tail = null;
		count = 0;
	}

	/*
	 * PURPOSE:
	 *   Remove all instances of value from the list.
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13,12} then after l.remove(12), l is {67,13}
	 *	If l is {1,2,3} then after l.remove(2), l is {1,3}
	 *	If l is {1,2,3} then after l.remove(99), l is {1,2,3}
	 */
	public void remove (int value)
	{
		IntegerNode curr = head;
		for(int i = 0; i < size(); i++) {
			if(curr.value == value) {
				removeAt(i);
				i--;
			}
			curr = curr.next;
		}
	}

	/*
	 * PURPOSE:
	 *   Remove the element at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for removeAt are
	 *   0, 1, 2.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *
	 * If l is {1} and l.removeAt(0) returns, then l is {}.
	 * If l is {1,2,3} and l.removeAt(1) returns, then l is {1,3}
	 * If l is {1,2,3} and l.removeAt(2) returns, then l is {1,2}
	 */
	public void removeAt (int pos)
	{
		if(pos < 0 || pos >= size()) {
			return;
		} else if(pos == 0) {
			head = head.next;
			if(head != null) {
				head.prev = null;
			}
		} else if(pos == size()-1) {
			tail = tail.prev;
			tail.next = null;
		} else {
			IntegerNode curr = head;
			IntegerNode prev = null;
			for(int i = 0; i < pos; i++) {
				prev = curr;
				curr = curr.next;
			}
			prev.next = curr.next;
			curr = curr.next;
			curr.prev = prev;
		}
		count--;
	}

	/*
	 * PURPOSE:
	 *	Return a string representation of the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {1,2,3,4} then l.toString() returns "{1,2,3,4}"
	 *	If l is {} then l.toString() returns "{}"
	 *
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(head != null) {
			IntegerNode temp = head;
			sb.append(head.value);
			temp = temp.next;
			for(int i = 1; i < size(); i++) {
				sb.append("," + temp.value);
				temp = temp.next;
			}	
		}
		sb.append("}");
		return sb.toString();
	}
}
