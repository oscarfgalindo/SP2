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
import java.util.Random;

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

	public StaticLayout<String, String> fRAlgorithm(Graph<String, String> g, int k, Dimension dim) {
		
		double height = dim.getHeight();
		double width = dim.getWidth();
		
		HashMap<String, Tuple> displacements = new HashMap<String, Tuple>();
		HashMap<String, Tuple> positions = new HashMap<String, Tuple>();
		
		//TODO randomly assign first positions and populate positions HashMap
	    Random rand = new Random();
	    Tuple tempPos;
		for(String u: g.getVertices()){
				tempPos = new Tuple(rand.nextDouble()*height, rand.nextDouble()*width);
				positions.put(u, tempPos);
				System.out.println(positions.get(u));
		}
		

		
		for (int t = 0; t < 0; t++) {
			Tuple displacementTemp = new Tuple(0, 0); // allowed due to the (int,int) constructor
			
			double dist; //temp variable for distance
			double aForce; //temp variable for force

			for (String e : g.getEdges()) {

				Pair<String> edge = g.getEndpoints(e);
				
				String a = edge.getFirst();
				String b = edge.getSecond();
				
				//find distance between endpoints
				dist = distance(positions.get(a),  positions.get(b));
				
				// TODO: calculate attractive force			
				aForce = attractF(dist, k); 
				displacementTemp = (positions.get(a).subtract(positions.get(b))).multiply(aForce/dist);
				displacements.get(e).sum(displacementTemp);
				System.out.println(e);
				System.out.println(displacements.get(e));

			}
			
			 
//			for(String u: g.getVertices()){
//				 for(String v: g.getVertices()){
//					 if( u != v) {
//					//find distance between vertices
//					double dist = distance(positions.get(a),  positions.get(b));
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
		
//		//TODO: create and return static layout with calculated positions 
		StaticLayout<String, String> l = new StaticLayout<String, String>(g);
		for(String u: g.getVertices()) {
			l.setLocation(u, positions.get(u).toPoint());
		}
		
		return l;
		
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

}