public class MySet<X>
{
	MyLinkedList<X> aset=new MyLinkedList<X>();
	void addElement(X element)
	{
		Node<X> a1=new Node<X>(element);
		aset.insertNode(a1);
	}
	Boolean IsMember(X o){
		Node<X> a1=new Node<X>(o);
		return aset.isNodeAMember(a1);
	}
	MySet<X> union(MySet<X> otherSet)
	{
		MySet<X> c=new MySet<X>();
		aset.ptr=aset.head;
		while(aset.ptr.next !=null)
		{
			aset.ptr=aset.ptr.next;
			c.addElement(aset.ptr.data);
		}
		otherSet.aset.ptr=otherSet.aset.head;
		while(otherSet.aset.ptr.next != null)
		{
			otherSet.aset.ptr=otherSet.aset.ptr.next;
			if(IsMember(otherSet.aset.ptr.data)){
				continue;
			}
			else
			{
				c.addElement(otherSet.aset.ptr.data);
			}
		}
		return c;
	}
	MySet<X> intersection(MySet<X> otherSet)
	{
		MySet<X> c=new MySet<X>();
		aset.ptr=aset.head;
		while(aset.ptr.next !=null){
			aset.ptr=aset.ptr.next;
			if(otherSet.IsMember(aset.ptr.data))
			{
				c.addElement(aset.ptr.data);
			}
		}
		return c;
	}
}