package graphen;
import org.junit.*;
public class GraphenPaketTest
{
	private Graph graph;
	private Graph_paint graphP;
	//Tests für Graph
	@Before
	public void setup(){
		graph = new Graph(10);
	}
	@Test
	public void knotenexistiertTest()
	{
		graph.knotenneu(1);
		Assert.assertTrue( graph.knotenexistiert(1) );
		Assert.assertFalse( graph.knotenexistiert(2) );
		Assert.assertFalse( graph.knotenexistiert(0) );
		Assert.assertFalse( graph.knotenexistiert(-1) );
	}
	@Test
	public void knotenneuTest()
	{
		graph.knotenneu(1);
		graph.knotenneu(0);
		graph.knotenneu(-1);
		Assert.assertTrue( graph.knotenexistiert(1) );
		Assert.assertFalse( graph.knotenexistiert(0) );
		Assert.assertFalse( graph.knotenexistiert(-1) );
	}
	@Test
	public void knotenloeschenTest()
	{
		graph.knotenneu(1);
		graph.knotenloeschen(1);
		Assert.assertFalse( graph.knotenexistiert(1) );
	}
	@Test
	public void knotennrTest()
	{
		graph.knotenneu(1);
		graph.knotenneu(2);
		Assert.assertTrue( graph.knotennr(1) == 0 );
		Assert.assertTrue( graph.knotennr(2) == 1 );
	}
	@Test
	public void kanteneuTest()
	{
		graph.knotenneu(1);
		graph.knotenneu(2);
		graph.kanteneu(2, 1, 3);
		Assert.assertTrue( graph.getKante()[graph.knotennr(1)][graph.knotennr(2)] == 3);
		Assert.assertTrue( graph.getKante()[graph.knotennr(2)][graph.knotennr(1)] == 3);
	}
	@Test
	public void kanteloeschenTest()
	{
		graph.knotenneu(1);
		graph.knotenneu(2);
		graph.kanteneu(1, 2, 3);
		graph.kanteloeschen(2, 1);
		Assert.assertFalse( graph.getKante()[graph.knotennr(1)][graph.knotennr(2)] == 3 );
		Assert.assertFalse( graph.getKante()[graph.knotennr(2)][graph.knotennr(1)] == 3 );
		Assert.assertTrue( graph.getKante()[graph.knotennr(1)][graph.knotennr(2)] == 0 );
		Assert.assertTrue( graph.getKante()[graph.knotennr(2)][graph.knotennr(1)] == 0 );
	}
	@Test
	public void saveAndLoadTest()
	{
		Graph a = new Graph(0),b = new Graph(0);
		a.setGraph( Graph.zufallsGraph(10, 10) );
		a.dateischreiben();
		b.setGraph( b.dateilesen() );
		for(int i = 0 ; i < a.getKante().length ; i++)
			for(int j = 0 ; j < a.getKante().length ; j++)
				Assert.assertTrue( a.getKante()[i][j] == b.getKante()[i][j] );
		for(int i = 0 ; i < a.getKnoten().length ; i++)
			Assert.assertTrue( a.getKnoten()[i] == b.getKnoten()[i] );
		Assert.assertTrue( a.getKnzahl() == b.getKnzahl() );
		
	}
	@Test
	public void wegfindenTest()
	{
		graph.knotenneu(1);
		graph.knotenneu(2);
		graph.knotenneu(3);
		graph.knotenneu(4);
		graph.kanteneu(1, 4, 10);
		graph.kanteneu(1, 2, 1);
		graph.kanteneu(2, 3, 1);
		graph.kanteneu(2, 4, 8);
		graph.kanteneu(3, 4, 1);
		Assert.assertTrue( graph.wegfinden(1, 4)[0] == 1 );
		Assert.assertTrue( graph.wegfinden(1, 4)[1] == 2 );
		Assert.assertTrue( graph.wegfinden(1, 4)[2] == 3 );
		Assert.assertTrue( graph.wegfinden(1, 4)[3] == 4 );
	}
	//Tests für Graph_paint	
	@Before
	public void setupP()
	{
		graphP = new Graph_paint();
	}
	@Test
	public void getMitteTest()
	{
		Graphicnode a = new Graphicnode(1,10,5);
		Graphicnode b = new Graphicnode(1,20,5);
		Graphicnode c = new Graphicnode(1,40,5);
		Assert.assertTrue(graphP.getMitte(a, b) == 15);
		Assert.assertTrue(graphP.getMitte(c, b) == 30);
	}
	@Test
	public void getArcXTest()
	{
		Graphicnode a = new Graphicnode(1,10,5);
		Graphicnode b = new Graphicnode(1,20,5);
		Graphicnode c = new Graphicnode(1,40,5);
		Assert.assertTrue( graphP.getArcX(a,b) == 10 );
		Assert.assertTrue( graphP.getArcX(c,a) == 10 );
	}
	@Test
	public void getArcWidthTest()
	{
		Graphicnode a = new Graphicnode(1,10,5);
		Graphicnode b = new Graphicnode(1,20,5);
		Graphicnode c = new Graphicnode(1,40,5);
		Assert.assertTrue( graphP.getArcWidth(a,b) == 10 );
		Assert.assertTrue( graphP.getArcWidth(c,b) == 20 );		
	}
	//Tests für Liste
	@Test
	public void newElemTest()
	{
		NodeList l = new NodeList();
		Assert.assertTrue( l.top == null);
		l.newElem(1, 2, 3);
		l.newElem(3, 2, 1);
		l.newElem(2, 3, 1);
		Assert.assertTrue( l.top != null);
		Assert.assertTrue( l.top.getName() == 1 );
		Assert.assertTrue( l.top.getZentrum().getX() == 2 );
		Assert.assertTrue( l.top.getZentrum().getY() == 3 );
	}
	@Test
	public void fetchNodeTest()
	{
		NodeList l = new NodeList();
		Assert.assertTrue( l.fetchNode(1) == null );
		l.newElem(1, 2, 3);
		l.newElem(3, 2, 1);
		l.newElem(2, 3, 1);
		Assert.assertTrue( l.fetchNode(3) != null );
		Assert.assertTrue( l.fetchNode(3).getName() == 3);
		Assert.assertTrue( l.fetchNode(3).getZentrum().getX() == 2 );
		Assert.assertTrue( l.fetchNode(3).getZentrum().getY() == 1 );
	}
}