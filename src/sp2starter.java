import java.awt.Dimension;
import java.util.HashMap;
import java.util.Random;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;


public class sp2starter {

	double minX = 0, minY = 0, maxX = 0, maxY = 0;
	double rangeX, rangeY, height, width;
	double buffer;
	StaticLayout<String, String> r;

	public StaticLayout<String, String> fRAlgorithm(Graph<String, String> g, int k, Dimension dim, int iter) {

		height = dim.getHeight();
		width = dim.getWidth();
		buffer = height * .05; // space between sides

		HashMap<String, Tuple> displacements = new HashMap<String, Tuple>();
		HashMap<String, Tuple> positions = new HashMap<String, Tuple>();

		// randomly assign first positions and populate positions HashMap
		Random rand = new Random();
		Tuple tempPos;
		for (String u : g.getVertices()) {
			tempPos = new Tuple(rand.nextDouble() * height, rand.nextDouble() * width);
			positions.put(u, tempPos);
			displacements.put(u, new Tuple(0, 0));
		}

		r = new StaticLayout<String, String>(g);
		for (String u : g.getVertices()) {
			Tuple newLoc = positions.get(u);
			r.setLocation(u, newLoc.toPoint());
		}

		for (int t = 0; t < iter; t++) {
			Tuple displacementTemp = new Tuple(0, 0); // allowed due to the (int,int) constructor

			double dist; // temp variable for distance
			double aForce; // temp variable for force

			for (String e : g.getEdges()) {

				Pair<String> edge = g.getEndpoints(e);

				String a = edge.getFirst();
				String b = edge.getSecond();

				// find distance between endpoints
				dist = distance(positions.get(a), positions.get(b));

				// calculate attractive force
				aForce = attractF(dist, k);
				displacementTemp = (positions.get(a).subtract(positions.get(b))).multiply(aForce / dist);
				displacements.get(a).sum(displacementTemp);
				displacements.get(b).sub(displacementTemp);

			}
			System.out.println("");
			System.out.println("iteration:" + t);

			double rForce;
			for (String u : g.getVertices()) {
				System.out.println(u + " attract:" + displacements.get(u));
				for (String v : g.getVertices()) {
					if (u != v) {
						// find distance between vertices
						dist = distance(positions.get(u), positions.get(v));
						// calculate and add repulsive force to u displacement
						rForce = repulseF(dist, k);
						displacementTemp = (positions.get(u).subtract(positions.get(v))).multiply(rForce / dist);
						displacements.get(u).sum(displacementTemp);

					}
				}
				System.out.println(u + " repulse:" + displacements.get(u));

			}
			//add displacements to positions
			for (String v : g.getVertices()) {
				positions.get(v).sum(displacements.get(v));
			}

		}

		for (String u : g.getVertices()) {
			if (positions.get(u).getX() < minX) {
				minX = positions.get(u).getX();
			}
			if (positions.get(u).getY() < minY) {
				minY = positions.get(u).getY();
			}
			if (positions.get(u).getX() > maxX) {
				maxX = positions.get(u).getX();
			}
			if (positions.get(u).getY() > maxY) {
				maxY = positions.get(u).getY();
			}
		}


		rangeX = maxX - minX;
		rangeY = maxY - minY;
		Tuple newLoc;
		// create and return static layout with calculated positions
		StaticLayout<String, String> l = new StaticLayout<String, String>(g);
		for (String u : g.getVertices()) {
			newLoc = normalize(positions.get(u));
			l.setLocation(u, newLoc.toPoint());
		}
		System.out.println("FINISHED");
		return l;

	}

	public Tuple normalize(Tuple pos) {
		double newX = width * (pos.getX() - minX) / rangeX;
		double newY = height * ((pos.getY() - minY) / rangeY);

		// move away from edges
		if (newX == 0) {
			newX = buffer;
		} else if (newX == width) {
			newX = newX - buffer;
		}
		if (newY == 0) {
			newY = buffer;
		} else if (newY == height) {
			newY = newY - buffer;
		}

		Tuple normalized = new Tuple(newX, newY);
		return normalized;
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