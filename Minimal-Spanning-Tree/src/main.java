import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class main {

	private static TreeSet<Edge> edges;

	public static void main(String[] args) throws IOException {
		edges = new TreeSet<Edge>();
//		Edge e1 = new Edge("A", "F", 2);
//		Edge e2 = new Edge("A", "B", 4);
//		Edge e3 = new Edge("B", "F", 5);
//		Edge e4 = new Edge("B", "C", 6);
//		Edge e5 = new Edge("C", "F", 1);
//
//		edges = new TreeSet<Edge>();
//		edges.add(e1);
//		edges.add(e2);
//		edges.add(e3);
//		edges.add(e4);
//		edges.add(e5);
//		System.out.println(edges);
		parse(args);
		KruskalAlgo result = new KruskalAlgo(edges);
		TreeSet<Edge> msp = result.getMinimalSpanningTree();
		int totalWeight = 0;
		for (Edge e : msp) {
			totalWeight += e.weight;
		}
		System.out.println(totalWeight);

	}

	// Syracuse--"Springfield, MO" [1114]
	private static void parse(String[] args) throws IOException {
		FileReader fr = new FileReader(args[0]);
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine();
			if (line.endsWith("]")) {
				String[] split = line.split("--");
				int blockStart = split[1].indexOf("[");
				int weight = Integer.parseInt(split[1].substring(blockStart+1, split[1].length()-1));
				split[1] = split[1].substring(0, blockStart - 1); // = toName
				edges.add(new Edge(split[0], split[1], weight));
			}
		}
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
		kruskalResult = new TreeSet<Edge>();
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
			if (!groupA.equals(groupB)) { // Both are in groups, and not the same =>
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

	public TreeSet<Edge> getMinimalSpanningTree() {
		return kruskalResult;
	}
}
