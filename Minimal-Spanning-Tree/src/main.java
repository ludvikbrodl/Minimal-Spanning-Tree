import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

public class main {
	public static void main(String[] args) {

		Edge e1 = new Edge("A", "B", 2);
		Edge e2 = new Edge("A", "C", 1);
		Edge e3 = new Edge("B", "D", 3);
		Edge e4 = new Edge("C", "D", 4);
		ArrayList<HashSet<String>> groups = new ArrayList<HashSet<String>>(); //Kanke något annat bättre än arrayList?

		
		TreeSet<Edge> edges = new TreeSet<Edge>();
		 edges.add(e1);
		 edges.add(e2);
		 edges.add(e3);
		 edges.add(e4);
		 System.out.println(edges);
		
	}
}

class Edge implements Comparable<Edge> {
	String cityA;
	String cityB;
	public int weight;

	public Edge(String cityA, String cityB, int weight) {
		this.cityA = cityA;
		this.cityB = cityB;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge edge) {
		if (this.weight > edge.weight) {
			return 1;
		} else if (this.weight < edge.weight) {
			return -1;
		} else {
			return 0;
		}

	}

	public String toString() {
		return weight + ": " + cityA + " - " + cityB;
	}
}
