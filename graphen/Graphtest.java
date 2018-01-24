package graphen;
public class Graphtest
{ 
    public static void main(String[] args)
    {
        Graph g = new Graph (15);
        //TESTBEISPIEL
        g.knotenneu (1);
        g.knotenneu (2);
        g.kanteneu  (1,2,1);
        g.knotenneu (3);
        g.kanteneu  (2,3,2);
        g.knotenneu (4);
        g.kanteneu  (3,4,3);
        g.knotenneu (5);
        g.kanteneu  (4,5,4);
        g.knotenneu (6);
        g.kanteneu  (5,6,5);
        g.knotenneu (7);
        g.kanteneu  (6,7,6);
        g.knotenneu (8);
        g.kanteneu  (7,8,7);
        g.knotenneu (9);
        g.kanteneu  (8,9,8);
        g.knotenneu (10);
        g.kanteneu  (9,10,9);
        g.kanteneu  (1,10,10);
        g.kanteneu  (2,10,11);
        g.kanteneu  (1,3,12);
        g.kanteneu  (4,6,13);
        g.ausgabe();
        System.out.println("ENDE I");
        //TEST FEHLER
        g.knotenneu (5);
        g.knotenloeschen(100);
        g.kanteneu(1, 11, 111);
        g.kanteloeschen(1, 11);
        System.out.println("ENDE II");
        //TEST OPERATIONEN
        g.knotenloeschen(5);
        g.knotenloeschen(9);
        g.kanteloeschen(1, 2);
        g.kanteloeschen(1, 10);
        g.ausgabe();
        System.out.println("ENDE III");
        //TEST LESEN/SCHREIBEN
        g.dateischreiben();
        g.setGraph( Graph.zufallsGraph(10, 10) );
        System.out.println("Neuer Graph:");
        g.ausgabe();
        g.setGraph( g.dateilesen() );
        System.out.println("Gespeicherter Graph:");
        g.ausgabe();
        System.out.println("ENDE IV");
    }
}