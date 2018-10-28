public class PageIndex
{
	MyLinkedList<WordEntry> we;
	PageIndex()
	{
		we=new MyLinkedList<WordEntry>();
	}
	void addPositionForWord(String str, Position p)
	{
		WordEntry wordUpdate=this.containsWordEntry(str);
		//System.out.println(wordUpdate.str);
		if(wordUpdate.str.equals(""))
		{
			WordEntry newWord=new WordEntry(str);
			//System.out.println(newWord.str);
			newWord.addPosition(p);
			Node<WordEntry> k=new Node<WordEntry>(newWord);
			we.insertNode(k);
		}
		else
		{
			//System.out.print("1"+" ");
			wordUpdate.addPosition(p);
		}
	}
	WordEntry containsWordEntry(String g)
	{
		WordEntry p=new WordEntry("");
		int flag=0;
		we.ptr=we.head;
		while(we.ptr.next!=null)
		{
			we.ptr=we.ptr.next;
			p=(WordEntry)we.ptr.data;
			if(p.str.equals(g))
			{
				flag=1;
				break;
			}
		}
		if(flag==1)
			return p;
		else
			return new WordEntry("");
	}
	MyLinkedList<WordEntry> getWordEntries()
	{
		return we;
	}
}