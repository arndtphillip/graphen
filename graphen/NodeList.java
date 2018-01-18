package graphen;
/**
 * Einfach verkettete Liste.
 * Funktionen:
 * newElem(Payload) Queue;
 * @author Leonardo Ruland, 558307
 */
public class NodeList
{
	//ATTRIBUTE
	Graphicnode top = null;
	//INSTANZMETHODEN
	/**
	 * Setzt ein neues Element ans Ende der Liste
	 * @param n Name des Knotens
	 * @param x X-Wert
	 * @param y Y-Wert
	 */
	protected void newElem(int n, int x, int y)
	{
		Graphicnode add = new Graphicnode(n,x,y);
		if( top == null )
			top = add;
		else{
			Graphicnode temp = top;
			while(temp.getNext() != null)
				temp = temp.getNext();
			temp.setNext(add);
		}			
	}
    /**
     * Gibt den gesuchten Knoten aus der Liste zurück.
     * @param kn Name des Knotens
     * @return Knoten, wenn er existiert, ansonsten null.
     */
    protected Graphicnode fetchNode(int kn)
    {
    	Graphicnode temp = top;
    	Graphicnode ret = null;
    	while (temp != null){
    		if (temp.getName() == kn)
    			ret = temp;
    		temp = temp.getNext();
    	}
    	if (ret == null)
    		System.out.println("Liste: Knoten nicht gefunden");
    	return ret;
    }
}
