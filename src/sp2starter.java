import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.security.auth.x500.X500Principal;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Hypergraph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.event.GraphEvent.Edge;
import edu.uci.ics.jung.graph.event.GraphEvent.Vertex;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationImageServer;

public class sp2starter {
	public static void main(String[] args) throws IOException {
		Graph<String, String> g = new SparseGraph<String, String>();

		g.addVertex("000");
		g.addVertex("001");
		g.addVertex("010");
		g.addVertex("100");
		g.addVertex("011");
		g.addVertex("101");
		g.addVertex("110");
		g.addVertex("111");
		g.addEdge("e0", new Pair<String>("000", "001"));
		g.addEdge("e1", new Pair<String>("000", "010"));
		g.addEdge("e2", new Pair<String>("000", "100"));
		g.addEdge("e3", new Pair<String>("001", "011"));
		g.addEdge("e4", new Pair<String>("001", "101"));
		g.addEdge("e5", new Pair<String>("010", "011"));
		g.addEdge("e6", new Pair<String>("010", "110"));
		g.addEdge("e7", new Pair<String>("100", "101"));
		g.addEdge("e8", new Pair<String>("100", "110"));
		g.addEdge("e9", new Pair<String>("011", "111"));
		g.addEdge("e10", new Pair<String>("101", "111"));
		g.addEdge("e11", new Pair<String>("110", "111"));

		// TODO: Compute a force directed layout

		Dimension dim = new Dimension(400, 400);
		// (0,0) is the top left corner
		Layout<String, String> l = new StaticLayout<String, String>(g);
//		l.setLocation("000", new Point(100, 50));
//		l.setLocation("001", new Point(300, 50));
//		l.setLocation("010", new Point(100, 350));
//		l.setLocation("100", new Point(50, 100));
//		l.setLocation("011", new Point(300, 350));
//		l.setLocation("101", new Point(350, 100));
//		l.setLocation("110", new Point(50, 300));
//		l.setLocation("111", new Point(350, 300));

		VisualizationImageServer<String, String> vis = new VisualizationImageServer<String, String>(l, dim);

		BufferedImage im = (BufferedImage) vis.getImage(new Point2D.Double(dim.getWidth() / 2, dim.getHeight() / 2),
				dim);
		ImageIO.write((RenderedImage) im, "jpg", new File("out.jpg"));
		for (String e : g.getEdges()) {
			System.out.println(e);
		}
	}

	public void fRAlgorithm(Graph<String, String> g, int k) {

		for (int t = 0; t < 100; t++) {
			Tuple displacementTemp = new Tuple(0, 0); // allowed due to the (int,int) constructor
			
			
			
			

			HashMap<String, Tuple> displacements = new HashMap<String, Tuple>();
			HashMap<String, Tuple> positions = new HashMap<String, Tuple>();

			for (String e : g.getEdges()) {

				Pair<String> edge = g.getEndpoints(e);
				
				String a = edge.getFirst();
				String b = edge.getSecond();
				
				
				
				displacements.get(e).sum(displacementTemp);
				System.out.println(e);
				System.out.println(displacements.get(e));

			}
			// for(String v: g.getVertices()){
			// positions.put(v, 0);
			//
			// }
			// }
			for (String e : g.getEdges()) {
				System.out.println(e);

			}

		}
	}

	public double distance(Tuple t, Tuple u) {
		return Math.sqrt((t.getX() - u.getX()) * (t.getX() - u.getX()) + (t.getY() - u.getY()) * (t.getY() - u.getY()));
	}

	public double attractF(Double d, int k) {
		return d * d / k;
	}

	public double repulseF(Double d, int k) {
		return -(k * k) / d;
	}

	public class Tuple {
		public Double x;
		public Double y;

		public Tuple(Double x, Double y) {
			this.x = x;
			this.y = y;
		}
		
		public Tuple(Integer x, Integer y) {
			this.x = Double.valueOf(x);
			this.y = Double.valueOf(y);
		}

		public void sum(Tuple other) {
			x += other.x;
			y += other.y;
			// this.edit(x, y);
		}

		public void edit(Double a, Double b) {
			x = a;
			y = b;
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public Tuple multiply(double d) {
			return new Tuple(x * d, y * d);
		}
	}

}