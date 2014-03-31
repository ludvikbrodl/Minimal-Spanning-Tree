import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class main {

	public static void main(String[] args) throws IOException {
		ArrayList<Edge> edges = new ArrayList<Edge>();
//		TreeSet<Edge> edges = new TreeSet<Edge>();
		edges.add(new Edge("a","b",6));
		edges.add(new Edge("a","d",3));
		edges.add(new Edge("a","e",9));
		edges.add(new Edge("b","d",4));
		edges.add(new Edge("b","c",2));
		edges.add(new Edge("b","g",9));
		edges.add(new Edge("c","d",2));
		edges.add(new Edge("c","g",9));
		edges.add(new Edge("d","e",9));
		edges.add(new Edge("d","f",9));
		edges.add(new Edge("e","f",8));
		edges.add(new Edge("g","f",7));
		edges.add(new Edge("g","j",4));
		edges.add(new Edge("g","h",5));
		edges.add(new Edge("h","f",9));
		edges.add(new Edge("j","h",1));
		edges.add(new Edge("j","i",4));
		edges.add(new Edge("h","i",3));
		edges.add(new Edge("f","i",10));
		edges.add(new Edge("i","e",18));
		edges.add(new Edge("c","f",8));
		Collections.sort(edges);




		System.out.println(edges);
		KruskalAlgo result = new KruskalAlgo(edges);
		ArrayList<Edge> msp = result.getMinimalSpanningTree();
		int totalWeight = 0;
		for (Edge e : msp) {
			totalWeight += e.weight;
		}
		System.out.println(totalWeight);

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
	ArrayList<Edge> edges;
	ArrayList<Edge> kruskalResult;
	ArrayList<HashSet<String>> groups;

	public KruskalAlgo(ArrayList<Edge> edges) {
		this.edges = edges;
		kruskalResult = new ArrayList<Edge>();
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

		// Do something depending of result from above
		if (groupA == null && groupB == null) {
			kruskalResult.add(e);
			HashSet<String> newGroup = new HashSet<String>();
			newGroup.add(cityA);
			newGroup.add(cityB);
			groups.add(newGroup);
		} else if (groupA == null && groupB != null) {
			kruskalResult.add(e);
			groupB.add(cityA);
		} else if (groupA != null && groupB == null) {
			kruskalResult.add(e);
			groupA.add(cityB);
		} else if (groupA != null && groupB != null) {
			if (!groupA.equals(groupB)) { // Both are in groups, and not the
											// same =>
				// they should be added to the same group
				groupA.addAll(groupB);
				groups.remove(groupB);
				kruskalResult.add(e);
			} else {
				// if both are the same, are we done????
			}
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

	public ArrayList<Edge> getMinimalSpanningTree() {
		return kruskalResult;
	}
}
