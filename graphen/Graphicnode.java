package graphen;
import java.awt.Color;
import java.awt.Graphics;
/**
 * Klasse für die graphische Repräsentation eines Knotens.
 * @author Leonardo Ruland, 558307
 * @see Graph_paint
 * @see Punkt
 */
public class Graphicnode
{
	//Attribute
	private boolean wurzel;
	private final int name;
	private Punkt zentrum,anker;
	private static float durchmesser=50;
	private Graphicnode next;
	/**
	 * Konstruktor der Klasse Graphicnode.
	 * Setzt Punkte für das Zentrum und für den Anker.
	 * @param n Knoten Nummer
	 * @param x X-Wert
	 * @param y Y-Wert
	 */
	Graphicnode(int n, float x, float y)
	{
		name = n;
		zentrum = new Punkt(x,y);
		anker = new Punkt( x-(durchmesser/2) , y-(durchmesser/2) );
		next = null;
		wurzel = false;
	}
	//Getter&Setter
	public void setWurzel(boolean b)
	{
		wurzel = b;
	}
	public int getName()
	{
		return name;
	}
	public Punkt getZentrum()
	{
		return zentrum;
	}
	public Punkt getAnker()
	{
		return anker;
	}
	public float getDurchmesser()
	{
		return durchmesser;
	}
	public Graphicnode getNext()
	{
		return next;
	}
	public void setNext(Graphicnode n)
	{
		next = n;
	}
	//Instanzmethoden
	/**
	 * Zeichnet den Knoten mit schwarzem Rand und zentralem Namen. Andere Farbe wenn Wurzelknoten.
	 * @param g Graphikobjekt
	 * @see Graph_paint
	 */
	public void paintNode(Graphics g)
	{
		g.setColor( new Color(0,0,0) );
		g.fillOval( (int)anker.getX(), (int)anker.getY(),
				(int)durchmesser, (int)durchmesser);
		g.setColor( new Color(0,230,200) );
		if(wurzel)
			g.setColor( new Color(150,150,150) );
		g.fillOval( (int)anker.getX()+1, (int)anker.getY()+1,
				(int)durchmesser-2, (int)durchmesser-2);
		g.setColor( new Color(0,0,0) );
		if(name < 10)
			g.drawString(Integer.toString(name),
					(int)(anker.getX()+durchmesser/2.5), (int)(zentrum.getY()+durchmesser/8) );
		else if (name < 100)
			g.drawString(Integer.toString(name),
					(int)(anker.getX()+durchmesser/3), (int)(zentrum.getY()+durchmesser/8) );
		else 
			g.drawString(Integer.toString(name),
					(int)(anker.getX()+durchmesser/4), (int)(zentrum.getY()+durchmesser/8) );
	}
}