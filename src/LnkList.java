import java.util.*;
import java.io.*;
public class LnkList implements Iterable, Serializable
{
    
    private LNode head;
    private LNode tail;

    public LnkList()
    {
        this.head = null;
	    this.tail = null;
    }

    public boolean isEmpty()
    {
	    boolean empty = (head == null);
	    return empty;
    }
    

    public void insertFirst(Object inValue)
    {
	    LNode newNd = new LNode(inValue);
	    if(isEmpty())
	    {
            head = newNd;
	        tail = newNd;
    	}
	    else
	    {
            newNd.setNext(head);
            head.setPrev(newNd);
            head = newNd;
        }
    }

    public void insertLast(Object newValue)
    {

	    LNode newNd = new LNode(newValue);
	    if(isEmpty())
	    {
	        head = newNd;
            tail = newNd;
	    }
	    else
	    {
            newNd.setPrev(tail);
            tail.setNext(newNd);
            tail = newNd;
        }
    }

    public Object peekFirst()
    {
        Object nodeValue;
	    if(isEmpty())
	    {
	        System.out.println("Peek failed, no element");
	        throw new NoSuchElementException();
	    }
	    else
	    {
	        nodeValue = head.getValue();
	    }
	    return nodeValue;	
    }

    public Object peekLast()
    {
        Object nodeValue;
	    if(isEmpty())
	    {
	        System.out.println("Could not peek last element.");
	        throw new NoSuchElementException();
	    }
	    else
	    {
	        nodeValue = tail.getValue();
	    }
	    return nodeValue;
    }

    public Object removeFirst()
    {
    	Object nodeValue;
	    LNode node = head;
        if(isEmpty())
	    {
	        System.out.println("No first element to remove");
    	    throw new NoSuchElementException();
	    }
        else if (head == tail)
        {
            nodeValue = head.getValue();
            head = tail = null;
        }
	    else
	    {        	    
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrev(null);
        }
    	return nodeValue;
    }

    public Object removeLast()
    {
        Object nodeValue;
	    if(isEmpty() || tail == null)
	    {
	        System.out.println("No last element to remove");
	        throw new NoSuchElementException();
    	}
        else if(head == tail)
        {
            nodeValue = tail.getValue();
            head = tail = null;
        }
	    else
	    {
            nodeValue = tail.getValue();
            tail = tail.getPrev();
            tail.setNext(null);
	    }
	    return nodeValue;
    }

    private class LNode
    {

    	private Object value;
	    private LNode next;
        private LNode prev;

    	public LNode(Object inValue)
    	{
            this.value = inValue;
            next = null;
            prev = null;
        }

    	public Object getValue()
        {
            return value;
    	}

    	public void setValue(Object inValue)
    	{
            value = inValue;
        }

        public LNode getNext()
    	{
            return next;
    	}

    	public void setNext(LNode newNext)
    	{
            next = newNext;
        }
       
        public LNode getPrev()
    	{
            return prev;
    	}

    	public void setPrev(LNode newPrev)
    	{
            prev = newPrev;
        }
    }

    public Iterator iterator()
    {
	    return new LinkedListIterator(this);
    }

    private class LinkedListIterator implements Iterator 
    {
	    private LNode iterNext;

	    public LinkedListIterator(LnkList theList)
	    {
	        iterNext = theList.head;
	    }

    	public boolean hasNext()
	    {
	        return (iterNext !=null);
	    }
	
	    public Object next()
	    {
	        Object value;
	        if(iterNext == null)
	        {
	 	        value = null;
	        }
	        else
	        {
		        value = iterNext.getValue();
		        iterNext = iterNext.getNext();
	        }
	        return value;	
 	    }

	    public void remove()
	    {
	        throw new UnsupportedOperationException("Not supported.");
	    }
    }
}

