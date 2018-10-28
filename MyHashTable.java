public class MyHashTable
{
	MyLinkedList<WordEntry>[] BArray;
	@SuppressWarnings("unchecked")
	public MyHashTable()
	{
		BArray=new MyLinkedList[26];
		int i=0;
		for(i=0;i<26;i++)
		{
			BArray[i]=new MyLinkedList<WordEntry>();
		}
	}
	private int getHashIndex(String str)
	{
		String s=str.toLowerCase();
		char ch=s.charAt(0);
		int n=(((int)ch)-97);
		if(n>=0)
			return n;
		else
		{
			int p=n-(-26*((int)Math.ceil(Math.abs(n)/26.0)));
			return p;
		}
	}
	void addPositionsForWord(WordEntry w)
	{
		WordEntry gh=new WordEntry(w.str);
		w.wpos.ptr=w.wpos.head;
		while(w.wpos.ptr.next!=null)
		{
			w.wpos.ptr=w.wpos.ptr.next;
			Node<Position> tmp=new Node<Position>(w.wpos.ptr.data);
			gh.wpos.insertNode(tmp);
		}
		Node<WordEntry> newEntry=new Node<WordEntry>(gh);
		int index=getHashIndex(w.str);
		BArray[index].ptr=BArray[index].head;
		WordEntry p=new WordEntry("");
		int flag=0;
		while(BArray[index].ptr.next!=null)
		{
			BArray[index].ptr=BArray[index].ptr.next;
			p=(WordEntry)BArray[index].ptr.data;
			if(p.str.equals(w.str))
			{
				flag=1;
				break;
			}
		}
		if(flag==1)
		{

			p.addPositions(w.wpos);
		}
		else
		{
			BArray[index].insertNode(newEntry);
		}
	}

	WordEntry findWordEntry(String s)
	{
		int i=getHashIndex(s);
		MyLinkedList<WordEntry> tmp=BArray[i];
		WordEntry tmp1=new WordEntry("");
		int flag=0;
		tmp.ptr=tmp.head;
		while(tmp.ptr.next!=null)
		{
			tmp.ptr=tmp.ptr.next;
			tmp1=(WordEntry)tmp.ptr.data;
			//System.out.println(tmp1.str);
			if(tmp1.str.equals(s))
			{
				flag=1;
				break;
			}
		}
		if(flag==1)
		{
			return tmp1;
		}
		else
			return new WordEntry("");
	}
}
