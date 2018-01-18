package graphen;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Klasse für die Ausführung der Klasse Graph_start in einer Umgebung von Dialogfenstern.
 * Benutzt die Erweiterungen AWT und SWING.
 * Operationen:
 * Knoten erstellen,
 * Knoten löschen,
 * Kante erstellen,
 * Kante löschen,
 * Graph speichern,
 * Graph laden,
 * zufälligen Graphen erzeugen.
 * @author Leonardo Ruland, 558307
 * @see Graph
 */
public class Graph_paint extends JFrame
{
	//ATTRIBUTE & KONSTRUKTOR
	private Graph graph;
	private int fensterX = 1450, fensterY = 400, maxKnoten = 20;
	//ELEMENTE DER OBERFLÄCHE
	private JFrame frame;
	private JPanel menu;
	private JLabel anzKnotenT,anzKnotenV;
	private JButton neuKnoten, delKnoten, neuKante, delKante, delGraph, speichern, laden, zufallsgraph;
	/**
	 * Konstruktor der Klasse. Bildet ein Graphen-Objekt und initialisiert die graphische Oberfläche.
	 */
	Graph_paint()
	{ 	
		graph = new Graph(maxKnoten);
		prepareGUI();
	}
	/**
	 * Ausführende Methode, bildet das Fenster.
	 * @param args Übergabeargumente
	 */
	public static void main(String[] args)
	{
		new Graph_paint();
	}
	//INSTANZMETHODEN
	/**
	 * Wird von Konstruktor aufgerufen. Setzt alle Elemente der Klasse auf ein Fenster um.
	 */
	private void prepareGUI()
	{
		//Fenster Eigenschaften
		frame = new JFrame("Graphen");
		frame.setLocation(0,0);
		frame.setSize(fensterX,fensterY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout() );
		//Menu
		menu = new JPanel();
		menu.setLayout(new FlowLayout() );
		frame.add(menu, BorderLayout.NORTH);
		//Neuer Knoten
		menu.add(neuKnoten = new JButton("Neuer Knoten") );
		neuKnoten.addActionListener( new KnotenErstellen() );
		//Knoten löschen
		menu.add(delKnoten = new JButton("Knoten löschen") );
		delKnoten.addActionListener( new KnotenLoeschen(graph) );
		//Neue Kante
		menu.add( neuKante = new JButton("Neue Kante") );
		neuKante.addActionListener( new KanteErstellen(graph) );
		//Kante löschen
		menu.add(delKante = new JButton("Kante löschen") );
		delKante.addActionListener( new KanteLoeschen(graph) );
		//Graphen speichern
		menu.add(delGraph = new JButton("Graphen löschen") );
		delGraph.addActionListener( new GraphLoeschen() );
		//Graphen speichern
		menu.add(speichern = new JButton("Graphen speichern") );
		speichern.addActionListener( new GraphSpeichern() );
		//Graphen laden
		menu.add(laden = new JButton("Graphen laden") );
		laden.addActionListener( new GraphLaden() );
		//Graphen speichern
		menu.add(zufallsgraph = new JButton("Zufallsgraph!") );
		zufallsgraph.addActionListener( new GraphZufall() );
		//Anzeige existente Knoten
		menu.add(anzKnotenT = new JLabel("Anzahl Knoten: ") );
		menu.add(anzKnotenV = new JLabel( Integer.toString(graph.getKnzahl()) + " \\ " + graph.getKnoten().length ));
		//Graphen Anzeige
		frame.add( new PaintPanel(),BorderLayout.CENTER );
		frame.setVisible(true);
	}
	/**
	 * Verbindet zwei Knoten über eine parabolische Kante.
	 * An der Spitze der Parabel wird das Gewicht der Kante angezeigt.
	 * @param g Graphicelement
	 * @param a Knoten der verbunden wird
	 * @param b Knoten der verbunden wird
	 */
	public void knotenVerbinden(Graphics g, Graphicnode a, Graphicnode b)
	{
		if(a != null && b != null){
			g.drawArc(getArcX(a,b), 150 , getArcWidth(a,b), 200, 0, 180);
			g.drawString(Integer.toString(graph.getKante()[graph.knotennr(a.getName())][graph.knotennr(b.getName())]),
					getMitte(a,b), 140 );
		} else {
			System.out.println("knotenVerbinden: mindestens ein Knoten existiert nicht");
		}
	}
	/**
	 * Hilfsmethode für knotenVerbinden().
	 * Berechnet zwischen zwei Knoten den X-Wert, an dem das Gewicht der Kante auf der X-Achse angezeigt werden soll.
	 * @param a Erster Knoten
	 * @param b Zweiter Knoten
	 * @return X-Wert zwischen beiden Knoten
	 */
	public int getMitte(Graphicnode a, Graphicnode b)
	{
		if(a.getZentrum().getX() < b.getZentrum().getX())
			return (int)( (b.getZentrum().getX()+a.getZentrum().getX()) / 2 );
		else
			return (int)( (a.getZentrum().getX()+b.getZentrum().getX()) / 2 );
	}
	/**
	 * Hilfsmethode für drawArc().
	 * Legt fest, an welchem Punkt auf der X-Achse die Kurve verankert werden soll.
	 * @param a Knoten
	 * @param b Knoten
	 * @return X-Wert an dem die Kurve von drawArc() ansetzen soll
	 */
	public int getArcX (Graphicnode a, Graphicnode b)
	{
		Graphicnode node=a;
		if(a.getZentrum().getX() > b.getZentrum().getX()){
			node = b;
		}
		return (int)node.getZentrum().getX();
	}
	/**
	 * Hilfmethode für drawArc().
	 * Berechnet die Breite der Kurve.
	 * @param a Knoten
	 * @param b Knoten
	 * @return Abstand zwischen Knoten a und b
	 */
	public int getArcWidth (Graphicnode a, Graphicnode b)
	{
		Graphicnode first=a,second=b;
		if(a.getZentrum().getX() > b.getZentrum().getX()){
			first = b;
			second = a;
		}	
		return (int)(second.getZentrum().getX()-first.getZentrum().getX());
	}
    /**
     * Subklasse zum Zeichnen im Hauptfenster.
     * @author Leonardo Ruland, 558307
     * @see Graph_paint
     * @see Graph
     * @see NodeList
     */
	class PaintPanel extends JPanel
	{
		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			//graph.ausgabe();
			NodeList knotenliste = null;
			knotenliste = new NodeList();
			int count = 0;
			for(int i = 0 ; i < graph.getKnoten().length ; i++)
				if(graph.getKnoten()[i] != 0 && graph.getKnoten()[i] != -1){
					knotenliste.newElem(graph.getKnoten()[i], (count*70)+50, 250);
					if (count == 0)
						knotenliste.top.setWurzel(true);
					count++;
				}
			//Kanten zeichnen
			for(int x = 0 ; x < graph.getKnoten().length ; x++)
				for(int y = 0 ; y < graph.getKnoten().length ; y++)
					if(graph.getKante()[x][y] != 0 && graph.getKante()[x][y] != -1){
						knotenVerbinden(g,
								knotenliste.fetchNode(graph.getKnoten()[x] ) ,
								knotenliste.fetchNode(graph.getKnoten()[y]) );
					}
			//Knoten zeichnen
			Graphicnode temp = knotenliste.top;
			while (temp != null) {
				temp.paintNode(g);
				temp = temp.getNext();
			}
		}
	}
    /**
     * Subklasse für den Button Knoten erstellen.
     * Öffnet ein neues Dialogfenster.
     * @author Leonardo Ruland, 558307
     * @see Graph_paint
     * @see Graph
     */
	class KnotenErstellen implements ActionListener
	{
		JDialog knotenNeu;
		JPanel panel;
		JLabel b;
		JTextField t;
		JButton k;
		KnotenErstellen()
		{
			knotenNeu = new JDialog();
			knotenNeu.setTitle("Neuer Knoten");
			knotenNeu.setBounds(100, 100, 200, 100);
			knotenNeu.setModal(true);
			panel = new JPanel();
			knotenNeu.add(panel);
			panel.add(b = new JLabel("Bezeichnung") );
			panel.add(t = new JTextField("0123") );
			panel.add(k = new JButton("OK") );
			panel.setVisible(true);
			k.addActionListener(new Schliessen() );
			knotenNeu.setVisible(false);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			knotenNeu.setVisible(true);
		}
	    /**
	     * Subsubklasse für den Button Knoten erstellen. Erstellt einen neuen Knoten auf dem Graphen.
	     * @author Leonardo Ruland, 558307
	     * @see Graph_paint
	     * @see Graph
	     * @see KnotenErstellen
	     */
		class Schliessen implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				graph.knotenneu( Integer.parseInt( t.getText() ) );
				anzKnotenV.setText( Integer.toString( graph.getKnzahl()) + " \\ " + graph.getKnoten().length );
				t.setText("0123");
				frame.repaint();
				knotenNeu.setVisible(false);
			}
		}
	}
    /**
     * Subklasse für den Button Knoten löschen.
     * Öffnet ein neues Dialogfenster.
     * @author Leonardo Ruland, 558307
     * @see Graph_paint
     * @see Graph
     */
	class KnotenLoeschen implements ActionListener
	{
		Graph graph;
		JDialog knotenNeu;
		JPanel panel;
		JLabel b;
		JTextField t;
		JButton k;
		KnotenLoeschen(Graph g)
		{
			graph = g;
			knotenNeu = new JDialog();
			knotenNeu.setTitle("Knoten löschen");
			knotenNeu.setBounds(100, 100, 200, 100);
			knotenNeu.setModal(true);
			panel = new JPanel();
			knotenNeu.add(panel);
			panel.add(b = new JLabel("Knoten Nr.") );
			panel.add(t = new JTextField("0123") );
			panel.add(k = new JButton("OK") );
			panel.setVisible(true);
			k.addActionListener(new Schliessen() );
			knotenNeu.setVisible(false);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			knotenNeu.setVisible(true);
		}
	    /**
	     * Subsubklasse für den Button Knoten entfernen. Entfernt einen Knoten von dem Graphen.
	     * @author Leonardo Ruland, 558307
	     * @see Graph_paint
	     * @see Graph
	     * @see KnotenEntfernen
	     */
		class Schliessen implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				graph.knotenloeschen( Integer.parseInt( t.getText() ) );
				anzKnotenV.setText( Integer.toString( graph.getKnzahl()) + " \\ " + graph.getKnoten().length );
				t.setText("0123");
				frame.repaint();
				knotenNeu.setVisible(false);
			}
		}
	}
    /**
     * Subklasse für den Button Kante erstellen.
     * Öffnet ein neues Dialogfenster.
     * @author Leonardo Ruland, 558307
     * @see Graph_paint
     * @see Graph
     */
	class KanteErstellen implements ActionListener
	{
		Graph graph;
		JDialog kanteNeu;
		JPanel panel;
		JLabel a,x,u;
		JTextField b,y,v;
		JButton k;
		KanteErstellen(Graph g)
		{
			graph = g;
			kanteNeu = new JDialog();
			kanteNeu.setTitle("Neue Kante");
			kanteNeu.setBounds(100, 100, 150, 160);
			kanteNeu.setModal(true);
			panel = new JPanel();
			kanteNeu.add(panel);
			panel.add(a = new JLabel("Knoten eins") );
			panel.add(b = new JTextField("0123") );
			panel.add(x = new JLabel("Knoten zwei") );
			panel.add(y = new JTextField("0123") );
			panel.add(u = new JLabel("Gewicht") );
			panel.add(v = new JTextField("0123") );
			panel.add(k = new JButton("OK") );
			panel.setVisible(true);
			k.addActionListener(new Schliessen() );
			kanteNeu.setVisible(false);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			kanteNeu.setVisible(true);
		}
	    /**
	     * Subsubklasse für den Button Kante erstellen. Entfernt einen Knoten von dem Graphen.
	     * @author Leonardo Ruland, 558307
	     * @see Graph_paint
	     * @see Graph
	     * @see KanteErstellen
	     */
		class Schliessen implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				graph.kanteneu( Integer.parseInt(b.getText()),
						Integer.parseInt(y.getText()),
						Integer.parseInt(v.getText()) );
				b.setText("0123");
				y.setText("0123");
				v.setText("0123");
				frame.repaint();
				kanteNeu.setVisible(false);
			}
		}
	}
    /**
     * Subklasse für den Button Kante löschen.
     * Öffnet ein neues Dialogfenster.
     * @author Leonardo Ruland, 558307
     * @see Graph_paint
     * @see Graph
     */
	class KanteLoeschen implements ActionListener
	{
		Graph graph;
		JDialog kanteNeu;
		JPanel panel;
		JLabel a,x;
		JTextField b,y;
		JButton k;
		KanteLoeschen(Graph g)
		{
			graph = g;
			kanteNeu = new JDialog();
			kanteNeu.setTitle("Kante löschen");
			kanteNeu.setBounds(100, 100, 150, 160);
			kanteNeu.setModal(true);
			panel = new JPanel();
			kanteNeu.add(panel);
			panel.add(a = new JLabel("Knoten eins") );
			panel.add(b = new JTextField("0123") );
			panel.add(x = new JLabel("Knoten zwei") );
			panel.add(y = new JTextField("0123") );
			panel.add(k = new JButton("OK") );
			panel.setVisible(true);
			k.addActionListener(new Schliessen() );
			kanteNeu.setVisible(false);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			kanteNeu.setVisible(true);
		}
	    /**
	     * Subsubklasse für den Button Kante löschen. Entfernt einen Knoten von dem Graphen.
	     * @author Leonardo Ruland, 558307
	     * @see Graph_paint
	     * @see KanteLoeschen
	     * @see Graph
	     */
		class Schliessen implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				graph.kanteloeschen( Integer.parseInt(b.getText()),
						Integer.parseInt(y.getText()));
				b.setText("0123");
				y.setText("0123");
				frame.repaint();
				kanteNeu.setVisible(false);
			}
		}
	}
	/**
     * Subklasse für den Button Graph löschen.
     * Erstellt einen neuen leeren Graphen.
     * @author Leonardo Ruland, 558307
     * @see Graph_paint
     * @see Graph
     */
	class GraphLoeschen implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			graph.setGraph( new Graph(maxKnoten) );
			anzKnotenV.setText( Integer.toString( graph.getKnzahl()) + " \\ " + graph.getKnoten().length );
			frame.repaint();
		}
	}
    /**
     * Subklasse für den Button Graph speichern.
     * Speichert den Graphen in eine .txt Datei.
     * @author Leonardo Ruland, 558307
     * @see Graph_paint
     * @see Graph
     */
	class GraphSpeichern implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			graph.dateischreiben();
		}
	}
    /**
     * Subklasse für den Button Graph laden.
     * Läd den Graphen aus einer .txt Datei.
     * @author Leonardo Ruland, 558307
     * @see Graph_paint
     * @see Graph
     */
	class GraphLaden implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			graph.setGraph( graph.dateilesen() );
			anzKnotenV.setText( Integer.toString( graph.getKnzahl()) + " \\ " + graph.getKnoten().length );
			frame.repaint();
		}
	}
    /**
     * Subklasse für den Button Zufallsgraph.
     * Erstellt einen Zufälligen Graphen..
     * @author Leonardo Ruland, 558307
     * @see Graph_paint
     * @see Graph
     */
	class GraphZufall implements ActionListener
	{
		Graph temp;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			graph.setGraph( Graph.zufallsGraph() );
			anzKnotenV.setText( Integer.toString( graph.getKnzahl()) + " \\ " + graph.getKnoten().length );
			frame.repaint();
		}
	}
}