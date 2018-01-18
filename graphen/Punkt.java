package graphen;
/**
 * Klasse für Punkt, enthält X und Y Wert.
 * @author Leonardo Ruland, 558307
 * @see Graphicnode
 */
public class Punkt 
{
	private float x,y;
	/**
	 * Konstruktor der Klasse Punkt, setzt übergebenen Wert für X und Y Koordinaten.
	 * @param a x-Wert
	 * @param b y-Wert
	 */
	Punkt(float a, float b)
	{
		x = a;
		y = b;
	}
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return y;
	}
}
