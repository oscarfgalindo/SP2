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

	public void fRAlgorithm(Graph<String, String> g, int k, Dimension dim) {
		
		double height = dim.getHeight();
		double width = dim.getWidth();
		
		HashMap<String, Tuple> displacements = new HashMap<String, Tuple>();
		HashMap<String, Tuple> positions = new HashMap<String, Tuple>();
		
		//TODO randomly assign first positions and populate positions HashMap
		
		

		
		for (int t = 0; t < 1; t++) {
			Tuple displacementTemp = new Tuple(0, 0); // allowed due to the (int,int) constructor
			
			double d; //temp for distance


			for (String e : g.getEdges()) {

				Pair<String> edge = g.getEndpoints(e);
				
				String a = edge.getFirst();
				String b = edge.getSecond();
				System.out.println(a);
	
				// TODO: calculate attractive force			
//				d = 
//				displacementTemp = attractF();
				
//				displacements.get(e).sum(displacementTemp);
//				System.out.println(e);
//				System.out.println(displacements.get(e));

			}
			
			 
//			for(String u: g.getVertices()){
//				 for(String v: g.getVertices()){
//					 if( u != v) {
//						 //TODO: add repulsive force to u displacement
//					 }
//						
//				 }
//					
//			 }
//		
//			for(String v: g.getVertices()){				 
//				//TODO: update v's position
//			 }
//		
		}
		
		//TODO: create and return static layout with calculated positions 
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
		
		public void sub(Tuple other){
			x -= other.x;
			y -= other.y;
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