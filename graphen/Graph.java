package graphen;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Klasse für Funktionalität eines Graphen. Speichert Knoten in einem Vektor und Kanten mit Gewichtung in einer Adjazenzmatrix.
 * @author Leonardo Ruland, 558307
 * @see Graphtest
 * @see Graph_paint
 */
public class Graph
{
	//ATTRIBUTE & KONSTRUKTOR
	private int knzahl;
	private int[][] kante;
	private int[] knoten;
	/**
	 * Konstruktor der Klasse Graph_start. Initialisiert leere Arrays für Kanten und Knoten.
	 * @param kn Maximal erlaubte Anzahl der Knoten.
	 */
	Graph(int kn)
	{
		knzahl = 0;
		kante = new int[kn][kn];
		knoten = new int[kn];
	}
	//GETTER & SETTER
	public int getKnzahl()
	{
		return knzahl;
	}
	public int[] getKnoten()
	{
		return knoten;
	}
	public void setKnoten(int[] k)
	{
		this.knoten = k;
	}
	public int[][] getKante()
	{
		return kante;
	}
	public void setGraph(Graph g)
	{
		this.kante = g.kante;
		this.knoten = g.knoten;
		this.knzahl = g.knzahl;
	}

	//KLASSENMETHODEN
	/**
	 * Erstellt einen Graphen nach zufälligen Vorgaben.
	 * @return Zufallsgraph
	 */
	public static Graph zufallsGraph(int kn, int kt)
	{
		Random zufall = new Random();
		int knotenmax = 20, kantenmax = 15, gewichtmax = 10, namensraum = 100;
				//kn = zufall.nextInt(knotenmax)+1, kt = zufall.nextInt(kantenmax)+1;
		Graph g = new Graph(knotenmax);
		for (int i = 0 ; i <= kn ; i++)
			g.knotenneu( zufall.nextInt(namensraum)+1 );
		for (int i = 0 ; i < kt ; i++)
			g.kanteneu(g.knoten[zufall.nextInt(g.getKnzahl())], g.knoten[zufall.nextInt(g.getKnzahl())], 1+zufall.nextInt(gewichtmax)+1 );
		return g;
	}

	//INSTANZMETHODEN
	/**
	 * Prüft, ob der übergebene Knoten im Format Integer im Knotenvektor vorhanden ist.
	 * @param kn Gesuchter Knoten
	 * @return true wenn Knoten existiert
	 */
	protected boolean knotenexistiert (int kn)
	{
		boolean exists = false;
		for (int i = 0 ; i < knoten.length ; i++)
			if (knoten[i] == kn && kn != 0 && kn != -1)
				exists = true;
		return exists;
	}
	/**
	 * Erstellt einen neuen Knoten und fügt diesen ggf. ins Array ein.
	 * Prüft, ob der Knoten bereits existiert oder das Array keine weiteren Knoten mehr fassen kann und gibt dementsprechend Fehlermeldungen auf der Konsole aus.
	 * @param kn Neu zu erstellender Knoten
	 */
	public void knotenneu(int kn)
	{
		if ( !knotenexistiert(kn) && knzahl < knoten.length ){
			boolean fertig = false;
			int i = 0;
			while (!fertig && i < knoten.length) {
				if ( knoten[i] == 0 || knoten[i] == -1) {
					knoten[i] = kn;
					fertig = true;
				}
				i++;
			}
			knzahl++;
		} else if ( knotenexistiert(kn) )
			System.out.println("Neuer Knoten: Knoten " + kn + " existiert bereits");
		else if ( knzahl >= knoten.length )
			System.out.println("Neuer Knoten: Zuviele Knoten");
	}
	/**
	 * Spezielle Funktion für das schreiben der Datei, da Quelle 1:1 eingespeist werden soll.
	 * Erstellt einen neuen Knoten und fügt diesen ins Array ein.
	 * @param kn Neu zu erstellender Knoten
	 */
	private void knotenneuDateilesen(int kn)
	{
		boolean fertig = false;
		int i = 0;
		while (!fertig && i < knoten.length) {
			if ( knoten[i] == 0 ) {
				knoten[i] = kn;
				fertig = true;
			}
			i++;
		}
	}
	/**
	 * Löscht den übergebenen Knoten und alle anhängenden Kanten.
	 * Prüft vorher ob der Knoten vorhanden ist und gibt ggf. eine Fehlermeldung auf der Konsole aus.
	 * @param Kn Zu löschender Knoten
	 */
	public void knotenloeschen(int Kn)
	{
		if ( knotenexistiert(Kn) ){
			for (int i = 0 ; i < kante.length ; i++ ) { //Anhängende Kanten löschen
				kante[knotennr(Kn)][i] = 0;
				kante[i][knotennr(Kn)] = 0;
			}
			knoten[knotennr(Kn)] = -1; //Knoten löschen
			knzahl--;
		} else
			System.out.println("Knoten Löschen: Knoten "+Kn+" existiert nicht");
	}
	/**
	 * Gibt die Stelle im Array Knoten[i] wieder, an welcher der Knoten steht.
	 * Gibt -1 zurück, falls der Knoten nicht existiert.
	 * @param kn Name des Knotens
	 * @return Stelle an welcher der Knoten steht
	 */
	public int knotennr(int kn)
	{
		for (int i = 0; i < knoten.length ; i++)
			if (knoten[i] == kn)
				return i;
		return -1;
	}
	/**
	 * Erstellt eine neue Kante und speichert das Gewicht in der Matrix kante an der Stelle [K1][K2].
	 * Prüft, ob beide Knoten existieren und gibt ggf. eine Fehlermeldung auf der Konsole aus.
	 * @param K1 Name des ersten Knotens
	 * @param K2 Name des zweiten Knotens
	 * @param wert Gewicht der Kante
	 */
	public void kanteneu(int K1, int K2, int wert)
	{
		int n1,n2;
		if ( knotenexistiert(K1) && knotenexistiert(K2) ) {
			n1 = knotennr(K1);
			n2 = knotennr(K2);
			kante[n1][n2] = wert;
			kante[n2][n1] = wert;
		} else
			System.out.println("Neue Kante: Mindestens ein Knoten existiert nicht");
	}
	/**
	 * Löscht eine bestehende Kante zwischen zwei bestimmten Knoten.
	 * Prüft, ob beide Knoten existieren und gibt ggf. eine Fehlermeldung auf der Konsole aus.
	 * @param K1 Knoten
	 * @param K2 Knoten
	 */
	public void kanteloeschen(int K1, int K2)
	{
		int n1,n2;
		if ( knotenexistiert(K1) && knotenexistiert(K2) ) {
			n1 = knotennr(K1);
			n2 = knotennr(K2);
			kante[n1][n2] = 0;
			kante[n2][n1] = 0;
		} else
			System.out.println("Kante Löschen: Mindestens ein Knoten existiert nicht");
	}
	/**
	 * Gibt Knoten und Kanten auf der Konsole aus.
	 */
	public void ausgabe()
	{
		System.out.println("\n Knotenliste\n");
		for (int i = 0; i < knoten.length; i++)
			if(knoten[i] != 0 && knoten[i] != -1)
				System.out.print("  " + knoten[i]);
		System.out.println("\n\n Kantenliste\n");
		for (int i = 0; i < kante.length; i++) {
			for (int j = 0; j < kante.length; j++){
				if (kante[i][j] != 0)
					System.out.print(" Kante " + knoten[i] + " -> " + knoten[j] + " (" + kante[i][j] + ")\t");
				if( j == kante.length-1 )
					System.out.println();
			}
		}
	}
	/**
	 * Liest die Eigenschaften eines Graphen aus einer Textdatei aus.
	 * @return Graph nach den Vorgaben aus der Datei.
	 */
	public Graph dateilesen()
	{
		//Attribute fürs Einlesen
		BufferedReader r;
		String s = "";
		int i,j;
		//Attribute fürs Schreiben
		Graph g = null;
		try {//Werte einlesen
			r = new BufferedReader(
					new FileReader("graphen.txt"));
			while (!s.equals("Knotenmax")) //Zur Stelle "Knotenmax" springen
				s = r.readLine();
			s = r.readLine();
			g = new Graph( Integer.valueOf( s ) );
			while (!s.equals("Knotenzahl")) //Zur Stelle "Knotenzahl" springen
				s= r.readLine();
			s = r.readLine();
			g.knzahl = Integer.valueOf(s);
			while (!s.equals("Knoten"))//Zur Stelle "Knoten" springen
				s = r.readLine();
			for (i = 0 ; i < g.knoten.length ; i++) { //Knoten einlesen
				s = r.readLine();
				g.knotenneuDateilesen( Integer.valueOf(s) );
			}
			while (!s.equals("Kanten")) //Zur Stelle "Kanten" springen
				s = r.readLine();
			for (i = 0 ; i < g.kante.length ; i++)//Kanten einlesen
				for ( j = 0 ; j < g.kante.length ; j++) {
					s = r.readLine();
					if( !s.equals("0") )
						g.kanteneu(g.getKnoten()[i], g.getKnoten()[j], Integer.valueOf(s) );
				}
		} catch (IOException e) {
			System.out.println("Fehler beim Lesen der Datei");
		}
		return g;
	}
	/**
	 * Schreibt die Attribute des aktuellen Graphen in eine Textdatei.
	 */
	public void dateischreiben()
	{
	    BufferedWriter f;
	    try {
	      f = new BufferedWriter(
	           new FileWriter("graphen.txt"));
	      f.write("Knotenmax\n");
	      f.write(knoten.length + "\n");
	      f.write("Knotenzahl\n");
	      f.write(knzahl + "\n");
	      f.write("Knoten\n");
	      for (int i = 0 ; i < knoten.length ; i++)
	    	  f.write(knoten[i]+"\n");
	      f.write("Kanten\n");//jeder Knoten in neue Zeile
	      for (int i = 0 ; i < kante.length ; i++)
	    	  for (int j = 0 ; j < kante.length ; j++)
	    		  f.write(kante[i][j]+"\n");//Jede Kante in neue Zeile
	      f.close();
	    } catch (IOException e) {
	      System.out.println("Fehler beim Erstellen der Datei");
	    }
	}
	// fakultativ Breitensuche, Tiefensuche oder Wegsuche
	public int[] wegfinden(int a, int z)
	{
		int[] ret = new int[knoten.length];
		//ALG
		SearchNode[] weg = new SearchNode[knoten.length];
		//ALG
		return ret;
	}

	/**
	 * Berechnet den kürzesten Weg zwischen 2 Konten mithilfe des Dijkstra-Alogrithmus
	 * @author Philipp Arndt
	 * @param start Startknoten
	 * @param end Zielknoten
	 * @return Den kürzesten Weg vom Typ Path
	 * @see Path
	 */
	public Path shortestPath(int start, int end) {
		if (start == end)	// nothing to do here
			return new Path(start + " --> " + end, 0);
		else {
			ArrayList<Integer[]> reachable = new ArrayList<>();
			ArrayList<Integer[]> reached = new ArrayList<>();

			Integer[] current = {start, start, 0};

			while (current[0] != end) {
				int currentIndex = knotennr(current[0]);

				// find reachable nodes with distance
				if (currentIndex > -1) {
					for (int i = 0; i < kante[currentIndex].length; i++) {
						if (kante[currentIndex][i] > 0 && i != currentIndex) {
							// connection exists
							int possibleNeighbor = knoten[i];

							boolean add = true;
							// check if a shorter path is already available (in reachable list)
							for(int j = 0; j < reachable.size(); j++) {
								if(reachable.get(j)[0] == possibleNeighbor && reachable.get(j)[2] < kante[currentIndex][i]
										&& reachable.get(j)[2] != 0)
									add = false;
							}

							// check if node was already reached
							for(int j = 0; j < reached.size(); j++) {
								if(reached.get(j)[0] == possibleNeighbor)
									add = false;
							}

							// only add if no shorter path is available
							if (add) {
								Integer[] newReachable = {knoten[i], current[0], current[2] + kante[currentIndex][i]};
								reachable.add(newReachable);
							}
						}
					}
				}

				// find the entry with lowest distance in reachable list
				Integer[] nearestNeighbor = { 0, 0, 0 };

				// save index to delete entry afterwards
				int index = -1;

				if(reachable.size() > 0) {
					int shortestDistance = reachable.get(0)[2];
					nearestNeighbor = reachable.get(0);
					index = 0;

					for (int i = 1; i < reachable.size(); i++) {
						if (reachable.get(i)[2] < shortestDistance) {
							shortestDistance = reachable.get(i)[2];
							nearestNeighbor = reachable.get(i);
							index = i;
						}
					}
				}

				if(index >= 0) {
					reachable.remove(index);
					reached.add(current);
					current = nearestNeighbor;
				} else {
					// kein weg vorhanden
					return new Path("Kein Weg vorhanden", 0);
				}
			}

			// find way with back-warding
			int previous = current[1];

			// string builder for creation of shortest path string
			StringBuilder sb = new StringBuilder();
			sb.append(end);

			while(previous != start) {
				for(int i = 0; i < reached.size(); i++) {
					if(reached.get(i)[0] == previous) {
						// append at the beginning
						sb.insert(0, previous + " --> ");

						previous = reached.get(i)[1];
					}
				}
			}

			// append at the beginning
			sb.insert(0, start + " --> ");

			return new Path(sb.toString(), current[2]);
		}
	}
}
