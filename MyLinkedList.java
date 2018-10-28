class Node<X>
{
	X data;
	Node<X> next;
	Node(){
		data=null;
		next=null;
	}
	Node(X temp){
		data=temp;
		next=null;
	}
}
public class MyLinkedList<X>
{
	Node<X> head;
	Node<X> ptr;
	MyLinkedList(){
		head=new Node<X>();
		ptr=new Node<X>();
	}
	Boolean isEmpty(){
		if(head.next == null)
			return true;
		else{
			return false;
		}
	}
	void insertNode(Node<X> o){
		ptr=head;
		while(ptr.next!=null)
		{
			ptr=ptr.next;
		}
		ptr.next=o;
		o.next=null;
	}
	void insertNodeFront(Node<X> o){
		o.next=head.next;
		head.next=o;
	}
	int countNodes(){
		ptr=head;int k=0;
		while(ptr.next!=null)
		{
			ptr=ptr.next;
			k=k+1;
		}
		return k;
	}
	void deleteNode(Node<X> o)throws Exception{
		ptr=head;
		Node<X> prev=new Node<X>();
		prev=head;
		int flag=0;
		if(isEmpty())
		{
			throw new Exception("Set is empty");
		}
		else{
			while(ptr.next != null){
				ptr=ptr.next;
				if(ptr.data==o.data)
				{
					prev.next=ptr.next;
					flag=1;
				}
				else
				{
					prev=ptr;
				}
		}
		}
		if(flag==0){
			throw new Exception("No such node found");
		}
	}
	Boolean isNodeAMember(Node<X> o){
		int flag=0;
		if(isEmpty())
			return false;
		ptr=head;
		while(ptr.next !=null)
		{
			ptr=ptr.next;
			if(ptr.data==o.data)
			{
				flag=1;
				break;
			}
		}
		if(flag==0)
			return false;
		else
			return true;
	}
	Boolean isNodeAStringMember(Node<X> o){
		int flag=0;
		if(isEmpty())
			return false;
		ptr=head;
		while(ptr.next !=null)
		{
			ptr=ptr.next;
			if(ptr.data.equals(o.data))
			{
				flag=1;
				break;
			}
		}
		if(flag==0)
			return false;
		else
			return true;
	}
	void printAll(){
		ptr=head;
		while(ptr.next!=null){
			ptr=ptr.next;
			System.out.println(ptr.data);
		}
	}
	MyLinkedList<X> unionlist(MyLinkedList<X> otherSet)
	{
		MyLinkedList<X> c=new MyLinkedList<X>();
		ptr=head;
		while(ptr.next !=null)
		{
			ptr=ptr.next;
			Node<X> a=new Node<X>(ptr.data);
			c.insertNode(a);
		}
		otherSet.ptr=otherSet.head;
		while(otherSet.ptr.next != null)
		{
			otherSet.ptr=otherSet.ptr.next;
			Node<X> a=new Node<X>(otherSet.ptr.data);
			if(isNodeAMember(a)){
				continue;
			}
			else
			{
				c.insertNode(a);
			}
		}
		return c;
	}
}