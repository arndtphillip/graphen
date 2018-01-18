package graphen;

public class SearchList
{
	SearchNode top = null;
	public void addNextNodes(SearchNode add)
	{
		SearchNode temp = top;
		while(temp.next != null)
			temp = temp.next;
		temp.next = add;
	}
	public SearchNode popNearest()
	{
		SearchNode temp = top, high = top;
		while(temp != null){
			if(temp.weg < high.weg)
				high = temp;
			temp = temp.next;
		}
        return high;
	}
	public void getNode(int a)
	{
		
	}
}
