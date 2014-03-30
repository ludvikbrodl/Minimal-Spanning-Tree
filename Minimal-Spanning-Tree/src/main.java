import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

public class main {

	public static void main(String[] args) {
		TreeSet<Edge> edges = new TreeSet<Edge>();
		Edge e1 = new Edge("A", "B", 2);
		Edge e2 = new Edge("A", "C", 1);
		Edge e3 = new Edge("B", "D", 3);
		Edge e4 = new Edge("C", "D", 4);

		edges = new TreeSet<Edge>();
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		System.out.println(edges);
		KruskalAlgo result = new KruskalAlgo(edges);
		System.out.println(result.getResult());

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

class KruskalAlgo {
	TreeSet<Edge> edges;
	TreeSet<Edge> kruskalResult;
	ArrayList<HashSet<String>> groups;

	public KruskalAlgo(TreeSet<Edge> edges) {
		this.edges = edges;
		groups = new ArrayList<HashSet<String>>(); // Annat än arrayList?
		runKruskal();
	}

	private void runKruskal() {
		for (Edge e : edges) {
			addEdge(e);
		}

	}

	private void addEdge(Edge e) {
		String cityA = e.cityA;
		String cityB = e.cityB;

		// Check if city A and B are already in a group
		HashSet<String> groupA = isInGroup(cityA);
		HashSet<String> groupB = isInGroup(cityB);

		//Do something depending of result from above
		if (groupA == null && groupB == null) {
			kruskalResult.add(e);
			HashSet<String> newGroup = new HashSet<String>();
			newGroup.add(cityA);
			newGroup.add(cityB);
			groups.add(newGroup);
		} else if (groupA == null && groupB != null) {
			
		} else if (groupA != null && groupB == null){
			
		} else if (groupA != null && groupB != null){
			
		}

	}

	private HashSet<String> isInGroup(String cityA) {
		for (HashSet<String> hs : groups) {
			if (hs.contains(cityA)) {
				return hs;
			}
		}
		return null;
	}

	public String getResult() {
		// TODO Auto-generated method stub
		return null;
	}
}

