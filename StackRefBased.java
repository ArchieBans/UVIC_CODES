/*
Archit Kumar
Stack Implemantation Using Linked List
*/

public class StackRefBased<T> implements Stack<T> {

     int count;
	 StackNode<T> head;
	 StackNode<T> tail;
    public StackRefBased() {
		count = 0;
		head = null;
		tail = null;
    }

    public int size() {
        return count;
    }


    public boolean isEmpty() {
        if(count==0)
		{
			return true;
		}
		else
		{
			return false;
		}
    }


    public void push(T data) {
        StackNode<T> temp = new StackNode<T>(data);
		if(count==0)
		{
			head = temp;
			tail = temp;
		}
		else
		{
			temp.next = head;
		    head = temp;
		}
		count++;
    }


    public T pop() throws StackEmptyException {
        StackNode<T> temp = head;
		if(count==0)
		{
		throw new StackEmptyException();
		
		}
		else 
		{
			head  = head.next;
		count--;
		
		return  temp.data;
			
		}
		
			
    }


    public T peek() throws StackEmptyException {
        StackNode<T> temp = head;
		if(count==0)
		{
		throw new StackEmptyException();
		}
		else return temp.data;
    }


    public void makeEmpty() {
		head = null;
		count = 0;
    }


    public String toString() {
             StringBuilder builder = new StringBuilder();
        StackNode<T> temp = head;
        
         for(temp = head;temp!=null;temp = temp.next){
            builder.append(temp);
			if(temp.next!=null)
			{
			builder.append(" ");
            }
        }
        return builder.toString();
    }

    
}
