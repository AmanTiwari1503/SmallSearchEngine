public class SearchResult implements Comparable<SearchResult>
{
	PageEntry p;
	float rel;
	public SearchResult(PageEntry p,float r)
	{
		this.p=p;
		rel=r;
	}
	public PageEntry getPageEntry()
	{
		return p;
	}
	public float getRelevance()
	{
		return rel;
	}
	public int compareTo(SearchResult otherObject)
	{
		return Integer.compare((int)(this.rel*1000000),(int)(otherObject.rel*1000000));
	}
}