public class WordEntry
{
	String str;
	MyLinkedList<Position> wpos;
	AVLTree<Position> wtree;
	WordEntry(String word)
	{
		str=word;
		wpos=new MyLinkedList<Position>();
		wtree=new AVLTree<Position>();
	}
	void addPosition(Position position)
	{
		Node<Position> a=new Node<Position>(position);
		wpos.insertNode(a);
		//wtree.root=wtree.insertNodeInTree(wtree.root,position);
	}
	void addPositions(MyLinkedList<Position> positions)
	{
		wpos=wpos.unionlist(positions);
	}
	MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return wpos;
	}
	AVLTree<Position> prepareAVLTree()
	{
		wpos.ptr=wpos.head;
		while(wpos.ptr.next!=null)
		{
			wpos.ptr=wpos.ptr.next;
			wtree.root=wtree.insertNodeInTree(wtree.root,wpos.ptr.data);
		}
		return wtree;
	}
	float getTermFrequency(String word)
	{
		wpos.ptr=wpos.head;
		int count=0;
		PageEntry tmp1=new PageEntry("stack_datastructure_wiki");
		while(wpos.ptr.next!=null)
		{
			wpos.ptr=wpos.ptr.next;
			Position tmp=(Position)wpos.ptr.data;
			if(tmp.p.nameOfPage.equals(word))
			{
				tmp1=tmp.p;
				count=count+1;
			} 
		}
		int t=tmp1.total;
		//System.out.println(t);
		float d=(float)(count/(t*1.0));
		return d;
	}
}