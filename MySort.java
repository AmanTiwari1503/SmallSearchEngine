import java.util.*;
public class MySort
{
	ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries)
	{
		MySet<SearchResult> tmp=listOfSortableEntries;
		tmp.aset.ptr=tmp.aset.head;
		ArrayList<SearchResult> sortedList=new ArrayList<SearchResult>();
		while(tmp.aset.ptr.next!=null)
		{
			tmp.aset.ptr=tmp.aset.ptr.next;
			sortedList.add(tmp.aset.ptr.data);
		}
		Comparator<SearchResult> comp=Collections.reverseOrder();
		sortingAlgorithm(sortedList,0,sortedList.size()-1,comp);
		return sortedList;
	}

	<X> void sortingAlgorithm(ArrayList<X> arr,int low,int high,Comparator<X> imp)
	{
		if(low>=high)
			return;


		if(low<high)
		{
		X pivot=arr.get(high);
		int i=low-1;
		int j=0;
		X temp;
		for(j=low;j<high;j++)
		{
			if(imp.compare(arr.get(j),pivot)<=0)
			{
				i++;
				temp=arr.get(i);
				arr.set(i,arr.get(j));
				arr.set(j,temp);
			}
		}
		temp=arr.get(i+1);
		arr.set(i+1,arr.get(high));
		arr.set(high,temp);
		int p=i+1;

		sortingAlgorithm(arr,low,p-1,imp);
		sortingAlgorithm(arr,p+1,high,imp);
		}
	}
}