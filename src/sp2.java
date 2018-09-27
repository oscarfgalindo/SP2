import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationImageServer;

public class sp2 {

	public static void main(String[] args) throws IOException {

		sp2starter tools = new sp2starter();
//		Graph<String, String> g;
//		String filename = "lesmis.dat";
//		g = tools.readGraph(filename);
//
	// Compute a force directed layout
		Dimension dim = new Dimension(500, 500);
		
		
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
		
		StaticLayout<String, String> l = tools.fRAlgorithm(g, 8, dim, 100000, 1048576);
		dim.setSize(dim.getWidth() * 1.1, dim.getHeight() * 1.1);

		// create image
		VisualizationImageServer<String, String> vis = new VisualizationImageServer<String, String>(l, dim);

		BufferedImage im = (BufferedImage) vis.getImage(new Point2D.Double(dim.getWidth() / 2, dim.getHeight() / 2),
				dim);
		ImageIO.write((RenderedImage) im, "jpg", new File("out.jpg"));

		// random image
		StaticLayout<String, String> rand = tools.r;
		vis = new VisualizationImageServer<String, String>(rand, dim);
		im = (BufferedImage) vis.getImage(new Point2D.Double(dim.getWidth() / 2, dim.getHeight() / 2), dim);
		ImageIO.write((RenderedImage) im, "jpg", new File("rand.jpg"));

	}
}
