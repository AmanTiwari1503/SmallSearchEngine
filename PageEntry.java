import java.io.*;
import java.util.*;
public class PageEntry
{
	PageIndex piw;
	String nameOfPage;
	int total;
	PageEntry()
	{
		piw=new PageIndex();
		nameOfPage="";
		total=0;
	}
	PageEntry(String pageName)
	{
		piw=new PageIndex();
		nameOfPage=pageName;
		String fname="./webpages/"+pageName;
		try
		{
			FileInputStream fstream=new FileInputStream(fname);
			Scanner k=new Scanner(fstream);
			int pos=0;
			int pos1=0;
			while(k.hasNextLine())
			{
				String s=k.nextLine();
				s=s+" ";
				//System.out.println(s);
				String word="";
				int l=s.length();
				int i=0;
				char temp='\u0000';
				for(i=0;i<l;i++)
				{
					temp=s.charAt(i);
					if(isPunctuationMark(s.charAt(i)))
					{
						temp=' ';
					}
					if(temp==' ' || temp=='\t')
					{
						if(!word.equals(""))
						{
							pos=pos+1;total=total+1;
						if(isStoppingWord(word))
							word="";
						else
						{
							pos1=pos1+1;
							if(word.equals("stacks")||word.equals("structures")||word.equals("applications"))
								word=word.substring(0,word.length()-1);
							Position posOfWord=new Position(this,pos);
							posOfWord.excludeStop=pos1;
							this.piw.addPositionForWord(word,posOfWord);
							//System.out.println(piw);
							word="";
							/*System.out.println(word);
							word="";*/
						}
					}
					}
					else
					{	
						word=word+temp;
						word=word.toLowerCase();
					}
				}
			}
			//System.out.println(total);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}
	PageIndex getPageIndex()
	{
		return piw;
	}
	/*public static void main(String args[])throws IOException
	{
		PageEntry u=new PageEntry("stackoverflow");
	}*/

	boolean isPunctuationMark(char ch)
	{
		if(ch=='{' || ch=='}' || ch=='[' || ch==']' || ch=='<' || ch=='>' || ch=='=' || ch=='(' || ch==')' || ch=='.' || ch==',' || 
			ch==';' || ch=='\'' || ch=='"' || ch=='?' || ch=='#' || ch=='!' || ch=='-' || ch==':')
			return true;
		else
			return false;
	}
	boolean isStoppingWord(String f)
	{
		if(f.equals("a") || f.equals("an") || f.equals("the") || f.equals("they") || f.equals("these") || f.equals("this") || f.equals("for") || 
			f.equals("is") || f.equals("are") || f.equals("was") || f.equals("of") || f.equals("or") || f.equals("and") || f.equals("does") || f.equals("will") || 
			f.equals("whose"))
			return true;
		else
			return false;
	}
	int doesPhraseOccur(String phr[])
	{
		int len=phr.length;
		AVLTree<Position>[] avlArray=new AVLTree[len];
		int i=0;int flag=0;
		for(i=0;i<len;i++)
		{
			flag=0;
		piw.we.ptr=piw.we.head;
		while(piw.we.ptr.next!=null)
		{
			piw.we.ptr=piw.we.ptr.next;
			WordEntry tmp=(WordEntry)piw.we.ptr.data;
			if(tmp.str.equals(phr[i]))
			{
				avlArray[i]=tmp.prepareAVLTree();
				flag=1;
			}
		}
		//System.out.println();
		if(flag==0)
			return 0;
		}
		//System.out.println();
		/*for(i=0;i<len;i++)
		{
			avlArray[i].printTree(avlArray[i].root);
			System.out.println();
		}*/
		WordEntry tmp=new WordEntry("");
		piw.we.ptr=piw.we.head;
		while(piw.we.ptr.next!=null)
		{
			piw.we.ptr=piw.we.ptr.next;
			WordEntry t=(WordEntry)piw.we.ptr.data;
			if(t.str.equals(phr[0]))
			{
				tmp=t;
				break;
			}
		}
		int counter=0;
		MySet<Position> relList=new MySet<Position>();
		tmp.wpos.countNodes();
		tmp.wpos.ptr=tmp.wpos.head;
		while(tmp.wpos.ptr.next!=null)
		{
			counter=0;
			tmp.wpos.ptr=tmp.wpos.ptr.next;
			Position posFirstWord=(Position)tmp.wpos.ptr.data;
			//System.out.println();
			//System.out.println(counter+" "+posFirstWord.p.nameOfPage+" "+posFirstWord.excludeStop);
			for(i=1;i<len;i++)
			{
				Position temporary=new Position(this,-10);
				temporary.excludeStop=posFirstWord.excludeStop+i;
				if(avlArray[i].findPosition(temporary)!=null)
				{
					counter++;
				}
			}
			//System.out.println(counter);
			if(counter==len-1)
			{
				relList.addElement(posFirstWord);
				//System.out.println(relList.aset.countNodes());
			}
		}
		int up=relList.aset.countNodes();
		//if(this.nameOfPage.equals("stack_datastructure_wiki"))
		//System.out.println(up+" "+this.nameOfPage);
		if(up==0)
			return 0;
		else
			return up;
	}
	float getRelevanceOfPage(String str[],boolean doTheseWordsRepresentAPhrase)
	{
		//System.out.println(this.nameOfPage);
		if(doTheseWordsRepresentAPhrase==true)
		{
			int len=str.length;
			int m=this.doesPhraseOccur(str);
			int wp=this.total;
			float tf=(float)((m*1.0)/(wp-((len-1)*m)));
			float idf=SearchEngine.getIDFPhrase(str);
			return tf*idf;
		}
		else
		{
			int len=str.length;
			int i=0;
			int count=0;
			float rel=0;
			for(i=0;i<len;i++)
			{
				count=0;
				piw.we.ptr=piw.we.head;
				while(piw.we.ptr.next!=null)
				{
					piw.we.ptr=piw.we.ptr.next;
					WordEntry tmp=(WordEntry)piw.we.ptr.data;
					if(tmp.str.equals(str[i]))
					{
						tmp.wpos.ptr=tmp.wpos.head;
						while(tmp.wpos.ptr.next!=null)
						{
							tmp.wpos.ptr=tmp.wpos.ptr.next;
							Position tmp2=(Position)tmp.wpos.ptr.data;
							if(tmp2.p.nameOfPage.equals(this.nameOfPage))
								count++;
						}
					}
				}
				float idf=SearchEngine.getIDFWord(str[i]);
				if(idf==0)
					idf=1.0f;
				//System.out.println(str[i]+" "+(float)(((count*1.0)/this.total)));
				rel=rel+(float)(((count*1.0)/this.total)*idf);
			}
			return rel;
		}
	}
}