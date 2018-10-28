public class Position implements Comparable<Position>
{
	PageEntry p;
	int wordIndex;
	int excludeStop;
	Position(PageEntry p,int wordIndex)
	{
		this.p=p;
		this.wordIndex=wordIndex;
		excludeStop=0;
	}
	PageEntry getPageEntry()
	{
		return p;
	}
	int getWordIndex()
	{
		return wordIndex;
	}
	public int compareTo(Position otherObject)
	{
		return Integer.compare(this.excludeStop,otherObject.excludeStop);
	}
}