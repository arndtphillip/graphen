package graphen;

public class SearchNode {
	int a,b,weg;
	SearchNode next;
	/**
	 * Element für die Funktionalität von Wegsuche in einem gewichteten, ungerichteten Graphen.
	 * @param x Aktueller Knoten
	 * @param y Vorgängerknoten
	 * @param z Zurückgelegter Weg
	 */
	SearchNode(int x, int y, int z)
	{
		a = x;
		b = y;
		weg = z;
		next = null;
	}
}
