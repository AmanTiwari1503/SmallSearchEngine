public class InvertedPageIndex
{
	MyHashTable ipi;
	InvertedPageIndex()
	{
		ipi=new MyHashTable();
	}
	void addPage(PageEntry p)
	{
		MyLinkedList<WordEntry> tmp2=p.piw.we;
		//System.out.println(p.piw.we);
		tmp2.ptr=tmp2.head;
		while(tmp2.ptr.next!=null)
		{
			tmp2.ptr=tmp2.ptr.next;
			ipi.addPositionsForWord(tmp2.ptr.data);
		}
	}
	MySet<PageEntry> getPagesWhichContainWord(String str)
	{
		MySet<PageEntry> ob=new MySet<PageEntry>();
		WordEntry k=ipi.findWordEntry(str);
		k.wpos.ptr=k.wpos.head;
		while(k.wpos.ptr.next!=null)
		{
			k.wpos.ptr=k.wpos.ptr.next;
			Position tmp1=(Position)k.wpos.ptr.data;
			if(!ob.IsMember(tmp1.p))
				ob.addElement(tmp1.p);
		}
		return ob;
	}
	MySet<PageEntry> getPagesWhichContainPhrase(String str[])
	{
		int len=str.length;
		MySet<PageEntry> ob=new MySet<PageEntry>();
		int i,j=0;
		int count=0;
		ob=this.getPagesWhichContainWord(str[0]);
		MySet<PageEntry> finalList=new MySet<PageEntry>();
		ob.aset.ptr=ob.aset.head;
		while(ob.aset.ptr.next!=null)
		{
			ob.aset.ptr=ob.aset.ptr.next;
			PageEntry p=(PageEntry)ob.aset.ptr.data;
			int ko=p.doesPhraseOccur(str);
			if(ko!=0)
			{
				if(!finalList.IsMember(p))
					finalList.addElement(p);
			}
		}
		return finalList;
	}
}
