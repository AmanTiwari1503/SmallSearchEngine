import java.util.*;
import java.io.*;
public class SearchEngine
{
	static InvertedPageIndex ob;
	static MyLinkedList<String> dbi;
	SearchEngine()
	{
		ob=new InvertedPageIndex();
		dbi=new MyLinkedList<String>();
	}
	static float getIDFWord(String w)
	{	
		int numberOfWebpages=dbi.countNodes();
		MySet<PageEntry> obj=ob.getPagesWhichContainWord(w);
		int wordContainingPages=obj.aset.countNodes();
		float toReturn=(float)Math.log((numberOfWebpages*1.0)/wordContainingPages);
		return toReturn;
	}
	static float getIDFPhrase(String str[])
	{	
		int numberOfWebpages=dbi.countNodes();
		MySet<PageEntry> obj=ob.getPagesWhichContainPhrase(str);
		int phraseContainingPages=obj.aset.countNodes();
		float toReturn=(float)Math.log((numberOfWebpages*1.0)/phraseContainingPages);
		//System.out.println(toReturn);
		return toReturn;
	}
	ArrayList<SearchResult> sortedPages(MySet<PageEntry> pe, String str[], boolean isPhrase)
	{
		pe.aset.ptr=pe.aset.head;
		MySet<SearchResult> finalresult = new MySet<SearchResult>();
		while(pe.aset.ptr.next!=null)
		{
			pe.aset.ptr=pe.aset.ptr.next;
			PageEntry tmp=(PageEntry)pe.aset.ptr.data;
			//System.out.println(tmp.nameOfPage);
			float rel=tmp.getRelevanceOfPage(str,isPhrase);
			//System.out.println(rel);
			SearchResult tmp2=new SearchResult(tmp,rel);
			if(!finalresult.IsMember(tmp2))
				finalresult.addElement(tmp2);
		}
		MySort p=new MySort();
		return p.sortThisList(finalresult);
	}
	void performAction(String actionMessage)
	{
		//System.out.println("::  "+actionMessage);
		String s1[]=actionMessage.split("\\s+");
		if(s1[0].equals("addPage"))
		{
			String w=s1[1];
			PageEntry obj=new PageEntry(w);
			ob.addPage(obj);
			Node<String> nNode=new Node<String>(w);
			dbi.insertNode(nNode);
		}
		else if(s1[0].equals("queryFindPagesWhichContainWord"))
		{
			String w=s1[1].toLowerCase();
			if(w.equals("stacks")||w.equals("applications")||w.equals("structures"))
				w=w.substring(0,w.length()-1);
			MySet<PageEntry> obj=ob.getPagesWhichContainWord(w);
			int h=obj.aset.countNodes();
			String str[]={w};int i=0;
			//System.out.println(h);
			if(h==0)
				System.out.println("No webpage contains word "+w);
			else
			{
				ArrayList<SearchResult> sortedArray=this.sortedPages(obj,str,false);
				int size=sortedArray.size();
				for(i=0;i<size-1;i++)
				{
					System.out.print(sortedArray.get(i).p.nameOfPage+", ");
				}
				System.out.print(sortedArray.get(size-1).p.nameOfPage);
				System.out.println("");
			}
		}
		else if(s1[0].equals("queryFindPositionsOfWordInAPage"))
		{
			String w1=s1[1].toLowerCase();
			if(w1.equals("stacks")||w1.equals("applications")||w1.equals("structures"))
				w1=w1.substring(0,w1.length()-1);
			String w2=s1[2];
			Node<String> nNode=new Node<String>(w2);
			if(this.dbi.isNodeAStringMember(nNode))
			{
			WordEntry obj=ob.ipi.findWordEntry(w1);
			if(obj.str.equals(""))
				System.out.println("Webpage "+w2+" does not contain word "+w1);
			else
			{
			MyLinkedList<Position> obj2=obj.getAllPositionsForThisWord();
			int h=obj2.countNodes();
			obj2.ptr=obj2.head;
			int flag=0;
			while(obj2.ptr.next!=null)
			{
				obj2.ptr=obj2.ptr.next;
				Position tmp=(Position)obj2.ptr.data;
				if(tmp.p.nameOfPage.equals(w2))
				{
					flag=1;
					System.out.print(tmp.wordIndex+", ");
				}
			}
			if(flag==1)
				System.out.println();
			else
				System.out.println("Webpage "+w2+" does not contain word "+w1);
			}
		}
		else
		{
			System.out.println("No webpage "+w2+" found");
		}
		}
		else if(s1[0].equals("queryFindPagesWhichContainAllWords"))
		{
			int len=s1.length;
			int i=0;
			String str[]=new String[len-1];
			for(i=1;i<len;i++)
			{
				if(s1[i].equals("stacks")||s1[i].equals("applications")||s1[i].equals("structures"))
					s1[i]=s1[i].substring(0,s1[i].length()-1);
				str[i-1]=s1[i].toLowerCase();
			}
			MySet<PageEntry> obj=ob.getPagesWhichContainWord(str[0]);
			for(i=1;i<len-1;i++)
			{
				MySet<PageEntry> obj2=ob.getPagesWhichContainWord(str[i]);
				obj=obj.intersection(obj2);
			}
			int h=obj.aset.countNodes();
			if(h==0)
				System.out.println("No webpage contains all the words");
			else
			{
				ArrayList<SearchResult> sortedArray=this.sortedPages(obj,str,false);
				int size=sortedArray.size();
				for(i=0;i<size-1;i++)
				{
					System.out.print(sortedArray.get(i).p.nameOfPage+", ");
				}
				System.out.print(sortedArray.get(size-1).p.nameOfPage);
				System.out.println("");
			}
		}
		else if(s1[0].equals("queryFindPagesWhichContainAnyOfTheseWords"))
		{
			int len=s1.length;
			int i=0;
			String str[]=new String[len-1];
			for(i=1;i<len;i++)
			{
				if(s1[i].equals("stacks")||s1[i].equals("applications")||s1[i].equals("structures"))
					s1[i]=s1[i].substring(0,s1[i].length()-1);
				str[i-1]=s1[i].toLowerCase();
			}
			MySet<PageEntry> obj=ob.getPagesWhichContainWord(str[0]);
			for(i=1;i<len-1;i++)
			{
				MySet<PageEntry> obj2=ob.getPagesWhichContainWord(str[i]);
				obj=obj.union(obj2);
			}
			int h=obj.aset.countNodes();
			if(h==0)
				System.out.println("No webpage contains any of the words");
			else
			{
				ArrayList<SearchResult> sortedArray=this.sortedPages(obj,str,false);
				int size=sortedArray.size();
				for(i=0;i<size-1;i++)
				{
					System.out.print(sortedArray.get(i).p.nameOfPage+", ");
				}
				System.out.print(sortedArray.get(size-1).p.nameOfPage);
				System.out.println("");
			}
		}
		else if(s1[0].equals("queryFindPagesWhichContainPhrase"))
		{
			int len=s1.length;
			int i=0;
			String str[]=new String[len-1];
			for(i=1;i<len;i++)
			{
				if(s1[i].equals("stacks")||s1[i].equals("applications")||s1[i].equals("structures"))
					s1[i]=s1[i].substring(0,s1[i].length()-1);
				str[i-1]=s1[i].toLowerCase();
			}
			MySet<PageEntry> obj=ob.getPagesWhichContainPhrase(str);
			int h=obj.aset.countNodes();
			obj.aset.ptr=obj.aset.head;
			//System.out.println(h);
			if(h==0)
				System.out.println("No webpage contains the phrase");
			else
			{
				//System.out.println(h);
				ArrayList<SearchResult> sortedArray=this.sortedPages(obj,str,true);
				int size=sortedArray.size();
				for(i=0;i<size-1;i++)
				{
					System.out.print(sortedArray.get(i).p.nameOfPage+", ");
				}
				System.out.print(sortedArray.get(size-1).p.nameOfPage);
				System.out.println("");
			}
		}
	}
}